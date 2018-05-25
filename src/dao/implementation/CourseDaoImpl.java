package dao.implementation;

import controller.utility.AccademicYear;
import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.InsertDaoException;
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
            selectCourseByUserAndYear,
            selectCourseByStudyCourseAndYear,
            storeLinkCourseUser,
            selectCourseByUser,
            deleteLinkCourseUser,
            selectCourses,
            selectCourseModulated,
            selectCoursePreparatory,
            selectCourseBorrowed,
            selectCourseByCodeAndYear,
            addLinkCourseModulated,
            addLinkCoursePreparatory,
            addLinkCourseBorrowed,
            deleteLinkCourseModulated,
            deleteLinkCoursePreparatory,
            deleteLinkCourseBorrowed;


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

            this.updateCourse = connection.prepareStatement("UPDATE course " +
                    "SET code=?," +
                    "name=?," +
                    "year=?," +
                    "cfu=?,"+
                    "sector=?,"+
                    "language=?,"+
                    "semester=?,"+
                    "prerequisite_ita=?,"+
                    "prerequisite_eng=?,"+
                    "goals_ita=?,"+
                    "goals_eng=?,"+
                    "exame_mode_ita=?,"+
                    "exame_mode_eng=?,"+
                    "teaching_mode_ita=?,"+
                    "teaching_mode_eng=?,"+
                    "syllabus_ita=?,"+
                    "syllabus_eng=?,"+
                    "note_ita=?,"+
                    "note_eng=?,"+
                    "knowledge_ita=?,"+
                    "knowledge_eng=?,"+
                    "application_ita=?,"+
                    "application_eng=?,"+
                    "evaluation_ita=?,"+
                    "evaluation_eng=?,"+
                    "communication_ita=?,"+
                    "communication_eng=?,"+
                    "lifelog_learning_skills_ita=?,"+
                    "lifelog_learning_skills_eng=?,"+
                    "external_material_ita=?,"+
                    "external_material_eng=? " +
                    "WHERE id=?");




            this.deleteCourseById = connection.prepareStatement("DELETE FROM course" +
                    "                                               WHERE id=?");

            this.selectCourseByStudyCourse = connection.prepareStatement("SELECT * " +
                    "                                                       FROM course " +
                    "                                                       LEFT JOIN course_studyCourse " +
                    "                                                       ON course.id = course_studyCourse.course_id " +
                    "                                                       WHERE course_studyCourse.studyCourse_id=? ");

            this.selectCourseByStudyCourseAndYear = connection.prepareStatement("SELECT * " +
                    "                                                       FROM course " +
                    "                                                       LEFT JOIN course_studyCourse " +
                    "                                                       ON course.id = course_studyCourse.course_id " +
                    "                                                       WHERE course_studyCourse.studyCourse_id=? " +
                    "                                                       AND year=?");

            this.selectCourses = connection.prepareStatement("SELECT *" +
                    "                                               FROM course");


            this.selectCourseByUserAndYear = connection.prepareStatement("SELECT * " +
                    "                                                       FROM course " +
                    "                                                       LEFT JOIN course_user " +
                    "                                                       ON course.id = course_user.course_id " +
                    "                                                       WHERE course_user.user_id=? " +
                    "                                                       AND year=?");

            this.storeLinkCourseUser = connection.prepareStatement("INSERT INTO course_user" +
                    "                                                       VALUES (?,?)");

            this.deleteLinkCourseUser = connection.prepareStatement("DELETE FROM course_user" +
                    "                                                       WHERE course_id=?" +
                    "                                                       AND user_id=?");

            this.selectCourseModulated = connection.prepareStatement("SELECT * FROM course" +
                    "                                                           LEFT JOIN moduleCourse" +
                    "                                                           ON course.id = moduleCourse.course_module_id " +
                    "                                                           WHERE moduleCourse.course_id = ? ");

            this.selectCoursePreparatory = connection.prepareStatement("SELECT * FROM course" +
                    "                                                           LEFT JOIN preparatoryCourse " +
                    "                                                           ON course.id = preparatoryCourse.course_preparatory_id " +
                    "                                                           WHERE preparatoryCourse.course_id = ?");

            this.selectCourseBorrowed = connection.prepareStatement("SELECT * FROM course" +
                    "                                                           LEFT JOIN borrowedCourse " +
                    "                                                           ON course.id = borrowedCourse.course_borrowed_id " +
                    "                                                           WHERE borrowedCourse.course_id = ?");

            this.selectCourseByUser = connection.prepareStatement("SELECT * " +
                    "                                                       FROM course " +
                    "                                                       LEFT JOIN course_user " +
                    "                                                       ON course.id = course_user.course_id " +
                    "                                                       WHERE course_user.user_id=? ");

            this.selectCourseByCodeAndYear = connection.prepareStatement("SELECT * " +
                    "                                                               FROM course" +
                    "                                                               WHERE code=?" +
                    "                                                               AND year=?");


            this.addLinkCourseModulated = connection.prepareStatement("INSERT INTO moduleCourse" +
                    "                                                       VALUES (?,?)");

            this.addLinkCoursePreparatory = connection.prepareStatement("INSERT INTO preparatoryCourse" +
                    "                                                           VALUES (?,?)");

            this.addLinkCourseBorrowed = connection.prepareStatement("INSERT INTO borrowedCourse" +
                    "                                                           VALUES (?,?)");


            this.deleteLinkCourseModulated = connection.prepareStatement("DELETE FROM moduleCourse" +
                    "                                                           WHERE course_id=?" +
                    "                                                           AND course_module_id=?");

            this.deleteLinkCoursePreparatory = connection.prepareStatement("DELETE FROM preparatoryCourse" +
                    "                                                           WHERE course_id=?" +
                    "                                                           AND course_preparatory_id=?");

            this.deleteLinkCourseBorrowed = connection.prepareStatement("DELETE FROM borrowedCourse" +
                    "                                                           WHERE course_id=?" +
                    "                                                           AND course_borrowed_id=?");


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

            throw new SelectDaoException("Error getCourseById", e);
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

        if(code == null){
            return null;
        }

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


    @Override
    public List<Course> getCourseByYear(String year) throws DaoException {

        List<Course> course = new ArrayList <>();

        try {

            this.selectCourseByYear.setString(1, year);

            ResultSet rs = this.selectCourseByYear.executeQuery();

            while(rs.next()){

                course.add(generateCourse(rs));
            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getCourseByYear", e);
        }

        return course;
    }

    @Override
    public List<Course> getCourseByCfu(int cfu) throws DaoException {

        List<Course> courseList = new ArrayList <>();

        try {

            this.selectCourseByCfu.setInt(1, cfu);

            ResultSet rs = this.selectCourseByCfu.executeQuery();

            while (rs.next()){

                courseList.add(this.generateCourse(rs));

            }
        } catch (SQLException e) {
            throw new SelectDaoException("Error getCourseByCfu", e);
        }

        return courseList;
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

            throw new DaoException("Error in getCoursesByCodeAndYear", e);
        }

        return course;
    }

    //lo store puo' essere il metodo che integra sia l'update che lo store,
    //si effettua la insert se nell'oggetto course creato non e' presente l'id, quindi si presuppone che sia nuovo,
    //update altrimenti
    @Override
    public Course storeCourse(Course course) throws DaoException {
        try {
            //se il corso non presenta l'id eseguo una insert
            if (course.getIdCourse() <= 0) {

                this.insertCourse.setString(1, course.getCode());
                this.insertCourse.setString(2, course.getName());
                this.insertCourse.setString(3,course.getYear());
                this.insertCourse.setInt(4,course.getCfu());
                this.insertCourse.setString(5,course.getSector());
                this.insertCourse.setString(6,course.getLanguage());
                this.insertCourse.setInt(7,course.getSemester());
                this.insertCourse.setString(8,course.getPrerequisite_ita());
                this.insertCourse.setString(9,course.getPrerequisite_eng());
                this.insertCourse.setString(10,course.getGoals_ita());
                this.insertCourse.setString(11,course.getGoals_eng());
                this.insertCourse.setString(12,course.getExame_mode_ita());
                this.insertCourse.setString(13,course.getExame_mode_eng());
                this.insertCourse.setString(14,course.getTeaching_mode_ita());
                this.insertCourse.setString(15,course.getTeaching_mode_eng());
                this.insertCourse.setString(16,course.getSyllabus_ita());
                this.insertCourse.setString(17,course.getSyllabus_eng());
                this.insertCourse.setString(18,course.getNote_ita());
                this.insertCourse.setString(19,course.getNote_eng());
                this.insertCourse.setString(20,course.getKnowledge_ita());
                this.insertCourse.setString(21,course.getKnowledge_eng());
                this.insertCourse.setString(22,course.getApplication_ita());
                this.insertCourse.setString(23,course.getApplication_eng());
                this.insertCourse.setString(24,course.getEvaluation_ita());
                this.insertCourse.setString(25,course.getEvaluation_eng());
                this.insertCourse.setString(26,course.getCommunication_ita());
                this.insertCourse.setString(27,course.getCommunication_eng());
                this.insertCourse.setString(28,course.getLifelog_learning_skills_ita());
                this.insertCourse.setString(29,course.getLifelog_learning_skills_eng());
                this.insertCourse.setString(30, course.getExternal_material_ita());
                this.insertCourse.setString(31,course.getExternal_material_eng());

                this.insertCourse.executeUpdate();

            } else {

                this.updateCourse.setString(1, course.getCode());
                this.updateCourse.setString(2, course.getName());
                this.updateCourse.setString(3,course.getYear());
                this.updateCourse.setInt(4,course.getCfu());
                this.updateCourse.setString(5,course.getSector());
                this.updateCourse.setString(6,course.getLanguage());
                this.updateCourse.setInt(7,course.getSemester());
                this.updateCourse.setString(8,course.getPrerequisite_ita());
                this.updateCourse.setString(9,course.getPrerequisite_eng());
                this.updateCourse.setString(10,course.getGoals_ita());
                this.updateCourse.setString(11,course.getGoals_eng());
                this.updateCourse.setString(12,course.getExame_mode_ita());
                this.updateCourse.setString(13,course.getExame_mode_eng());
                this.updateCourse.setString(14,course.getTeaching_mode_ita());
                this.updateCourse.setString(15,course.getTeaching_mode_eng());
                this.updateCourse.setString(16,course.getSyllabus_ita());
                this.updateCourse.setString(17,course.getSyllabus_eng());
                this.updateCourse.setString(18,course.getNote_ita());
                this.updateCourse.setString(19,course.getNote_eng());
                this.updateCourse.setString(20,course.getKnowledge_ita());
                this.updateCourse.setString(21,course.getKnowledge_eng());
                this.updateCourse.setString(22,course.getApplication_ita());
                this.updateCourse.setString(23,course.getApplication_eng());
                this.updateCourse.setString(24,course.getEvaluation_ita());
                this.updateCourse.setString(25,course.getEvaluation_eng());
                this.updateCourse.setString(26,course.getCommunication_ita());
                this.updateCourse.setString(27,course.getCommunication_eng());
                this.updateCourse.setString(28,course.getLifelog_learning_skills_ita());
                this.updateCourse.setString(29,course.getLifelog_learning_skills_eng());
                this.updateCourse.setString(30, course.getExternal_material_ita());
                this.updateCourse.setString(31,course.getExternal_material_eng());

                System.out.println("id corso: " + course.getIdCourse());

                this.updateCourse.setInt(32,course.getIdCourse());

                this.updateCourse.executeUpdate();

            }


        } catch (SQLException e) {
            e.printStackTrace();

            throw new DaoException("Error storeCourse",e);
        }

        return null;
    }

    @Override
    public void deleteCourse(Course course) throws DaoException {
        try {
            this.deleteCourseById.setInt(1, course.getIdCourse());
            this.deleteCourseById.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();

            throw new DaoException("Error deleteCourse", e);

        }
    }

    @Override
    public List <Course> getCoursesByCode(String code) throws DaoException {
        List<Course> courseList = new ArrayList <>();

        try {

            this.selectCourseByCode.setString(1, code);

            ResultSet rs = this.selectCourseByCode.executeQuery();

            while (rs.next()){

                courseList.add(this.generateCourse(rs));

            }
        } catch (SQLException e) {
            throw new SelectDaoException("Error getCourseByCfu", e);
        }

        return courseList;
    }

    @Override
    public List<Course> getCoursesByUser(User user) throws DaoException {

        //inizializzo una lista di corsi
        List<Course> courses = new ArrayList <>();

        try {

            //setto l'id dell'utente
            this.selectCourseByUser.setInt(1, user.getId());

            //eseguo la query
            ResultSet rs = this.selectCourseByUser.executeQuery();

            //ciclo il risultato della query
            while (rs.next()){

                //aggiungo alla lista il corso nella riga attuale della query
                courses.add(this.generateCourse(rs));

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getCoursesByUser",e);
        }

        //restituisco la lista
        return courses;

    }

    @Override
    public Course getCoursesByCodeAndYear(String code, AccademicYear accademicYear) throws DaoException {

        Course course = null;

        try {

            this.selectCourseByCodeAndYear.setString(1, code);
            this.selectCourseByCodeAndYear.setString(2, accademicYear.toString());

            ResultSet rs = this.selectCourseByCodeAndYear.executeQuery();

            if (rs.next()){

                course = generateCourse(rs);
            }
            else {//se risultato nullo ritorno null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new DaoException("Error in getCoursesByCodeAndYear", e);
        }

        return course;

    }

    @Override
    public List <Course> getCoursesByUserAndYear(User user, String year) throws DaoException {

        //inizializzo una lista di corsi
        List<Course> courses = new ArrayList <>();

        try {

            //setto l'id dell'utente
            this.selectCourseByUserAndYear.setInt(1, user.getId());
            this.selectCourseByUserAndYear.setString(2, year);

            //eseguo la query
            ResultSet rs = this.selectCourseByUserAndYear.executeQuery();

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
    public List <Course> getCourseByStudyCourseAndYear(StudyCourse studyCourse, String year) throws DaoException {
        //inizializzo una lista di corsi
        List<Course> courses = new ArrayList <>();

        try {

            //setto l'id del corso di studi
            this.selectCourseByStudyCourseAndYear.setInt(1, studyCourse.getId());
            this.selectCourseByStudyCourseAndYear.setString(2, year);

            //eseguo la query
            ResultSet rs = this.selectCourseByStudyCourseAndYear.executeQuery();

            //ciclo il risultato della query
            while (rs.next()){

                //aggiungo alla lista il corso nella riga attuale della query
                courses.add(this.generateCourse(rs));

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getCourseByStudyCourseAndYear",e);
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

    @Override
    public void storeLinkCourseUser(Course course, User user) throws DaoException {

        try {

            this.storeLinkCourseUser.setInt(1, course.getIdCourse());
            this.storeLinkCourseUser.setInt(2, user.getId());

            this.storeLinkCourseUser.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            throw new InsertDaoException("Error storeLinkCourseUser", e);
        }

    }

    @Override
    public void deleteLinkCourseUser(Course course, User user) throws DaoException {

        try {

            this.deleteLinkCourseUser.setInt(1, course.getIdCourse());
            this.deleteLinkCourseUser.setInt(2, user.getId());

            this.deleteLinkCourseUser.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            throw new InsertDaoException("Error deleteLinkCourseUser", e);
        }

    }

    @Override
    public List <Course> getCourseModulated(Course course) throws DaoException {

        List<Course> courses = new ArrayList <>();

        try {

            this.selectCourseModulated.setInt(1, course.getIdCourse());

            ResultSet rs = this.selectCourseModulated.executeQuery();

            while (rs.next()){

                courses.add(this.generateCourse(rs));

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getCourseModulated", e);
        }

        return courses;
    }

    @Override
    public List <Course> getCoursePreparatory(Course course) throws DaoException {
        List<Course> courses = new ArrayList <>();

        try {

            this.selectCoursePreparatory.setInt(1, course.getIdCourse());

            ResultSet rs = this.selectCoursePreparatory.executeQuery();

            while (rs.next()){

                courses.add(this.generateCourse(rs));

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getCourseModulated", e);
        }

        return courses;
    }

    @Override
    public List <Course> getCourseBorrowed(Course course) throws DaoException {
        List<Course> courses = new ArrayList <>();

        try {

            this.selectCourseBorrowed.setInt(1, course.getIdCourse());

            ResultSet rs = this.selectCourseBorrowed.executeQuery();

            while (rs.next()){

                courses.add(this.generateCourse(rs));

            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getCourseBorrowed", e);
        }

        return courses;
    }

    @Override
    public void addLinkCourseModulated(Course course, int idCourseModulated) throws DaoException {

        try {

            this.addLinkCourseModulated.setInt(1, course.getIdCourse());
            this.addLinkCourseModulated.setInt(2, idCourseModulated);

            this.addLinkCourseModulated.executeUpdate();


        } catch (SQLException e) {
            throw new SelectDaoException("Error addLinkCourseModulated", e);
        }

    }

    @Override
    public void addLinkCoursePreparatory(Course course, int idCoursePreparatory) throws DaoException {

        try {

            this.addLinkCoursePreparatory.setInt(1, course.getIdCourse());
            this.addLinkCoursePreparatory.setInt(2, idCoursePreparatory);

            this.addLinkCoursePreparatory.executeUpdate();


        } catch (SQLException e) {
            throw new SelectDaoException("Error addLinkCoursePreparatory", e);
        }

    }

    @Override
    public void addLinkCourseBorrowed(Course course, int idCourseBorrowed) throws DaoException {

        try {

            this.addLinkCourseBorrowed.setInt(1, course.getIdCourse());
            this.addLinkCourseBorrowed.setInt(2, idCourseBorrowed);

            this.addLinkCourseBorrowed.executeUpdate();


        } catch (SQLException e) {
            throw new SelectDaoException("Error addLinkCourseBorrowed", e);
        }
    }

    @Override
    public void deleteLinkCourseModulated(Course course, int idCourseModulated) throws DaoException {

        try {

            this.deleteLinkCourseModulated.setInt(1,course.getIdCourse());
            this.deleteLinkCourseModulated.setInt(2,idCourseModulated);

            this.deleteLinkCourseModulated.executeUpdate();


        } catch (SQLException e) {

            throw new SelectDaoException("Error deleteLinkCourseModulated", e);
        }

    }

    @Override
    public void deleteLinkCoursePreparatory(Course course, int idCoursePreparatory ) throws DaoException {

        try {

            this.deleteLinkCoursePreparatory.setInt(1,course.getIdCourse());
            this.deleteLinkCoursePreparatory.setInt(2,idCoursePreparatory);

            this.deleteLinkCoursePreparatory.executeUpdate();


        } catch (SQLException e) {

            throw new SelectDaoException("Error deleteLinkCoursePreparatory", e);
        }

    }

    @Override
    public void deleteLinkCourseBorrowed(Course course, int idCourseBorrowed) throws DaoException {

        try {

            this.deleteLinkCourseBorrowed.setInt(1,course.getIdCourse());
            this.deleteLinkCourseBorrowed.setInt(2,idCourseBorrowed);

            this.deleteLinkCourseBorrowed.executeUpdate();


        } catch (SQLException e) {

            throw new SelectDaoException("Error deleteLinkCourseBorrowed", e);
        }

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
