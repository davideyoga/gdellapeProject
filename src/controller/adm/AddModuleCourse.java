package controller.adm;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;
import model.Course;
import model.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Davide Micarelli
 * DA NON USARE!!!    DA NON USARE!!!    DA NON USARE!!!    DA NON USARE!!!    DA NON USARE!!!    DA NON USARE!!!    DA NON USARE!!!    DA NON USARE!!!
 */
public class AddModuleCourse extends BaseController {


    /**
     * Prende parametri get di id corso mutuato e id corso mutuante e aggiunge la connessione
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

        try {
            //pulisco messaggio
            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service addModule = this.getServiceAndCreate(request, response, ds, "addModule", "Permission for adding module course",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio modGroups...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(addModule)) {

                    //se ho gli id in nell'array GET
                    if(request.getParameter("idCoursoModulato") != null && request.getParameter("idCorsoDaModulare") != null){

                        //inizializzo il dao dei corsi
                        CourseDao courseDao = new CourseDaoImpl(ds);
                        courseDao.init();

                        //estraggo i corsi tramite id nell'array get
                        Course coursoDaModulare = courseDao.getCourseById(Integer.parseInt(request.getParameter("idCorsoDaModulare")));
                        Course corsoModulato = courseDao.getCourseById(Integer.parseInt(request.getParameter("idCoursoModulato")));

                        //se i corsi esistono
                        if(corsoModulato != null && coursoDaModulare != null ){

                            //controllo se


                            //se i corsi estratti non esistono
                        }else{

                        }

                        //se no ho gli id nei parametri get
                    }else{

                    }

                    //se non ho il permesso
                } else {

                }
                //se non ho la sessione abbastanza nuova
            } else {

            }

        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }
}
