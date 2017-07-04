package controller.adm;

import com.sun.xml.internal.bind.v2.model.core.ID;
import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.UserDao;
import model.Groups;
import model.Service;
import model.User;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
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
public class AdmModUser extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    /**
     * Riceve da AdmGetListuser i parametri get dell'utente
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se la sessione e' valida
        if(sessionManager.isValid(request)){

            //estraggo il servizio di creazione degli utenti
            Service modUser = utilityManager.getServiceAndCreate(request,response,ds,"modUser","Permissed for modification User",
                    datamodel, getServletContext());

            //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
            if (((List<Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                /*
                 *carico l'utente richiesto nel datamodel e lancio il template
                 */


                try {
                    //inizializzo i dao
                    UserDao userDao = new UserDaoImpl(ds);
                    userDao.init();

                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    groupsDao.init();

                    //estraggo l'id dell'utente passato trmaite get
                    int idUser = Integer.parseInt(request.getParameter("id"));

                    //estraggo l'utente tramite l'id passato dalla richiesta get
                    User user = userDao.getUserById( idUser);

                    //estraggo i gruppi a cui appartiene l'utente
                    List<Groups> listUserGroups = groupsDao.getGroupsByUser(user);

                    //estraggo tutti i gruppi
                    List<Groups> listGroups = groupsDao.getAllGroups();

                    //carico nel template l'utente
                    datamodel.put("user", user);

                    //carico i gruppi a cui appartiene l'utente
                    datamodel.put("listUserGroups", listUserGroups);

                    //carico tutti i gruppi presenti nel sistema
                    datamodel.put("listGroups", listGroups);

                    //chiudo i dao
                    userDao.destroy();
                    userDao = null;

                    groupsDao.destroy();
                    groupsDao= null;

                    //lancio il template di modifica dell'utente
                    TemplateController.process("user_mod.ftl", datamodel,response,getServletContext());

                } catch (DaoException e) {
                    //in caso di dao exception ecc. lancio il template di errore
                    TemplateController.process("error.ftl", datamodel,response,getServletContext());
                }


                //se l'utente in sessione non ha i permessi
            }else{

                //lancio il template di non permesso
                TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );
            }
        //se la sessione non e' valida
        }else{

            //setto la pagina alla lista degli utenti in quanto questa chiamata avviene tramite GET
            createPreviousPageAndRedirectToLogin(request,response,"AdmGetListUser");

        }

    }
}
