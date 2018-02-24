package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.*;
import dao.interfaces.StudyCourseDao;
import model.Course;
import model.StudyCourse;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;

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
                                selectStudyCourseByCourse,
                                selectStudyCourses,
                                insertCourseStudyCourseAssociation,
                                selectCfuType,
                                updateCourseStudyCourse,
                                deleteCourseStudyCourseAssociation;


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

            super.init();

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
                    "                                           SET code=?," +
                    "                                               name=?," +
                    "                                               description_ita=?," +
                    "                                               description_eng=?," +
                    "                                               department_ita=?," +
                    "                                               department_eng=?," +
                    "                                               level_ita=?," +
                    "                                               level_eng=?," +
                    "                                               duration=?," +
                    "                                               class=?," +
                    "                                               seat=?," +
                    "                                               accessType_ita=?," +
                    "                                               accessType_eng=?," +
                    "                                               language_ita=?," +
                    "                                               language_eng=?" +
                    "                                           WHERE id=?");

            this.deleteStudyCourse = connection.prepareStatement("DELETE FROM studyCourse" +
                    "                                               WHERE id=?");

            this.selectStudyCourseByCourse = connection.prepareStatement("SELECT * " +
                    "                                                       FROM studyCourse " +
                    "                                                       LEFT JOIN course_studyCourse " +
                    "                                                       ON studyCourse.id = course_studyCourse.studyCourse_id " +
                    "                                                       WHERE course_studyCourse.course_id=? ");

            this.selectStudyCourses = connection.prepareStatement("SELECT * " +
                    "                                                       FROM studyCourse");

            this.insertCourseStudyCourseAssociation = connection.prepareStatement("INSERT INTO course_studyCourse" +
                    "                                                                       (`course_id`, `studyCourse_id`, `cfuType`)" +
                    "                                                                       VALUES (?,?,?)");

            this.selectCfuType = connection.prepareStatement("SELECT *" +
                    "                                               FROM course_studyCourse" +
                    "                                               WHERE course_id=?" +
                    "                                               AND studyCourse_id=?");

            this.updateCourseStudyCourse = connection.prepareStatement("UPDATE course_studyCourse" +
                    "                                                           SET course_id=?," +
                    "                                                               studyCourse_id=?," +
                    "                                                               cfuType=?" +
                    "                                                               WHERE course_id=?" +
                    "                                                               AND studyCourse_id=?");



            this.deleteCourseStudyCourseAssociation = connection.prepareStatement("DELETE FROM course_studyCourse" +
                    "                                                                       WHERE course_id=?" +
                    "                                                                       AND studyCourse_id=?");

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

        StudyCourse studyCourse = this.getStudyCouse();

        try {

            this.selectStudyCourseById.setInt(1, id);

            ResultSet rs = this.selectStudyCourseById.executeQuery();

            if(rs.next()){

                studyCourse = generateStudyCourse(rs);

            }else return null;

        } catch (SQLException e) {
            e.printStackTrace();
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

        StudyCourse studyCourse = this.getStudyCouse();

        try {

            this.selectStudyCourseByCode.setString(1, code);

            ResultSet rs = this.selectStudyCourseByCode.executeQuery();

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

        StudyCourse studyCourse = this.getStudyCouse();

        try {

            this.selectStudyCourseByName.setString(1, name);

            ResultSet rs = this.selectStudyCourseByName.executeQuery();

            if(rs.next()){

                studyCourse = generateStudyCourse(rs);

            }else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SelectDaoException("Error getStudyCourseByCode", e);
        }

        return studyCourse;
    }


    /**
     * dato un corso di studi, se presenta un id, quindi gia' risulta salvato nel database, viene effettuato un update,
     * altrimenti una insert
     * @param studyCourse
     * @throws DaoException
     */
    @Override
    public void storeStudyCourse(StudyCourse studyCourse) throws DaoException {

        if( studyCourse.getId() > 0 ){ //lo studyCourse ha gia' un id, quindi e' gia nel database, eseguo un update

            try {

                this.updateStudyCourse.setString(1, studyCourse.getCode() );
                this.updateStudyCourse.setString(2, addSlashes(studyCourse.getName()));
                this.updateStudyCourse.setString(3, addSlashes(studyCourse.getDescription_ita()));
                this.updateStudyCourse.setString(4, addSlashes(studyCourse.getDescription_eng()));
                this.updateStudyCourse.setString(5, addSlashes(studyCourse.getDepartment_ita()));
                this.updateStudyCourse.setString(6, addSlashes(studyCourse.getDepartment_eng()));
                this.updateStudyCourse.setInt(7, (studyCourse.getLevel_ita()));
                this.updateStudyCourse.setInt(8, studyCourse.getLevel_eng());
                this.updateStudyCourse.setInt(9, studyCourse.getDuration());
                this.updateStudyCourse.setString(10, addSlashes(studyCourse.getClasses()));
                this.updateStudyCourse.setString(11, addSlashes(studyCourse.getSeat()));
                this.updateStudyCourse.setString(12, addSlashes(studyCourse.getAccessType_ita()));
                this.updateStudyCourse.setString(13, addSlashes(studyCourse.getAccessType_eng()));
                this.updateStudyCourse.setString(14, addSlashes(studyCourse.getLanguage_ita()));
                this.updateStudyCourse.setString(15, addSlashes(studyCourse.getLanguage_eng()));
                this.updateStudyCourse.setInt(16, studyCourse.getId());

                this.updateStudyCourse.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();

                throw new UpdateDaoException("Error storeStudyCourse", e);
            }


        }else{ //user e' un nuovo utente
            //eseguo una insert

            try {

                this.insertStudyCourse.setString(1, studyCourse.getCode() );
                this.insertStudyCourse.setString(2, addSlashes(studyCourse.getName()));
                this.insertStudyCourse.setString(3, addSlashes(studyCourse.getDescription_ita()));
                this.insertStudyCourse.setString(4, addSlashes(studyCourse.getDescription_eng()));
                this.insertStudyCourse.setString(5, addSlashes(studyCourse.getDepartment_ita()));
                this.insertStudyCourse.setString(6, addSlashes(studyCourse.getDepartment_eng()));
                this.insertStudyCourse.setInt(7, (studyCourse.getLevel_ita()));
                this.insertStudyCourse.setInt(8, studyCourse.getLevel_eng());
                this.insertStudyCourse.setInt(9, studyCourse.getDuration());
                this.insertStudyCourse.setString(10, addSlashes(studyCourse.getClasses()));
                this.insertStudyCourse.setString(11, addSlashes(studyCourse.getSeat()));
                this.insertStudyCourse.setString(12, addSlashes(studyCourse.getAccessType_ita()));
                this.insertStudyCourse.setString(13, addSlashes(studyCourse.getAccessType_eng()));
                this.insertStudyCourse.setString(14, addSlashes(studyCourse.getLanguage_ita()));
                this.insertStudyCourse.setString(15, addSlashes(studyCourse.getLanguage_eng()));

                this.insertStudyCourse.executeUpdate();

            } catch (SQLException e) {
                throw new InsertDaoException("Error storeStudyCourse", e);
            }

        }
    }

    /**
     * Esegue la delete sullo StudyCourse passato in base al suo id
     * @param studyCourse
     * @throws DaoException
     */
    @Override
    public void deleteStudyCourse(StudyCourse studyCourse) throws DaoException {

        try {

            this.deleteStudyCourse.setInt(1, studyCourse.getId());

            this.deleteStudyCourse.executeUpdate();

        } catch (SQLException e) {
            throw new DeleteDaoException("Error deleteStudyCourse", e);
        }

    }

    /**
     * Torna StudyCourse estratto da un dato ResultSet
     * @param rs
     * @return
     * @throws DaoException
     */
    @Override
    public StudyCourse generateStudyCourse(ResultSet rs) throws DaoException {

        StudyCourse studyCourse = this.getStudyCouse();

        try {

            studyCourse.setId( rs.getInt("id"));
            studyCourse.setCode(stripSlashes(rs.getString("code")));
            studyCourse.setName(stripSlashes(rs.getString("name")));
            studyCourse.setDescription_ita(stripSlashes(rs.getString("description_ita")));
            studyCourse.setDescription_eng(stripSlashes(rs.getString("description_eng")));
            studyCourse.setDepartment_ita(stripSlashes(rs.getString("department_ita")));
            studyCourse.setDepartment_eng(stripSlashes(rs.getString("department_eng")));
            studyCourse.setLevel_ita(rs.getInt("level_ita"));
            studyCourse.setLevel_eng(rs.getInt("level_eng"));
            studyCourse.setDuration(rs.getInt("duration"));
            studyCourse.setClasses(stripSlashes(rs.getString("class")));
            studyCourse.setSeat(stripSlashes(rs.getString("seat")));
            studyCourse.setAccessType_ita(stripSlashes(rs.getString("accessType_ita")));
            studyCourse.setAccessType_eng(stripSlashes(rs.getString("accessType_eng")));
            studyCourse.setLanguage_ita(stripSlashes(rs.getString("language_ita")));
            studyCourse.setLanguage_eng(stripSlashes(rs.getString("language_eng")));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new GenerateDaoException("Error generateStudyCourse", e);
        }

        return studyCourse;
    }

    /**
     * Torna la lista dei corsi di studio in cui e' presente il corso passaato
     * @param course
     * @return
     * @throws DaoException
     */
    @Override
    public List<StudyCourse> getStudyCourseByCourse(Course course) throws DaoException {

        List<StudyCourse> listStudyCourse = new ArrayList <>();

        try {

            this.selectStudyCourseByCourse.setInt(1, course.getIdCourse());

            ResultSet rs = this.selectStudyCourseByCourse.executeQuery();

            while (rs.next()){

                listStudyCourse.add( this.generateStudyCourse(rs));

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getStudyCourseByCourse", e);
        }

        return listStudyCourse;
    }

    @Override
    public List <StudyCourse> getAllStudyCourses() throws DaoException {

        List<StudyCourse> studyCourses = new ArrayList <>();

        try {
            ResultSet rs = this.selectStudyCourses.executeQuery();

            while (rs.next()){

                studyCourses.add(this.generateStudyCourse(rs));
            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getAllStudyCourses", e);
        }

        return studyCourses;
    }

    @Override
    public void insertCourseStudyCourseConnection(Course course, StudyCourse studyCourse, String cfuType) throws DaoException{

        try {

            this.insertCourseStudyCourseAssociation.setInt(1, course.getIdCourse());
            this.insertCourseStudyCourseAssociation.setInt(2, studyCourse.getId());
            this.insertCourseStudyCourseAssociation.setString(3, cfuType);

            this.insertCourseStudyCourseAssociation.executeUpdate();


        } catch (SQLException e) {
            throw new InsertDaoException("Error insertCourseStudyCourseConnection", e);
        }

    }

    @Override
    public void deleteCourseStudyCourseConnection(Course course, StudyCourse studyCourse) throws DaoException {
        try {

            this.deleteCourseStudyCourseAssociation.setInt(1, course.getIdCourse());
            this.deleteCourseStudyCourseAssociation.setInt(2, studyCourse.getId());

            this.deleteCourseStudyCourseAssociation.executeUpdate();


        } catch (SQLException e) {
            throw new InsertDaoException("Error insertCourseStudyCourseConnection", e);
        }
    }

    @Override
    public String getCfuType(Course course, StudyCourse studyCourse) throws DaoException {

        String cfuType = "";

        try {

            this.selectCfuType.setInt(1, course.getIdCourse());
            this.selectCfuType.setInt(2, studyCourse.getId());

            ResultSet rs = this.selectCfuType.executeQuery();

            if (rs.next()) {

                cfuType = rs.getString("cfuType");
            }

        }catch (SQLException e) {
            throw new InsertDaoException("Error insertCourseStudyCourseConnection", e);
        }

        return cfuType;
    }

    @Override
    public void updateCourseStudyCourse(Course course, StudyCourse studyCourse, String cfuType) throws DaoException {


        try {
            this.updateCourseStudyCourse.setInt(1, course.getIdCourse());
            this.updateCourseStudyCourse.setInt(2, studyCourse.getId());
            this.updateCourseStudyCourse.setString(3, cfuType);

            this.updateCourseStudyCourse.setInt(4, course.getIdCourse());
            this.updateCourseStudyCourse.setInt(5, studyCourse.getId());

            this.updateCourseStudyCourse.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            throw new SelectDaoException("Error updateCourseStudyCourse", e);
        }

    }

    /**
     * Chiude connession e query precompilate
     * @throws DaoException
     */
    @Override
    public void destroy() throws DaoException {

        try {

            this.insertStudyCourse.close();
            this.selectStudyCourseById.close();
            this.selectStudyCourseByName.close();
            this.selectStudyCourseByCode.close();
            this.updateStudyCourse.close();
            this.deleteStudyCourse.close();
            this.selectStudyCourseByCourse.close();
            this.selectStudyCourses.close();
            this.insertCourseStudyCourseAssociation.close();
            this.selectCfuType.close();
            this.updateCourseStudyCourse.close();

            super.destroy();

        } catch (SQLException e) {

            throw new DestroyDaoException("Error destroy in StudyCourse", e);
        }
    }
}
