package dao.interfaces;

import controller.utility.AccademicYear;
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
    List<Course> getCourseByYear(String year) throws DaoException;
    List<Course> getCourseByCfu(int cfu) throws DaoException;
    Course getCourseBySector(String sector) throws DaoException;
    Course getCourseByLanguage(String language) throws DaoException;
    Course getCourseBySemester(int semester) throws DaoException;
    Course storeCourse(Course course) throws DaoException;
    void deleteCourse(Course course) throws DaoException;

    List<Course> getCoursesByCode(String code) throws DaoException;

    List<Course> getCoursesByUser(User user) throws DaoException;

    Course getCoursesByCodeAndYear(String code, AccademicYear accademicYear) throws DaoException;

    List<Course> getCoursesByUserAndYear(User user, String year) throws DaoException;

    List<Course> getCourseByStudyCourse(StudyCourse studyCourse) throws DaoException ;

    List<Course> getCourseByStudyCourseAndYear(StudyCourse studyCourse, String year) throws DaoException;

    List<Course> getCourses() throws DaoException ;

    void storeLinkCourseUser(Course course, User user) throws DaoException;

    void deleteLinkCourseUser(Course course, User user) throws DaoException;

    List<Course> getCourseModulated(Course course) throws DaoException;

    List<Course> getCoursePreparatory(Course course) throws DaoException;

    List<Course> getCourseBorrowed(Course course) throws DaoException;

    void addLinkCourseModulated( Course course, int idCourseModulated ) throws DaoException;

    void addLinkCoursePreparatory( Course course, int idCoursePreparatory )throws DaoException;

    void addLinkCourseBorrowed( Course course, int idCourseBorrowed)throws DaoException;

    void deleteLinkCourseModulated( Course course, int idCourseModulated ) throws DaoException;

    void deleteLinkCoursePreparatory( Course course, int idCoursePreparatory )throws DaoException;

    void deleteLinkCourseBorrowed( Course course, int idCourseBorrowed)throws DaoException;
}
