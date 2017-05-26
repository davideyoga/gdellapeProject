package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.GroupsService;

/**
 * Created by davide on 26/05/17.
 */
public interface GroupsServiceDao extends DaoData {

    public GroupsService getGroupsSerivce() throws DaoException;

    public void insertGroupsService(GroupsService groupsService) throws DaoException;

    public void updateGroupsService(GroupsService groupsService) throws DaoException;

    public GroupsService selectGroupsServiceById (int idGroups, int idService ) throws DaoException;

    public void deleteGroupsService( GroupsService groupsService ) throws DaoException;

}
