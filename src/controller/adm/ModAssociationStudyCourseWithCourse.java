package controller.adm;

import controller.BaseController;
import controller.logController.LogException;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //ciclo sulla lista dei corsi che appartengono al corso di studi
        for (Course course : coursesMatch ){

            //se il corso e' di un anno diverso da quello attuale
            if( utilityManager.getDifferenceByAccademicAge(course, accademicYear) != 0 ){

                //elimino il corso dalla lista
                coursesMatch.remove(course);
            }
        }

        return coursesMatch;
    }

    /**
     * Controlla permessi e sessione, estrae il corso di studi, tutti i corsi, i corsi a lui associati,
     * inserisc nel datamodel e lancia il template
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

                    //estraggo l'attuale anno accademico
                    String accademicYear = utilityManager.getCurrentAccademicYear(Calendar.getInstance());

                    //estraggo la lista dei corsi di questo anno accademico
                    List<Course> allCourses = courseDao.getCourseByYear(accademicYear);

                    //estraggo la lista dei corsi collegati al corso di studi di quest'anno
                    List<Course> coursesMatch = getCourseRelatedStudyCourse(courseDao, studyCourse, accademicYear);


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


                    //chiudo i dao
                    studyCourseDao.destroy();
                    courseDao.destroy();

                    //prima di lanciara il template carico nella sessione dell'amministatore l'id dell'utente che intendo modificare
                    request.getSession().setAttribute("idStudyCourseToModify", studyCourse.getId() );

                    //lancio il template
                    TemplateController.process("mod_association_study_course_with_course.ftl", datamodel, response, getServletContext());

                //se l'utente non ha il permesso
                }else{

                    response.sendRedirect("ServiceNotPermissed");
                }

            //se non ha una sessione valida
            }else{

                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"ModAssociationStudyCourseWithCourse");
            }

        }catch (DaoException e) {
            e.printStackTrace();
            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel, response, getServletContext());
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

                    //estraggo il corso di studi dall'id inserito precedetnemente nella sessione
                    StudyCourse studyCourse = studyCourseDao.getStudyCourseById((Integer) request.getSession().getAttribute("idStudyCourseToModify"));

                    //estraggo l'attuale anno accademico
                    String accademicYear = utilityManager.getCurrentAccademicYear(Calendar.getInstance());






                    /*
                        RACCOLO I DATI DELLE ASSOCIAZIONI TRA IL CORSO DI STUDI E IL CORSO
                     */

                    //raccolgo i dati precedenti con le connessioni tra il corso di studi in esame e i corsi dell'utlimo anno
                    List<Course> courseListPrima = getCourseRelatedStudyCourse(courseDao, studyCourse, accademicYear);

                    //estraggo la lista dei corsi di quest'anno
                    List<Course> courseListAll = courseDao.getCourseByYear(accademicYear);

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

                            System.out.println("SONO ENTRATO NEL PRIMO CASO");

                            //tolgo il servizio service al gruppo
                            studyCourseDao.deleteCourseStudyCourseConnection(course, studyCourse);

                            serviziCambiati = true;

                        }

                        //caso 2, se groups non e' contenuto in groupsListPrima ed ora e' contenuto in nameGroupsListDopo
                        if (!courseListPrima.contains(course) && nameCourseListDopo.contains(course.getName())) {

                            System.out.println("SONO ENTRATO NEL SECONDO CASO");

                            //aggiungo il servizio al gruppo
                            studyCourseDao.insertCourseStudyCourseConnection(course, studyCourse, request.getParameter("cfuType"+course.getName()));

                            serviziCambiati = true;

                            //se non lo ho aggiunto controllo se ha cambiato solo il tipo di cfu
                        }


                        //se non sono state effettuate modifiche rispetto a prima
                        if( courseListPrima.contains(course) && nameCourseListDopo.contains(course.getName()) ) {

                            System.out.println("SONO ENTRATO NEL TERZO CASO");

                            //estraggo il tipo dei cfu
                            String cfuType = studyCourseDao.getCfuType(course, studyCourse);

                            //se il tipo dei cfu e' null lo rendo vuoto per eseguire il confronto con l'equals
                            if (cfuType == null) cfuType = "";

                            //se il corso era gia associato al corso di studi ma ha cambiato il tipo di cfu
                            if (!cfuType.equals(request.getParameter("cfuType" + course.getName()))) {


                                System.out.println("SONO ENTRATO NEL QUARTO CASO");

                                System.out.println(request.getParameter("cfuType" + course.getName()));

                                //cambio il tipo dei cfu
                                studyCourseDao.updateCourseStudyCourse(course, studyCourse, request.getParameter("cfuType" + course.getName()));

                                serviziCambiati = true;

                            }

                        }

                        //se non sono veri nessuno dei due ci troviamo nel caso 1, quindi non faccio nulla

                    }

                    //se i servizi sono cambiati aggiungo il messaggio
                    if (serviziCambiati) datamodel.put("message", "UPDATE SUCCESSFUL");


                    //aggiungo al datamodel i dati aggiornati e il messaggio
                    datamodel.put("studyCourse", studyCourse);
                    datamodel.put("allCourses", courseListAll);
                    datamodel.put("coursesMatch", getCourseRelatedStudyCourse(courseDao, studyCourse, accademicYear));

                    //se ho effettuato delle modifiche aggiungo un log
                    if(serviziCambiati == true){
                        logManager.addLog(sessionManager.getUser(request),"MODIFIED ASSOCIATION BETWEEN COURSE AND THIS STUDY COURSE:" + studyCourse, ds);
                    }

                    //chiudo i dao
                    studyCourseDao.destroy();
                    courseDao.destroy();



                    //lancio il template
                    TemplateController.process("mod_association_study_course_with_course.ftl", datamodel, response, getServletContext());



                    //se l'utente non ha il permesso
                }else{

                    response.sendRedirect("ServiceNotPermissed");
                }

                //se non ha una sessione valida
            }else{

                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"ModAssociationStudyCourseWithCourse");
            }

        }catch (DaoException e) {
            e.printStackTrace();
            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel, response, getServletContext());
        } catch (LogException e) {
            e.printStackTrace();
        }
    }
}
