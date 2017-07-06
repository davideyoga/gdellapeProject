package controller.adm;

import controller.BaseController;
import dao.implementation.GroupsDaoImpl;
import dao.interfaces.GroupsDao;
import model.Groups;
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
public class AdmGetListGroups extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione valida
        if(this.sessionManager.isValid(request)){

            //estraggo il servizio di creazione degli utenti
            Service modGroups = utilityManager.getServiceAndCreate(request,response,ds,"modGroups","Permissed for modification Groups",
                    datamodel, getServletContext());

            //se l'utente in sessione possiede il servizio modUser...
            if (((List<Service>) request.getSession().getAttribute("services")).contains(modGroups)) {

                //inizializzo la lista di tutti gli utenti
                List<Groups> groupsList = new ArrayList<>();

                try {

                    //inizializzo il dao dei gruppi
                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    groupsDao.init();

                    //estraggo tutti i gruppi
                    groupsList = groupsDao.getAllGroups();

                    //chiudo il dao
                    groupsDao.close();
                    groupsDao = null;

                } catch (Exception e) {
                    //in caso di dao exception ecc. lancio il template di errore
                    TemplateController.process("error.ftl", datamodel,response,getServletContext());
                }

                //lancio il template con gli utenti caricati
                datamodel.put("groups", groupsList);
                TemplateController.process( "groups_list.ftl", datamodel ,response, getServletContext() );


            } else {

                //lancio il messaggio di servizio non permesso
                TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );
            }


        }else{//se sessione non valida

            //setto la pagina precedente e reindirizzo al login
            createPreviousPageAndRedirectToLogin(request, response, "AdmGetListGroups");

        }
    }
}
