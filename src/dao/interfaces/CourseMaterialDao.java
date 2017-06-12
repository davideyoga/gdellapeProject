package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Course;
import model.CourseMaterial;
import model.Material;

import java.util.List;

/**
 * Created by max on 30/05/17.
 */
public interface CourseMaterialDao extends DaoData {

    public CourseMaterial getCourseMaterial() throws DaoException;

    public void insertCourseMaterial(CourseMaterial courseMaterial) throws DaoException;

    public List<CourseMaterial> selectCourseMaterialByCourseId(Course course) throws DaoException;

    public List<CourseMaterial> selectCourseMaterialByMaterialId(Material material) throws DaoException;

    public void deleteCourseMaterial(CourseMaterial courseMaterial) throws DaoException;
}
