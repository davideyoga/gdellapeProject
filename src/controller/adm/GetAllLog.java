package controller.adm;

import controller.BaseController;
import dao.implementation.LogDaoImpl;
import dao.interfaces.LogDao;
import model.Log;
import model.Service;
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
public class GetAllLog extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    /**
     * Visualizza tutti i log
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione valida
        if(this.sessionManager.isValid(request)){

            //estraggo il servizio di visualizzazzione dei log
            Service logView = this.getServiceAndCreate(request,response,ds,"logView","Permissed for view the log",
                    datamodel, getServletContext());

            //se l'utente in sessione possiede il servizio viewLog...
            if (((List<Service>) request.getSession().getAttribute("services")).contains(logView)) {

                //inizializzo la lista di tutti i log
                List<Log> logList = new ArrayList<>();

                try {

                    //inizializzo il dao dei log
                    LogDao logDao = new LogDaoImpl(ds);
                    logDao.init();

                    //estraggo tutti i log
                    logList = logDao.getAllLog();

                    //chiudo il dao
                    logDao.close();
                    logDao = null;

                } catch (Exception e) {
                    e.printStackTrace();
                    //in caso di dao exception ecc. lancio il template di errore
                    this.processError(request, response);
                }

                //lancio il template con gli utenti caricati
                datamodel.put("logs", logList);

                //setto l'utente in sessione
                this.datamodel.put("user", sessionManager.getUser(request));

                TemplateController.process( "log_list.ftl", datamodel ,response, getServletContext() );


            } else {

                //lancio il messaggio di servizio non permesso
                this.processNotPermitted(request, response);
            }


        }else{//se sessione non valida

            //setto la pagina precedente e reindirizzo al login
            createPreviousPageAndRedirectToLogin(request, response, "GetAllLog");

        }

    }
}
