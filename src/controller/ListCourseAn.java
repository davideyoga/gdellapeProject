package controller;

import controller.utility.AccademicYear;
import controller.utility.AccademicYearException;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.implementation.StudyCourseDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.interfaces.CourseDao;
import dao.interfaces.StudyCourseDao;
import dao.interfaces.UserDao;
import model.Course;
import model.StudyCourse;
import model.User;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
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

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, List<Course> listCourse){

        //carico nel datodel la lista dei corsi rimasti
        datamodel.put("listCourse", listCourse);

        //lancio messaggio di errore
        TemplateController.process("home.ftl", datamodel, response, getServletContext());

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

            AccademicYear accademicYear = null;

            //controllo se esiste l'anno accademico nel parametro get
            if(request.getParameter("year") != null){

                //se esiste creo un anno accademico, nel caso in cui non sia un intero si genera un NumberFormatException (gestito)
                accademicYear = new AccademicYear(  Integer.parseInt(request.getParameter("year")));


            }else{
                //inizilizza l'anno accademico come quello corrente
                accademicYear = new AccademicYear( Calendar.getInstance() );
            }

            //inizializzo i dao
            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();

            UserDao userDao = new UserDaoImpl(ds);
            userDao.init();

            StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
            studyCourseDao.init();


            //estraggo i corsi dell'anno di accademicYear
            List<Course> listCourse = courseDao.getCourseByYear(accademicYear.toString());



            /*
                INIZIO SELEZIONE CORSI PER NOME
             */

            //inizilizzo un booleano per capire se un corso metcha con il nome passato
            boolean match = true;
            int pos = 0;

            //elimino corsi che non mecciano con il path name
            String regex = request.getParameter("name");
            if(regex != null) {

                List<Integer> listDelete = new LinkedList <>();

                //ciclo sulla lista dei corsi
                for (Course curr : listCourse) {

                    //se l'attuale corso non metcha con la stringa passata dall'user, inserisco l'indice dell'elemento da cancellare
                    if (!this.matchNome(regex, curr.getName())){
                        listDelete.add(pos);
                        System.out.println("Ho aggiunto " + pos + ". Corso: " + curr);
                    }

                    //aumento la posizione
                    pos++;

                }

                for(Integer curr: listDelete){

                    System.out.println("Sto per rimuovere: " +listCourse.get(curr));


                    listCourse.remove(curr.intValue());

                    System.out.println("Ho rimosso corso in posizione " + curr);


                }
            }
            /*
                FINE SELEZIONE CORSI PER NOME
             */



            /*
                INIZIO SELEZIONE CORSI PER CODICE
             */
            String code = request.getParameter("code");

            //se l'utente ha inserito il codice filtro la losta per il codice
            if(code != null) {

                match = true;
                pos=0;

                for(Course curr: listCourse){

                    //se la varibile match e' a false vuol dire che il corso precedente non metchava con
                    //il codice passato con l'utente
                    if(match == false) listCourse.remove(pos);

                    //se l'attuale corso metcha con la stringa passata dall'user la variabile viene settata a true,
                    //in modo che al prossimo ciclo non venga eliminato
                    if ( curr.getCode().equals(code)) match = true;

                        //altrimenti la setto a false ijn modo che venga eliminata
                    else {
                        match = false;
                    }
                    //aumento la posizione
                    pos++;
                }
            }
            /*
                FINE SELEZIONE CORSI PER CODICE
             */



            /*
                INIZIO SELEZIONE CORSI PER SETTORE
             */

            String sector = request.getParameter("sector");

            //se l'utente ha inserito il codice filtro la losta per il codice
            if(sector != null) {

                match = true;
                pos=0;

                for(Course curr: listCourse){

                    //se la varibile match e' a false vuol dire che il corso precedente non metchava con
                    //il codice passato con l'utente
                    if(match == false) listCourse.remove(pos);

                    //se l'attuale corso metcha con la stringa passata dall'user la variabile viene settata a true,
                    //in modo che al prossimo ciclo non venga eliminato
                    if ( curr.getSector().equals(sector)) match = true;

                        //altrimenti la setto a false in modo che venga eliminata
                    else {
                        match = false;
                    }
                    //aumento la posizione
                    pos++;
                }
            }

            /*
                FINE SELEZIONE CORSI PER SETTORE
             */





            /*
                INIZIO SELEZIONE CORSI PER SEMESTRE
             */

            if(request.getParameter("semester") != null) {

                int semester = Integer.parseInt(request.getParameter("semester"));

                //se l'utente ha inserito il codice filtro la lista per il codice
                if (semester != 0 && semester <= 2) {

                    match = true;
                    pos = 0;

                    for (Course curr : listCourse) {

                        //se la varibile match e' a false vuol dire che il corso precedente non metchava con
                        //il codice passato con l'utente
                        if (match == false) listCourse.remove(pos);

                        //se l'attuale corso metcha con la stringa passata dall'user la variabile viene settata a true,
                        //in modo che al prossimo ciclo non venga eliminato
                        if (curr.getSemester() == semester) match = true;

                            //altrimenti la setto a false ijn modo che venga eliminata
                        else {
                            match = false;
                        }
                        //aumento la posizione
                        pos++;
                    }
                }
            }

            /*
                FINE SELEZIONE CORSI PER SEMESTRE
             */

            /*
                INIZIO SELEZIONE CORSI PER LINGUA
             */
            String language = request.getParameter("semester");

            //se l'utente ha inserito la lingua filtro la lista per il codice
            if(language != null) {

                match = true;
                pos=0;

                for(Course curr: listCourse){

                    //se la varibile match e' a false vuol dire che il corso precedente non metchava con
                    //il codice passato con l'utente
                    if(match == false) listCourse.remove(pos);

                    //se l'attuale corso metcha con la stringa passata dall'user la variabile viene settata a true,
                    //in modo che al prossimo ciclo non venga eliminato
                    if ( curr.getLanguage() == language) match = true;

                        //altrimenti la setto a false ijn modo che venga eliminata
                    else {
                        match = false;
                    }
                    //aumento la posizione
                    pos++;
                }
            }

            /*
                FINE SELEZIONE CORSI PER LINGUA
             */







            /*
                INIZIO SELEZIONE CORSI PER DOCENTE
             */

            //controllo se e' stato passato il nome di un docente tramite parametro get
            if(request.getParameter("user")!=null){

                match = true;
                pos=0;

                for(Course curr: listCourse){

                    //se la varibile match e' a false vuol dire che il corso precedente non metchava con
                    //il codice passato con l'utente
                    if(match == false) listCourse.remove(pos);

                    //estraggo la lista dei docenti del corso corrente
                    List<User> listuser = userDao.getUserByCourse(curr);

                    //setto il match a false
                    match = false;

                    //ciclo sulla lista degli utenti collegati al corso corrente
                    for (User currUser : listuser) {

                        //se l'attuale corso metcha con la stringa passata dall'user la variabile viene settata a true,
                        //in modo che al prossimo ciclo non venga eliminato
                        if (currUser.getName().equals(request.getParameter("user"))) match = true;

                    }
                    //se esco senza aver trovato un utente collegato al corso il match rimane a false
                    //e quindi viene eliminato

                    //aumento la posizione del corso che potrebbe essere eliminato
                    pos++;
                }

            }


            /*
                FINE SELEZIONE CORSI PER DOCENTE
             */




            /*
                INIZIO SELEZIONE CORSI PER CORSO DI STUDI
             */

            //controllo se e' stato passato il nome di un corso di studi tramite parametro get
            if(request.getParameter("studyCourse")!=null){

                match = true;
                pos=0;

                //ciclo sulla lista dei corsi di studi rimasti
                for(Course curr: listCourse){

                    //se la varibile match e' a false vuol dire che il corso precedente non metchava con
                    //il codice passato con l'utente
                    if(match == false) listCourse.remove(pos);

                    //estraggo i corsi di studi del corso corrente
                    List<StudyCourse> listStudyCourse = studyCourseDao.getStudyCourseByCourse(curr);

                    //setto il match a false
                    match = false;

                    //ciclo sulla lista dei corsi di studio
                    for(StudyCourse currStudyCourse : listStudyCourse){

                        //se l'attuale corso metcha con la stringa passata dall'user la variabile viene settata a true,
                        //in modo che al prossimo ciclo non venga eliminato
                        if (currStudyCourse.getName().equals(request.getParameter("studyCourse"))) match = true;

                    }

                    //se esco senza aver trovato un corso di studi collegato al corso il match rimane a false
                    //e quindi viene eliminato

                    //aumento la posizione del corso che potrebbe essere eliminato
                    pos++;

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




        } catch (DaoException | NumberFormatException e) {

            e.printStackTrace();

            //lancio template di errore
            TemplateController.process("error.ftl", datamodel, response, getServletContext());
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
     * Torna true se trova il path passato, false altrimenti
     *
     */
    private boolean matchNome( String regex, String input){

        //String regex = "(inn)";
        //String input = "informatica";

        Pattern pattern1 = Pattern.compile(regex);
        Matcher matcher = pattern1.matcher(input);

        if(matcher.find()) return true;
        else return false;
    }
}
