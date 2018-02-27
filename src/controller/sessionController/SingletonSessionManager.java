package controller.sessionController;

import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.ServiceDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.ServiceDao;
import model.Groups;
import model.Service;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Davide Micarelli
 * MODIFICARE CIO' CHE SUCCEDE IN CASO DI DaoException
 */
public class SingletonSessionManager implements SessionManager {

    //durata validità sessione in minuti
    private static final int SESSION_EXPIRE_TIME = 60*10;

    private static SingletonSessionManager sessionManager;

    private SingletonSessionManager(){}

    /**
     * Singleton constructor
     */
    public static SingletonSessionManager getSessionManager(){

        if(sessionManager == null){
            sessionManager = new SingletonSessionManager();
        }

        return sessionManager;
    }



    /**
     * Se esiste sessione nella HttpServletRequest passata la distruggo e la ricreo,
     * inserendo l'utente e la data, ritorna la sessione appena creata.
     * @param request request per estrarre una sessione
     * @param user user che, se non gia presente in sessione, viene inserito nella sessione
     * @return HttpSession con sessio_start e user
     */
    public HttpSession initSession(HttpServletRequest request, User user, DataSource ds ){

        //se la sessione esiste la distruggo
        this.destroySession(request);

        //creo la sessione
        HttpSession session = request.getSession(true);


        /*
            PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO
         */
        user.setPassword(null);
        /*
            PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO PERICOLO
         */


        //carico i dati nella sessione
        if(user != null){
            session.setAttribute("user", user); //carico user
        }
        session.setAttribute("ip_address", request.getRemoteHost()); //carico l'ip dell'user
        session.setAttribute("session_start", Calendar.getInstance()); //carico la data della sessione

        //carico la lingua predefinita
        session.setAttribute("language", "IT" );

        return session;
    }

    /**
     * Data una request, si prende la sessione, se non esiste si crea, gli viene assegnata la lingua base
     * @param request
     * @param ds
     * @return
     */
    @Override
    public HttpSession initLanguageSession(HttpServletRequest request, DataSource ds) {

        //creo la sessione
        HttpSession session = this.getSession(request);

        //carico la lingua predefinita
        session.setAttribute("language", "IT" );

        return session;
    }

    @Override
    public HttpSession getSession(HttpServletRequest request) {
        return request.getSession(true);
    }

    /**
     * Aggiungo alla sessione nella HttpServletRequest passata
     * i gruppi e i servizi dell'utente nella sessione.
     * Se la HTTPServletRequest passata non e' valita lancia un'eccezzione.
     * @param request
     * @param ds datasource
     * @return sessione con gruppi e servizi caricati
     * @throws SessionException
     */
    public HttpSession getSessionWithGroupsAndService( HttpServletRequest request, DataSource ds ) throws SessionException{

        //se sessione non valida
        if( !this.isValid(request)){
            throw new SessionException("Not valid session");
        }

        HttpSession session = request.getSession();

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

        //carico in sessione i servizi a cui ha accesso l'utente
        ServiceDao serviceDao = new ServiceDaoImpl(ds);

        List<Service> listService = new ArrayList <>();
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
     * Carica in sessione i gruppi a cui l'utente in sessione fa parte.
     * Se la HTTPServletRequest passata non e' valita lancia un'eccezzione.
     * @param request
     * @param ds datasource
     * @return sessione con gruppi e servizi caricati in base all'utente in sessione
     * @throws SessionException
     */
    public HttpSession getSessionWithGroups( HttpServletRequest request, DataSource ds ) throws SessionException{

        //se sessione non valida
        if( !this.isValid(request)){
            throw new SessionException("Not valid session");
        }

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

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
     * Se la HTTPServletRequest passata non e' valita lancia un'eccezzione.
     * @param request
     * @param ds datasource
     * @return sessione con i servizi in base ai gruppi che si trovano in sessione, null se non ci sono in sessione utente e gruppi
     */
    public HttpSession getSessionWithService( HttpServletRequest request, DataSource ds ) throws SessionException{

        //se sessione non valida
        if( !this.isValid(request)){
            throw new SessionException("Not valid session");
        }

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        //se non ho caricato l'utente o i gruppi dell'utente in sessione
        if( session.getAttribute("groups") == null) throw new SessionException("Not present groups");

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
    public boolean isPermissed( HttpServletRequest request, Service service) throws SessionException{

        //se sessione non valida
        if( !this.isValid(request)){
            throw new SessionException("Not valid session");
        }

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

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
    public boolean isValid(  HttpServletRequest request){

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        //se i dati della sessione sono corretti
        if(     session != null &&
                session.getAttribute("user") != null &&
                session.getAttribute("ip_address") != null &&
                session.getAttribute("session_start") != null &&
                session.getAttribute("ip_address").equals( request.getRemoteHost()  )){

            return true;

        //se i dati in sessione sono scorretti
        }else{

            return false;
        }
    }

    /**
     * Torna true se ci sono i dati in sessione, se l'ip corrisponde a quello della richiesta
     * e se la sessione non e' vecchia, false altrimenti
     * @return
     */
    public boolean isHardValid(HttpServletRequest request){

        //estraggo la sessione dalla richiesta
        HttpSession session = request.getSession(false);

        //se la sessione non e' attiva
        if( session == null ) return false;

        //se ci sono i dati  in sessione, se l'ip corrisponde a quello della richiesta
        if( this.isValid(request) ){

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
     * Setta l'attributo con la pagina precedente in cui si rovava l'utente
     * @param request
     * @param previusPage
     * @return sessione ricaricata con la pagina precedente
     * @throws SessionException
     */
    public HttpSession setPreviusPage(HttpServletRequest request, String previusPage) throws SessionException {

        //se non esiste la sessione la creo
        request.getSession(true);

        //setto la pagina precedentemente visitata
        request.getSession().setAttribute("previusPage", previusPage);

        return request.getSession();
    }

    /**
     * Torna la pagina precedentemente visitata
     * @param request
     * @return
     */
    public String getPreviusPage(HttpServletRequest request){

        return (String) request.getSession(true).getAttribute("previusPage");
    }

    /**
     * distrugge la sessione se esiste
     *
     * @param request richiesta servlet
     */
    public void destroySession(HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        //se la sessione esiste la elimino
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }else{
            session = null;
        }
    }

    @Override
    public User getUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("user");
    }


    @Override
    public void setUser(HttpServletRequest request, User user) {

        request.getSession().setAttribute("user", user);

    }


}


