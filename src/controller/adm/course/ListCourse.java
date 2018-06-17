package controller.adm.course;

import controller.BaseController;
import controller.utility.AccademicYear;
import controller.utility.AccademicYearException;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;
import model.Course;
import model.Service;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * @author Davide Micarelli
 */
public class ListCourse extends BaseController {

    /**
     * PER UTENTI REGISTRATI
     * Controlla la validita' della sessione con il metodo isHardValid,
     * Esegue un check se l'utente puo' accedere a tutti i corsi oppure solo ai suoi,
     * Se puo' accedere solo ai suoi corsi, carica i corsi dell'anno specificato dalla richiesta get (se non presente lancia i corsi di questo anno accademico)
     * Se puo' accedere a tutti i corsi, carica tutti i corsi dell'anno accademico (come sopra)
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
                    accademicYear = null;
                }else{

                    //estraggo l'anno dalla richiesta get (se non e' un valore consono lo gestisco nel blocco catch)
                    accademicYear = new AccademicYear(Integer.parseInt(request.getParameter("year")));

                }

                //inserisco nel datamodel l'anno e l'anno accademico
                //datamodel.put("accademicYear", accademicYear.toString());
                //datamodel.put("year", accademicYear.getFirstYear());

                /*
                    ADMIN
                 */

                //se l'utente in sessione possiede il servizio che gli permette di modificare tutti i corsi (quindi e' un amministratore)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse)) {

                    //inizializzo il dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    if(accademicYear != null){

                        //inserisco i corsi nel datamodel
                        datamodel.put("courses", courseDao.getCourseByYear(accademicYear.toString()));

                    }else {

                        //inserisco i corsi nel datamodel
                        datamodel.put("courses", courseDao.getCourses());

                    }



                    courseDao.destroy();

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

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

                        //se e' stato inserito l'anno accademico
                        if(accademicYear !=null) {

                            //estraggo i corsi collegati all'utente in sessione e li inserisco i corsi nel datamodel
                            datamodel.put("courses", courseDao.getCoursesByUserAndYear(sessionManager.getUser(request), accademicYear.toString()));


                            //se non e' presente l'anno accademico
                        }else{

                            //inizilizzo l'anno accademico attuale
                            AccademicYear thisYear = new AccademicYear(Calendar.getInstance());

                            //estraggo la lista dei corsi dell'utente
                            List<Course> listaCorsi = courseDao.getCoursesByUser(sessionManager.getUser(request));

                            List<Course> corsiDaTornare = new ArrayList <>();

                            for (Course c : listaCorsi){

                                if (new AccademicYear(c.getYear()).getFirstYear() >= thisYear.getFirstYear()){

                                    corsiDaTornare.add(c);
                                }
                            }

                            listaCorsi = null;

                            //estraggo tutti gli anni collegati al docente e li insersco nel datamodel
                            datamodel.put("courses", corsiDaTornare);
                        }


                        courseDao.destroy();

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //lancio il template dell'utente che non puo' eliminare i corsi
                        TemplateController.process("list_courses.ftl", datamodel, response, getServletContext());




                        //se non ha nessuno dei due permessi
                   } else {
                        //lancio il messaggio di servizio non permesso
                        this.processNotPermitted(request, response);
                    }
                }
                //se la sessione non e' valida
            } else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        } catch (DaoException | AccademicYearException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);
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
