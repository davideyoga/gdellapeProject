package controller;

import controller.utility.AccademicYear;
import controller.utility.AccademicYearException;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;
import model.Course;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
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
public class ListCourse extends BaseController {

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response){



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

            //inizializzo il datamodel
            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();

            //estraggo i corsi dell'anno di accademicYear
            List<Course> listCourse = courseDao.getCourseByYear(accademicYear.toString());

            /*
                UNA VOLTA OTTENUTA LA LISTA DEI CORSI PER ANNO, SI ELIMINANO I CORSI CHE NON CORRISPONDONO A QUELLI SETTATI NELLA FORM

                NOME (ANCHE PARZIALE) = DA USARE UNA ESPRESSIONE REGOLARE
                CODICE
                SSD
                SEMESTRE
                DOCENTE
                LINGUA
                CORSO DI LAUREA IN CUI VIENE EROGATO
             */


            courseDao.destroy();

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
     * Restituisce lista dei corsi che metchano con il name passato
     *
     */
    private List<Course> getCourseByName( List<Course> listCourse, String name){

        // String to be scanned to find the pattern.
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);

        return listCourse;
    }
}
