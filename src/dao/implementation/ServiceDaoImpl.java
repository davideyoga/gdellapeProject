package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.DestroyDaoException;
import dao.exception.InitDaoException;
import dao.exception.SelectDaoException;
import dao.interfaces.ServiceDao;
import model.Groups;
import model.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;

/**
 * Created by davide on 23/05/17.
 */
public class ServiceDaoImpl extends DaoDataMySQLImpl implements ServiceDao {

    private PreparedStatement   insertService,
                                selectServiceById,
                                selectServiceByName,
                                updateService,
                                deleteServiceById,
                                selectServicesByGroupId,
                                selectAllservice;


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
                    "                                           SET name=?," +
                    "                                               description=?" +
                    "                                           WHERE id=?");

            this.deleteServiceById = connection.prepareStatement("DELETE FROM service" +
                    "                                               WHERE id=?");

            //Query che restituisce i servizi a cui può accedere un dato gruppo
            this.selectServicesByGroupId=connection.prepareStatement("SELECT service.id," +
                    "														      service.name," +
                    "														      service.description" +
                    "													  FROM service " +
                    "													  LEFT JOIN groups_service ON service.id = groups_service.service_id" +
                    "													  WHERE groups_service.groups_id=?");

            this.selectAllservice = connection.prepareStatement("SELECT *" +
                    "                                                   FROM service");

        } catch (SQLException e) {
            throw new InitDaoException("Error initializing service dao", e);
        }
    }

    @Override
    public Service getService() {
        return new Service(this);
    }

    @Override
    public Service getServiceById(int idService) throws DaoException{

        Service service = null;

        try {

            this.selectServiceById.setInt(1, idService);

            ResultSet rs = this.selectServiceById.executeQuery();

            if( rs.next() ){

                service = this.generateService(rs);

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

        Service service = null;

        try {

            this.selectServiceByName.setString(1, name);

            ResultSet rs = this.selectServiceByName.executeQuery();

            if( rs.next()){

                service = this.generateService(rs);

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

    /**
     * Metodo che ritorna la lista di servizi a cui può accedervi un dato gruppo
     * @param groups gruppo di cui si vogliono i servizi a cui puo accedere
     * @return lista di servizi
     * @throws DaoException lancia eccezione in caso di errore
     */
    @Override
    public List<Service> getServicesByGroup(Groups groups) throws DaoException {

        List<Service> list = new ArrayList<>();

        try{

            this.selectServicesByGroupId.setInt( 1, groups.getId());

            ResultSet rs = this.selectServicesByGroupId.executeQuery();

            //rs ritorna un insieme di tuple rappresentanti i gruppi acuoi appartiene l'utente
            //scorro rs ed aggiungo alla lista il gruppo
            while( rs.next() ){

                Service s = this.generateService(rs);

                list.add(s);
            }

        }catch (Exception e) {
            throw new DaoException("Error query getServiceByGroupId", e);
        }

        return list;
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

    @Override
    public Service generateService(ResultSet resultSet) throws DaoException {

        Service s = this.getService();

        try {

            s.setId(resultSet.getInt("id"));
            s.setName(stripSlashes(resultSet.getString("name")));
            s.setDescription(stripSlashes(resultSet.getString("description")));

        } catch (SQLException e) {
            throw new SelectDaoException("Error generateServoce", e );
        }
        return s;
    }

    @Override
    public List <Service> getAllService() throws DaoException {

        try {

            List<Service> serviceList = new ArrayList <>();

            ResultSet rs = this.selectAllservice.executeQuery();

            while (rs.next()){

                serviceList.add(this.generateService(rs));

            }

            return serviceList;

        } catch (SQLException e) {
            throw new SelectDaoException("Error in getAllService",e);
        }

    }

    @Override
    public void destroy() throws DaoException{

        try {
            this.insertService.close();
            this.selectServiceById.close();
            this.selectServiceByName.close();
            this.updateService.close();
            this.deleteServiceById.close();
            this.selectServicesByGroupId.close();
            this.selectAllservice.close();

            super.destroy();

        } catch (SQLException e) {
            throw new DestroyDaoException("Error destroy in ServiceDao", e);
        }
    }

}
