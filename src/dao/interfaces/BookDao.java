package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Book;

/**
 * Created by max on 25/05/17.
 */
public interface BookDao extends DaoData {

    public Book getBook();
    public Book getBookById(int IdBook) throws DaoException;
    public void storeBook (Book book) throws DaoException;
    public void deleteBook (Book book) throws DaoException;

}
