package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.PreparatoryCourseDao;
import model.PreparatoryCourse;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by max on 29/05/17.
 */
public class PreparatoryCourseDaoImpl extends DaoDataMySQLImpl implements PreparatoryCourseDao{

    PreparedStatement insertPreparatoryCourse,
                      updatePreparatoryCourse,
                      selectPreparatoryCourse,
                      deletePreparatoryCourse;

    public PreparatoryCourseDaoImpl(DataSource datasource) {
        super(datasource);
    }

    @Override
    public void init() throws DaoException {
        try{

            super.init();

            this.insertPreparatoryCourse = connection.prepareStatement("INSERT INTO preparatoryCourse" +
                                                                          " VALUES (?,?)");

            this.updatePreparatoryCourse = connection.prepareStatement("UPDATE preparatoryCourse" +
                                                                          " SET course_id = ?" +
                                                                          " course_preparatory_id = ?" +
                                                                          " WHERE course_id = ?"+
                                                                          " AND course_preparatory_id = ?");

            this.selectPreparatoryCourse = connection.prepareStatement("SELECT preparatoryCourse" +
                                                                              " WHERE course_id = ?" +
                                                                              " AND course_preparatory_id = ?");

            this.deletePreparatoryCourse = connection.prepareStatement("DELETE FROM preparatoryCourse" +
                                                                          " WHERE course_id = ?" +
                                                                          " AND course_preparatory = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PreparatoryCourse getPreparatoryCourse() {
        return new PreparatoryCourse(this);
    }

    @Override
    public void insertPreparatoryCourse(PreparatoryCourse preparatoryCourse) throws DaoException {

    }

    @Override
    public void updatePreparatoryCourse(PreparatoryCourse preparatoryCourse) throws DaoException {

    }

    @Override
    public PreparatoryCourse selectPreparatoryCourse(int course_id, int preparatory_course_id) throws DaoException {
        return null;
    }

    @Override
    public void deletePreparatoryCourse(PreparatoryCourse preparatoryCourse) throws DaoException {

    }

    public void destroy() throws DaoException{
        try{
            this.insertPreparatoryCourse.close();
            this.updatePreparatoryCourse.close();
            this.selectPreparatoryCourse.close();
            this.deletePreparatoryCourse.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
