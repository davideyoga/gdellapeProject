package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.InitDaoException;
import dao.exception.SelectDaoException;
import dao.interfaces.StudyCourseDao;
import model.Course;
import model.StudyCourse;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Davide Micarelli
 */
public class StudyCourseDaoImpl extends DaoDataMySQLImpl implements StudyCourseDao{

    private PreparedStatement   insertStudyCourse,
                                selectStudyCourseById,
                                selectStudyCourseByName,
                                selectStudyCourseByCode,
                                updateStudyCourse,
                                deleteStudyCourse,
                                selectStudyCourseByCourse;


    public StudyCourseDaoImpl(DataSource datasource) {
        super(datasource);
    }

    /**
     * Precompilazione delle query
     * @throws DaoException
     */
    @Override
    public void init() throws DaoException {
        try {

            this.insertStudyCourse = connection.prepareStatement("INSERT INTO studyCourse" +
                    "                                          VALUES (NULL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            this.selectStudyCourseById = connection.prepareStatement("SELECT *" +
                    "                                                     FROM studyCourse" +
                    "                                                     WHERE id=?");

            this.selectStudyCourseByName = connection.prepareStatement("SELECT *" +
                    "                                                     FROM studyCourse" +
                    "                                                     WHERE name=?");


            this.selectStudyCourseByCode = connection.prepareStatement("SELECT *" +
                    "                                                     FROM studyCourse" +
                    "                                                     WHERE code=?");

            this.updateStudyCourse = connection.prepareStatement("UPDATE studyCourse" +
                    "                                           SET code=?" +
                    "                                               name=?" +
                    "                                               description_ita=?" +
                    "                                               description_eng=?" +
                    "                                               department_ita=?" +
                    "                                               department_eng=?" +
                    "                                               level_ita=?" +
                    "                                               level_eng=?" +
                    "                                               duration=?" +
                    "                                               class=?" +
                    "                                               seat=?" +
                    "                                               accessType_ita=?" +
                    "                                               accessType_eng=?" +
                    "                                               language_ita=?" +
                    "                                               language_eng=?" +
                    "                                           WHERE id=?");

            this.deleteStudyCourse = connection.prepareStatement("DELETE FROM studyCourse" +
                    "                                               WHERE id=?");

            this.selectStudyCourseByCourse = connection.prepareStatement("SELECT * " +
                    "                                                       FROM studyCourse " +
                    "                                                       LEFT JOIN course_studyCourse " +
                    "                                                       ON studyCourse.id = course_studyCourse.studyCourse_id " +
                    "                                                       WHERE course_studyCourse.course_id=? ");

        } catch (SQLException e) {
            throw new InitDaoException( "Error Init in StudyCourseDaoImpl", e);
        }

    }

    /**
     * Torna studyCourse vuoto
     * @return
     */
    @Override
    public StudyCourse getStudyCouse() {
        return new StudyCourse(this);
    }

    /**
     * Torna StudyCourse con tale id se presente nel db
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    public StudyCourse getStudyCourseById(int id) throws DaoException {

        StudyCourse studyCourse = null;

        try {

            this.selectStudyCourseById.setInt(1, id);

            ResultSet rs = this.selectStudyCourseById.executeQuery();

            if(rs.next()){
                studyCourse = generateStudyCourse(rs);

            }else return null;

        } catch (SQLException e) {
            throw new SelectDaoException("Error getStudyCourseById", e);
        }

        return studyCourse;
    }

    /**
     * Torna StudyCourse con tale codice se presente nel db
     * @param code
     * @return
     * @throws DaoException
     */
    @Override
    public StudyCourse getStudyCourseByCode(String code) throws DaoException {

        StudyCourse studyCourse = null;

        try {

            this.selectStudyCourseByCode.setString(1, code);

            ResultSet rs = this.selectStudyCourseById.executeQuery();

            if(rs.next()){
                studyCourse = generateStudyCourse(rs);

            }else return null;

        } catch (SQLException e) {
            throw new SelectDaoException("Error getStudyCourseByCode", e);
        }

        return studyCourse;
    }

    /**
     * Torna StudyCourse con tale name se presente nel db
     * @param name
     * @return
     * @throws DaoException
     */
    @Override
    public StudyCourse getStudyCourseByName(String name) throws DaoException {

        StudyCourse studyCourse = null;

        try {

            this.selectStudyCourseByName.setString(1, name);

            ResultSet rs = this.selectStudyCourseById.executeQuery();

            if(rs.next()){
                studyCourse = generateStudyCourse(rs);

            }else return null;

        } catch (SQLException e) {
            throw new SelectDaoException("Error getStudyCourseByCode", e);
        }

        return studyCourse;
    }


    @Override
    public void storeStudyCourse(StudyCourse studyCourse) throws DaoException {

    }

    @Override
    public void deleteStudyCourse(StudyCourse studyCourse) throws DaoException {

    }

    @Override
    public StudyCourse generateStudyCourse(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    public List<StudyCourse> getStudyCourseByCourse(Course course) throws DaoException {
        return null;
    }

    @Override
    public void destroy() throws DaoException {

    }
}
