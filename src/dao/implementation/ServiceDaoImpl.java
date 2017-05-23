package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.ServiceDao;
import model.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;

/**
 * Created by davide on 23/05/17.
 */
public class ServiceDaoImpl extends DaoDataMySQLImpl implements ServiceDao {

    private PreparedStatement insertService,
            selectServiceById,
            selectServiceByName,
            updateService,
            deleteServiceById;


    public ServiceDaoImpl(DataSource datasource) {
        super(datasource);
    }

    public void init() throws DaoException {

        try {
            super.init();

            //inizializzazione query
            this.insertService = connection.prepareStatement("INSERT INTO service" +
                    "                                          VALUES (NULL, ?,?)");

            this.selectServiceById = connection.prepareStatement("SELECT *" +
                    "                                              FROM service" +
                    "                                               WHERE id=?");

            this.selectServiceByName = connection.prepareStatement("SELECT *" +
                    "                                                FROM service" +
                    "                                                WHERE name=?");

            this.updateService = connection.prepareStatement("UPDATE service" +
                    "                                           SET name=?" +
                    "                                               description=?" +
                    "                                           WHERE id=?");

            this.deleteServiceById = connection.prepareStatement("DELETE FROM service" +
                    "                                               WHERE id=?");

        } catch (SQLException e) {
            throw new DaoException("Error initializing service dao", e);
        }
    }

    @Override
    public Service getService() {
        return new Service(this);
    }

    @Override
    public Service getServiceById(int idService) throws DaoException{

        Service service = getService();

        try {

            this.selectServiceById.setInt(1, idService);

            ResultSet rs = this.selectServiceById.executeQuery();

            if( rs.next() ){

                service.setId(idService);
                service.setName( stripSlashes( rs.getString("name")));
                service.setDescription(stripSlashes( rs.getString("description")));

            }else { // se il risultato della query e' vuoto torno null
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException("Error getServicesById in service dao",e);
        }

        return service;
    }

    @Override
    public Service getServiceByName(String name) throws DaoException{

        Service service = getService();

        try {

            this.selectServiceByName.setString(1, name);

            ResultSet rs = this.selectServiceByName.executeQuery();

            if( rs.next()){

                service.setId( rs.getInt("id"));
                service.setName( stripSlashes( "name"));
                service.setDescription(stripSlashes( rs.getString("description")));

            }else { // se il risultato della query e' vuoto torno null
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException("Error getGroupsById in groups dao",e);
        }

        return service;
    }


    @Override
    public void storeService(Service service) throws DaoException{

        if( service.getId() > 0 ){ //il servizio ha gia' un id, quindi e' gia nel database

            try {

                this.updateService.setString(1, addSlashes( service.getName()) );
                this.updateService.setString(2, addSlashes( service.getDescription()) );
                this.updateService.setInt(3, service.getId());

                this.updateService.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("Error storeService in service dao", e);
            }


        }else{ //service non e' presente nel database
            //eseguo una insert

            try {

                this.insertService.setString(1, addSlashes( service.getName()));
                this.insertService.setString(2, addSlashes( service.getDescription()));

                this.insertService.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("Error storeService in service dao", e);
            }

        }
    }

    @Override
    public void deleteService(Service service) throws DaoException{
        try {

            this.deleteServiceById.setInt(1, service.getId() );

            this.deleteServiceById.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error deleteService in service dao", e);
        }
    }

}
