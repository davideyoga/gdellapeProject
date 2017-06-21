package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.BookDao;
import model.Book;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;
/**
 * Created by max on 25/05/17.
 */
public class BookDaoImpl extends DaoDataMySQLImpl implements BookDao {

    private PreparedStatement insertBook,
            selectBookById,
            updateBook,
            deleteBookById;

    public BookDaoImpl(DataSource datasource) {
        super(datasource);
    }

    public void init() throws DaoException{
        try{

            super.init();

            this.insertBook = connection.prepareStatement("INSERT INTO book" +
                                                            " VALUES (NULL,?,?,?,?,?,?,?)");

            this.selectBookById = connection.prepareStatement("SELECT *" +
                                                                 " FROM book" +
                                                                 " WHERE id = ?");

            this.deleteBookById = connection.prepareStatement("DELETE FROM book" +
                    "                                               WHERE id=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getBook() {
        return new Book(this);
    }

    @Override
    public Book getBookById(int IdBook) throws DaoException {
        Book book = getBook();

        try{
            this.selectBookById.setInt(1, IdBook);

            ResultSet rs =this.selectBookById.executeQuery();

            if(rs.next()){
                book.setId(IdBook);
                book.setCode(stripSlashes(rs.getString("code")));
                book.setAuthor(stripSlashes(rs.getString("author")));
                book.setTitle(stripSlashes(rs.getString("title")));
                book.setVolume(stripSlashes(rs.getString("volume")));
                book.setAge(rs.getInt("age"));
                book.setEditor(stripSlashes(rs.getString("editor")));
                book.setLink(stripSlashes(rs.getString("link")));
            }
            else{ //se il risultato della query è vuoto ritorno null.
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void storeBook(Book book) throws DaoException {
        if (book.getId() > 0){ //se > di 0 è gia nel database
            try{
                this.updateBook.setString(1,addSlashes(book.getCode()));
                this.updateBook.setString(2,addSlashes(book.getAuthor()));
                this.updateBook.setString(3,addSlashes(book.getTitle()));
                this.updateBook.setString(4,addSlashes(book.getVolume()));
                this.updateBook.setInt(5,book.getAge());
                this.updateBook.setString(6,addSlashes(book.getEditor()));
                this.updateBook.setString(7,addSlashes(book.getLink()));

                this.updateBook.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{ //non è presente nel db quindi eseguo una insert
            try{
                this.insertBook.setString(1,addSlashes(book.getCode()));
                this.insertBook.setString(2,addSlashes(book.getAuthor()));
                this.insertBook.setString(3,addSlashes(book.getTitle()));
                this.insertBook.setString(4,addSlashes(book.getVolume()));
                this.insertBook.setInt(5,book.getAge());
                this.insertBook.setString(6,addSlashes(book.getEditor()));
                this.insertBook.setString(7,addSlashes(book.getLink()));

                this.insertBook.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deleteBook(Book book) throws DaoException {
        try{

            this.deleteBookById.setInt(1,book.getId());
            this.deleteBookById.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
