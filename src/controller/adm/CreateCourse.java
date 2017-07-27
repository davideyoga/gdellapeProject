package controller.adm;

import controller.BaseController;
import controller.logController.LogException;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;
import model.*;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Davide Micarelli
 */
public class CreateCourse extends BaseController {

    /**
     * Controlla i permessi, se si hanno i permessi si lancia la form di creazione del Corso
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione dei gruppi
                Service createGroups = this.getServiceAndCreate(request, response, ds, "createCourse", "Service to create course", datamodel, this.getServletContext());

                //se l'utente in sessione possiede il servizio
                if (((List <Service>) request.getSession().getAttribute("services")).contains(createGroups)) {

                    /*
                        AGGIUNTA DATI PER INSERIRE I CECKBOX PER COLLEGARE GLI UTENTI AI CORSI
                    */

                    this.insertUserInCourseServlet(request);

                    //lancio il template di creazione
                    TemplateController.process("create_course.ftl", datamodel, response, getServletContext());


                } else {
                    //lancio il messaggio di servizio non permesso
                    TemplateController.process("not_permissed.ftl", datamodel, response, getServletContext());
                }
                //se la sessione non e' valida
            } else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "CreateCourse");

            }

        } catch (DaoException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());
        }

    }

    /**
     * Controlla i permessi, prende in input i dati dalla form, controlla che non esista un corso con lo stesso codice e nome, infine inserisc il corso
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //elimino messaggio
            datamodel.put("message", null);

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione dei corsi
                Service createCourse = this.getServiceAndCreate(request, response, ds, "createCourse", "Service to create course", datamodel, this.getServletContext());

                //se l'utente in sessione possiede il servizio
                if (((List <Service>) request.getSession().getAttribute("services")).contains(createCourse)) {


                    //inizlizzo il dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //inizializzo un corso
                    Course course = courseDao.getCourse();

                    //estraggo i dati dalla form con cui ci riempio course, gli setto id a 0
                    course = this.getCourseByForm(request, course, 0);

                    //estraggo i corsi con lo stesso nome e con con lo stesso codice
                    Course courseWithName = courseDao.getCourseByName(course.getName());
                    Course courseWithCode = courseDao.getCourseByCode(course.getCode());

                    //se esistono corsi con stesso codice e nome lancio un messagio di errore per informare l'utente di aver inserito dati errati
                    if (courseWithName != null || courseWithCode != null) {

                        if (courseWithName != null) {
                            //inserisco nel messaggio di errore la notifica di nome gia' esistente nel db
                            datamodel.put("message", "Error: Existing Name. ");


                        }

                        if (courseWithCode != null) {
                            //inserisco nel messaggio di errore la notifica di codice gia' esistente nel db
                            datamodel.put("message", datamodel.get("message") + "Error: Existing Code. ");
                        }

                        //lancio il template
                        TemplateController.process("create_course.ftl", datamodel, response, getServletContext());

                        //se i dati sono corretti
                    } else{

                        //inserisco il corso
                        courseDao.storeCourse(course);

                        //inserisco un log di avvenuto inserimento
                        logManager.addLog(sessionManager.getUser(request), "STORE COURSE: " + course, ds);


                        /*
                            RACCOLTA DATI SULL'ASSOCIAZIONE DEI CORSI
                         */


                        /*
                            AGGIUNTA DATI PER INSERIRE I CECKBOX PER COLLEGARE GLI UTENTI AI CORSI
                         */
                        this.insertUserInCourseServlet(request);

                        //chiudo i dao
                        courseDao.destroy();

                        //inserisco messaggio di avvenuta creazione del corso e lancio il template
                        datamodel.put("message", "Course Created");
                        TemplateController.process("create_course.ftl", datamodel, response, getServletContext());
                    }

                } else {
                    //lancio il messaggio di servizio non permesso
                    TemplateController.process("not_permissed.ftl", datamodel, response, getServletContext());
                }
                //se la sessione non e' valida
            } else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "CreateCourse");

            }
        }catch(DaoException e){
            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());

        } catch (LogException e) {
            //inserisco nel datamodel un messaggio di avvenuto inserimento del corso ma di errore nell'inserimnto del log
            datamodel.put("message", "Created Course but log insert error");

            //lancio il template
            TemplateController.process("create_course.ftl", datamodel, response, getServletContext());
        }
    }
}