package controller.adm.course;

import controller.BaseController;
import controller.logController.LogException;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.interfaces.CourseDao;
import dao.interfaces.UserDao;
import model.Course;
import model.Service;
import model.User;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet per associare un corso esistente ai docenti
 *
 * PARAMETRI GET:
 * id: id del corso con cui si vogliono effettuare le associazioni
 *
 * PARAMETRI PASSATI AL DATAMODEL:
 * course: corso su cui si vogliono effettuare modifiche
 * userMatch: utenti collegati al corso
 * allUser: tutti gli utenti del sistema
 *
 * @author Davide Micarelli
 */
public class ModAssociationCourseWithUser extends BaseController {

        /**
         * Dato come parametro get l'id del corso,
         * inserisco nel datamodel:
         *      Corso preso in esame
         *      Docenti gia' associati al corso
         *      Tutti i docenti
         *
         * Infine lancio il template
         */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {

                //elimino messaggio
                datamodel.put("message", null);

                //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
                if (sessionManager.isHardValid(request)) {

                    //estraggo il servizio di eliminazione del corso
                    Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());

                    //se l'utente in sessione possiede il servizio che gli permette di modificare tutti i corsi (quindi e' un amministratore)
                    if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse)) {

                        //controllo e estraggo l'id del corso passato come get
                        if(request.getParameter("id")==null || request.getParameter("id").equals("")) {

                            //lancio servlet di errore
                            response.sendRedirect("Error");
                            return;

                        }

                        //inizializzo i dao
                        CourseDao courseDao = new CourseDaoImpl(ds);
                        courseDao.init();
                        UserDao userDao = new UserDaoImpl(ds);
                        userDao.init();

                        //estraggo il corso
                        Course course = courseDao.getCourseById(Integer.parseInt(request.getParameter("id")));

                        if(course == null || course.getIdCourse() <= 0){
                            //se il corso passato non esiste lancio la servlet della lista dei corsi
                            response.sendRedirect("ListCourse");
                            return;
                        }

                        //inserisco l'id del corso in sessione
                        request.getSession().setAttribute("idCourseToModify", course.getIdCourse());


                        //estraggo gli utenti che sono gia collegati al corso
                        List<User> userMatchWithCourse = userDao.getUserByCourse(course);

                        //estraggo tutti gli utenti
                        List<User> alluser = userDao.getUsers();

                        //inserisco nel datamodel le liste di utenti
                        datamodel.put("course", course);
                        datamodel.put("userMatch", userMatchWithCourse);
                        datamodel.put("allUser", alluser);


                        //chiudo i dao
                        courseDao.destroy();
                        userDao.destroy();

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //lancio il template
                        TemplateController.process("mod_association_course_with_user.ftl", datamodel, response, getServletContext());

                        //se non possiede il servizio
                    }else {
                        //lancio il messaggio di servizio non permesso
                        this.processNotPermitted(request, response);
                    }

                    //se sessione non valida
                }else {

                    //setto la previous page e reindirizzo alla login
                    createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
                }

            } catch (DaoException e) {
                e.printStackTrace();

                //lancio il template di errore
                this.processError(request, response);
            }
        }

    /**
     * Controllo i permessi e sessione,
     * estraggo i collegamenti precedenti tra il corso e gli utenti,
     * estraggo i dati dalla richiesta post,
     * eseguo i cambiamenti se necessario,
     * carico nel datamodel i nuovi dati e lancio il template
     *
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

                //estraggo il servizio di eliminazione del corso
                Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());

                //se l'utente in sessione possiede il servizio che gli permette di modificare tutti i corsi (quindi e' un amministratore)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse)) {


                    //inizializzo i dao
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();
                    UserDao userDao = new UserDaoImpl(ds);
                    userDao.init();

                    //controllo la presenza dell'id nella sessione
                    if(request.getSession().getAttribute("idCourseToModify") == null || request.getSession().getAttribute("idCourseToModify").equals("")){

                        //se non ho in sessione l'id del corso lancio la lista
                        response.sendRedirect("ListCourse");
                        return;
                    }

                    //estraggo il corso dall'id inserito nella sessione dell'admin
                    Course course = courseDao.getCourseById(Integer.parseInt(String.valueOf(request.getSession().getAttribute("idCourseToModify"))));

                    //se il corso non esiste
                    if(course == null || course.getIdCourse() <= 0 ){

                        //se non esiste il corso
                        response.sendRedirect("ListCourse");
                        return;
                    }

                    //estraggo gli utenti collegati al corso
                    List<User> userCollegatiPrima = userDao.getUserByCourse(course);

                    //estraggo tutti gli utenti
                    List<User> allUsers = userDao.getUsers();

                    //inizializzo la lista dei corsi da collegare nuovi
                    List<User> userCollegatiDopo = new ArrayList <>();

                    //ciclo sui parametri post per estrarre gli utenti settati dall'admin
                    for(User user : allUsers){

                        //se la eail dell' utente e' presente tra i parametri post
                        if( request.getParameter(user.getEmail()) != null ){

                            //inserisco nella lista degli utenti collegati
                            userCollegatiDopo.add(user);
                        }
                    }

                    List<User> gianni = utilityManager.getContentInAButNotInB(userCollegatiPrima, userCollegatiDopo);

                    System.out.println("Gianni: " + gianni);

                    //ciclo su utenti presenti prima e non presenti dopo
                    for( User user: gianni){

                        System.out.println();

                        //inserisco collegamento tra corso e utente
                        courseDao.deleteLinkCourseUser(course, user);

                    }

                    List<User> pino = utilityManager.getContentInAButNotInB(userCollegatiDopo, userCollegatiPrima);

                    System.out.println("Pino: " + pino);

                    //ciclo su utenti non presenti prima ma presenti dopo
                    for( User user: pino){

                        //elimino il collegamento tra corso e utente
                        courseDao.storeLinkCourseUser(course, user);

                    }


                    //inserisco nel datamodel i dati
                    datamodel.put("course", course);
                    datamodel.put("userMatch", userCollegatiDopo);
                    datamodel.put("allUser", allUsers);

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //inserisco un log
                    logManager.addLog(sessionManager.getUser(request), "USER: " + sessionManager.getUser(request).toStringForLog() +
                            " HAS CHANGED THE CONNECTION BETWEEN USERS AND THE COURSE: " + course.toStringForLog(), ds);

                    //lancio il template
                    TemplateController.process("mod_association_course_with_user.ftl", datamodel, response, getServletContext());

                    //se non ho il permesso
                } else {

                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);
                }

                 //se non ho una sessione valida
            }else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            //lancio il template di errore
            this.processError(request, response);
        }
        //Se c'e' un errore al log nn faccio nulla
        catch (LogException e) {
            e.printStackTrace();

            TemplateController.process("mod_association_course_with_user.ftl", datamodel, response, getServletContext());
        }

    }
}




