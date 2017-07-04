package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Groups;
import model.Service;
import model.User;

import java.sql.ResultSet;
import java.util.List;

public interface GroupsDao  extends DaoData{

    public Groups getGroups();

    public Groups getGroupsById( int idGroups ) throws DaoException;

    public Groups getGroupsByName( String name ) throws DaoException;

    public void storeGroups( Groups groups ) throws DaoException;

    public void deleteGroups( Groups groups ) throws DaoException;

    public List<Groups> getGroupsByService(Service service)throws DaoException;

    public List<Groups> getGroupsByUser(User user)throws DaoException;

    Groups generateGroups(ResultSet rs) throws DaoException;

    List<Groups> getAllGroups() throws DaoException;

}