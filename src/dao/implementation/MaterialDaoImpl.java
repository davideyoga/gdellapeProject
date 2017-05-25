package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.MaterialDao;
import model.Material;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;
/**
 * Created by max on 25/05/17.
 */
public class MaterialDaoImpl extends DaoDataMySQLImpl implements MaterialDao {

    private PreparedStatement
            insertMaterial,
            selectMaterialById,
            updateMaterial,
            deleteMaterialById;

    public MaterialDaoImpl(DataSource datasource) {
        super(datasource);
    }
    public void init() throws DaoException{

        try{
            super.init();

            this.insertMaterial = connection.prepareStatement("INSERT INTO materials" + " VALUES (NULL,?,?,?,?,?)");

            this.selectMaterialById = connection.prepareStatement("SELECT *" + " FROM material" + " WHERE id=?");

            this.updateMaterial = connection.prepareStatement("UPDATE material" + " SET description_ita=?" +
                                                                 "description_eng=? " + " data = ?" + " size = ?"
                                                                 + " type = ?");

            this.deleteMaterialById = connection.prepareStatement(" DELETE FROM material" + "WHERE id=?");

        } catch (SQLException e) {
            throw new DaoException("Error initializing groups dao", e);
        }
    }

    @Override
    public Material getMaterial() {
        return new Material(this);
    }

    @Override
    public Material getMaterialById(int idMaterial) throws DaoException {
        Material material = getMaterial();

        try{
            this.selectMaterialById.setInt(1, idMaterial);

            ResultSet rs = this.selectMaterialById.executeQuery();

            if(rs.next()){
                material.setId(idMaterial);
                material.setDescription_ita(stripSlashes(rs.getString("description_ita")));
                material.setDescription_eng(stripSlashes(rs.getString("description_eng")));
                material.setData(rs.getTimestamp("data"));
                material.setSize(rs.getDouble("size"));
                material.setType(stripSlashes(rs.getString("type")));
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }

    @Override
    public void storeMaterial(Material material) throws DaoException {

        if( material.getId() > 0){
            try{
                this.updateMaterial.setString(1,addSlashes(material.getDescription_ita()));
                this.updateMaterial.setString(2,addSlashes(material.getDescription_eng()));
                this.updateMaterial.setTimestamp(3,material.getData());
                this.updateMaterial.setDouble(4,material.getSize());
                this.updateMaterial.setString(5,addSlashes(material.getType()));
            } catch (SQLException e) {
                throw new DaoException("Error storeGroups in groups dao", e);
            }
        }
        else{ //non presente nel db quindi eseguo una insert
            try{
                this.insertMaterial.setString(1,addSlashes(material.getDescription_ita()));
                this.insertMaterial.setString(2,addSlashes(material.getDescription_eng()));
                this.insertMaterial.setTimestamp(3,material.getData());
                this.insertMaterial.setDouble(4,material.getSize());
                this.insertMaterial.setString(5,addSlashes(material.getType()));
            } catch (SQLException e) {
                throw new DaoException("Error a in groups dao", e);
            }
        }

    }

    @Override
    public void deleteMaterial(Material material) throws DaoException {
        try{
            this.deleteMaterialById.setInt(1,material.getId());
            this.deleteMaterialById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
