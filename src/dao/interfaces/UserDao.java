package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.User;

public interface UserDao extends DaoData{

    public User getuser();

    public User getUserById(int idUser) throws DaoException;

    public User getUserByEmailPassword( String email, String password) throws DaoException;

    public void storeUser( User user) throws DaoException;

    public void deleteUser(User user) throws DaoException;

}