package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.GroupsDao;
import model.Groups;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupsDaoImpl extends DaoDataMySQLImpl implements GroupsDao {

    private PreparedStatement insertGroups,
            selectGroupsById,
            selectGroupsByName,
            updateGroups,
            deleteGroupsById;


    public GroupsDaoImpl(DataSource datasource) {
        super(datasource);
    }

    public void init() throws DaoException{

        try {
            super.init();

            //inizializzazione query
            this.insertGroups = connection.prepareStatement("INSERT INTO user" +
                    "                                          VALUES (NULL, ?,?)");

            this.selectGroupsById = connection.prepareStatement("SELECT *" +
                    "                                              FROM groups" +
                    "                                               WHERE id=?");

            this.selectGroupsByName = connection.prepareStatement("SELECT *" +
                    "                                                FROM groups" +
                    "                                                WHERE name=?");

            this.updateGroups = connection.prepareStatement("UPDATE groups" +
                    "                                           SET name=?" +
                    "                                               description=?" +
                    "                                           WHERE id=?");

            this.deleteGroupsById = connection.prepareStatement("DELETE FROM groups" +
                    "                                               WHERE id=?");

        } catch (SQLException e) {
            throw new DaoException("Error initializing groups dao", e);
        }
    }

    @Override
    public Groups getGroups() {
        return new Groups(this);
    }

    @Override
    public Groups getGroupsById(int idGroups) throws DaoException{

        Groups groups = getGroups();

        try {

            this.insertGroups.setInt(1, idGroups);
            
            this.insertGroups.executeUpdate();

            /*
            RIPRENDERE DA QUI
             */

        } catch (SQLException e) {
            throw new DaoException("Error getGroupsById in groups dao",e);
        }

        return groups;
    }

    @Override
    public Groups getGroupsByName(String name) throws DaoException{
        return null;
    }

    @Override
    public void storeGroups(Groups groups) throws DaoException{

    }

    @Override
    public void deleteGroups(Groups groups) throws DaoException{

    }
}
