package controller.session;

import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.ServiceDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.ServiceDao;
import model.Groups;
import model.Service;
import model.User;
import view.TemplateController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Davide Micarelli
 */
public class SessionManager {

    //durata validità sessione in minuti
    private static final int SESSION_EXPIRE_TIME = 60*10;

    /**
     * Torna sessione con user e vari parametri
     * @param request request per estrarre una sessione
     * @param user user che, se non gia presente in sessione, viene inserito nella sessione
     * @return HttpSession con sessio_start e user
     */
    public static HttpSession initSession(HttpServletRequest request, User user, DataSource ds ){

        //creo la sessione o la prendo da quella gia presente
        HttpSession session = request.getSession(true);

        //carico i dati nella sessione
        if(user != null){
            session.setAttribute("user", user); //carico user
        }
        session.setAttribute("ip_address", request.getRemoteHost()); //carico l'ip dell'user
        session.setAttribute("session_start", Calendar.getInstance()); //carico la data della sessione

        return session;
    }

    /**
     * Aggiunge alla sessione passata che contiene un utente e gli aggiunge i gruppi e i servizi,
     * se la sessione non esiste ed i dati non sono corretti torna null
     * @param request
     * @param ds datasource
     * @return
     */
    public static HttpSession getSessionWithGroupsAndService( HttpServletRequest request, DataSource ds ){

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        System.out.println("passo 1a");

        //se la sessione non e' attiva
        if( session == null ) return null;

        System.out.println("passo 2a");

        //carico in sessione i gruppi dell'utente
        GroupsDao groupsDao = new GroupsDaoImpl(ds);

        System.out.println("passo 3a");

        List<Groups> listGroups = null; //dichiaro la lista dei gruppi
        try {

            System.out.println("passo 4a");

            groupsDao.init(); // inizializzo il dao

            System.out.println("passo 5a");

            //se non ho caricato in sessione l'utente torna null
            if(session.getAttribute("user") == null) return null;

            System.out.println("passo 6a");

            // estraggo tutti i gruppi a cui appartiene l'utente settato nella sessione
            listGroups = groupsDao.getGroupsByUser((User) session.getAttribute("user") );


            System.out.println("passo 7a");

            session.setAttribute("groups", listGroups ); // aggiungo alla sessione i gruppi a cui appartiene l'utente

            System.out.println("passo 8a");

            groupsDao.destroy(); // chiudo groupsDao

            System.out.println("passo 9a");

        } catch (DaoException e) {
            e.printStackTrace();
        }

        System.out.println("passo 10a");

        //carico in sessione i servizi a cui ha accesso l'utente
        ServiceDao serviceDao = new ServiceDaoImpl(ds);

        System.out.println("passo 11a");

        List<Service> listService = new ArrayList <>();

        System.out.println("passo 12a");

        try {

            serviceDao.init(); //inizializzo il dao del service

            for( Groups groups : listGroups ) { // ciclo sulla lista dei gruppi

                List<Service> listService2 = new ArrayList <>();

                listService2 = serviceDao.getServicesByGroup(groups);

                // estraggo i servizi a cui il gruppo ha accesso e li aggiungo alla lista
                listService.addAll(listService2);
            }

            serviceDao.destroy(); // chiudo serviceDao

        } catch (DaoException e) {
            e.printStackTrace();
        }

        // aggiungo alla sessione la lista dei servizi
        session.setAttribute("services", listService);

        return session;
    }

    /**
     * Carica in sessione i gruppi a cui l'utente in sessione fa parte,
     * se non esiste o i dati sono scorretti torna null
     * @param request
     * @param ds datasource
     * @return sessione con gruppi e servizi caricati in base all'utente in sessione
     */
    public static HttpSession getSessionWithGroups( HttpServletRequest request, DataSource ds ){

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        //se la sessione non e' attiva
        if( session == null ) return null;

        //carico in sessione i gruppi dell'utente
        GroupsDao groupsDao = new GroupsDaoImpl(ds);

        List<Groups> listGroups = null; //dichiaro la lista dei gruppi
        try {

            groupsDao.init(); // inizializzo il dao

            //se non ho caricato in sessione l'utente torna null
            if(session.getAttribute("user") == null) return null;

            // estraggo tutti i gruppi a cui appartiene l'utente settato nella sessione
            listGroups = groupsDao.getGroupsByUser((User) session.getAttribute("user") );

            session.setAttribute("groups", listGroups ); // aggiungo alla sessione i gruppi a cui appartiene l'utente

            groupsDao.destroy(); // chiudo groupsDao

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return session;
    }

    /**
     * Carica in sessione i servizi a cui l'utente (in sessione) fa parte,
     * se non esiste o i dati sono scorretti torna null
     * @param request
     * @param ds datasource
     * @return sessione con i servizi in base ai gruppi che si trovano in sessione, null se non ci sono in sessione utente e gruppi
     */
    public static HttpSession getSessionWithService( HttpServletRequest request, DataSource ds ){

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        //se la sessione non e' attiva
        if( session == null ) return null;

        //torna null se non ho caricato l'utente o i gruppi dell'utente in sessione
        if( session.getAttribute("user") == null && session.getAttribute("groups") == null) return null;

        //carico in sessione i servizi a cui ha accesso l'utente
        ServiceDao serviceDao = new ServiceDaoImpl(ds);

        List<Service> listService = null;

        // estraggo un puntatore alla lista dei gruppi in sessione
        List<Groups> listGroups = (List <Groups>) session.getAttribute("groups");

        try {
            serviceDao.init();

            for( Groups groups : listGroups ) { // ciclo sulla lista dei gruppi

                // estraggo i servizi a cui il gruppo ha accesso e li aggiungo alla lista
                listService.addAll(serviceDao.getServicesByGroup(groups));
            }

            serviceDao.destroy(); // chiudo serviceDao

        } catch (DaoException e) {
            e.printStackTrace();
        }

        // aggiungo alla sessione la lista dei servizi
        session.setAttribute("services", listService);

        return session;
    }

    /**
     * Torna true se il servizio e' nella lista dei servizi inseriti in sessione,
     * se la sessione non esiste o i dati sono scorretti torna false
     * @param request
     * @param service servizio da confrontare con quelli in sessione
     * @return true se service e' tra la lista di servizi in sessione
     */
    public static boolean isPermissed( HttpServletRequest request, Service service){

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        //se la sessione non e' attiva
        if( session == null ) return false;

        if( session.getAttribute("services")!= null ) { // se si e' caricata la lista dei servizi nella sessione

            //estraggo la lista di servizi dalla sessione
            List <Service> listService = (List <Service>) session.getAttribute("services");

            for( Service s : listService ){

                //se il serivzio nella lista e' = a quello passato al metodo torna true
                if( s.equals(service)) return true;
            }

            return false;

        }else return false; // non ho caricato i servizi in sessione
    }


    /**
     * Torna true se ci sono i dati in sessione e se l'ip corrisponde a quello della richiesta e la sessione esiste
     * false altrimenti,
     * @param request
     * @return
     */
    public static boolean isValid(  HttpServletRequest request){

        System.out.println("lanciato metodo isValid");

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        //se i dati della sessione sono corretti
        if(     session != null &&
                session.getAttribute("user") != null &&
                session.getAttribute("ip_address") != null &&
                session.getAttribute("session_start") != null &&
                session.getAttribute("ip_address").equals( request.getRemoteHost()  )){

            System.out.println("isValid torna true");

            return true;

        //se i dati in sessione sono scorretti
        }else{

            System.out.println("isValid torna false");
            return false;
        }
    }

    /**
     * Torna true se ci sono i dati in sessione, se l'ip corrisponde a quello della richiesta
     * e se la sessione non e' vecchia, false altrimenti
     * @return
     */
    public static boolean isHardValid(HttpServletRequest request){

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        //se la sessione non e' attiva
        if( session == null ) return false;

        //se ci sono i dati  in sessione, se l'ip corrisponde a quello della richiesta
        if( isValid(request) ){

            // prendo la data attuale
            Calendar now = Calendar.getInstance();

            //prendo la data in sessione
            Calendar start = (Calendar) session.getAttribute("session_start");

            //estraggo la differenza tra le due date
            long sessionAgeInMinutes = ((now.getTimeInMillis() - start.getTimeInMillis()) / 1000) / 30;

            //se la sessione e' abbastanza nuova
            if (sessionAgeInMinutes < SESSION_EXPIRE_TIME) {
                return true;
            }
        }
        //se una delle due condizioni sono false
        return false;
    }

    /**
     * distrugge la sessione se esiste
     *
     * @param request richiesta servlet
     */
    public static void destroySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        //se la sessione esiste la elimino
        if (session != null) {
            session.invalidate();
        }
    }

}


