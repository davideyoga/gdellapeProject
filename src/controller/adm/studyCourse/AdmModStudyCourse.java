package controller.adm.studyCourse;

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


    private void insertCourse(StudyCourse studyCourse, CourseDao courseDao)throws DaoException{

        //estraggo i corsi che vengono erogati dal mio corso di studi
        List<Course> corsiDelCorsoDiStudi = courseDao.getCourseByStudyCourse(studyCourse);

        //estraggo tutti i corsi di studio
        List<Course> corsiDiStudio = courseDao.getCourses();

        //inserisco nel datamodel i corsi
        datamodel.put("listCourseByStudyCourse",corsiDelCorsoDiStudi);
        datamodel.put("listCourses", corsiDiStudio);
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
                Service modUser = this.getServiceAndCreate(request, response, ds, "modStudyCourse", "Permissed for modification Study Course",
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
                        //insertCourse(studyCourse, courseDao);

                        //chiudo il dao dei corsi
                        courseDao.destroy();

                        //prima di lanciara il template carico nella sessione dell'amministatore l'id dell'utente che intendo modificare
                        request.getSession().setAttribute("idStudyCourseToModify", studyCourse.getId() );

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //lancio il template
                        TemplateController.process("study_course_mod_adm.ftl", datamodel,response,getServletContext());

                        //se il corso di studi estratto non esiste
                    }else{
                        //lancio messaggio di errore
                        this.processError(request, response);
                    }


                    //infine chiudo il dao
                    studyCourseDao.destroy();

                    //se non ha il permesso
                }else{
                    //lancio il template di non permesso
                    this.processNotPermitted(request, response);
                }

                //se la sessione non valida
            }else{
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"AdmGetListStudyCourse");
            }

        } catch (DaoException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);
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
                Service modUser = this.getServiceAndCreate(request, response, ds, "modStudyCourse", "Permissed for modification Study Course",
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
                    studyCourse = this.getStudyCourseByForm(request, studyCourse, (Integer) request.getSession().getAttribute("idStudyCourseToModify"));

                    System.out.println("Corso eztratto dalla form: " + studyCourse);



                    /*
                        CONTROLLI SUI DATI PASSATI DALLA FORM
                     */


                    //se il nome o il codice sono diversi da prima controllo che nel sistema non vi siano corsi con stesso nome o codice
                    if (!studyCourse.getName().equals(studyCoursePrecedente.getName()) ||
                            !studyCourse.getCode().equals(studyCoursePrecedente.getCode())) {

                        boolean cambiamnti=false;


                        //estraggo i corsi di studio con codice e nome uguale a quello modificato
                        StudyCourse studyCourseWithCode = studyCourseDao.getStudyCourseByCode(studyCourse.getCode());
                        StudyCourse studyCourseWithName = studyCourseDao.getStudyCourseByName(studyCourse.getName());


                        //se i corsi di studio estratto sono gli stessi che sto modificando vuol dire che non ho modificato nome e codice
                        //se uno dei due corsi di studio esiste e
                        //uno dei due corsi di studio non e' uguale a quello che stiamo modificando
                        if ((studyCourseWithCode != null) || (studyCourseWithName != null)) {

                            //se esiste un corso di studi con lo stesso codice
                            if (studyCourseWithCode != null && !studyCourse.getCode().equals(studyCoursePrecedente.getCode())) {

                                //inserisco messaggio di errore di codice esistente
                                datamodel.put("message", "Error: Existing Code. ");

                                cambiamnti=true;
                            }

                            if (studyCourseWithName != null && !studyCourse.getName().equals(studyCoursePrecedente.getName())) {

                                //concateno al messaggio di prima(se esiste) il messaggio di errore di nome fia' esistente
                                datamodel.put("message", datamodel.get("message") + "Error: Existing Name.");

                                cambiamnti = true;
                            }

                            if(cambiamnti == true) {

                                //setto l'utente in sessione
                                this.datamodel.put("user", sessionManager.getUser(request));

                                //lancio template con messaggi di errore
                                TemplateController.process("study_course_mod_adm.ftl", datamodel, response, getServletContext());
                                return;


                            }else{
                                //se cambiamenti e' settato a false non faccio nulla
                            }
                        }

                    }

                    System.out.println("Sono uscito dai controlli su nome e codice");


                    //se i corsi di studi sono diversi eseguo un update
                    if (!studyCourse.equals(studyCoursePrecedente)) {

                        System.out.println("Corso di Studio diverso da prima");

                        //eseguo update del corso di studi
                        studyCourseDao.storeStudyCourse(studyCourse);

                        logManager.addLog(sessionManager.getUser(request), "STUDY COURSE CHANGED, BEFORE: " + studyCoursePrecedente.toStringForLog() +
                                " BY: " + sessionManager.getUser(request).toStringForLog(), ds);

                        //se i corsi sono uguali non faccio nulla
                    }else{
                        System.out.println("Non ho modificato il corso");
                    }


                    /*
                        FINE MODIFICA CORSO DI STUDI
                     */

                    /*
                        INIZIO CECK SULLE ASSOCIAZIONI CON I CORSI


                    //inizializzo un dao dei Corsi per estrarre tutti i corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();
                    List <Course> allCourses = courseDao.getCourses();
                    List <Course> courseListPrima = courseDao.getCourseByStudyCourse(studyCourse);

                    //lista rappresentante i nomi dei corsi ricevuti dalla form
                    List <String> nameCoursesDopo = new ArrayList <>();

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

                    //ciclo la lista contenente tutti i corsi
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

                            studyCourseDao.insertCourseStudyCourseConnection(course, studyCourse, "cfuType" + course.getName());

                            courseCambiati = true;
                        }

                        //se non sono veri nessuno dei due ci troviamo nel caso 1, quindi non faccio nulla

                    }

                    if (courseCambiati == true) {

                        //aggiungo log di modifica associazioni con i gruppi
                        logManager.addLog(sessionManager.getUser(request), "USER: " + sessionManager.getUser(request).toStringForLog() +
                                " HAS SUBMITTED CHANGES TO ASSOCIATED STUDY COURSE: " + studyCourse.toStringForLog() + " WITH COURSE", ds);

                    }


                        FINE MODIFICA ASSOCIAZIONI CORSI
                     */

                    //riestraggo i corsi associati al mio corso di studi e li inserisco nel template
                    //insertCourse(studyCourse, courseDao);

                    //inserisco nel template il corso di studi aggiornato
                    datamodel.put("studyCourse", studyCourse);

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template
                    TemplateController.process("study_course_mod_adm.ftl", datamodel, response, getServletContext());


                    //chiudo i vari dao
                    //courseDao.destroy();
                    studyCourseDao.destroy();


                    //se non ha il permesso
                }else{
                    //lancio il template di non permesso
                    this.processNotPermitted(request, response);
                }

                //se la sessione non valida
            }else{
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"AdmModStudyCourse");
            }

        } catch (DaoException | NullPointerException e) {
            e.printStackTrace();
            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);

        } catch (LogException e) {
            e.printStackTrace();

            this.processError(request, response);
        }

    }

}
