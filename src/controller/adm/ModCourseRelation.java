package controller.adm;

import controller.BaseController;
import controller.logController.LogException;
import controller.utility.AccademicYear;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Davide Micarelli
 *
 * Servlet per l'aggiunta e l'eliminazione dei collegamenti tra corsi propedeutici, modulati ecc..
 *
 * Possibilita' di aggiungere collegamenti solo con corsi di questo anno
 */
public class ModCourseRelation extends BaseController {

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, Course course ,List<Course> allCourse, List<Course> courseRelated, String mode){

        //inserisco nel datamodel i dati
        datamodel.put("allCourse", allCourse);
        datamodel.put("courseRelated", courseRelated);
        datamodel.put("course", course);
        datamodel.put("mode", mode);

        //inserisco dati nella sessione per il metodo post
        request.setAttribute("idCourse", course.getIdCourse());
        request.setAttribute("mode", mode);

        //lancio il template
        TemplateController.process("mod_relation_course.ftl", datamodel,response,getServletContext());

    }

    private boolean ceckGetValue(HttpServletRequest request){

        try {
            //controllo sull'esistenza del parametro id del corso e della presenza di un parametro mode valido
            if(request.getParameter("idCourse")!=null && SecurityLayer.checkNumeric(request.getParameter("idCourse")) != 0
                    &&
                    (request.getParameter("mode") == "borrowed" ||
                            request.getParameter("mode")  == "module" ||
                            request.getParameter("mode") == "preparatory") )
            {
                return true;

            }else{
                return false;
            }

        }catch (NumberFormatException e){

            return false;

        }
    }


    /**
     * Estraggo id del corso e la modalita' di modifca (Propedeutici, Modulati o Mutuati),
     * estraggo: corso con tale id, tutti i corsi e tutti i corsi collegati al mio corso con tale modalita'
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isHardValid(request)) {

                //estraggo il servizio di modifica delle relazione tra corsi
                Service modRelationWithCourse = this.getServiceAndCreate(request, response, ds, "modRelationWithCourse", "Permit for modification Relation between courses ",
                        datamodel, getServletContext());

                //estraggo il servizio di modifica delle relazione tra tutti i corsi
                Service modAllRelationWithCourse = this.getServiceAndCreate(request, response, ds, "modRelationWithCourse", "Permit for modification Relation between courses ",
                        datamodel, getServletContext());


                //controllo sulla correttezza dei dati passati come GET
                if(!this.ceckGetValue(request)){
                    this.processError(request, response);
                }

                //inizializzo un dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                //estraggo il corso per vedere se esite
                Course courseById =courseDao.getCourseById(Integer.parseInt(request.getParameter("idCourse")));

                //se il corso esiste
                if(courseById != null) {


                    //se l'utente in sessione possiede il servizio modAllRelationWithCourse oppure possiede il corso e puo' modificare relazioni
                    //nel caso in cu
                    if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllRelationWithCourse) ||
                            (((List <Service>) request.getSession().getAttribute("services")).contains(modRelationWithCourse) &&
                                    courseDao.getCoursesByUser(sessionManager.getUser(request)).contains(courseById))) {

                        AccademicYear accademicYear = new AccademicYear(Calendar.getInstance());

                        //estraggo tutti i corsi di questo anno
                        List<Course> allCourses = courseDao.getCourseByYear(accademicYear.getFirstYear() + "/" + accademicYear.getSecondYear());


                        //estraggo i corsi della modalita' selezionata
                        List<Course> courseRelated = new ArrayList <>();

                        String mode = request.getParameter("mode");

                        switch(mode) {

                            case "borrowed":

                                courseRelated = courseDao.getCourseBorrowed(courseById);

                                this.processTemplate(request, response, courseById , allCourses, courseRelated,"borrowed");

                                break;

                            case "module":

                                courseRelated = courseDao.getCourseModulated(courseById);

                                this.processTemplate(request, response, courseById , allCourses, courseRelated,"module");

                                break;

                            // eventuali altri case

                            case "preparatory":

                                courseRelated = courseDao.getCoursePreparatory(courseById);
                                //lancio il processo template
                                this.processTemplate(request, response, courseById , allCourses, courseRelated,"preparatory");

                            default:
                        }

                        courseDao.destroy();


                        //se l'untente non ha il permesso
                    } else {

                        courseDao.destroy();

                        //lancio servlet di servizio non permesso
                        response.sendRedirect("ServiceNotPermissed");

                    }

                    //se il corso non esiste
                }else{

                    courseDao.destroy();

                    this.processError(request, response);

                }

                //se la sessione non e' valida
            }else{
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }

        }catch (DaoException e){

            this.processError(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //controllo sessione
        //pulisco messaggio

        try {



            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isHardValid(request)) {

                //estraggo il servizio di modifica delle relazione tra corsi
                Service modRelationWithCourse = this.getServiceAndCreate(request, response, ds, "modRelationWithCourse", "Permit for modification Relation between courses ",
                        datamodel, getServletContext());

                //estraggo il servizio di modifica delle relazione tra tutti i corsi
                Service modAllRelationWithCourse = this.getServiceAndCreate(request, response, ds, "modRelationWithCourse", "Permit for modification Relation between courses ",
                        datamodel, getServletContext());


                //inizializzo un dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();


                //estraggo il corso per vedere se esite
                Course courseById =courseDao.getCourseById((Integer) request.getSession().getAttribute("idCourse"));

                //se il corso esiste
                if(courseById != null) {


                    //se l'utente in sessione possiede il servizio modAllRelationWithCourse oppure possiede il corso e puo' modificare relazioni
                    //nel caso in cu
                    if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllRelationWithCourse) ||
                            (((List <Service>) request.getSession().getAttribute("services")).contains(modRelationWithCourse) &&
                                    courseDao.getCoursesByUser(sessionManager.getUser(request)).contains(courseById))) {


                        /*
                            RECUPERO I VECCHI DATI
                         */

                        //estraggo il corso di studi attuale
                        AccademicYear accademicYear = new AccademicYear(Calendar.getInstance());

                        //estraggo tutti i corsi di quest'anno
                        List<Course> allCourses = courseDao.getCourseByYear(accademicYear.getFirstYear() + "/" + accademicYear.getSecondYear());

                        //estraggo i corsi della modalita' selezionata
                        List<Course> oldCourseRelated = new ArrayList <>();

                        String mode = (String) request.getSession().getAttribute("mode");

                        if(mode != null && ( request.getParameter("mode") == "borrowed" ||
                                request.getParameter("mode")  == "module" ||
                                request.getParameter("mode") == "preparatory")){

                            switch(mode) {

                                case "borrowed":

                                    oldCourseRelated = courseDao.getCourseBorrowed(courseById);

                                    break;

                                case "module":

                                    oldCourseRelated = courseDao.getCourseModulated(courseById);

                                    break;

                                case "preparatory":

                                    oldCourseRelated = courseDao.getCoursePreparatory(courseById);

                                default:
                            }


                            /*
                                ESTRAGGO I NUOVI COLLEGAMENTI CON I CORSI
                             */

                            //creo una lista di corsi per i nuovi collegamenti
                            List<Integer> newCourseRelated = new ArrayList <>();


                            //ciclo sulla lista di tutti i corsi di quest'anno per estrarre i servizi dalla form
                            for (Course course: allCourses) {

                                //se l'admin ha ceckato sul ceckbox del corso course
                                if (request.getParameter(String.valueOf(course.getIdCourse())) != null) {

                                    //aggiungo l'id del corso alla lista degli id dei corsi ceckati dalla form
                                    newCourseRelated.add(Integer.valueOf(request.getParameter(String.valueOf(course.getIdCourse()))));


                                }
                            }






                            /*
                                EFFETTUO LE MODIFICHE
                             */

                            //inizializzo un booleano per capire se devo aggiungere un log
                            boolean serviziCambiati = false;

                            //per chiarimenti maggiori di quello fatto sotto andare nella servlet AdmModUser, e' lo stesso principio

                            //ciclo la lista dei corsi
                            for (Course course : allCourses) {

                                //caso 3, se il corso e' presente in oldCourseRelated ma non in nenewCourseRelated
                                if (oldCourseRelated.contains(course) && !newCourseRelated.contains(course.getIdCourse())) {

                                    //tolgo il collegamento con il corso course al corso

                                    /*
                                        MANCA
                                     */

                                    serviziCambiati = true;

                                }

                                //caso 2, se groups non e' contenuto in groupsListPrima ed ora e' contenuto in nameGroupsListDopo
                                if (!oldCourseRelated.contains(course) && newCourseRelated.contains(course.getIdCourse())) {

                                    //aggiungo il servizio al gruppo

                                    /*
                                        MANCA
                                     */


                                    serviziCambiati = true;

                                }

                                //se non sono veri nessuno dei due ci troviamo nel caso 1, quindi non faccio nulla

                            }

                            if (serviziCambiati == true) {

                                //aggiungo log di modifica associazioni con i servizi
                                logManager.addLog(sessionManager.getUser(request), "COURSE: " + courseById + " HAS SUBMITTED CHANGES TO ASSOCIATED COURSE IN: " + mode, ds);

                            }




                            courseDao.destroy();

                            /*
                                DA QUESTO PUNTO IN POI E' ANDATO STORTO QUALCOSA
                             */

                            //se nel parametro mode c'e' un problema
                        }else{

                            this.processError(request, response);

                        }

                        //controllo tra vecchi dati e nuovi e eseguo operazioni appropriate


                        //chiudo dao corsi
                        courseDao.destroy();

                        //se non ha permesso di modificare questo corso
                    }else{


                        //chiudo dao corsi
                        courseDao.destroy();
                    }

                    //se il corso non esiste piu'
                }else{



                    //chiudo dao corsi
                    courseDao.destroy();

                }



                //se non ha un sessione valida per tale operazione
            } else {


            }

        } catch (DaoException e) {
            e.printStackTrace();


        } catch (LogException e) {
            e.printStackTrace();
        }
    }
}
