package dao.interfaces;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dao.data.DaoData;
import dao.exception.DaoException;
import model.ModuleCourse;

/**
 * Created by max on 26/05/17.
 */
public interface ModuleCourseDao extends DaoData{

    public ModuleCourse getModuleCourse() throws DaoException;

    public void insertModuleCourse( ModuleCourse moduleCourse) throws DaoException;

    public void updateModuleCourse(ModuleCourse moduleCourse) throws DaoException;

    public ModuleCourse selectModuleCourseById(int course_id, int course_module_it) throws DaoException;

    public void deleteModuleCourse(ModuleCourse moduleCourse) throws DaoException;

}
