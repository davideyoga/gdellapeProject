package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.*;
import dao.interfaces.UserDao;
import dao.interfaces.UserGroupsDao;
import model.Groups;
import model.User;
import model.UserGroups;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  Davide Micarelli
 */
public class UserGroupsDaoImpl extends DaoDataMySQLImpl implements UserGroupsDao {

    PreparedStatement insertUserGroups,
            //selectUserGroupsById,
            selectUserGroupsByUserId,
            selectUserGroupsByGroupsId,
            deleteUserGroups;


    public UserGroupsDaoImpl(DataSource datasource) {
        super(datasource);
    }


    @Override
    public void init() throws DaoException {

        try {

            super.init();

            this.insertUserGroups = connection.prepareStatement("INSERT INTO user_groups" +
                    "                                                        VALUES (?,?)");



            this.selectUserGroupsByUserId = connection.prepareStatement("SELECT * FROM user_groups" +
                    "                                                                   WHERE user_id=?");

            this.selectUserGroupsByGroupsId = connection.prepareStatement("SELECT * FROM user_groups" +
                    "                                                                   WHERE groups_id=?");

            this.deleteUserGroups = connection.prepareStatement("DELETE FROM user_groups" +
                    "                                                       WHERE user_id=?" +
                    "                                                       AND  groups_id=?");

        } catch (SQLException e) {
            throw new InitDaoException("Error init group service", e);
        }

    }

    /**
     * torna UserGroups vuoto
     * @return UserGroups vuoto
     * @throws DaoException
     */
    @Override
    public UserGroups getUserGroups() throws DaoException {
        return new UserGroups(this);
    }

    /**
     * Inserisce userGroups nel database
     * @param userGroups da inserire nel database
     * @throws DaoException
     */
    @Override
    public void insertUserGroups(UserGroups userGroups) throws DaoException {

        try {

            //setto i parametri della query
            this.insertUserGroups.setInt(1, userGroups.getIdUser());
            this.insertUserGroups.setInt(2, userGroups.getIdGroups());
            //lancio la query
            this.insertUserGroups.executeUpdate();


        }catch (SQLException e) {
            throw new InsertDaoException("Error insertUserGroups", e);
        }
    }


    /**
     * Torna lista di UserGroups collegati con user
     * @param user con collegati UserGroups
     * @return lista di UserGroups collegati all'utente user
     * @throws DaoException
     */
    @Override
    public List<UserGroups> getUserGroupsByUser(User user) throws DaoException {

        List<UserGroups> ugList = new ArrayList<UserGroups>();

        try {

            this.selectUserGroupsByUserId.setInt(1,user.getId());

            ResultSet rs = this.selectUserGroupsByUserId.executeQuery();

            //ciclo il risultato della query
            while( rs.next() ) {

                UserGroups ug = this.getUserGroups();//creo il GroupsService da aggiungere alla lista

                ug.setIdUser(rs.getInt("user_id"));
                ug.setIdGroups(rs.getInt("groups_id"));

                ugList.add(ug);

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error selectUserGroupsByUser", e);
        }

        return ugList;
    }

    @Override
    public List<UserGroups> getUserGroupsByGroups(Groups groups) throws DaoException {

        List<UserGroups> ugList = new ArrayList<UserGroups>();

        try {

            this.selectUserGroupsByGroupsId.setInt(1,groups.getId());

            ResultSet rs = this.selectUserGroupsByGroupsId.executeQuery();

            //ciclo il risultato della query
            while( rs.next() ) {

                UserGroups ug = this.getUserGroups();//creo il GroupsService da aggiungere alla lista

                ug.setIdUser(rs.getInt("user_id"));
                ug.setIdGroups(rs.getInt("groups_id"));

                ugList.add(ug);

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error selectUserGroupsByUser", e);
        }

        return ugList;
    }

    /**
     * cancello dal db lo userGroups passato
     * @param userGroups da eliminare dal db
     * @throws DaoException
     */
    @Override
    public void deleteUserGroups(UserGroups userGroups) throws DaoException {

        try {

            this.deleteUserGroups.setInt(1, userGroups.getIdUser());
            this.deleteUserGroups.setInt(2, userGroups.getIdGroups());

            this.deleteUserGroups.executeUpdate();

        } catch (SQLException e) {
            throw new DeleteDaoException("Error deleteUserGroups");
        }
    }

    /**
     * chiusura connessione e query precompilate
     * @throws DaoException
     */
    @Override
    public void destroy() throws DaoException{


        try {

            //chiudo le query precompilate
            this.insertUserGroups.close();
            //this.selectUserGroupsById.close();
            this.selectUserGroupsByUserId.close();
            this.selectUserGroupsByGroupsId.close();
            this.deleteUserGroups.close();

            //chiudo la connessione
            super.destroy();

        } catch (DaoException e) {
            throw new DestroyDaoException("Error destroyUserGroups", e);
        } catch (SQLException e) {
            throw new DestroyDaoException("Error destroyUserGroups", e);
        }
    }

}
