package controller.adm;

import controller.BaseController;
import controller.utility.AccademicYear;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;
import model.Service;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * @author Davide Micarelli
 */
public class ListCourse extends BaseController {

    /**
     * PER UTENTI REGISTRATI
     * Controlla la validita' della sessione con il metodo isHardValid,
     * Esegue un check se l'utente puo' accere a tutti i corsi oppure solo ai suoi,
     * Se puo' accedere solo ai suoi corsi, carica i corsi dell'anno specificato dalla richiesta get (se non presente lancia i corsi di questo anno accademico)
     * Se puo' accedere a tuti i corsi, carica tutti i corsi dell'anno accademico (come sopra)
     * Infine lancia il template approprioato al tipo di gruppo a cui l'utente appartiene
     *
     * PARAMETRI GET:
     * year: anno di cui vengono estratti i corsi.
     *
     * PARAMETRI NEL DATAMODEL:
     * cercare datamodel.put
     *
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //elimino messaggio
            datamodel.put("message", null);

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione dei gruppi
                Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());

                //estraggo il servizio di creazione dei gruppi
                Service modCourse = this.getServiceAndCreate(request, response, ds, "modCourse", "Service to modificate course associated with the user", datamodel, this.getServletContext());

                //dichiaro un anno accademico
                AccademicYear accademicYear;

                //estraggo l'anno accademico dalla richiesta get
                if(request.getParameter("year") == null || request.getParameter("year").equals("")){

                    //inizializzo l'anno accademico come quello corrente
                    accademicYear = new AccademicYear(Calendar.getInstance());

                }else{

                    //estraggo l'anno dalla richiesta get (se non e' un valore consono lo gestisco nel blocco catch)
                    accademicYear = new AccademicYear(Integer.parseInt(request.getParameter("year")));

                }

                //inserisco nel datamodel l'anno e l'anno accademico
                datamodel.put("accademicYear", accademicYear.toString());
                datamodel.put("year", accademicYear.getFirstYear());

                /*
                    ADMIN
                 */

                //se l'utente in sessione possiede il servizio che gli permette di modificare tutti i corsi (quindi e' un amministratore)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse)) {

                    //inizializzo il dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //inserisco i corsi nel datamodel
                    datamodel.put("courses", courseDao.getCourseByYear(accademicYear.toString()));

                    courseDao.destroy();

                    //lancio il template dei corsi dell'admin che puo' anche eliminarli
                    TemplateController.process("list_courses_adm.ftl", datamodel, response, getServletContext());





                /*
                    DOCENTE
                 */

                    //se non ha il permesso di modificare tutti i corsi
                } else {

                    //se ha il permesso di modificare alcuni corsi ( quindi e' un docente)
                    if (((List <Service>) request.getSession().getAttribute("services")).contains(modCourse)) {

                        //inizializzo il dao dei corsi
                        CourseDao courseDao = new CourseDaoImpl(ds);
                        courseDao.init();

                        //estraggo i corsi collegati all'utente in sessione e li inserisco i corsi nel datamodel
                        datamodel.put("courses", courseDao.getCoursesByUserAndYear(sessionManager.getUser(request), accademicYear.toString()));

                        courseDao.destroy();

                        //lancio il template dell'utente che non puo' eliminare i corsi
                        TemplateController.process("list_courses.ftl", datamodel, response, getServletContext());

                        //se non ha nessuno dei due permessi
                   } else {
                        //lancio il messaggio di servizio non permesso
                        TemplateController.process("not_permissed.ftl", datamodel, response, getServletContext());
                    }
                }
                //se la sessione non e' valida
            } else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        } catch (DaoException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

}
