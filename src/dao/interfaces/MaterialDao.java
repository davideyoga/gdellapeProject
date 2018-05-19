package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Course;
import model.Material;

import java.util.List;

/**
 * Created by max on 25/05/17.
 */
public interface MaterialDao extends DaoData {

    public Material getMaterial();

    public Material getMaterialById( int idMaterial) throws DaoException;

    public int storeMaterial ( Material material ) throws DaoException;

    public void deleteMaterial (Material material, String path ) throws DaoException;

    public void addConnectionWithCourseMaterial(Course course, Material material) throws DaoException;

    public List<Material> getMaterialByCourse(Course course) throws DaoException;
}
