package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.SelectDaoException;
import dao.interfaces.MaterialDao;
import model.Course;
import model.Material;

import javax.sql.DataSource;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            insertCourseMaterial,
            selectMaterialByCourse,
            deleteMaterialById;

    public MaterialDaoImpl(DataSource datasource) {
        super(datasource);
    }

    public void init() throws DaoException{

        try{
            super.init();

            this.insertMaterial = connection.prepareStatement("INSERT INTO material" +
                                                                    " VALUES (NULL,?,?,?,CURRENT_TIMESTAMP,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            this.selectMaterialById = connection.prepareStatement("SELECT *" + " FROM material" + " WHERE id=?");

            this.updateMaterial = connection.prepareStatement("UPDATE material" + " SET description_ita=?" +
                                                                 "description_eng=? " + " data = ?" + " size = ?"
                                                                 + " type = ?");

            this.insertCourseMaterial = connection.prepareStatement("INSERT INTO course_material" +
                    "                                                       VALUES (?,?)");


            this.selectMaterialByCourse = connection.prepareStatement("SELECT * FROM material" +
                    "                                                       LEFT JOIN course_material" +
                    "                                                       ON material.id = course_material.material_id" +
                    "                                                       WHERE course_material.course_id = ?");

            this.deleteMaterialById = connection.prepareStatement(" DELETE FROM material" +
                    "                                                            WHERE id=?");

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

                material = this.generateMaterial(rs);

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
    public int storeMaterial(Material material) throws DaoException {

        if( material.getId() > 0){
            try{
                this.updateMaterial.setString(1,addSlashes(material.getName()));
                this.updateMaterial.setString(2,addSlashes(material.getDescription_ita()));
                this.updateMaterial.setString(3,addSlashes(material.getDescription_eng()));
                this.updateMaterial.setLong(4,material.getSize());
                this.updateMaterial.setString(5,material.getType());
                this.updateMaterial.setString(6,addSlashes(material.getRoute()));

                this.updateMaterial.executeUpdate();

                return material.getId();

            } catch (SQLException e) {

                throw new DaoException("Error storeGroups in groups dao", e);
            }
        }
        else{ //non presente nel db quindi eseguo una insert
            try{
                this.insertMaterial.setString(1,addSlashes(material.getName()));
                this.insertMaterial.setString(2,addSlashes(material.getDescription_ita()));
                this.insertMaterial.setString(3,addSlashes(material.getDescription_eng()));
                this.insertMaterial.setLong(4,material.getSize());
                this.insertMaterial.setString(5,material.getType());
                this.insertMaterial.setString(6,addSlashes(material.getRoute()));

               this.insertMaterial.executeUpdate();

               //estraggo il resultset per estrarne l'id appena insertito
                ResultSet keys = insertMaterial.getGeneratedKeys();

                //inizializzo un valore dell'id per ritornarlo
                int key = 0;

                if (keys.next()) {
                    //i campi del record sono le componenti della chiave
                    //(nel nostro caso, un solo intero)
                    //the record fields are the key componenets
                    //(a single integer in our case)
                    key = keys.getInt(1);
                }

                return key;

            } catch (SQLException e) {

                throw new DaoException("Error a in groups dao", e);
            }
        }

    }



    @Override
    public void deleteMaterial(Material material, String path) throws DaoException {
        try{

            this.deleteMaterialOnServer(material, path);

            this.deleteMaterialById.setInt(1,material.getId());
            this.deleteMaterialById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void addConnectionWithCourseMaterial(Course course, Material material) throws DaoException {

        try {

            this.insertCourseMaterial.setInt(1, course.getIdCourse());
            this.insertCourseMaterial.setInt(2, material.getId());

            this.insertCourseMaterial.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

            throw new DaoException("Error in addConnectionWithCourseMaterial", e);
        }

    }

    @Override
    public List<Material> getMaterialByCourse(Course course) throws DaoException {

        List<Material> materials = new ArrayList<>();

        try {

            this.selectMaterialByCourse.setInt(1, course.getIdCourse());

            ResultSet rs = this.selectMaterialByCourse.executeQuery();

            while(rs.next()){

                materials.add(generateMaterial(rs));
            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getMaterialByCourse", e);
        }

        return materials;

    }

    private Material generateMaterial(ResultSet rs) throws DaoException {

        Material material = this.getMaterial();

        try {

            material.setId(rs.getInt("id"));
            material.setName(rs.getString("name"));
            material.setDescription_ita(stripSlashes(rs.getString("description_ita")));
            material.setDescription_eng(stripSlashes(rs.getString("description_eng")));
            material.setData(rs.getTimestamp("date"));
            material.setSize(rs.getLong("size"));
            material.setType(stripSlashes(rs.getString("type")));
            material.setRoute(rs.getString("route"));

        } catch (SQLException e) {
            e.printStackTrace();

            throw new DaoException("Error in generateMaterial",e);
        }

        return material;
    }


    private void deleteMaterialOnServer(Material material, String path) throws DaoException {

        ///etc/apache-tomcat-8.5.14/download

        try{

            String newPath = path + "/" + material.getRoute();

            File file = new File(newPath);

            file.delete();

        }catch(Exception e){

            e.printStackTrace();

            throw new DaoException("Error in deleteMaterialOnServer");

        }

    }

}
