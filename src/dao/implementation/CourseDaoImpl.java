package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.SelectDaoException;
import dao.interfaces.CourseDao;
import model.Course;
import model.StudyCourse;
import model.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.security.DaoSecurity.stripSlashes;

/**
 * Created by antonello on 09/06/17.
 */
public class CourseDaoImpl extends DaoDataMySQLImpl implements CourseDao{

    private PreparedStatement insertCourse,
            selectCourseById,
            selectCourseByName,
            selectCourseByCode,
            selectCourseByYear,
            selectCourseByCfu,
            selectCourseBySector,
            selectCourseByLanguage,
            selectCourseBySemester,
            updateCourse,
            deleteCourseById,
            deleteCourseByName,
            selectCourseByStudyCourse,
            selectCourses;

    public CourseDaoImpl(DataSource datasource) {
        super(datasource);
    }

    public void init() throws DaoException{
        try{

            super.init();

            this.insertCourse = connection.prepareStatement("INSERT INTO course" +
                    " VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            this.selectCourseById = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE id = ?");

            this.selectCourseByName = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE name = ?");

            this.selectCourseByCode = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE code = ?");

            this.selectCourseByYear = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE year = ?");

            this.selectCourseByCfu = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE cfu = ?");

            this.selectCourseBySector = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE sector = ?");

            this.selectCourseByLanguage = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE language = ?");

            this.selectCourseBySemester = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE semester = ?");

            this.updateCourse = connection.prepareStatement("UPDATE USER " +
                    "SET code = ?," +
                    "name = ?," +
                    "year = ?," +
                    "cfu = ?,"+
                    "sector = ?,"+
                    "language = ?,"+
                    "semester = ?,"+
                    "prerequisite_ita = ?"+
                    "prerequisite_eng = ?"+
                    "goals_ita = ?"+
                    "goals_eng = ?"+
                    "exame_mode_ita = ?"+
                    "exam_mode_eng = ?"+
                    "teaching_mode_ita=?"+
                    "teaching_mode_eng=?"+
                    "syllabus_ita=?"+
                    "syllabus_eng=?"+
                    "note_ita=?"+
                    "note_eng=?"+
                    "knowledge_ita=?"+
                    "knowledge_eng=?"+
                    "application_ita=?"+
                    "application_eng=?"+
                    "evaluation_ita=?"+
                    "evaluation_eng=?"+
                    "communication_ita=?"+
                    "communication_eng=?"+
                    "lifelog_learning_skills_ita=?"+
                    "lifelog_learning_skills_eng=?"+
                    "external_material_ita=?"+
                    "external_material_eng=?" +
                    "WHERE id=?");


            this.deleteCourseById = connection.prepareStatement("DELETE FROM course" +
                    "                                               WHERE id=?");

            this.selectCourseByStudyCourse = connection.prepareStatement("SELECT * " +
                    "                                                       FROM course " +
                    "                                                       LEFT JOIN course_studyCourse " +
                    "                                                       ON course.id = course_studyCourse.course_id " +
                    "                                                       WHERE course_studyCourse.studyCourse_id=? ");

            this.selectCourses = connection.prepareStatement("SELECT *" +
                    "                                               FROM course");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course getCourse() {
        return new Course(this);
    }

    @Override
    public Course getCourseById(int idCourse) throws DaoException {
        Course course = null;

        try {

            this.selectCourseById.setInt(1, idCourse);

            ResultSet rs = this.selectCourseById.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public Course getCourseByName(String name) throws DaoException {
        Course course = this.getCourse();

        try {

            this.selectCourseByName.setString(1, name);

            ResultSet rs = this.selectCourseByName.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new SelectDaoException("Error getCourseByName", e);
        }

        return course;
    }

    @Override
    public Course getCourseByCode(String code) throws DaoException {
        Course course = null;

        try {

            this.selectCourseByCode.setString(1, code);

            ResultSet rs = this.selectCourseByCode.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SelectDaoException("Error getCourseByCode", e);
        }

        return course;
    }

    /*
        DEVE TORNARE UNA LISTA DI CORSI
     */
    @Override
    public Course getCourseByYear(String year) throws DaoException {
        Course course = null;

        try {

            this.selectCourseByYear.setString(1, year);

            ResultSet rs = this.selectCourseByYear.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }
    /*
        DEVE TORNARE UNA LISTA DI CORSI
     */
    @Override
    public Course getCourseByCfu(int cfu) throws DaoException {
        Course course = null;

        try {

            this.selectCourseByCfu.setInt(1, cfu);

            ResultSet rs = this.selectCourseByCfu.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    /*
        DEVE TORNARE UNA LISTA DI CORSI
     */
    @Override
    public Course getCourseBySector(String sector) throws DaoException {
        Course course = null;

        try {

            this.selectCourseBySector.setString(1, sector);

            ResultSet rs = this.selectCourseBySector.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    /*
        DEVE TORNARE UNA LISTA DI CORSI
     */
    @Override
    public Course getCourseByLanguage(String language) throws DaoException {
        Course course = null;

        try {

            this.selectCourseByLanguage.setString(1, language);

            ResultSet rs = this.selectCourseByLanguage.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    /*
        DEVE TORNARE UNA LISTA DI CORSI
     */
    @Override
    public Course getCourseBySemester(int semester) throws DaoException {
        Course course = null;

        try {

            this.selectCourseBySemester.setInt(1, semester);

            ResultSet rs = this.selectCourseBySemester.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    //lo store puo' essere il metodo che integra sia l'update che lo store,
    //si effettua la insert se nell'oggetto course creato non e' presente l'id, quindi si presuppone che sia nuovo,
    //update altrimenti
    @Override
    public Course storeCourse(Course course) throws DaoException {
        //non ho capito a che serve
        return null;
    }

    @Override
    public void deleteCourse(Course course) throws DaoException {
        try {
            this.deleteCourseById.setInt(1, course.getIdCourse());
            this.deleteCourseById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course getCourseByUser(User user) throws DaoException {
        return null;
    }

    @Override
    public List<Course> getCourseByStudyCourse(StudyCourse studyCourse) throws DaoException  {

        //inizializzo una lista di corsi
        List<Course> courses = new ArrayList <>();

        try {

            //setto l'id del corso di studi
            this.selectCourseByStudyCourse.setInt(1, studyCourse.getId());

            //eseguo la query
            ResultSet rs = this.selectCourseByStudyCourse.executeQuery();

            //ciclo il risultato della query
            while (rs.next()){

                //aggiungo alla lista il corso nella riga attuale della query
                courses.add(this.generateCourse(rs));

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getCourseByStudyCourse",e);
        }

        //restituisco la lista
        return courses;
    }

    @Override
    public List <Course> getCourses() throws DaoException {

        List<Course> courses = new ArrayList <>();

        try {
            ResultSet rs = this.selectCourses.executeQuery();

            while (rs.next()){

                courses.add(this.generateCourse(rs));

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Eror getCourses", e);
        }

        return courses;

    }


    public Course generateCourse(ResultSet rs) throws DaoException {

        Course course = this.getCourse();

        try {

            course.setIdCourse(rs.getInt("id"));
            course.setCode(stripSlashes(rs.getString("code")));
            course.setName(stripSlashes(rs.getString("name")));
            course.setYear(stripSlashes(rs.getString("year")));
            course.setCfu(rs.getInt("cfu"));
            course.setSector(stripSlashes((rs.getString("sector"))));
            course.setLanguage(stripSlashes(rs.getString("language")));
            course.setSemester(rs.getInt("semester"));
            course.setPrerequisite_ita(stripSlashes(rs.getString("prerequisite_ita")));
            course.setPrerequisite_eng(stripSlashes(rs.getString("prerequisite_eng")));
            course.setGoals_ita(stripSlashes(rs.getString("goals_ita")));
            course.setGoals_eng(stripSlashes(rs.getString("goals_eng")));
            course.setExame_mode_ita(stripSlashes(rs.getString("exame_mode_ita")));
            course.setExame_mode_eng(stripSlashes(rs.getString("exame_mode_eng")));
            course.setTeaching_mode_ita(stripSlashes(rs.getString("teaching_mode_ita")));
            course.setTeaching_mode_eng(stripSlashes(rs.getString("teaching_mode_eng")));
            course.setSyllabus_ita(stripSlashes(rs.getString("syllabus_ita")));
            course.setSyllabus_eng(stripSlashes(rs.getString("syllabus_eng")));
            course.setNote_ita(stripSlashes(rs.getString("note_ita")));
            course.setNote_eng(stripSlashes(rs.getString("note_eng")));
            course.setKnowledge_ita(stripSlashes(rs.getString("knowledge_ita")));
            course.setKnowledge_eng(stripSlashes(rs.getString("knowledge_eng")));
            course.setApplication_ita(stripSlashes(rs.getString("application_ita")));
            course.setApplication_eng(stripSlashes(rs.getString("application_eng")));
            course.setEvaluation_ita(stripSlashes(rs.getString("evaluation_ita")));
            course.setEvaluation_eng(stripSlashes(rs.getString("evaluation_eng")));
            course.setCommunication_ita(stripSlashes(rs.getString("communication_ita")));
            course.setCommunication_eng(stripSlashes(rs.getString("communication_eng")));
            course.setLifelog_learning_skills_ita(stripSlashes(rs.getString("lifelog_learning_skills_ita")));
            course.setLifelog_learning_skills_eng(stripSlashes(rs.getString("lifelog_learning_skills_eng")));
            course.setExternal_material_ita(stripSlashes(rs.getString("external_material_ita")));
            course.setExternal_material_ita(stripSlashes(rs.getString("external_material_eng")));

        } catch (SQLException e) {
            e.printStackTrace();

            throw new SelectDaoException("Error generateService", e );
        }
        return course;
    }


    //METODO DESTROY CHE CHIUDE LA CONNESSIONE E TUTTE LE QUERY

}
