package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.DestroyDaoException;
import dao.exception.InitDaoException;
import dao.exception.SelectDaoException;
import dao.interfaces.GroupsDao;
import model.Groups;
import model.Service;
import model.User;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;

/**
 * @author Davide Micarelli
 */
public class GroupsDaoImpl extends DaoDataMySQLImpl implements GroupsDao {

    private PreparedStatement   insertGroups,
                                selectGroupsById,
                                selectGroupsByName,
                                updateGroups,
                                deleteGroupsById,
                                selectGroupsByServiceId,
                                selectGroupsByUserId,
                                selectAllGroups;

    public GroupsDaoImpl(DataSource datasource) {
        super(datasource);
    }

    @Override
    public void init() throws DaoException{

        try {
            super.init();

            //inizializzazione query
            this.insertGroups = connection.prepareStatement("INSERT INTO groups" +
                    "                                          VALUES (NULL, ?,?)");

            this.selectGroupsById = connection.prepareStatement("SELECT *" +
                    "                                              FROM groups" +
                    "                                               WHERE id=?");

            this.selectGroupsByName = connection.prepareStatement("SELECT *" +
                    "                                                FROM groups" +
                    "                                                WHERE name=?");

            this.updateGroups = connection.prepareStatement("UPDATE groups" +
                    "                                           SET name=?," +
                    "                                               description=?" +
                    "                                           WHERE id=?");

            this.deleteGroupsById = connection.prepareStatement("DELETE FROM groups" +
                    "                                               WHERE id=?");

            this.selectGroupsByServiceId = connection.prepareStatement("SELECT groups.id," +
                    "														      groups.name," +
                    "														      groups.description" +
                    "													  FROM groups " +
                    "													  LEFT JOIN groups_service ON groups.id = groups_service.groups_id" +
                    "													  WHERE groups_service.service_id=?");

            this.selectGroupsByUserId = connection.prepareStatement("SELECT groups.id," +
                    "														      groups.name," +
                    "														      groups.description" +
                    "													  FROM groups " +
                    "													  LEFT JOIN user_groups ON groups.id = user_groups.groups_id" +
                    "													  WHERE user_groups.user_id=?");

            this.selectAllGroups = connection.prepareStatement("SELECT *" +
                    "                                               FROM groups");

        } catch (SQLException e) {
            throw new InitDaoException("Error initializing groups dao", e);
        }
    }

    /**
     * Ritorna Group vuoto
     * @return Groups vuoto
     */
    @Override
    public Groups getGroups() {
        return new Groups(this);
    }

    /**
     * Ritorna Group con id = idGroup, null se non presente
     * @param idGroups id del gruppo da restituire
     * @return Group con determinato id
     * @throws DaoException
     */
    @Override
    public Groups getGroupsById(int idGroups) throws DaoException{

        Groups groups = null;

        try {

            this.selectGroupsById.setInt(1, idGroups);

            ResultSet rs = this.selectGroupsById.executeQuery();

            if( rs.next()){

                groups = this.generateGroups(rs);

            }else { // se il risultato della query e' vuoto torno null
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException("Error getGroupsById in groups dao",e);
        }

        return groups;
    }

    /**
     * ritorna gruppo con nome = name, null se non esiste
     * @param name del gruppo da restituire
     * @return Group con nome dato
     * @throws DaoException
     */
    @Override
    public Groups getGroupsByName(String name) throws DaoException{

        Groups groups = null;

        try {

            this.selectGroupsByName.setString(1, name);

            ResultSet rs = this.selectGroupsByName.executeQuery();

            if( rs.next()){

                groups = this.generateGroups(rs);

            }else { // se il risultato della query e' vuoto torno null
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException("Error getGroupsById in groups dao",e);
        }

        return groups;
    }

    /**
     * Insericce nel database il gruppo scelto, null se non presente nel database
     * @param groups da inserire nel database
     * @throws DaoException
     */
    @Override
    public void storeGroups(Groups groups) throws DaoException{

        if( groups.getId() > 0 ){ //il gruppo ha gia' un id, quindi e' gia nel database

            try {

                this.updateGroups.setString(1, addSlashes( groups.getName()) );
                this.updateGroups.setString(2, addSlashes( groups.getDescription()) );
                this.updateGroups.setInt(3, groups.getId());

                this.updateGroups.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("Error storeGroups in groups dao", e);
            }


        }else{ //groups non e' presente nel database
            //eseguo una insert

            try {

                this.insertGroups.setString(1, addSlashes( groups.getName()));
                this.insertGroups.setString(2, addSlashes( groups.getDescription()));

                this.insertGroups.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("Error storeGroups in groups dao", e);
            }

        }
    }

    /**
     * Cancella dal database il gruppo uguale a groups
     * @param groups
     * @throws DaoException
     */
    @Override
    public void deleteGroups(Groups groups) throws DaoException{
        try {

            this.deleteGroupsById.setInt(1, groups.getId() );

            this.deleteGroupsById.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error deleteGroups in groups dao", e);
        }
    }

    @Override
    public List<Groups> getGroupsByService(Service service) throws DaoException {

        List<Groups> list = new ArrayList<>();

        try{

            this.selectGroupsByServiceId.setInt( 1, service.getId());

            ResultSet rs = this.selectGroupsByServiceId.executeQuery();

            //rs ritorna un insieme di tuple rappresentanti i gruppi acuoi appartiene l'utente
            //scorro rs ed aggiungo alla lista il gruppo
            while( rs.next() ){

                Groups groups = this.generateGroups(rs);

                list.add(groups);
            }

        }catch (Exception e) {
            throw new DaoException("Error query getGroupsByService", e);
        }

        return list;
    }

    @Override
    public List<Groups> getGroupsByUser(User user) throws DaoException {

        List<Groups> list = new ArrayList<>();

        try{

            this.selectGroupsByUserId.setInt( 1, user.getId());

            ResultSet rs = this.selectGroupsByUserId.executeQuery();

            //rs ritorna un insieme di tuple rappresentanti i gruppi a cui appartiene l'utente
            //scorro rs ed aggiungo alla lista il gruppo
            while( rs.next() ){

                Groups groups = this.generateGroups(rs);

                list.add(groups);
            }

        }catch (Exception e) {
            throw new DaoException("Error query getGroupsByUser", e);
        }

        return list;
    }

    @Override
    public Groups generateGroups(ResultSet rs) throws DaoException {

        Groups groups = this.getGroups();

        try {

            groups.setId(rs.getInt("id"));
            groups.setName(stripSlashes(rs.getString("name")));
            groups.setDescription(stripSlashes(rs.getString("description")));

        } catch (SQLException e) {
            throw new SelectDaoException("Error generateServoce", e );
        }
        return groups;
    }

    @Override
    public List <Groups> getAllGroups() throws DaoException {

        try {

            List<Groups> groupsList = new ArrayList <>();

            ResultSet rs = this.selectAllGroups.executeQuery();

            while (rs.next()){

                groupsList.add(this.generateGroups(rs));

            }

            return groupsList;

        } catch (SQLException e) {
            throw new SelectDaoException("Error in getAllGroups",e);
        }
    }

    @Override
    public void destroy() throws DaoException{

        try {
            this.insertGroups.close();
            this.selectGroupsById.close();
            this.selectGroupsByName.close();
            this.updateGroups.close();
            this.deleteGroupsById.close();
            this.selectGroupsByServiceId.close();
            this.selectGroupsByUserId.close();
            this.selectAllGroups.close();

            super.destroy();

        } catch (SQLException e) {
            throw new DestroyDaoException("Error destroy in GroupsDao", e);
        }

    }

}
