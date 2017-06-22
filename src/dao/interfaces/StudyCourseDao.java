package dao.interfaces;

import dao.exception.DaoException;
import model.Course;
import model.StudyCourse;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author Davide Micarelli
 */
public interface StudyCourseDao {

    void init()throws DaoException;

    StudyCourse getStudyCouse();

    StudyCourse getStudyCourseById( int id) throws DaoException;

    StudyCourse getStudyCourseByCode( String code) throws DaoException;

    StudyCourse getStudyCourseByName( String name) throws DaoException;

    void storeStudyCourse( StudyCourse studyCourse) throws DaoException;

    void deleteStudyCourse(StudyCourse studyCourse) throws DaoException;

    StudyCourse generateStudyCourse(ResultSet rs ) throws DaoException;

    List<StudyCourse> getStudyCourseByCourse(Course course)throws DaoException;

    void destroy() throws DaoException;
}
