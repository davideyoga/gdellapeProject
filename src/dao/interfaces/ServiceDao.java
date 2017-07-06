package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Groups;
import model.Service;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by davide on 23/05/17.
 */
public interface ServiceDao extends DaoData {


        public Service getService();

        public Service getServiceById( int idService ) throws DaoException;

        public Service getServiceByName( String name ) throws DaoException;

        public void storeService( Service groups ) throws DaoException;

        public void deleteService( Service groups ) throws DaoException;

        public List<Service> getServicesByGroup(Groups groups)throws DaoException;

        Service generateService(ResultSet resultSet) throws DaoException;

        List<Service> getAllService() throws DaoException;

}
