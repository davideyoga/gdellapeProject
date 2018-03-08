package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.DestroyDaoException;
import dao.interfaces.CourseBookDao;
import model.CourseBook;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by max on 30/05/17.
 */
public class CourseBookDaoImpl extends DaoDataMySQLImpl implements CourseBookDao{

    PreparedStatement insertCourseBook,
                      selectCourseBookByCourseId,
                      selectCourseBookByBookId,
                      deleteCourseBook;

    public CourseBookDaoImpl(DataSource datasource) {
        super(datasource);
    }

    @Override
    public void init() throws DaoException {
        try{

            super.init();

            this.insertCourseBook = connection.prepareStatement("INSERT INTO course_book " +
                                                                   " VALUES (?,?)");

            this.selectCourseBookByCourseId = connection.prepareStatement("SELECT * FROM course_book" +
                                                                           " WHERE course_id = ?");

            this.selectCourseBookByBookId = connection.prepareStatement("SELECT * FROM course_book" +
                                                                           " WHERE book_id = ?");
            this.deleteCourseBook =connection.prepareStatement("DELETE FROM course_book" +
                                                                  " WHERE course_id =? AND book_id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CourseBook getCourseBook(){
        return new CourseBook(this);
    }

    @Override
    public void insertCourseBook(CourseBook courseBook) throws DaoException {
        try{
            this.insertCourseBook.setInt(1,courseBook.getCourse_id());
            this.insertCourseBook.setInt(2,courseBook.getBook_id());
            this.insertCourseBook.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<CourseBook> selectCourseBookByCourseId(int course_id) throws DaoException {
        List<CourseBook> cbList = new ArrayList<CourseBook>();

        try{
            this.selectCourseBookByCourseId.setInt(1, course_id);
            ResultSet rs = this.selectCourseBookByCourseId.executeQuery();

            while (rs.next()){

                CourseBook cb = this.getCourseBook();

                cb.setCourse_id(rs.getInt("course_id"));
                cb.setBook_id(rs.getInt("book_id"));
                cbList.add(cb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cbList;
    }

    @Override
    public List<CourseBook> selectCourseBookByBookId(int book_id) throws DaoException {
        List<CourseBook> cbList = new ArrayList<CourseBook>();

        try{
            this.selectCourseBookByCourseId.setInt(1, book_id);
            ResultSet rs = this.selectCourseBookByBookId.executeQuery();

            while (rs.next()){

                CourseBook cb = this.getCourseBook();

                cb.setCourse_id(rs.getInt("course_id"));
                cb.setBook_id(rs.getInt("book_id"));
                cbList.add(cb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cbList;
    }

    @Override
    public void deleteCourseBook(CourseBook courseBook) throws DaoException {
        try{
            this.deleteCourseBook.setInt(1,courseBook.getCourse_id());
            this.deleteCourseBook.setInt(2,courseBook.getBook_id());
            this.deleteCourseBook.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() throws DaoException{

        try {

            this.insertCourseBook.close();
            this.selectCourseBookByBookId.close();
            this.selectCourseBookByCourseId.close();
            this.deleteCourseBook.close();

            super.destroy();

        }catch (DaoException e){
            throw new DestroyDaoException("Error destroyGroupsService", e);
        } catch (SQLException e) {
            throw new DestroyDaoException("Error destroyGroupsService", e);
        }
    }
}
