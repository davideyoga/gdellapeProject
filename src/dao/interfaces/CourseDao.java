package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Course;
import model.Groups;
/**
 * Created by antonello on 25/05/17.
 */
public interface CourseDao extends DaoData{

    public Course getCourse();
    public Course getCourseById(int Id) throws DaoException;
    public Course getCourseByName(String name) throws DaoException;
    public Course getCourseByCode(int code) throws DaoException;
    public Course getCourseByYear(String year) throws DaoException;
    public Course getCourseByCfu(int cfu) throws DaoException;
    public Course getCourseBySector(String sector) throws DaoException;
    public Course getCourseByLanguage(String language) throws DaoException;
    public Course getCourseBySemester(int semester) throws DaoException;
    public Course storeCourse (Course course) throws DaoException;
    public Course deleteCourse (Course course) throws DaoException;
}
