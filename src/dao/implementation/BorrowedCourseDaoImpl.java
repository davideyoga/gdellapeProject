package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.BorrowedCourseDao;
import model.BorrowedCourse;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by max on 26/05/17.
 */
public class BorrowedCourseDaoImpl extends DaoDataMySQLImpl implements BorrowedCourseDao {

    PreparedStatement insertBorrowedCourse,
                      updateBorrowedCourse,
                      selectBorrowedCourseById,
                      deleteBorrowedCourse;

    public BorrowedCourseDaoImpl(DataSource dataSource){
        super(dataSource);
    }

    public void init() throws DaoException{
        try{

            super.init();

            this.insertBorrowedCourse = connection.prepareStatement(" INSERT INTO borrowedCourse" +
                                                                       " VALUES (?,?)");

            this.updateBorrowedCourse = connection.prepareStatement(" UPDATE borrowedCourse" +
                                                                       " SET course_id = ?" +
                                                                            "course_borrowed_id " +
                                                                       " WHERE course_id = ?" +
                                                                       " AND course_borrowed_id = ?");
            this.selectBorrowedCourseById = connection.prepareStatement(" SELECT borrowedCourse" +
                                                                           " WHERE course_id = ?" +
                                                                           " AND course_borrowed_id = ?");
            this.deleteBorrowedCourse = connection.prepareStatement(" DELETE FROM borrowedCourse" +
                                                                       " WHERE course_id = ?" +
                                                                       " AND  course_borrowed_id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BorrowedCourse getBorrowedCourse() {
        return new BorrowedCourse(this);
    }

    @Override
    public void insertBorrowedCourse(BorrowedCourse borrowedCourse) throws DaoException {
    }

    @Override
    public void updateBorrowedCourse(BorrowedCourse borrowedCourse) throws DaoException {
    }

    @Override
    public BorrowedCourse selectBorrowedCourseById(int id_course, int id_borrowed) throws DaoException {
        return null;
    }

    @Override
    public void deleteBorrowedCourse(BorrowedCourse borrowedCourse) throws DaoException {
    }

    @Override
    public void destroy() throws DaoException{
        try{
            this.insertBorrowedCourse.close();
            this.updateBorrowedCourse.close();
            this.selectBorrowedCourseById.close();
            this.deleteBorrowedCourse.close();

            super.destroy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
