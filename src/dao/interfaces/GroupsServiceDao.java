package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Groups;
import model.GroupsService;
import model.Service;

import java.util.List;

/**
 * Created by davide on 26/05/17.
 */
public interface GroupsServiceDao extends DaoData {

    public GroupsService getGroupsSerivce() throws DaoException;

    public void insertGroupsService(GroupsService groupsService) throws DaoException;

    public List<GroupsService> selectGroupsServicesByService ( Service service ) throws DaoException;

    public List<GroupsService> selectGroupsServicesByGroups (Groups groups) throws DaoException;

    public void deleteGroupsService( GroupsService groupsService ) throws DaoException;

}
