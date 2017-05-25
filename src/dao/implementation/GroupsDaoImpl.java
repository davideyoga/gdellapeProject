package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.GroupsDao;
import model.Groups;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;

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
            this.insertGroups = connection.prepareStatement("INSERT INTO groups" +
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

        Groups groups = getGroups();

        try {

            this.selectGroupsById.setInt(1, idGroups);

            ResultSet rs = this.selectGroupsById.executeQuery();

            if( rs.next()){

                groups.setId(idGroups);
                groups.setName( stripSlashes( rs.getString("name")));
                groups.setDescription(stripSlashes( rs.getString("description")));

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

        Groups groups = getGroups();

        try {

            this.selectGroupsByName.setString(1, name);

            ResultSet rs = this.selectGroupsByName.executeQuery();

            if( rs.next()){

                groups.setId( rs.getInt("id"));
                groups.setName( stripSlashes( "name"));
                groups.setDescription(stripSlashes( rs.getString("description")));

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
}
