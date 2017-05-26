package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.GroupsServiceDao;
import model.GroupsService;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by davide on 26/05/17.
 */
public class GroupsServiceDaoImpl extends DaoDataMySQLImpl implements GroupsServiceDao {

    PreparedStatement insertGroupsService,
                        updateGroupsService,
                        selectGroupsServiceById,
                        deleteGroupsService;

    public GroupsServiceDaoImpl(DataSource datasource) {
        super(datasource);
    }

    @Override
    public void init() throws DaoException{
        try {

            this.insertGroupsService = connection.prepareStatement("INSERT INTO groups_serivce" +
                    "                                                        VALUES (?,?)");

            this.updateGroupsService = connection.prepareStatement("UPDATE groups_service" +
                    "                                                           SET groups_id=?" +
                    "                                                               service_id=?" +
                    "                                                            WHERE groups_id=?" +
                    "                                                            AND service_id=?");

            this.selectGroupsServiceById = connection.prepareStatement("SELECT groups_service" +
                    "                                                       WHERE groups_id=?" +
                    "                                                       AND service_id=?");

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

    @Override
    public void insertGroupsService(GroupsService groupsService) throws DaoException {

    }

    @Override
    public void updateGroupsService(GroupsService groupsService) throws DaoException {

    }


    @Override
    public GroupsService selectGroupsServiceById(int idGroups, int idService) throws DaoException {
        return null;
    }

    @Override
    public void deleteGroupsService(GroupsService groupsService) throws DaoException {

    }

    @Override
    public void destroy() throws DaoException{


        try {

            this.insertGroupsService.close();
            this.updateGroupsService.close();
            this.selectGroupsServiceById.close();
            this.deleteGroupsService.close();

            super.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
