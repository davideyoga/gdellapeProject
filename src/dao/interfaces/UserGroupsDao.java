package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Groups;
import model.User;
import model.UserGroups;

import java.util.List;

/**
 * @author  Davide Micarelli
 */
public interface UserGroupsDao extends DaoData {

    public UserGroups getUserGroups() throws DaoException;

    public void insertUserGroups( UserGroups userGroups) throws DaoException;

    public List<UserGroups> getUserGroupsByUser ( User user ) throws DaoException;

    public List<UserGroups> getUserGroupsByGroups (Groups groups) throws DaoException;

    public void deleteUserGroups( UserGroups userGroups ) throws DaoException;
}
