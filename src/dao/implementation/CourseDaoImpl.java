package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.DestroyDaoException;
import dao.exception.InitDaoException;
import dao.exception.SelectDaoException;
import dao.interfaces.CourseDao;
import dao.interfaces.GroupsDao;
import model.Course;
import model.Groups;
import model.Service;
import model.User;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonello on 09/06/17.
 */
public class CourseDaoImpl extends DaoDataMySQLImpl implements CourseDao{

    private PreparedStatement insertCourse,
            selectCourseById,
            selectCourseByName,
            selectCourseByCode,
            selectCourseByYear,
            selectCourseByCfu,
            selectCourseBySector,
            selectCourseByLanguage,
            selectCourseBySemester,
            updateCourse,
            deleteCourseById,
            deleteCourseByName;

    public CourseDaoImpl(DataSource datasource) {
        super(datasource);
    }

    public void init() throws DaoException{
        try{

            super.init();

            this.insertCourse = connection.prepareStatement("INSERT INTO course" +
                    " VALUES (NULL,?,?,?,?,?,?,?)");

            this.selectCourseById = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE id = ?");

            this.deleteCourseById = connection.prepareStatement("DELETE FROM course" +
                    "                                               WHERE id=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course getCourse() {
        return null;
    }

    @Override
    public Course getCourseById(int Id) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseByName(String name) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseByCode(int code) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseByYear(String year) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseByCfu(int cfu) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseBySector(String sector) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseByLanguage(String language) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseBySemester(int semester) throws DaoException {
        return null;
    }

    @Override
    public Course storeCourse(Course course) throws DaoException {
        return null;
    }

    @Override
    public Course deleteCourse(Course course) throws DaoException {
        return null;
    }
}
