package controller.adm.studyCourse;

import controller.BaseController;
import controller.logController.LogException;
import controller.utility.AccademicYear;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.implementation.StudyCourseDaoImpl;
import dao.interfaces.CourseDao;
import dao.interfaces.StudyCourseDao;
import model.Course;
import model.Service;
import model.StudyCourse;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Davide Micarelli
 */
public class ModAssociationStudyCourseWithCourse extends BaseController {

    /**
     * Torna la lista dei corsi collegati ai corsi di studio di quest'anno
     * @param courseDao
     * @param studyCourse
     * @param accademicYear
     * @return
     * @throws DaoException
     */
    protected List<Course> getCourseRelatedStudyCourse(CourseDao courseDao, StudyCourse studyCourse, String accademicYear) throws DaoException {

        //estraggo la lista dei corsi collegati al corso di studi
        List<Course> coursesMatch = courseDao.getCourseByStudyCourse(studyCourse);

        //TOLGO I CORSI DI STUDIO DI UN ANNO PRECEDENTE A QUELLO ATTUALE

        Iterator<Course> iterator = coursesMatch.iterator();

        while (iterator.hasNext()){

            Course course = iterator.next();

            //se il corso e' di un anno diverso da quello attuale
            if( utilityManager.getDifferenceByAccademicAge(course, accademicYear) != 0 ){

                //elimino il corso dalla lista
                iterator.remove();
            }

        }

        /*  IL CICLO COL FOR EACH DA UN ConcurrentModificationException PRIMA DEL 2009 E DOPO IL 2020
        //ciclo sulla lista dei corsi che appartengono al corso di studi
        for (Course course : coursesMatch ){

            //se il corso e' di un anno diverso da quello attuale
            if( utilityManager.getDifferenceByAccademicAge(course, accademicYear) != 0 ){

                //elimino il corso dalla lista
                coursesMatch.remove(course);
            }
        }

        */

        return coursesMatch;
    }

    /**
     * Controlla permessi e sessione, estrae il corso di studi, tutti i corsi, i corsi a lui associati,
     * inserisce nel datamodel e lancia il template
     * Devo dare la possibilita' di modificare le associazioni con un corso anche di anni diversi da quello attuale,
     * in questo modo l'adm non e' legato all'anno attuale e puo' ad esempio ad agosto mod. un corso di studi dell'anno
     * accademico successivo.
     * Devo dare alla servlet tramite parametro get l'anno che si vuole modificare
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

            //se la sessione e' valida
            if (sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modUser = this.getServiceAndCreate(request, response, ds, "modStudyCourse", "Permissed for modification Study Course",
                        datamodel, getServletContext());

                //se l'utente ha il permesso
                if (((List<Service>) request.getSession().getAttribute("services")).contains(modUser)) {


                    //inizializzo i dao
                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();



                    //controllo se non e' presente il parametro get con l'id del corso di studi
                    if( request.getParameter("idStudyCourse") == null || request.getParameter("idStudyCourse").equals("") ){

                        //se il parametro non e' presente lancio la servlet della lista dei corsi di studi
                        response.sendRedirect("AdmGetListStudyCourse");
                        return;

                    }

                    //estraggo il corso di studi dal parametro get
                    StudyCourse studyCourse = studyCourseDao.getStudyCourseById(Integer.parseInt(request.getParameter("idStudyCourse")));

                    //controllo se esiste nel db un corso di studi con tale id
                    if(studyCourse == null || studyCourse.getId() <= 0){

                        //se il corso di studi non esiste lancio la servlet della lista dei corsi di studi
                        response.sendRedirect("AdmGetListStudyCourse");
                        return;

                    }

                    //inizializzo un anno accademico
                    AccademicYear accademicYear;

                    //controllo l'esistenza del parametro get dell'anno accademico che si vuole modificare
                    //se non esiste
                    if(request.getParameter("age") == null || request.getParameter("age").equals("")){

                        //se non esiste l'anno accademico, setto quello attuale
                        accademicYear = new AccademicYear(Calendar.getInstance());

                        //se esiste
                    }else{

                       //se esiste setto quello impostato nella richiesta get
                       accademicYear = new AccademicYear(Integer.parseInt(request.getParameter("age")));
                    }

                    //estraggo la lista dei corsi di questo anno accademico
                    List<Course> allCourses = courseDao.getCourseByYear(accademicYear.toString());


                    //estraggo la lista dei corsi collegati al corso di studi di quest'anno
                    List<Course> coursesMatch = getCourseRelatedStudyCourse(courseDao, studyCourse, accademicYear.toString());


                    //INIZIALIZZO E RIEMPIO UNA LISTA CON TUTTI I TIPI DI CFU PER OGNI CORSO CHE APPARTIENE AL CORSO DI STUDI IN ESAME

                    //inizializzo la mappa
                    Map<Integer, String> cfuType = new HashMap <>();

                    //ciclo sulla lista dei corsi che matchano col corso di studi
                    for(Course course : coursesMatch ){

                        //aggiungo come chiave l'id del corso e come valore il tipo di cfu
                        cfuType.put(course.getIdCourse(), studyCourseDao.getCfuType(course, studyCourse) );

                    }


                    //inserisco i dati nel datamodel
                    datamodel.put("studyCourse", studyCourse);
                    datamodel.put("allCourses", allCourses);
                    datamodel.put("coursesMatch", coursesMatch);
                    datamodel.put("cfuType", cfuType);
                    datamodel.put("currentYear", accademicYear );
                    datamodel.put("currentFirstYear", accademicYear.getFirstYear());


                    //chiudo i dao
                    studyCourseDao.destroy();
                    courseDao.destroy();

                    //prima di lanciara il template carico nella sessione dell'amministatore l'id dell'utente e l'anno che intendo modificare, tale parametro mi permette di tenere traccia
                    //anche dopo aver eseguito una richiesta POST
                    request.getSession().setAttribute("idStudyCourseToModify", studyCourse.getId() );
                    request.getSession().setAttribute("currentYear", accademicYear.getFirstYear() );


                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template
                    TemplateController.process("mod_association_study_course_with_course.ftl", datamodel, response, getServletContext());

                //se l'utente non ha il permesso
                }else{

                    this.processNotPermitted(request, response);
                }

            //se non ha una sessione valida
            }else{

                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"ModAssociationStudyCourseWithCourse");
            }

        }catch (DaoException e) {
            e.printStackTrace();
            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);
        }
    }


    /**
     * Controllo sessione e permessi, raccoglie i checkbox e esegue le associazioni di conseguenza
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message", null);

            //se la sessione e' valida
            if (sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modUser = this.getServiceAndCreate(request, response, ds, "modStudyCourse", "Permissed for modification Study Course",
                        datamodel, getServletContext());

                //se l'utente ha il permesso
                if (((List<Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                    //controllo la presenza dell'id nella sessione
                    if(request.getSession().getAttribute("idStudyCourseToModify") == null ||
                            request.getSession().getAttribute("idStudyCourseToModify").equals("")){

                        //se l'id non presente lancio la servlet della lista dei corsi di studio
                        response.sendRedirect("AdmGetListStudyCourse");
                        return;
                    }

                    //inizializzo i dao
                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //estraggo il corso di studi dall'id inserito precedentemente nella sessione
                    StudyCourse studyCourse = studyCourseDao.getStudyCourseById((Integer) request.getSession().getAttribute("idStudyCourseToModify"));


                    //controllo la presenza del parametro currentYear che rappresenta l'anno preso in esame, dovrebbe essere sempre presente,
                    //se cosi non fosse c'e' un problema, per ovviare lancio la lista dei corsi di studio
                    if(request.getSession().getAttribute("currentYear") == null ||
                            request.getSession().getAttribute("currentYear").equals("")){

                        //se l'anno non presente lancio la servlet della lista dei corsi di studio
                        response.sendRedirect("AdmGetListStudyCourse");
                        return;
                    }

                    //estraggo l'anno accademico dalla sessione
                    AccademicYear accademicYear = new AccademicYear((Integer) request.getSession().getAttribute("currentYear"));




                    /*
                        RACCOLO I DATI DELLE ASSOCIAZIONI TRA IL CORSO DI STUDI E IL CORSO
                     */

                    //raccolgo i dati precedenti con le connessioni tra il corso di studi in esame e i corsi dell'anno accademico preso in esame
                    List<Course> courseListPrima = getCourseRelatedStudyCourse(courseDao, studyCourse, accademicYear.toString());

                    //estraggo la lista dei corsi dell'anno accademico preso in esame
                    List<Course> courseListAll = courseDao.getCourseByYear(accademicYear.toString());

                    //creo una lista con i nomi dei servizi che mi arrivano dalla form
                    List <String> nameCourseListDopo = new ArrayList<>();

                    //ciclo sulla lista di tutti i servizi per estrarre i servizi dalla form
                    for (Course course : courseListAll) {

                        //se l'admin ha ceckato sul ceckbox del servizio service
                        if (request.getParameter(course.getName()) != null) {

                            //aggiungo il nome del servizio alla lista dei nomi dei servizi dalla ceckati dalla form
                            nameCourseListDopo.add(request.getParameter(course.getName()));
                        }
                    }

                    //inizializzo un booleano per capire se devo aggiungere un log
                    boolean serviziCambiati = false;

                    //per chiarimenti maggiori di quello fatto sotto andare nella servlet AdmModUser, e' lo stesso principio

                    //ciclo la lista dei corsi
                    for (Course course : courseListAll) {

                        //caso 3, se il corso e' presente in courseListAll ma non in nameCourseList
                        if (courseListPrima.contains(course) && !nameCourseListDopo.contains(course.getName())) {

                            //tolgo il servizio service al gruppo
                            studyCourseDao.deleteCourseStudyCourseConnection(course, studyCourse);

                            serviziCambiati = true;

                        }

                        //caso 2, se groups non e' contenuto in groupsListPrima ed ora e' contenuto in nameGroupsListDopo
                        if (!courseListPrima.contains(course) && nameCourseListDopo.contains(course.getName())) {

                            //aggiungo il servizio al gruppo
                            studyCourseDao.insertCourseStudyCourseConnection(course, studyCourse, request.getParameter("cfuType"+course.getName()));

                            serviziCambiati = true;

                            //se non lo ho aggiunto controllo se ha cambiato solo il tipo di cfu
                        }


                        //se non sono state effettuate modifiche rispetto a prima
                        if( courseListPrima.contains(course) && nameCourseListDopo.contains(course.getName()) ) {

                            //estraggo il tipo dei cfu
                            String cfuType = studyCourseDao.getCfuType(course, studyCourse);

                            //se il tipo dei cfu e' null lo rendo vuoto per eseguire il confronto con l'equals
                            if (cfuType == null) cfuType = "";

                            //se il corso era gia associato al corso di studi ma ha cambiato il tipo di cfu
                            if (!cfuType.equals(request.getParameter("cfuType" + course.getName()))) {

                                System.out.println(request.getParameter("cfuType" + course.getName()));

                                //cambio il tipo dei cfu
                                studyCourseDao.updateCourseStudyCourse(course, studyCourse, request.getParameter("cfuType" + course.getName()));

                                serviziCambiati = true;

                            }

                        }

                        //se non sono veri nessuno dei due ci troviamo nel caso in cui non e' stata e quindi non faccio nulla

                    }

                    //se i servizi sono cambiati aggiungo il messaggio
                    if (serviziCambiati) datamodel.put("message", "UPDATE SUCCESSFUL");



                    //inizializzo la mappa
                    Map<Integer, String> cfuType = new HashMap <>();

                    //ciclo sulla lista dei corsi che matchano col corso di studi
                    for(Course course : getCourseRelatedStudyCourse(courseDao, studyCourse, accademicYear.toString()) ){

                        //aggiungo come chiave l'id del corso e come valore il tipo di cfu
                        cfuType.put(course.getIdCourse(), studyCourseDao.getCfuType(course, studyCourse) );

                    }


                    //aggiungo al datamodel i dati aggiornati e il messaggio
                    datamodel.put("studyCourse", studyCourse);
                    datamodel.put("allCourses", courseListAll);
                    datamodel.put("coursesMatch", getCourseRelatedStudyCourse(courseDao, studyCourse, accademicYear.toString()));
                    datamodel.put("cfuType", cfuType);
                    datamodel.put("currentYear", accademicYear );



                    //se ho effettuato delle modifiche aggiungo un log
                    if(serviziCambiati == true){
                        logManager.addLog(sessionManager.getUser(request),"USER: " + sessionManager.getUser(request).toStringForLog() +
                                                                            " MODIFIED ASSOCIATION BETWEEN COURSE AND THIS STUDY COURSE:" + studyCourse.toStringForLog(), ds);
                    }

                    //chiudo i dao
                    studyCourseDao.destroy();
                    courseDao.destroy();

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template
                    TemplateController.process("mod_association_study_course_with_course.ftl", datamodel, response, getServletContext());



                    //se l'utente non ha il permesso
                }else{

                    this.processNotPermitted(request, response);
                }

                //se non ha una sessione valida
            }else{

                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"ModAssociationStudyCourseWithCourse");
            }

        }catch (DaoException e) {
            e.printStackTrace();
            //in caso di dao exception ecc.. lancio il template di errore
            this.processError(request, response);

        } catch (LogException e) {
            e.printStackTrace();

            //lancio template
            this.processError(request, response);
        }
    }
}
