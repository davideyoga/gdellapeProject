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

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public class AdmModStudyCourse extends BaseController{

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    private void inserCourse(StudyCourse studyCourse, CourseDao courseDao)throws DaoException{

            //estraggo i corsi che vengono erogati dal mio corso di studi
            List<Course> corsiDelCorsoDiStudi = courseDao.getCourseByStudyCourse(studyCourse);

            //estraggo tutti i corsi di studio
            List<Course> corsiDiStudio = courseDao.getCourses();

            //inserisco nel datamodel i corsi
            datamodel.put("listCourseByStudyCourse",corsiDelCorsoDiStudi);
            datamodel.put("listCourses", corsiDiStudio);
    }

    /**
     * Riempie e restituisce lo studyCourse con i dati della form
     * @param request
     * @param studyCourse
     * @return
     */
    private StudyCourse getStudyCourseByForm( HttpServletRequest request, StudyCourse studyCourse){

        studyCourse.setId((Integer) request.getSession().getAttribute("idStudyCourseToModify"));
        studyCourse.setCode(request.getParameter("code"));
        studyCourse.setName(request.getParameter("name"));
        studyCourse.setDescription_ita(request.getParameter("description_ita"));
        studyCourse.setDescription_eng(request.getParameter("description_eng"));
        studyCourse.setDepartment_ita(request.getParameter("department_ita"));
        studyCourse.setDepartment_eng(request.getParameter("department_eng"));
        studyCourse.setLevel_ita(Integer.parseInt(request.getParameter("level_ita")));
        studyCourse.setLevel_eng(Integer.parseInt(request.getParameter("level_eng")));
        studyCourse.setDuration(Integer.parseInt(request.getParameter("duration")));
        studyCourse.setClasses(request.getParameter("class"));
        studyCourse.setSeat(request.getParameter("seat"));
        studyCourse.setAccessType_ita(request.getParameter("accessType_ita"));
        studyCourse.setAccessType_eng(request.getParameter("accessType_eng"));
        studyCourse.setLanguage_ita(request.getParameter("language_ita"));
        studyCourse.setLanguage_eng(request.getParameter("language_eng"));

        return studyCourse;
    }


    /**
     * Dopo i vari controlli estrae dalla chiamata get l'id del corso di studi, lo estrae e lo inserisce nel datamodel
     * in fine si lancia il template
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message",null);

            //se la sessione e' valida
            if(sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modUser = utilityManager.getServiceAndCreate(request, response, ds, "modStudyCourse", "Permissed for modification Study Course",
                        datamodel, getServletContext());

                //se l'utente ha il permesso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                    //inizializzo il dao
                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();

                    //se non e' presente l'id del corso di studi passato come parametro get c'e' un problema
                    if(request.getParameter("id") == null ){
                        response.sendRedirect("AdmGetListStudyCourse");
                        return;
                    }

                    //estraggo il corso di studi dall'id passato
                    StudyCourse studyCourse = studyCourseDao.getStudyCourseById(Integer.parseInt(request.getParameter("id")));

                    System.out.println(studyCourse);

                    if(studyCourse!= null && studyCourse.getId() > 0){

                        //inserisco nel template il corso di studi
                        datamodel.put("studyCourse", studyCourse);

                        //inizializzo il dao dei corsi
                        CourseDao courseDao = new CourseDaoImpl(ds);
                        courseDao.init();

                        //inserisco tutti i corsi e i corsi associati al corso di studi nel datamodel
                        inserCourse(studyCourse, courseDao);

                        //chiudo il dao dei corsi
                        courseDao.destroy();

                        //prima di lanciara il template carico nella sessione dell'amministatore l'id dell'utente che intendo modificare
                        request.getSession().setAttribute("idStudyCourseToModify", studyCourse.getId() );

                        //lancio il template
                        TemplateController.process("study_course_mod_adm.ftl", datamodel,response,getServletContext());

                        //se il corso di studi estratto non esiste
                    }else{
                        //lancio messaggio di errore
                        TemplateController.process("error.ftl", datamodel,response,getServletContext());
                    }


                    //infine chiudo il dao
                    studyCourseDao.destroy();

                    //se non ha il permesso
                }else{
                    //lancio il template di non permesso
                    TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );
                }

                //se la sessione non valida
            }else{
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"AdmGetListStudyCourse");
            }

        } catch (DaoException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());
        }
    }

    /**
     * Raccoglie i parametri tramite post e n=modifica il corso di studi di conseguenza
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message",null);

            //se la sessione e' valida
            if(sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modUser = utilityManager.getServiceAndCreate(request, response, ds, "modStudyCourse", "Permissed for modification Study Course",
                        datamodel, getServletContext());

                //se l'utente ha il permesso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                    //inizializzo il dao
                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();

                    //estraggo dalla sessione l'id del corso di studi che intendo modificare e lo uso per estrarre tutto il corso di studi
                    StudyCourse studyCoursePrecedente = studyCourseDao.getStudyCourseById((int) request.getSession().getAttribute("idStudyCourseToModify"));

                    //estraggo il corso di studi dalla form
                    StudyCourse studyCourse = studyCourseDao.getStudyCouse();
                    studyCourse = getStudyCourseByForm(request, studyCourse);


                    /*
                        INSERIRE VAI CONTROLLI SUI DATI PASSATI DALLA FORM
                     */

                    //se i corsi di studi sono diversi eseguo un update
                    if( !studyCourse.equals(studyCoursePrecedente)){

                        //eseguo update del corso di studi
                        studyCourseDao.storeStudyCourse(studyCourse);

                        logManager.addLog(sessionManager.getUser(request), "Study Course changed. BEFORE: " + studyCoursePrecedente +
                                                                                                            ". AFTER: " + studyCourse, ds);

                    }//se i corsi sono uguali non faccio nulla


                    /*
                        FINE MODIFICA CORSO DI STUDI
                     */

                    /*
                        INIZIO CECK SULLE ASSOCIAZIONI CON I CORSI
                     */

                    //inizializzo un dao dei Corsi per estrarre tutti i corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();
                    List<Course> allCourses = courseDao.getCourses();
                    List<Course> courseListPrima = courseDao.getCourseByStudyCourse(studyCourse);

                    //lista rappresentante i nomi dei corsi ricevuti dalla form
                    List<String> nameCoursesDopo = new ArrayList <>();

                    //ciclo sulla lista di tutti i corsi per estrarre i corsi con i vari dati dalla form
                    for (Course course : allCourses) {

                        //se l'admin ha ceckato sul ceckbox del corso course
                        if (request.getParameter(course.getName()) != null) {

                            //aggiungo il nome del gruppo alla lista dei nomi dei gruppi ceckati dalla form
                            nameCoursesDopo.add(request.getParameter(course.getName()));
                        }
                    }

                    //inizializzo un booleano per capire se devo aggiungere un log
                    boolean courseCambiati = false;

                    //ciclo la lista contenente tutti i corsi, SI PUO' OTTIMIZZARE
                    for (Course course : allCourses) {


                        //caso 3, se il corso e' presente prima e non presente dopo
                        if (courseListPrima.contains(course) && !nameCoursesDopo.contains(course.getName())) {

                            //tolgo il corso al corso di studi

                            studyCourseDao.deleteCourseStudyCourseConnection(course, studyCourse);

                            courseCambiati = true;

                        }

                        //caso 2, se course non e' contenuto in coursePrima ed ora e' contenuto in nameCoursesDopo
                        if (!courseListPrima.contains(course) && nameCoursesDopo.contains(course.getName())) {

                            //aggiungo il gruppo all'utente

                            studyCourseDao.insertCourseStudyCourseConnection(course, studyCourse);

                            courseCambiati = true;
                        }

                        //se non sono veri nessuno dei due ci troviamo nel caso 1, quindi non faccio nulla

                    }

                    if (courseCambiati = true) {

                        //aggiungo log di modifica associazioni con i gruppi
                        logManager.addLog(sessionManager.getUser(request), "USER: " + sessionManager.getUser(request) + " HAS SUBMITTED CHANGES TO ASSOCIATED STUDY COURSE WITH COURSE", ds);

                    }

                    /*
                        FINE MODIFICA ASSOCIAZIONI CORSI
                     */

                    //riestraggo i corsi associati al mio corso di studi e li inserisco nel template
                    inserCourse(studyCourse, courseDao);

                    //inserisco nel template il corso di studi aggiornato
                    datamodel.put("studyCourse", studyCourse);

                    //lancio il template
                    TemplateController.process("study_course_mod_adm.ftl", datamodel,response,getServletContext());


                    //chiudo i vari dao
                    courseDao.destroy();
                    studyCourseDao.destroy();


                    //se non ha il permesso
                }else{
                    //lancio il template di non permesso
                    TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );
                }

                //se la sessione non valida
            }else{
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"AdmModStudyCourse");
            }

        } catch (DaoException e) {
            e.printStackTrace();
            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());
        } catch (LogException e) {
            e.printStackTrace();
        }

    }

}
