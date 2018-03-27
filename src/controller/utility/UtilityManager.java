package controller.utility;

import model.Course;
import model.Service;
import model.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public interface UtilityManager {

    void removePassword(User user);
    void removePassword( List<User> users);

    boolean isCorrectEmail(String email);

    int getPasswordLength();

    String getCurrentAccademicYear(Calendar calendar);

    int getDifferenceByAccademicAge(Course course, String accademicAge);

    String getAccademicYearByYear(int year);

    int getFirstParameterAccademicYear( String accademicYear);

    String sha1Encrypt(String x);

    /**
     * Torna oggetti presenti nella lista a e non presenti nella lista b
     * Per non stravolgere tutto gli passo una lista di utenti invece che un oggetto iterabile
     * @param a
     * @param b
     * @return
     */
    List<User> getContentInAButNotInB(List<User> a, List<User> b);

}
