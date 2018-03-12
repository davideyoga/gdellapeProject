package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Course;
import model.Groups;
import model.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserDao extends DaoData{

    public User getUser();

    public User getUserById(int idUser) throws DaoException;

    User getUserByEmail(String email) throws DaoException;//

    public User getUserByEmailPassword( String email, String password) throws DaoException;//

    public int storeUser( User user) throws DaoException;//

    public void deleteUser(User user) throws DaoException;

    public User generateUser(ResultSet rs) throws DaoException;

    List<User> getUserByGroups(Groups groups) throws DaoException;

    List<User> getUserByCourse(Course course) throws DaoException;


    List<User> getUsers() throws DaoException;

}