package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.exception.DestroyDaoException;
import dao.exception.InitDaoException;
import dao.exception.SelectDaoException;
import dao.interfaces.CourseDao;
import dao.interfaces.GroupsDao;
import model.Course;
import model.Groups;
import model.Service;
import model.User;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
            deleteCourseByName;

    public CourseDaoImpl(DataSource datasource) {
        super(datasource);
    }

    public void init() throws DaoException{
        try{

            super.init();

            this.insertCourse = connection.prepareStatement("INSERT INTO course" +
                    " VALUES (NULL,?,?,?,?,?,?,?)");

            this.selectCourseById = connection.prepareStatement("SELECT *" +
                    " FROM course" +
                    " WHERE id = ?");

            this.deleteCourseById = connection.prepareStatement("DELETE FROM course" +
                    "                                               WHERE id=?");

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

                course.setIdCourse(idCourse);
                course.setCode(stripSlashes(rs.getString("code")));
                course.setName(stripSlashes(rs.getString("name")));
                course.setYear(stripSlashes(rs.getString("year")));
                course.setCfu(rs.getInt("cfu"));
                course.setSector(stripSlashes(rs.getString("sector")));
                course.setLanguage(stripSlashes(rs.getString("language")));
                course.setSemester(rs.getInt("semester"));
                course.setPrerequisite_ita(stripSlashes(rs.getString("prerequiste_ita")));
                course.setPrerequisite_eng(stripSlashes(rs.getString("prerequiste_eng")));
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
        Course course = null;

        try {

            this.selectCourseByName.setString(1, name);

            ResultSet rs = this.selectCourseById.executeQuery();

            if (rs.next()){

                course.setIdCourse(rs.getInt("idCourse"));
                course.setCode(stripSlashes(rs.getString("code")));
                course.setName(name);
                course.setYear(stripSlashes(rs.getString("year")));
                course.setCfu(rs.getInt("cfu"));
                course.setSector(stripSlashes(rs.getString("sector")));
                course.setLanguage(stripSlashes(rs.getString("language")));
                course.setSemester(rs.getInt("semester"));
                course.setPrerequisite_ita(stripSlashes(rs.getString("prerequiste_ita")));
                course.setPrerequisite_eng(stripSlashes(rs.getString("prerequiste_eng")));
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
    public Course getCourseByCode(String code) throws DaoException {
        Course course = null;

        try {

            this.selectCourseByName.setString(1, code);

            ResultSet rs = this.selectCourseById.executeQuery();

            if (rs.next()){

                course.setIdCourse(rs.getInt("idCourse"));
                course.setCode(code);
                course.setName(stripSlashes(rs.getString("name")));
                course.setYear(stripSlashes(rs.getString("year")));
                course.setCfu(rs.getInt("cfu"));
                course.setSector(stripSlashes(rs.getString("sector")));
                course.setLanguage(stripSlashes(rs.getString("language")));
                course.setSemester(rs.getInt("semester"));
                course.setPrerequisite_ita(stripSlashes(rs.getString("prerequiste_ita")));
                course.setPrerequisite_eng(stripSlashes(rs.getString("prerequiste_eng")));
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
    public Course getCourseByYear(String year) throws DaoException {
        Course course = null;

        try {

            this.selectCourseByName.setString(1, year);

            ResultSet rs = this.selectCourseById.executeQuery();

            if (rs.next()){

                course.setIdCourse(rs.getInt("idCourse"));
                course.setCode(stripSlashes(rs.getString("code")));
                course.setName(stripSlashes(rs.getString("name")));
                course.setYear(year);
                course.setCfu(rs.getInt("cfu"));
                course.setSector(stripSlashes(rs.getString("sector")));
                course.setLanguage(stripSlashes(rs.getString("language")));
                course.setSemester(rs.getInt("semester"));
                course.setPrerequisite_ita(stripSlashes(rs.getString("prerequiste_ita")));
                course.setPrerequisite_eng(stripSlashes(rs.getString("prerequiste_eng")));
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
    public Course getCourseByCfu(int cfu) throws DaoException {
        Course course = null;

        try {

            this.selectCourseByName.setInt(1, cfu);

            ResultSet rs = this.selectCourseById.executeQuery();

            if (rs.next()){

                course.setIdCourse(rs.getInt("idCourse"));
                course.setCode(stripSlashes(rs.getString("code")));
                course.setName(stripSlashes(rs.getString("name")));
                course.setYear(stripSlashes(rs.getString("year")));
                course.setCfu(cfu);
                course.setSector(stripSlashes(rs.getString("sector")));
                course.setLanguage(stripSlashes(rs.getString("language")));
                course.setSemester(rs.getInt("semester"));
                course.setPrerequisite_ita(stripSlashes(rs.getString("prerequiste_ita")));
                course.setPrerequisite_eng(stripSlashes(rs.getString("prerequiste_eng")));
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
    public Course getCourseBySector(String sector) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseByLanguage(String language) throws DaoException {
        return null;
    }

    @Override
    public Course getCourseBySemester(int semester) throws DaoException {
        return null;
    }

    @Override
    public Course storeCourse(Course course) throws DaoException {
        return null;
    }

    @Override
    public Course deleteCourse(Course course) throws DaoException {
        return null;
    }

/*    public Course generateCourse(ResultSet rs) throws DaoException {
        Course course = this.getCourse();

        try {

            course.setIdCourse(rs.getInt("id"));
            course.setName(stripSlashes(rs.getString("name")));
            course.setDescription(stripSlashes(rs.getString("description")));

        } catch (SQLException e) {
            throw new SelectDaoException("Error generateServoce", e );
        }
        return groups;
    }*/
}
