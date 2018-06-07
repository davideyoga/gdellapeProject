package controller.adm.course;

import controller.BaseController;
import controller.logController.LogException;
import controller.utility.AccademicYear;
import controller.utility.AccademicYearException;
import controller.utility.SecurityLayer;
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
import java.util.Calendar;
import java.util.List;

/**
 * @author Davide Micarelli
 * Unico modo per aggiornare un corso da un anno precedente,
 * dato un corso con id passato tramite get, se il corso non e' di questo anno,
 * viene creato copiando tutti i suoi dati tranne l'anno e l'id,
 *
 */
public class UpdateCourse extends BaseController {

    private void processTemplate(HttpServletRequest request, HttpServletResponse response, String message, List<Course> courses, Course courseNotUpdatable) throws IOException {

        //carico il corso nel datamodel
        datamodel.put("message", message);

        datamodel.put("courses", courses);

        datamodel.put("courseNotUpdatable", courseNotUpdatable);

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        this.datamodel.put("actualYear", new AccademicYear(Calendar.getInstance()).getFirstYear());

        TemplateController.process("update_course.ftl", datamodel, response, getServletContext());
    }


//    /**
//     * Controlli su validita' della sessione e permesso,
//     * prendo l'id del corso passato come parametro get,
//     * controllo se il parametro mode e' a 1 oppure a 2;
//     *      se e' a 1 devo riportare il corso passato all'anno attuale
//     *      se e' a 2 devo aggiornare il corso al prossimo anno accademico
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        try {
//
//            //pulisco messaggio
//            datamodel.put("message", null);
//
//            //se sessione valida
//            if (this.sessionManager.isValid(request)) {
//
//                System.out.println("a\n");
//
//                //estraggo il servizio di aggiornare un corso
//                Service updateCourse = this.getServiceAndCreate(request, response, ds, "updateCourse", "Permissed for update Course",
//                        datamodel, getServletContext());
//
//                //se l'utente in sessione possiede il servizio updateCourse...
//                if (((List <Service>) request.getSession().getAttribute("services")).contains(updateCourse)) {
//
//                    //inizializzo un dao dei corsi
//                    CourseDao courseDao = new CourseDaoImpl(ds);
//                    courseDao.init();
//
//                    //estraggo id nella sessione del corso che si intende aggiornare, se non esiste o non e' un numero lancia un NumberFormatException
//                    int idCourse = Integer.parseInt(request.getParameter("idCourse"));
//
//                    //estraggo il corso (non viene messo tutto in sessione per maggiore sicurezza che esiste ancora)
//                    Course courseById = courseDao.getCourseById(idCourse);
//
//                    //se il corso esiste
//                    if (courseById != null){
//
//                        //estraggo l'anno dal corso da aggiornare
//                        int firstYear = new AccademicYear(courseById.getYear()).getFirstYear();
//                        //inizializzo un anno accademico con l'anno del corso passato
//                        AccademicYear accademicYear = new AccademicYear(firstYear);
//
//                        if (request.getParameter("mode") == null){
//                            this.processError(request, response);
//                            return;
//                        }
//
//                        switch (request.getParameter("mode")){
//
//                            case "1":
//                                //devo riportare il croso all'attuale anno accademico
//
//                                //inizializzo l'attuale anno accademico
//                                AccademicYear year = new AccademicYear(Calendar.getInstance());
//
//                                //estraggo corso con stesso anno e codice del corso che vado a creare
//                                Course courseByCodeAndYear = courseDao.getCoursesByCodeAndYear(courseById.getCode(), year);
//
//                                //controllo se non esiste un corso con codice uguale al corso passato e con l'anno attuale
//                                if(courseByCodeAndYear==null || courseByCodeAndYear.getIdCourse()<=0){
//
//                                    //creo il nuovo corso
//                                    courseById.setIdCourse(0);
//                                    courseById.setYear(year.toString());
//
//                                    courseDao.storeCourse(courseById);
//
//                                    courseDao.destroy();
//
//                                    logManager.addLog(sessionManager.getUser(request),
//                                                "USER: " + sessionManager.getUser(request).toStringForLog() +
//                                                        " UPDATED THE COURSE: "+ courseById.toStringForLog()  + "TO A SUBSEQUENT YEAR " , ds);
//
//                                    this.processTemplate(request, response, "UPDATE COURSE");
//                                    return;
//
//                                }else{
//                                    //se esiste gia un corso di quell'anno
//
//                                    //chiudo il dao
//                                    courseDao.destroy();
//
//                                    this.processTemplate(request, response, "ALREADY EXISTING COURSE");
//                                    return;
//                                }
//
//
//                            case "2":
//                                //devo portare il corso all'anno successivo di quello attuale
//
//                                //inizializzo il corso successivo a quello attuale
//                                AccademicYear nextYear = new AccademicYear( new AccademicYear(Calendar.getInstance()).getFirstYear() + 1 );
//
//                                //estraggo corso con stesso anno e codice del corso che vado a creare
//                                Course courseByCodeAndYear2 = courseDao.getCoursesByCodeAndYear(courseById.getCode(), nextYear);
//
//                                //controllo se non esiste un corso con codice uguale al corso passato e con l'anno attuale
//                                if(courseByCodeAndYear2==null || courseByCodeAndYear2.getIdCourse()<=0){
//
//                                    //creo il nuovo corso
//                                    courseById.setIdCourse(0);
//                                    courseById.setYear(nextYear.toString());
//
//                                    courseDao.storeCourse(courseById);
//
//                                    courseDao.destroy();
//
//                                    logManager.addLog(sessionManager.getUser(request),
//                                            "USER: " + sessionManager.getUser(request).toStringForLog() +
//                                                    " UPDATED THE COURSE: "+ courseById.toStringForLog()  + "TO A SUBSEQUENT YEAR " , ds);
//
//                                    this.processTemplate(request, response, "UPDATE COURSE");
//                                    return;
//
//                                }else{
//                                    //se esiste gia un corso di quell'anno
//
//                                    //chiudo il dao
//                                    courseDao.destroy();
//
//                                    this.processTemplate(request, response, "ALREADY EXISTING COURSE");
//                                    return;
//                                }
//                        }
//
//                        //se il corso non esiste
//                    }else{
//
//                        courseDao.destroy();
//
//                        this.processError(request, response);
//
//                    }
//
//
//                    //se l'utente non possiede il servizio
//                } else {
//
//                    //lancio la servlet di non permesso\
//                    this.processNotPermitted(request, response);
//
//                }
//
//                //se l'utente non ha una sessione valida
//            } else {
//
//                //lancio il login
//                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
//
//            }
//
//        } catch (DaoException | NumberFormatException e) {
//            e.printStackTrace();
//
//            //lancio servlet di errore
//            this.processError(request, response);
//        } catch (LogException e) {
//            e.printStackTrace();
//        } catch (AccademicYearException e) {
//            e.printStackTrace();
//        }
//
//    }


    /**
     * Mando lista tutti i corsi - corsi nuovi (anno precendente all'attuale)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isValid(request)) {

                System.out.println("a\n");

                //estraggo il servizio di aggiornare un corso
                Service updateCourse = this.getServiceAndCreate(request, response, ds, "updateCourse", "Permissed for update Course",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio updateCourse...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(updateCourse)) {

                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //estraggo la lista di tutti i corsi
                    List<Course> allCourse = courseDao.getCourses();

                    //estraggo i corsi del prossimo anno accademico
                    AccademicYear currentYear = new AccademicYear(Calendar.getInstance());
                    currentYear.setFirstYear(currentYear.getFirstYear()+1);
                    currentYear.setFirstYear(currentYear.getFirstYear()+2);
                    List<Course> courseNextYear = courseDao.getCourseByYear(currentYear.toString());

                    //rimuovo i corsi del prossimo anno accademici
                    allCourse.removeAll(courseNextYear);


                    if (request.getParameter("idCourse") != null && request.getParameter("mode") != null) {


                        //estraggo corso con id
                        Course course = courseDao.getCourseById(Integer.parseInt(request.getParameter("idCourse")));

                        if (course!=null && course.getIdCourse()>0){


                            switch (request.getParameter("mode")) {

                                case "1":
                                    //devo riportare il croso all'attuale anno accademico

                                    //inizializzo l'attuale anno accademico
                                    AccademicYear year = new AccademicYear(Calendar.getInstance());

                                    //estraggo corso con stesso anno e codice del corso che vado a creare
                                    Course courseByCodeAndYear = courseDao.getCoursesByCodeAndYear(course.getCode(), year);

                                    //controllo se non esiste un corso con codice uguale al corso passato e con l'anno attuale
                                    if (courseByCodeAndYear == null || courseByCodeAndYear.getIdCourse() <= 0) {

                                        //creo il nuovo corso
                                        course.setIdCourse(0);
                                        course.setYear(year.toString());

                                        courseDao.storeCourse(course);

                                        courseDao.destroy();

                                        logManager.addLog(sessionManager.getUser(request),
                                                "USER: " + sessionManager.getUser(request).toStringForLog() +
                                                        " UPDATED THE COURSE: " + course.toStringForLog() + "TO A SUBSEQUENT YEAR ", ds);

                                        this.processTemplate(request, response, "UPDATE SUCCESSFULL", allCourse, null);

                                    } else {
                                        //se esiste gia un corso di quell'anno

                                        //chiudo il dao
                                        courseDao.destroy();

                                        this.processTemplate(request, response, "ALREADY EXISTING COURSE", allCourse, courseByCodeAndYear );
                                        return;
                                    }



                                case "2":
                                    //devo portare il corso all'anno successivo di quello attuale

                                    //inizializzo il corso successivo a quello attuale
                                    AccademicYear nextYear = new AccademicYear(new AccademicYear(Calendar.getInstance()).getFirstYear() + 1);

                                    //estraggo corso con stesso anno e codice del corso che vado a creare
                                    Course courseByCodeAndYear2 = courseDao.getCoursesByCodeAndYear(course.getCode(), nextYear);

                                    //controllo se non esiste un corso con codice uguale al corso passato e con l'anno attuale
                                    if (courseByCodeAndYear2 == null || courseByCodeAndYear2.getIdCourse() <= 0) {

                                        //creo il nuovo corso
                                        course.setIdCourse(0);
                                        course.setYear(nextYear.toString());

                                        courseDao.storeCourse(course);

                                        courseDao.destroy();

                                        logManager.addLog(sessionManager.getUser(request),
                                                "USER: " + sessionManager.getUser(request).toStringForLog() +
                                                        " UPDATED THE COURSE: " + course.toStringForLog() + "TO A SUBSEQUENT YEAR ", ds);

                                        this.processTemplate(request, response, "UPDATE SUCCESSFULL", allCourse, null);
                                        return;

                                    } else {
                                        //se esiste gia un corso di quell'anno

                                        //chiudo il dao
                                        courseDao.destroy();

                                        this.processTemplate(request, response, "ALREADY EXISTING COURSE", allCourse, courseByCodeAndYear2);
                                        return;
                                    }

                                    default:{
                                        this.processError(request, response);
                                        return;
                                    }
                            }//FINE SWITCH





                        }else{
                            this.processError(request, response);
                            return;
                        }


                        //se non mi vengono passati parametri get
                    }else{


//                        //estraggo la lista di tutti i corsi
//                        List<Course> allCourse = courseDao.getCourses();
//
//                        //estraggo i corsi del prossimo anno accademico
//                        AccademicYear currentYear = new AccademicYear(Calendar.getInstance());
//                        currentYear.setFirstYear(currentYear.getFirstYear()+1);
//                        currentYear.setFirstYear(currentYear.getFirstYear()+2);
//                        List<Course> courseNextYear = courseDao.getCourseByYear(currentYear.toString());

                        //chiudo dao
                        courseDao.destroy();

                        //rimuovo i corsi del prossimo anno accademici
                        allCourse.removeAll(courseNextYear);

                        //lancio template
                        this.processTemplate(request, response, null, allCourse, null);


                    }

                    //se l'utente non possiede il permesso
                }else{

                    this.processNotPermitted(request, response);

                }

                //se sessione non valida
            }else{

                createPreviousPageAndRedirectToLogin(request, response, "UpdateCourse");

            }
        }catch (NumberFormatException | DaoException e){

            e.printStackTrace();
            this.processError(request, response);

        } catch (LogException e) {
            e.printStackTrace();

            this.processError(request, response);
        }
    }

    /**
     * Controllo la presenza in sessione dell'id del corso, dalla form passata estraggo l'anno
     * del corso che si intende aggiornare, controllo che non vi sia un corso con stesso codice ed anno (quindi e' gia stato aggiornato)
     * se non esiste lo aggiorno copiando i dati vecchi nel nuovo corso tranne id e codice
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {


            //pulisco messaggio
            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isValid(request)) {

                //estraggo il servizio di aggiornare un corso
                Service updateCourse = this.getServiceAndCreate(request, response, ds, "updateCourse", "Permissed for update Course",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio updateCourse...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(updateCourse)) {

                    //inizializzo un dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //estraggo id nella sessione del corso che si intende aggiornare, se non esiste o non e' un numero lancia un NumberFormatException
                    int idCourse = SecurityLayer.checkNumeric(String.valueOf(request.getSession().getAttribute("idCourse")));

                    //estraggo il corso (non viene messo tutto in sessione per maggiore sicurezza che esiste ancora)
                    Course courseById = courseDao.getCourseById(idCourse);

                    //se il corso esiste
                    if (courseById != null){

                        //estraggo l'anno dalla form
                        int firstYear = SecurityLayer.checkNumeric(request.getParameter("year"));
                        //inizializzo un anno accademico con l'anno estratto dalla form
                        AccademicYear accademicYear = new AccademicYear(firstYear);

                        //controllo sull'anno accademico: deve essere >= dell'anno accademico attuale, non devono esistere corsi con stesso codice e anno
                        if(courseDao.getCoursesByCodeAndYear(courseById.getCode(), accademicYear) == null){

                            //setto il nuovo corso per essere inserito nel database
                            courseById.setIdCourse(0);
                            courseById.setYear(accademicYear.toString());

                            //inserisco il corso
                            courseDao.storeCourse(courseById);

                            //chiudo il dao
                            courseDao.destroy();

                            //aggiungo log Update del corso
                            logManager.addLog(sessionManager.getUser(request), "USER:" + sessionManager.getUser(request).toStringForLog() +  " HAS UPDATED A COURSE: " + courseById.toStringForLog() , ds);

                            //lancio la servlet della lista dei corsi
                            response.sendRedirect("ListCourse");
                            return;


                            //se esistono altri corsi con stesso anno accademico e codice
                        }else{

                            //inserisco nel datamodel il messaggio di corso gia' aggiornato
                            datamodel.put("message", "ALREADY EXISTING COURSE");

                            //chiudo il dao
                            courseDao.destroy();

                            //lancio il template
                            //this.processTemplate(request, response, courseById);

                        }



                        //se il corso non esiste
                    }else{

                        courseDao.destroy();

                        this.processError(request, response);

                    }

                    //se non possiede il permesso
                }else{

                    this.processNotPermitted(request, response);

                }

                //se non ha una sessione valida
            }else{
                //lancio il login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }


        }catch (NumberFormatException | DaoException e){
            e.printStackTrace();

            this.processError(request, response);

            this.processError(request, response);
        } catch (LogException e) {
            e.printStackTrace();

            //lancio la servlet della lista dei corsi
            response.sendRedirect("ListCourse");
        }
    }
}
