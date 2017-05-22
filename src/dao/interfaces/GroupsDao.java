package dao.interfaces;

import dao.exception.DaoException;
import model.Groups;

public interface GroupsDao{

    public Groups getGroups();

    public Groups getGroupsById( int idGroups ) throws DaoException;

    public Groups getGroupsByName( String name ) throws DaoException;

    public void storeGroups( Groups groups ) throws DaoException;

    public void deleteGroups( Groups groups ) throws DaoException;

}