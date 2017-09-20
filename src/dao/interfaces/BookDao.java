package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Book;
import model.Course;

/**
 * Created by max on 25/05/17.
 */
public interface BookDao extends DaoData {

    public Book getBook();
    public Book getBookById(int IdBook) throws DaoException;
    public int storeBook (Book book) throws DaoException;
    public void deleteBook (Book book) throws DaoException;
    public void deleteLinkToCourseBook(Course course, Book book) throws DaoException;

    public void addLinkBookToCourse( Course course, Book book) throws DaoException;

}
