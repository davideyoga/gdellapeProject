package controller.sessionController;

import model.Service;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * @author Davide Micarelli
 */
public interface SessionManager {

    HttpSession initSession(HttpServletRequest request, User user, DataSource ds );

    /**
     * Sessione per utenti base, non vengono caricati dati se non la lingua scelta e poco altro
     * @param request
     * @param ds
     * @return
     */
    HttpSession initLanguageSession(HttpServletRequest request, DataSource ds );

    /**
     * Trona sessione, se non esiste la crea
     * @param request
     * @return
     */
    HttpSession getSession(HttpServletRequest request);

    HttpSession getSessionWithGroupsAndService( HttpServletRequest request, DataSource ds ) throws SessionException;

    HttpSession getSessionWithGroups( HttpServletRequest request, DataSource ds ) throws SessionException;

    HttpSession getSessionWithService( HttpServletRequest request, DataSource ds ) throws SessionException;

    boolean isPermissed( HttpServletRequest request, Service service) throws SessionException;

    boolean isValid(  HttpServletRequest request);

    boolean isHardValid(HttpServletRequest request);

    HttpSession setPreviusPage(HttpServletRequest request, String previusPage) throws SessionException ;

    String getPreviusPage(HttpServletRequest request);

    void destroySession(HttpServletRequest request);

    /**
     * Torna utente in sessione
     * @return
     */
    User getUser(HttpServletRequest request);

    void setUser(HttpServletRequest request, User user);

}
