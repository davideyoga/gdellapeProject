package controller.utility;

import model.Course;
import model.Service;

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

    boolean isCorrectEmail(String email);

    int getPasswordLength();

    String getCurrentAccademicYear(Calendar calendar);

    int getDifferenceByAccademicAge(Course course, String accademicAge);

    String getAccademicYearByYear(int year);

    int getFirstParameterAccademicYear( String accademicYear);


}
