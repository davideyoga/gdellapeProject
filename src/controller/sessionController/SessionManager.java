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

    HttpSession getSessionWithGroupsAndService( HttpServletRequest request, DataSource ds ) throws SessionException;

    HttpSession getSessionWithGroups( HttpServletRequest request, DataSource ds ) throws SessionException;

    HttpSession getSessionWithService( HttpServletRequest request, DataSource ds ) throws SessionException;

    boolean isPermissed( HttpServletRequest request, Service service) throws SessionException;

    boolean isValid(  HttpServletRequest request);

    boolean isHardValid(HttpServletRequest request);

    HttpSession setPreviusPage(HttpServletRequest request, String previusPage) throws SessionException ;

    String getPreviusPage(HttpServletRequest request);

    void destroySession(HttpServletRequest request);


}
