package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.ModuleCourseDao;
import model.ModuleCourse;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by max on 26/05/17.
 */
public class ModuleCourseDaoImpl extends DaoDataMySQLImpl implements ModuleCourseDao{

    PreparedStatement insertModuleCourse,
                      updateModuleCourse,
                      selectModuleCourseById,
                      deleteModuleCourse;

    public ModuleCourseDaoImpl(DataSource datasource) {
        super(datasource);
    }

    @Override
    public void init() throws DaoException {
        try {

            super.init();

            this.insertModuleCourse = connection.prepareStatement("INSERT INTO moduleCourse" +
                                                                     " VALUES (?,?)");

//            this.updateModuleCourse = connection.prepareStatement("UPDATE groups_service" +
//                                                                     " SET groups_id=?" + "service_id=?" +
//                                                                     " WHERE groups_id=?" +
//                                                                     " AND service_id=?");
            this.selectModuleCourseById = connection.prepareStatement("SELECT moduleCourse" +
                                                                         " WHERE course_id = ? " +
                                                                         " AND course_module_id = ?");
            this.deleteModuleCourse = connection.prepareStatement("DELETE FROM moduleCourse" +
                                                                     " WHERE course_id = ?" +
                                                                     " AND course_module_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ModuleCourse getModuleCourse(){
        return new ModuleCourse(this);
    }

    @Override
    public void insertModuleCourse(ModuleCourse moduleCourse) throws DaoException {

    }

    @Override
    public void updateModuleCourse(ModuleCourse moduleCourse) throws DaoException {

    }

    @Override
    public ModuleCourse selectModuleCourseById(int course_id, int course_module_it) throws DaoException {
        return null;
    }

    @Override
    public void deleteModuleCourse(ModuleCourse moduleCourse) throws DaoException {

    }

    public void destroy() throws DaoException{
        try{
            this.insertModuleCourse.close();
            this.updateModuleCourse.close();
            this.selectModuleCourseById.close();
            this.deleteModuleCourse.close();

            super.destroy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
