package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Course;
import model.Groups;
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
    Course deleteCourse(Course course) throws DaoException;
}
