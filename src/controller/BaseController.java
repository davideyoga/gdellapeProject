package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.logController.LogManager;
import controller.logController.SingletonLogManager;
import controller.sessionController.SessionException;
import controller.sessionController.SessionManager;
import controller.sessionController.SingletonSessionManager;
import controller.utility.UtilityManager;
import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.ServiceDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.implementation.UserGroupsDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.ServiceDao;
import dao.interfaces.UserDao;
import dao.interfaces.UserGroupsDao;
import model.*;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Davide Micarelli
 * Classe che assegna il sessionManager e il logManager
 */
public class BaseController extends HttpServlet {

    protected SessionManager sessionManager;

    protected LogManager logManager;

    protected UtilityManager utilityManager;

    @Resource(name = "jdbc/gdellapeProject")
    protected static DataSource ds;

    protected Map<String, Object> datamodel = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();

        //setto i manager
        this.sessionManager = SingletonSessionManager.getSessionManager();

        this.logManager = SingletonLogManager.getLogManager();

        this.utilityManager = controller.utility.SingletonUtilityManager.getUtilityManager();

        //inserisco nel datamodel l'utente
    }

    /**
     * Setta la pagina precedentemente visitata nella sessione (se non esiste la crea) e indirizza al login
     * Una volta la login verra' reindirizzato a previousPage
     * @param request
     * @param response
     * @param servletName
     */
    protected void createPreviousPageAndRedirectToLogin(HttpServletRequest request, HttpServletResponse response, String servletName){

        try {
            //creo la sessione se non esiste e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
            //in modo di poterlo reindirizzare dopo aver rieffettuato il login
            request.getSession(true);

            sessionManager.setPreviusPage(request, servletName);

            //reindirizzo verso servlet di login
            response.sendRedirect("Login");

        } catch (SessionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Torna true se esiste nel sistema un utente con la stessa mail, false altrimenti.
     * Il dao passato deve essere gia inizializzato con la init
     * @param userDao
     * @param email
     * @return
     * @throws DaoException
     */
    protected boolean isExistEmail(UserDao userDao, String email) throws DaoException {

        User user = userDao.getUserByEmail(email);

        if(user == null || user.getId()<= 0) return false;
        else return true;

    }

    /**
     * Si occupa di lanciare il template template name nella giusta lingua caricata in sessione
     * @param request
     * @param response
     * @param templateName
     */
    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, String templateName, Map<String, Object> datamodel){

        //se non e' settato nessun parametro della lingua:
        if( request.getParameter("lng") == null ){

            templateName = templateName+".ftl";

            TemplateController.process(templateName, datamodel, response, getServletContext());
        }else {
            if (request.getParameter("lng").equals("IT")) {
                templateName = templateName + ".ftl";
                TemplateController.process(templateName, datamodel, response, getServletContext());
            } else {

                templateName = templateName + "_en.ftl";
                TemplateController.process(templateName, datamodel, response, getServletContext());
            }
        }
    }

    /**
     * Setta nel datamodel passato la lingua a seconda della lingua in cui si trova attualmente il sito
     * @param request
     * @param datamodel
     */
    protected void setLng(HttpServletRequest request, Map<String, Object> datamodel){

        //se non e' settato nessun parametro della lingua:
        if( request.getParameter("lng") == null ){

            datamodel.put("lng","IT");

        }else {
            //se ho la lingua settata in ita
            if (request.getParameter("lng").equals("IT")) {
                datamodel.put("lng", "IT");

                //ho la lingua settata in inglese
            } else {
                datamodel.put("lng", "EN");
            }
        }
    }


    protected Service getServiceAndCreate(HttpServletRequest request, HttpServletResponse response, DataSource ds, String nameService, String descriptionService, Map<String, Object> datamodel, ServletContext context){

        //inizializzo il dao per estrarre il servizio createUser
        ServiceDao serviceDao = new ServiceDaoImpl(ds);

        Service service = null;

        try {

            //inizializzo query ecc...
            serviceDao.init();

            //estraggo il servizio
            service = serviceDao.getServiceByName(nameService);

            //se il servizio non e' presente nel database o non ha un id valido lo creo
            if (service == null || service.getId() <= 0) {

                //faccio puntare createUser ad un servizio vuoto da riempire
                service = serviceDao.getService();

                //setto il servizio
                service.setName(nameService);
                service.setDescription(descriptionService);

                //inserisco il servizio nel database
                serviceDao.storeService(service);
            }

            serviceDao.destroy();
            serviceDao = null;

        } catch (DaoException e) {

            datamodel.put("message", "internal error");

            //reindirizzo alla pagina di errore
            TemplateController.process("error.ftl", datamodel, response, context);
        }

        return service;

    }

    /**
     * Riempie e restituisce lo studyCourse con i dati della form
     * @param request
     * @param studyCourse
     * @return
     */
    protected StudyCourse getStudyCourseByForm(HttpServletRequest request, StudyCourse studyCourse, int idStudyCourse){

        studyCourse.setId(idStudyCourse);
        studyCourse.setCode(request.getParameter("code"));
        studyCourse.setName(request.getParameter("name"));
        studyCourse.setDescription_ita(request.getParameter("description_ita"));
        studyCourse.setDescription_eng(request.getParameter("description_eng"));
        studyCourse.setDepartment_ita(request.getParameter("department_ita"));
        studyCourse.setDepartment_eng(request.getParameter("department_eng"));

        try {

            if(request.getParameter("level_ita") != null) {
                studyCourse.setLevel_ita(Integer.parseInt(request.getParameter("level_ita")));
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            studyCourse.setLevel_ita(0);
        }

        try {
            if(request.getParameter("level_eng") != null) {
                studyCourse.setLevel_eng(Integer.parseInt(request.getParameter("level_eng")));
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            studyCourse.setLevel_eng(0);
        }

        try {
            if(request.getParameter("duration") != null) {
                studyCourse.setDuration(Integer.parseInt(request.getParameter("duration")));
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            studyCourse.setDuration(0);
        }


        studyCourse.setClasses(request.getParameter("class"));
        studyCourse.setSeat(request.getParameter("seat"));
        studyCourse.setAccessType_ita(request.getParameter("accessType_ita"));
        studyCourse.setAccessType_eng(request.getParameter("accessType_eng"));
        studyCourse.setLanguage_ita(request.getParameter("language_ita"));
        studyCourse.setLanguage_eng(request.getParameter("language_eng"));

        return studyCourse;


    }

    protected Course getCourseByForm(HttpServletRequest request,Course course, int idCourse){

        course.setIdCourse(idCourse);
        course.setCode((request.getParameter("code")));
        course.setName(request.getParameter("name"));
        course.setYear(request.getParameter("year"));

        try {

            if(request.getParameter("semester") != null ) {
                course.setSemester(Integer.parseInt(request.getParameter("semester")));
            }

        }catch (NumberFormatException e){
            e.printStackTrace();
            course.setSemester(0);
        }

        try {
            if (request.getParameter("cfu") != null) {
                course.setCfu(Integer.parseInt(request.getParameter("cfu")));
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            course.setCfu(0);
        }

        course.setSector(request.getParameter("sector"));
        course.setLanguage(request.getParameter("language"));
        course.setPrerequisite_ita(request.getParameter("prerequisite_ita"));
        course.setPrerequisite_eng(request.getParameter("prerequisite_eng"));
        course.setGoals_ita(request.getParameter("goals_ita"));
        course.setGoals_eng(request.getParameter("goals_eng"));
        course.setExame_mode_ita(request.getParameter("exame_mode_ita"));
        course.setExame_mode_eng(request.getParameter("exame_mode_eng"));
        course.setTeaching_mode_ita(request.getParameter("teaching_mode_ita"));
        course.setTeaching_mode_eng(request.getParameter("teaching_mode_eng"));
        course.setSyllabus_ita(request.getParameter("syllabus_ita"));
        course.setSyllabus_eng(request.getParameter("syllabus_eng"));
        course.setNote_ita(request.getParameter("note_ita"));
        course.setNote_eng(request.getParameter("note_eng"));
        course.setKnowledge_ita(request.getParameter("knowledge_ita"));
        course.setKnowledge_eng(request.getParameter("knowledge_eng"));
        course.setApplication_ita(request.getParameter("application_ita"));
        course.setApplication_eng(request.getParameter("application_eng"));
        course.setEvaluation_ita(request.getParameter("evaluation_ita"));
        course.setEvaluation_eng(request.getParameter("evaluation_eng"));
        course.setCommunication_ita(request.getParameter("communication_ita"));
        course.setCommunication_eng(request.getParameter("communication_eng"));
        course.setLifelog_learning_skills_ita(request.getParameter("lifelog_learning_skills_ita"));
        course.setLifelog_learning_skills_eng(request.getParameter("lifelog_learning_skills_eng"));
        course.setExternal_material_ita(request.getParameter("external_material_ita"));
        course.setExternal_material_ita(request.getParameter("external_material_eng"));

        return course;
    }

    protected User getUserByForm(HttpServletRequest request,User user, int iduser) throws NumberFormatException{

        user.setId(iduser);
        user.setSurname(request.getParameter("surname"));
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setNumber(Long.parseLong(request.getParameter("number")));
        user.setCurriculum_ita(request.getParameter("curriculum_ita"));
        user.setCurriculum_eng(request.getParameter("curriculum_eng"));
        user.setReceprion_hours_ita(request.getParameter("receprion_hours_ita"));
        user.setReceprion_hours_eng(request.getParameter("receprion_hours_eng"));
        user.setPassword(request.getParameter("password"));

        return user;

    }

    protected void insertUserInCourseServlet(HttpServletRequest request) throws DaoException{

            //aggiungo tutti i gruppi al datamodel (per creare un selettore)
            GroupsDao groupsDao = new GroupsDaoImpl(ds);
            groupsDao.init();

            List <Groups> groupsList = groupsDao.getAllGroups();
            datamodel.put("groupsList", groupsList);


            //aggiungo tutti gli utenti al datamodel
            UserDao userDao = new UserDaoImpl(ds);
            userDao.init();

            List <User> userList = userDao.getUsers();
            datamodel.put("userList", userList);

            //AGGIUNGO UNA MAPPA CON CHIAVE UN GRUPPO E COME VALORE UN UserGroups
            UserGroupsDao userGroupsDao = new UserGroupsDaoImpl(ds);
            userDao.init();

            //inizializzo una mappa di liste, ad ogni gruppo corrispondera' una lista di UserGroups
            Map <Groups, List <UserGroups>> groupsUserGroupsList = new HashMap <>();

            //ciclo sulla lista di tutti i gruppi
            for (Groups groups : groupsList) {

                //aggiungo groups come chiave e come valorep la lista di tutti i suoi UserGroups
                groupsUserGroupsList.put(groups, userGroupsDao.getUserGroupsByGroups(groups));

            }
            //aggiungo al template la mappa
            datamodel.put("groupsUserGroupsAssociation", groupsUserGroupsList);

            //chiudo i dao
            groupsDao.destroy();
            userDao.destroy();
            userGroupsDao.destroy();
    }




    protected void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //lancio servlet di errore
        response.sendRedirect("Error");
        return;
    }

    protected void processNotPermitted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //lancio servlet di errore
        response.sendRedirect("NotPermitted");
        return;

    }


}
