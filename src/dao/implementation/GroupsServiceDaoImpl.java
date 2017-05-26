package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.DeleteDaoException;
import dao.exception.InsertDaoException;
import dao.exception.SelectDaoException;
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

    PreparedStatement insertGroupsService,
                        selectGroupsServiceById,
                        selectGroupsServiceByServiceId,
                        selectGroupsServiceByGroupsId,
                        deleteGroupsService;

    public GroupsServiceDaoImpl(DataSource datasource) {
        super(datasource);
    }

    /**
     * Inizializza connessione e query
     * @throws DaoException
     */
    @Override
    public void init() throws DaoException{
        try {

            this.insertGroupsService = connection.prepareStatement("INSERT INTO groups_serivce" +
                    "                                                        VALUES (?,?)");

            this.selectGroupsServiceById = connection.prepareStatement("SELECT groups_service" +
                    "                                                       WHERE groups_id=?" +
                    "                                                       AND service_id=?");

            this.selectGroupsServiceByServiceId = connection.prepareStatement("SELECT * FROM groups_serice" +
                    "                                                                   WHERE service_id=?");

            this.selectGroupsServiceByGroupsId = connection.prepareStatement("SELECT * FROM groups_serice" +
                    "                                                                   WHERE groups_id=?");

            this.deleteGroupsService = connection.prepareStatement("DELETE FROM geoups_service" +
                    "                                                       WHERE groups_id=?" +
                    "                                                       AND  service_id=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ritorna un GroupsService vuoto
     * @return
     */
    @Override
    public GroupsService getGroupsSerivce() {
        return new GroupsService(this);
    }

    /**
     * Inserisce un GroupsService
     * @param groupsService da inserire
     * @throws DaoException
     */
    @Override
    public void insertGroupsService(GroupsService groupsService) throws DaoException {

        try {

            //setto i parametri della query
            this.insertGroupsService.setInt(1, groupsService.getIdGroups());
            this.insertGroupsService.setInt(2, groupsService.getIdService());
            //lancio la query
            this.insertGroupsService.executeUpdate();


        }catch (SQLException e) {
            throw new InsertDaoException("Error insertGroupsService", e);
        }
    }

    /**
     * Torna GroupsService con determinato id grups e id service
     * @param idGroups
     * @param idService
     * @return GroupsService con determinato idGroup e idService
     * @throws DaoException
     */
    @Override
    public GroupsService selectGroupsServiceById(int idGroups, int idService) throws DaoException {

        GroupsService gs = this.getGroupsSerivce();

        try {
            //setto la query
            this.selectGroupsServiceById.setInt(1, idGroups);
            this.selectGroupsServiceById.setInt(2, idService);

            ResultSet rs = this.selectGroupsServiceById.executeQuery();

            if(rs.next()){ // il risultato e' pieno

                gs.setIdGroups(rs.getInt("groups_id"));
                gs.setIdService(rs.getInt("service_id"));

            }else{ //risultato vuoto
                return null;
            }

            //lancio la query
        } catch (SQLException e) {
            throw new SelectDaoException("Error selectGroupsService", e);
        }

        return gs;
    }

    /**
     * Torna lista di GroupsSerivice collegato al service passato
     * @param service
     * @return
     * @throws DaoException
     */
    @Override
    public List<GroupsService> selectGroupsServicesByService(Service service) throws DaoException {

        List<GroupsService> gsList = new ArrayList<GroupsService>();

        try {

            this.selectGroupsServiceByServiceId.setInt(1,service.getId());

            ResultSet rs = this.selectGroupsServiceByServiceId.executeQuery();

            //ciclo il risultato della query
            while( rs.next() ) {

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
     * @param groups
     * @return Lista di GroupsService collegati al Grups
     * @throws DaoException
     */
    @Override
    public List<GroupsService> selectGroupsServicesByGroups(Groups groups) throws DaoException {

        List<GroupsService> gsList = new ArrayList<GroupsService>();

        try {

            this.selectGroupsServiceByGroupsId.setInt(1,groups.getId());

            ResultSet rs = this.selectGroupsServiceByGroupsId.executeQuery();

            //ciclo il risultato della query
            while( rs.next() ) {

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
            throw new DeleteDaoException("Error deleteGroupsService");
        }
    }

    /**
     * CHiude connessione e query
     * @throws DaoException
     */
    @Override
    public void destroy() throws DaoException{


        try {

            this.insertGroupsService.close();
            //this.updateGroupsService.close();
            this.selectGroupsServiceById.close();
            this.deleteGroupsService.close();

            super.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
