package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.CourseMaterialDao;
import model.Course;
import model.CourseMaterial;
import model.Material;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by max on 30/05/17.
 */
public class CourseMaterialDaoImpl extends DaoDataMySQLImpl implements CourseMaterialDao{

    PreparedStatement insertCourseMaterial,
                      selectCourseMaterialByCourseId,
                      selectCourseMaterialByMaterialId,
                      deleteCourseMaterial;

    public CourseMaterialDaoImpl(DataSource datasource) {
        super(datasource);
    }

    @Override
    public void init() throws DaoException{
        super.init();

        try {
            this.insertCourseMaterial = connection.prepareStatement("INSERT");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CourseMaterial getCourseMaterial() throws DaoException {
        return null;
    }

    @Override
    public void insertCourseMaterial(CourseMaterial courseMaterial) throws DaoException {

    }

    @Override
    public List<CourseMaterial> selectCourseMaterialByCourseId(Course course) throws DaoException {
        return null;
    }

    @Override
    public List<CourseMaterial> selectCourseMaterialByMaterialId(Material material) throws DaoException {
        return null;
    }

    @Override
    public void deleteCourseMaterial(CourseMaterial courseMaterial) throws DaoException {

    }
}
