package controller;

import controller.utility.AccademicYear;
import controller.utility.AccademicYearException;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.StudyCourseDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.interfaces.CourseDao;
import dao.interfaces.GroupsDao;
import dao.interfaces.StudyCourseDao;
import dao.interfaces.UserDao;
import model.Course;
import model.Groups;
import model.StudyCourse;
import model.User;
import org.apache.catalina.Group;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe che estrae i corsi di studio secondo i parametri passati dalla form tramite parmetri get.
 * Se non e' presente il parametro dell'anno prende quello attuale
 *
 *
 * @author Davide Micarelli
 */
public class ListCourseAn extends BaseController {

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, List<Course> listCourse, List<User> userList, List<StudyCourse> allStudyCourse){

        //carico la lingua nel datamodel
        this.setLng(request, datamodel);

        //carico nome servlet
        this.datamodel.put("nameServlet","ListCourseAn");

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //carico nel datodel la lista dei corsi rimasti
        datamodel.put("listCourse", listCourse);

        //inserisco i docenti nel datamodel
        datamodel.put("listTheacher", userList);

        datamodel.put("allStudyCourse", allStudyCourse);

        System.out.println("allStudyCourse" + allStudyCourse);

        //lancio messaggio di errore
        //TemplateController.process("course_list_an.ftl", datamodel, response, getServletContext());
        this.processTemplate(request, response, "course_list_an", datamodel);

    }


    /**
     * Raccoglie parametri falla form,
     * estrae i corsi corrispondenti,
     * li inserisci nel datamodel,
     * infine lancia il template
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //inizializzo i dao
            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();

            List<Course> listCourse = null;

            //controllo se esiste l'anno accademico nel parametro get
            if(request.getParameter("year") != null && request.getParameter("year").length()!=0){

                //se esiste creo un anno accademico, nel caso in cui non sia un intero si genera un NumberFormatException (gestito)
                AccademicYear accademicYear = new AccademicYear(  Integer.parseInt(request.getParameter("year")));
                listCourse = courseDao.getCourseByYear(accademicYear.toString());

                accademicYear = null;


            }else{
                //se non presente anno accademico estraggo tuti i corsi
                listCourse = courseDao.getCourses();
            }



            UserDao userDao = new UserDaoImpl(ds);
            userDao.init();

            StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
            studyCourseDao.init();

            List<StudyCourse> allStudyCourse = studyCourseDao.getAllStudyCourses();

            System.out.println("allStudyCourse" + allStudyCourse);

            List<User> allTeachers = this.getAllTeacher(userDao, ds);

            String param;


            /*
                INIZIO SELEZIONE CORSI PER NOME (nome del corso)
             */
            if(request.getParameter("name") != null && request.getParameter("name").length()!=0) {

                //estaggo il nome della stringa che l'utente ha passato
                param = request.getParameter("name");

                //inizializzo iteratore
                Iterator <Course> itr = listCourse.iterator();

                //ciclo sulla lista
                while ( itr.hasNext() ) {

                    //prendo il prossimo corso
                    Course curr = itr.next();

                    //se il corso non metcha con il pattern lo elimino
                    if(!this.matchNome(param, curr.getName())){

                        //se non matcha lo rimuovo
                        itr.remove();
                    }
                }
            }
            /*
                FINE SELEZIONE CORSI PER NOME
             */


            /*
                INIZIO SELEZIONE CORSI PER CODICE
             */

            if(request.getParameter("code") != null && request.getParameter("code").length()!=0) {

                //estaggo il parametro passato
                param = request.getParameter("code");

                //inizializzo iteratore
                Iterator <Course> itr = listCourse.iterator();

                //ciclo sulla lista
                while (itr.hasNext()) {

                    //prendo il prossimo corso
                    Course curr = itr.next();

                    //se il corso non metcha con il pattern passato lo elimino
                    if( !curr.getCode().equals(param)){
                        itr.remove();
                    }
                }
            }
            /*
                FINE SELEZIONE CORSI PER CODICE
             */



            /*
                INIZIO SELEZIONE CORSI PER SETTORE
             */

            if(request.getParameter("sector") != null && request.getParameter("sector").length()!=0) {

                //estaggo il parametro passato
                param = request.getParameter("sector");

                //inizializzo iteratore
                Iterator <Course> itr = listCourse.iterator();

                //ciclo sulla lista
                while (itr.hasNext()) {

                    //prendo il prossimo corso
                    Course curr = itr.next();

                    //se il corso non metcha con il pattern passato setto match a false, altrimenti lo setto a true
                    if( !curr.getSector().equals(param)){
                        itr.remove();
                    }
                }
            }

            /*
                FINE SELEZIONE CORSI PER SETTORE
             */





            /*
                INIZIO SELEZIONE CORSI PER SEMESTRE
             */
            if(request.getParameter("semester") != null && request.getParameter("semester").length()!=0) {

                try {

                    int semester = Integer.parseInt(request.getParameter("semester"));

                    if (semester != 0 && semester <= 2) {

                        //estaggo il parametro passato
                        param = request.getParameter("semester");

                        //inizializzo iteratore
                        Iterator <Course> itr = listCourse.iterator();

                        //ciclo sulla lista
                        while (itr.hasNext()) {

                            //prendo il prossimo corso
                            Course curr = itr.next();

                            //se il corso non metcha con il pattern passato setto match a false, altrimenti lo setto a true
                            if ( !(curr.getSemester() == semester) ) {
                                itr.remove();
                            }
                        }
                    }

                }catch (NumberFormatException e){

                    //se il valore inserito non e' un numero non faccio nulla

                }
            }
            /*
                FINE SELEZIONE CORSI PER SEMESTRE
             */





            /*
                INIZIO SELEZIONE CORSI PER LINGUA
             */

            if(request.getParameter("language") != null && request.getParameter("language").length()!=0) {

                //estaggo il parametro passato
                param = request.getParameter("language");

                //inizializzo iteratore
                Iterator <Course> itr = listCourse.iterator();

                //ciclo sulla lista
                while (itr.hasNext()) {

                    //prendo il prossimo corso
                    Course curr = itr.next();

                    //se il corso non metcha con il pattern passato setto match a false, altrimenti lo setto a true
                    if( !curr.getLanguage().equals(param)){
                        itr.remove();
                    }
                }
            }
            /*
                FINE SELEZIONE CORSI PER LINGUA
             */







            /*
                INIZIO SELEZIONE CORSI PER DOCENTE
             */

            if(request.getParameter("docent") != null && request.getParameter("docent").length()!=0) { //doobiamo sistemare il fatto che id docente è un numero, con null non funzionano i numeri, usare tipo lo zero

                //estaggo il parametro passato
                int idDocentePassato = Integer.parseInt(request.getParameter("docent"));

                //inizializzo iteratore
                Iterator <Course> itr = listCourse.iterator();

                boolean match = false;

                //ciclo sulla lista
                while (itr.hasNext()) {

                    //prendo il prossimo corso
                    Course curr = itr.next();

                    //estraggo la lista dei docenti del corso corrente
                    List<User> listuser = userDao.getUserByCourse(curr);

                    //setto il match a false
                    match = false;

                    //ciclo sulla lista degli utenti collegati al corso corrente
                    for (User currUser : listuser) {

                        //se trovo il nome o cognome il professore lo setto a true, in modo ce non venga eliminato
                        //if (currUser.getName().equals(param)) match = true;
                        //if (currUser.getSurname().equals(param)) match = true;
                        /*if(this.matchNome(param, currUser.getName())) match = true;
                        if(this.matchNome(param, currUser.getSurname())) match = true;
                        if(this.matchNome(param, currUser.getSurname()+ " " + currUser.getName())) match = true;
                        if(this.matchNome(param, currUser.getName() + " " + currUser.getSurname())) match = true;
                        */

                        if( idDocentePassato==currUser.getId()) match = true;

                    }

                    //se match esce a false, l'elemento e' da eliminare
                    if(match==false){
                        itr.remove();
                    }
                }

            }
            /*
                FINE SELEZIONE CORSI PER DOCENTE
             */




            /*
                INIZIO SELEZIONE CORSI PER CORSO DI STUDI
             */

            if(request.getParameter("studyCourse") != null && request.getParameter("studyCourse").length()!=0) {

                int idStudyCourse = Integer.parseInt(request.getParameter("studyCourse"));

                //inizializzo iteratore
                Iterator <Course> itr = listCourse.iterator();

                boolean match;

                //ciclo sulla lista
                while (itr.hasNext()) {

                    //prendo il prossimo corso
                    Course curr = itr.next();

                    //estraggo i corsi di studi del corso corrente
                    List<StudyCourse> listStudyCourse = studyCourseDao.getStudyCourseByCourse(curr);

                    //setto il match a false
                    match = false;

                    //ciclo sulla lista dei corsi di studio
                    for(StudyCourse currStudyCourse : listStudyCourse){

                        //se l'attuale corso metcha con la stringa passata dall'user la variabile viene settata a true,
                        //in modo che al prossimo ciclo non venga eliminato
                        //if (currStudyCourse.getName().equals(param)) match = true;
                        if(idStudyCourse==currStudyCourse.getId()) match = true;

                    }

                    //se match esce a false, l'ultimo elemento e' da eliminare
                    if(match==false){
                        itr.remove();
                    }
                }

            }
            /*
                FINE SELEZIONE CORSI PER CORSO DI STUDI
             */

            //chiudo i dao
            courseDao.destroy();
            userDao.destroy();
            studyCourseDao.destroy();


            for(Course course: listCourse){

                System.out.println(course);

            }

            System.out.println();

            this.processTemplate(request,response,listCourse, allTeachers, allStudyCourse);



        } catch (DaoException | NumberFormatException e) {

            e.printStackTrace();

            //lancio template di errore
            this.processError(request, response);
        }



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    /**
     * Torna true se trova il path passato, false altrimenti,
     * non tiene conto di minuscole e maiuscole
     *
     */
    private boolean matchNome( String regex, String input){


        regex = regex.toLowerCase();
        input = input.toLowerCase();

        Pattern pattern1 = Pattern.compile(regex);
        Matcher matcher = pattern1.matcher(input);

        if(matcher.find()) return true;
        else return false;
    }


    /**
     * Estrae tutti i docenti
     * @param userDao
     * @param ds
     * @return
     * @throws DaoException
     */
    private List<User> getAllTeacher(UserDao userDao, DataSource ds) throws DaoException {

        GroupsDao groupsDao = new GroupsDaoImpl(ds);
        groupsDao.init();

        Groups groups = groupsDao.getGroupsByName("Docente");

        groupsDao.destroy();

        return userDao.getUserByGroups(groups);
    }



}
