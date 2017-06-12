package dao.interfaces;

import dao.exception.DaoException;
import model.CourseBook;

import java.util.List;

/**
 * Created by max on 30/05/17.
 */
public interface CourseBookDao {

    public CourseBook getCourseBook() throws DaoException;

    public void insertCourseBook(CourseBook courseBook) throws DaoException;

    public List<CourseBook> selectCourseBookByCourseId(int course_id) throws DaoException;

    public List<CourseBook> selectCourseBookByBookId(int book_id) throws DaoException;

    public void deleteCourseBook(CourseBook courseBook) throws DaoException;


}
