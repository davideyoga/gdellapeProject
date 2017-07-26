package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Course;
import model.StudyCourse;
import model.User;

import java.util.List;

/**
 * Created by antonello on 25/05/17.
 */
public interface CourseDao extends DaoData{

    Course getCourse();
    Course getCourseById(int Id) throws DaoException;
    Course getCourseByName(String name) throws DaoException;
    Course getCourseByCode(String code) throws DaoException;
    Course getCourseByYear(String year) throws DaoException;
    Course getCourseByCfu(int cfu) throws DaoException;
    Course getCourseBySector(String sector) throws DaoException;
    Course getCourseByLanguage(String language) throws DaoException;
    Course getCourseBySemester(int semester) throws DaoException;
    Course storeCourse(Course course) throws DaoException;
    void deleteCourse(Course course) throws DaoException;

    List<Course> getCoursesByUser(User user) throws DaoException;

    List<Course> getCourseByStudyCourse(StudyCourse studyCourse) throws DaoException ;

    List<Course> getCourses() throws DaoException ;
}
