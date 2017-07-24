package controller.adm;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.StudyCourseDaoImpl;
import dao.interfaces.StudyCourseDao;
import model.Service;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public class DeleteStudyCourse extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {

            //se la sessione e' valida
            if (sessionManager.isValid(request)) {

                //estraggo il servizio di eliminazione dei gruppi
                Service deleteGroups = this.getServiceAndCreate(request, response, ds, "deleteStudyCourse", "Permissed for delete Study Course",
                        datamodel, getServletContext());

                //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(deleteGroups)) {

                    //inizializzo il dao del corso di studi
                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();

                    //se non e' presente nei parametri GET l'id del corso di studi
                    if( request.getParameter("id") == null){

                        //rimando alla lista dei corsi di studio
                        response.sendRedirect("AdmGetListStudyCourse");
                        return;

                    }

                    //estraggo il corso di studi in base all'id passato tramite parametro GET e lo elimino
                    studyCourseDao.deleteStudyCourse(studyCourseDao.getStudyCourseById(Integer.parseInt(request.getParameter("id"))));

                    //chiudo il dao
                    studyCourseDao.destroy();

                    //reindirizzo verso la servlet che si occupa di restituire la lista dei corsi di studio
                    response.sendRedirect("AdmGetListStudyCourse");

                    //se non ha i permessi
                } else {
                    //lancio il template di non permesso
                    TemplateController.process("not_permissed.ftl", datamodel, response, getServletContext());
                }
                //se session non valida o non abbastanza nuova
            } else {

                //setto la pagina alla lista degli utenti in quanto questa chiamata avviene tramite GET
                createPreviousPageAndRedirectToLogin(request, response, "AdmGetListStudyCourse");
            }

        } catch (DaoException e) {
        e.printStackTrace();

        //lancio template di errore
        TemplateController.process("error.ftl", datamodel, response, getServletContext());

        } catch (IOException e) {
            //errore generato dalla response.sendRedirect()

            e.printStackTrace();
        }
    }
}
