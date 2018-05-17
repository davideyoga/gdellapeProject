package controller.adm.course;

import controller.BaseController;
import controller.logController.LogException;
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
import java.util.List;

/**
 * @author Davide Micarelli
 */
public class ModAdmCourse extends BaseController {


    /**
     * ModAdmCourse e' la servlet per la modifica dei corsi da parte degli amministratori, possono modiifcare tutto
     * Controllo permessi, controllo se l'utente ha il permesso di modificare tutti i corsi
     * raccolgo dati, controllo nome e codice,
     * eseguo update.
     *
     * Permetto ad un admin di modificare dati su un corso precedente.
     *
     * PARAMETRI GET:
     * id: id del corso da modificare
     *
     * PARAMETRI NEL DATAMODEL
     * vedere datamodel.put
     *
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

                //controllo se la richiesta contiene il parametro get
                if (request.getParameter("id") == null) {

                    //se non contiene il parametro lo rimando alla lista dei corsi
                    response.sendRedirect("ListCourse");
                    return;
                }

                //inizializzo il dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                //estraggo il corso con id = al quello contenuto nella richiesta get
                Course courseById = courseDao.getCourseById(Integer.parseInt(request.getParameter("id")));

                //se il corso non esiste
                if(courseById == null || courseById.getIdCourse() <= 0 ){

                    //lancio servlet di errore
                    response.sendRedirect("Error");
                    return;
                }

                //estraggo il servizio di modifica dei corsi
                Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());


                //controllo se l'utente possiede il servizio
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse)){

                    //inserisco nella sessione dell'utente l'id del corso (per la futura richiesta post)
                    request.getSession().setAttribute("idCourseToModify", courseById.getIdCourse());

                    //lancio il template con il corso caricato
                    datamodel.put("course", courseById);

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //setto i servizi a cui ha accesso l'utente
                    this.datamodel.put("services", request.getSession().getAttribute("services"));

                    TemplateController.process("course_mod_adm.ftl", datamodel, response, getServletContext());

                    //se l'utente non puo' modificare il corso
                } else {
                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);
                }



                //chiudo il dao
                courseDao.destroy();

                //se non ha la sessione valida e troppo vecchia
            }else{
                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }
        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            this.processError(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //controllo i permessi
        try {

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //inizializzo il dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                //se in sessione non ho l'id del corso
                if(request.getSession().getAttribute("idCourseToModify") == null || request.getSession().getAttribute("idCourseToModify").equals("")){
                    //lancio servlet di errore
                    response.sendRedirect("Error");
                    return;
                }

                //estraggo il corso con id presente nella sessione
                Course courseById = courseDao.getCourseById((Integer) request.getSession().getAttribute("idCourseToModify"));

                //se il corso estratto non esiste
                if(courseById == null || courseById.getIdCourse()<=0){
                    //lancio servlet della lista dei corsi
                    response.sendRedirect("ListCourse");
                }

                //estraggo il servizio di creazione dei gruppi
                Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());


                //controllo se l'utente puo' modificare tutti i corsi (prerogativa per modificare il corso nella sua interezza)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse)){


                    //estraggo il corso dalla form e gli setto l'id a 0
                    Course courseByForm = courseDao.getCourse();
                    courseByForm = this.getCourseByForm(request, courseByForm, 0);

                    Course courseWithName = null;
                    Course courseWithCode = null;

                    //controllo se il corso e' stato modificato
                    if(!courseByForm.equals(courseById)){

                        //se ho modificato il nome estraggo corsi con stesso nome per evitare omonimie
                        if(!courseById.getName().equals(courseByForm.getName())){

                            courseWithName = courseDao.getCourseByName(courseByForm.getName());

                        }

                        //se ho modificato il codice estraggo corsi con stesso codice per evitare omonimie
                        if(!courseById.getCode().equals(courseByForm.getCode())){

                            System.out.println("courseWithName" + courseWithName);

                            courseWithCode = courseDao.getCoursesByCodeAndYear(courseByForm.getCode(), new AccademicYear(courseById.getYear()));

                        }

                        //pulisco il messaggio
                        datamodel.put("message", "");

                        //se non esistono corsi con stesso nome e codice
                        if(courseWithName == null && courseWithCode == null){

                            //siccome e' stato modificato eseguo un update
                            courseByForm.setYear(courseById.getYear());
                            courseByForm.setIdCourse(courseById.getIdCourse());
                            courseDao.storeCourse(courseByForm);


                            //inserisco messaggio di avvenuta modifica del corso
                            datamodel.put("message", "Update Successful");

                            //inserisco un log di avvenuta modofica
                            logManager.addLog(sessionManager.getUser(request), "USER: " + sessionManager.getUser(request).toStringForLog() +
                                    " HAS CHANGED: " + courseByForm.toStringForLog(), ds);

                        }else {

                            if (courseWithName != null) {
                                datamodel.put("message", "Course with Name early exist. ");
                            }
                            if (courseWithCode != null) {
                                datamodel.put("message", datamodel.get("message") + "Course with Code early exist");
                            }

                        }

                        //se non e' stato modificao non eseguo cambiamenti
                    }else{

                        //non faccio nulla

                    }

                    //carico il corso nel template (setto l'id in caso di necessita del template)
                    courseByForm.setIdCourse(courseById.getIdCourse());
                    datamodel.put("course", courseByForm);

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //setto i servizi a cui ha accesso l'utente
                    this.datamodel.put("services", request.getSession().getAttribute("services"));

                    //lancio il template
                    TemplateController.process("course_mod_adm.ftl", datamodel, response, getServletContext());

                    //se l'utente non ha il permesso di modificare il corso
                }else{
                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);
                }

                //se non ha sessione valida
            }else{
                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }

        } catch (DaoException | AccademicYearException e ) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);

        } catch (LogException e) {
            e.printStackTrace();

            this.processError(request, response);

        }

    }
}
