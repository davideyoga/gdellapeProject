package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.*;
import dao.interfaces.GroupsServiceDao;
import model.Groups;
import model.GroupsService;
import model.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davide on 26/05/17.
 */
public class GroupsServiceDaoImpl extends DaoDataMySQLImpl implements GroupsServiceDao {

    PreparedStatement insertGroupsService,  selectGroupsServiceByServiceId, selectGroupsServiceByGroupsId, deleteGroupsService;

    public GroupsServiceDaoImpl(DataSource datasource) {
        super(datasource);
    }

    /**
     * Inizializza connessione e query
     *
     * @throws DaoException
     */
    @Override
    public void init() throws DaoException {
        try {

            super.init();

            this.insertGroupsService = connection.prepareStatement("INSERT INTO groups_service" +
                    "                                                        VALUES (?,?)");


            this.selectGroupsServiceByServiceId = connection.prepareStatement("SELECT * FROM groups_service" +
                    "                                                                   WHERE service_id=?");

            this.selectGroupsServiceByGroupsId = connection.prepareStatement("SELECT * FROM groups_service" +
                    "                                                                   WHERE groups_id=?");

            this.deleteGroupsService = connection.prepareStatement("DELETE FROM groups_service" +
                    "                                                       WHERE groups_id=?" +
                    "                                                       AND  service_id=?");

        } catch (SQLException e) {
            throw new InitDaoException("Error init group service", e);
        }
    }

    /**
     * Ritorna un GroupsService vuoto
     *
     * @return
     */
    @Override
    public GroupsService getGroupsSerivce() {
        return new GroupsService(this);
    }

    /**
     * Inserisce un GroupsService
     *
     * @param groupsService da inserire
     * @throws DaoException
     */
    @Override
    public void insertGroupsService(GroupsService groupsService) throws DaoException {

        try {

            //setto i parametri della query
            this.insertGroupsService.setInt(2, groupsService.getIdGroups());
            this.insertGroupsService.setInt(1, groupsService.getIdService());
            //lancio la query
            this.insertGroupsService.executeUpdate();


        } catch (SQLException e) {
            throw new InsertDaoException("Error insertGroupsService", e);
        }
    }


    /**
     * Torna lista di GroupsSerivice collegato al service passato
     *
     * @param service
     * @return
     * @throws DaoException
     */
    @Override
    public List<GroupsService> selectGroupsServicesByService(Service service) throws DaoException {

        List<GroupsService> gsList = new ArrayList<GroupsService>();

        try {

            this.selectGroupsServiceByServiceId.setInt(1, service.getId());

            ResultSet rs = this.selectGroupsServiceByServiceId.executeQuery();

            //ciclo il risultato della query
            while (rs.next()) {

                GroupsService gs = this.getGroupsSerivce();//creo il GroupsService da aggiungere alla lista

                gs.setIdGroups(rs.getInt("groups_id"));
                gs.setIdService(rs.getInt("service_id"));

                gsList.add(gs);

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error selectGroupsServicesByService", e);
        }

        return gsList;
    }

    /**
     * Torna lista di GroupsSerivice collegato al grups passato
     *
     * @param groups
     * @return Lista di GroupsService collegati al Grups
     * @throws DaoException
     */
    @Override
    public List<GroupsService> selectGroupsServicesByGroups(Groups groups) throws DaoException {

        List<GroupsService> gsList = new ArrayList<GroupsService>();

        try {

            this.selectGroupsServiceByGroupsId.setInt(1, groups.getId());

            ResultSet rs = this.selectGroupsServiceByGroupsId.executeQuery();

            //ciclo il risultato della query
            while (rs.next()) {

                GroupsService gs = this.getGroupsSerivce(); //creo il GroupsService da aggiungere alla lista

                gs.setIdGroups(rs.getInt("groups_id"));
                gs.setIdService(rs.getInt("service_id"));

                gsList.add(gs); //aggiungo gs alla lista

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error selectGroupsServicesByGroups", e);
        }

        return gsList;
    }


    /**
     * cancella il GroupsService passato dal database
     *
     * @param groupsService
     * @throws DaoException
     */
    @Override
    public void deleteGroupsService(GroupsService groupsService) throws DaoException {

        try {

            this.deleteGroupsService.setInt(1, groupsService.getIdGroups());
            this.deleteGroupsService.setInt(2, groupsService.getIdService());

            this.deleteGroupsService.executeUpdate();

        } catch (SQLException e) {
            throw new DeleteDaoException("Error deleteGroupsService", e);
        }
    }

    /**
     * Chiude connessione e query precompilate
     *
     * @throws DaoException
     */
    @Override
    public void destroy() throws DaoException {


        try {

            this.insertGroupsService.close();
            this.selectGroupsServiceByGroupsId.close();
            this.selectGroupsServiceByServiceId.close();
            this.deleteGroupsService.close();

            super.destroy();

        } catch (DaoException e) {
            throw new DestroyDaoException("Error destroyGroupsService", e);
        } catch (SQLException e) {
            throw new DestroyDaoException("Error destroyGroupsService", e);
        }
    }
}
