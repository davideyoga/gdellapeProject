package model;

import dao.data.DaoData;
/**
 * Created by antonello on 25/05/17.
 */
public class Course {
    int idCourse;
    String code;
    String name;
    String year;
    int cfu;
    String sector;
    String language;
    int semester;
    String prerequisite_ita;
    String prerequisite_eng;
    String goals_ita;
    String goals_eng;
    String exame_mode_ita;
    String exame_mode_eng;
    String teaching_mode_ita;
    String teaching_mode_eng;
    String syllabus_ita;
    String syllabus_eng;
    String note_ita;
    String note_eng;
    String knowledge_ita;
    String knowledge_eng;
    String application_ita;
    String application_eng;
    String evaluation_ita;
    String evaluation_eng;
    String communication_ita;
    String communication_eng;
    String lifelog_learning_skills_ita;
    String lifelog_learning_skills_eng;
    String external_material_ita;
    String external_material_eng;

    public Course(DaoData daoData) {
        this.idCourse = 0;
        this.code = null;
        this.name = null;
        this.year = null;
        this.cfu = 0;
        this.sector = null;
        this.language = null;
        this.semester = 0;
        this.prerequisite_ita = null;
        this.prerequisite_eng = null;
        this.goals_ita = null;
        this.goals_eng = null;
        this.exame_mode_ita = null;
        this.exame_mode_eng = null;
        this.teaching_mode_ita = null;
        this.teaching_mode_eng = null;
        this.syllabus_ita = null;
        this.syllabus_eng = null;
        this.note_ita = null;
        this.note_eng = null;
        this.knowledge_ita = null;
        this.knowledge_eng = null;
        this.application_ita = null;
        this.application_eng = null;
        this.evaluation_ita = null;
        this.evaluation_eng = null;
        this.communication_ita = null;
        this.communication_eng = null;
        this.lifelog_learning_skills_ita = null;
        this.lifelog_learning_skills_eng = null;
        this.external_material_ita = null;
        this.external_material_eng = null;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getPrerequisite_ita() {
        return prerequisite_ita;
    }

    public void setPrerequisite_ita(String prerequisite_ita) {
        this.prerequisite_ita = prerequisite_ita;
    }

    public String getPrerequisite_eng() {
        return prerequisite_eng;
    }

    public void setPrerequisite_eng(String prerequisite_eng) {
        this.prerequisite_eng = prerequisite_eng;
    }

    public String getGoals_ita() {
        return goals_ita;
    }

    public void setGoals_ita(String goals_ita) {
        this.goals_ita = goals_ita;
    }

    public String getGoals_eng() {
        return goals_eng;
    }

    public void setGoals_eng(String goals_eng) {
        this.goals_eng = goals_eng;
    }

    public String getExame_mode_ita() {
        return exame_mode_ita;
    }

    public void setExame_mode_ita(String exame_mode_ita) {
        this.exame_mode_ita = exame_mode_ita;
    }

    public String getExame_mode_eng() {
        return exame_mode_eng;
    }

    public void setExame_mode_eng(String exame_mode_eng) {
        this.exame_mode_eng = exame_mode_eng;
    }

    public String getTeaching_mode_ita() {
        return teaching_mode_ita;
    }

    public void setTeaching_mode_ita(String teaching_mode_ita) {
        this.teaching_mode_ita = teaching_mode_ita;
    }

    public String getTeaching_mode_eng() {
        return teaching_mode_eng;
    }

    public void setTeaching_mode_eng(String teaching_mode_eng) {
        this.teaching_mode_eng = teaching_mode_eng;
    }

    public String getSyllabus_ita() {
        return syllabus_ita;
    }

    public void setSyllabus_ita(String syllabus_ita) {
        this.syllabus_ita = syllabus_ita;
    }

    public String getSyllabus_eng() {
        return syllabus_eng;
    }

    public void setSyllabus_eng(String syllabus_eng) {
        this.syllabus_eng = syllabus_eng;
    }

    public String getNote_ita() {
        return note_ita;
    }

    public void setNote_ita(String note_ita) {
        this.note_ita = note_ita;
    }

    public String getNote_eng() {
        return note_eng;
    }

    public void setNote_eng(String note_eng) {
        this.note_eng = note_eng;
    }

    public String getKnowledge_ita() {
        return knowledge_ita;
    }

    public void setKnowledge_ita(String knowledge_ita) {
        this.knowledge_ita = knowledge_ita;
    }

    public String getKnowledge_eng() {
        return knowledge_eng;
    }

    public void setKnowledge_eng(String knowledge_eng) {
        this.knowledge_eng = knowledge_eng;
    }

    public String getApplication_ita() {
        return application_ita;
    }

    public void setApplication_ita(String application_ita) {
        this.application_ita = application_ita;
    }

    public String getApplication_eng() {
        return application_eng;
    }

    public void setApplication_eng(String application_eng) {
        this.application_eng = application_eng;
    }

    public String getEvaluation_ita() {
        return evaluation_ita;
    }

    public void setEvaluation_ita(String evaluation_ita) {
        this.evaluation_ita = evaluation_ita;
    }

    public String getEvaluation_eng() {
        return evaluation_eng;
    }

    public void setEvaluation_eng(String evaluation_eng) {
        this.evaluation_eng = evaluation_eng;
    }

    public String getCommunication_ita() {
        return communication_ita;
    }

    public void setCommunication_ita(String communication_ita) {
        this.communication_ita = communication_ita;
    }

    public String getCommunication_eng() {
        return communication_eng;
    }

    public void setCommunication_eng(String communication_eng) {
        this.communication_eng = communication_eng;
    }

    public String getLifelog_learning_skills_ita() {
        return lifelog_learning_skills_ita;
    }

    public void setLifelog_learning_skills_ita(String lifelog_learning_skills_ita) {
        this.lifelog_learning_skills_ita = lifelog_learning_skills_ita;
    }

    public String getLifelog_learning_skills_eng() {
        return lifelog_learning_skills_eng;
    }

    public void setLifelog_learning_skills_eng(String lifelog_learning_skills_eng) {
        this.lifelog_learning_skills_eng = lifelog_learning_skills_eng;
    }

    public String getExternal_material_ita() {
        return external_material_ita;
    }

    public void setExternal_material_ita(String external_material_ita) {
        this.external_material_ita = external_material_ita;
    }

    public String getExternal_material_eng() {
        return external_material_eng;
    }

    public void setExternal_material_eng(String external_material_eng) {
        this.external_material_eng = external_material_eng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (getCfu() != course.getCfu()) return false;
        if (getSemester() != course.getSemester()) return false;
        if (getCode() != null ? !getCode().equals(course.getCode()) : course.getCode() != null) return false;
        if (getName() != null ? !getName().equals(course.getName()) : course.getName() != null) return false;
        if (getYear() != null ? !getYear().equals(course.getYear()) : course.getYear() != null) return false;
        if (getSector() != null ? !getSector().equals(course.getSector()) : course.getSector() != null) return false;
        if (getLanguage() != null ? !getLanguage().equals(course.getLanguage()) : course.getLanguage() != null)
            return false;
        if (getPrerequisite_ita() != null ? !getPrerequisite_ita().equals(course.getPrerequisite_ita()) : course.getPrerequisite_ita() != null)
            return false;
        if (getPrerequisite_eng() != null ? !getPrerequisite_eng().equals(course.getPrerequisite_eng()) : course.getPrerequisite_eng() != null)
            return false;
        if (getGoals_ita() != null ? !getGoals_ita().equals(course.getGoals_ita()) : course.getGoals_ita() != null)
            return false;
        if (getGoals_eng() != null ? !getGoals_eng().equals(course.getGoals_eng()) : course.getGoals_eng() != null)
            return false;
        if (getExame_mode_ita() != null ? !getExame_mode_ita().equals(course.getExame_mode_ita()) : course.getExame_mode_ita() != null)
            return false;
        if (getExame_mode_eng() != null ? !getExame_mode_eng().equals(course.getExame_mode_eng()) : course.getExame_mode_eng() != null)
            return false;
        if (getTeaching_mode_ita() != null ? !getTeaching_mode_ita().equals(course.getTeaching_mode_ita()) : course.getTeaching_mode_ita() != null)
            return false;
        if (getTeaching_mode_eng() != null ? !getTeaching_mode_eng().equals(course.getTeaching_mode_eng()) : course.getTeaching_mode_eng() != null)
            return false;
        if (getSyllabus_ita() != null ? !getSyllabus_ita().equals(course.getSyllabus_ita()) : course.getSyllabus_ita() != null)
            return false;
        if (getSyllabus_eng() != null ? !getSyllabus_eng().equals(course.getSyllabus_eng()) : course.getSyllabus_eng() != null)
            return false;
        if (getNote_ita() != null ? !getNote_ita().equals(course.getNote_ita()) : course.getNote_ita() != null)
            return false;
        if (getNote_eng() != null ? !getNote_eng().equals(course.getNote_eng()) : course.getNote_eng() != null)
            return false;
        if (getKnowledge_ita() != null ? !getKnowledge_ita().equals(course.getKnowledge_ita()) : course.getKnowledge_ita() != null)
            return false;
        if (getKnowledge_eng() != null ? !getKnowledge_eng().equals(course.getKnowledge_eng()) : course.getKnowledge_eng() != null)
            return false;
        if (getApplication_ita() != null ? !getApplication_ita().equals(course.getApplication_ita()) : course.getApplication_ita() != null)
            return false;
        if (getApplication_eng() != null ? !getApplication_eng().equals(course.getApplication_eng()) : course.getApplication_eng() != null)
            return false;
        if (getEvaluation_ita() != null ? !getEvaluation_ita().equals(course.getEvaluation_ita()) : course.getEvaluation_ita() != null)
            return false;
        if (getEvaluation_eng() != null ? !getEvaluation_eng().equals(course.getEvaluation_eng()) : course.getEvaluation_eng() != null)
            return false;
        if (getCommunication_ita() != null ? !getCommunication_ita().equals(course.getCommunication_ita()) : course.getCommunication_ita() != null)
            return false;
        if (getCommunication_eng() != null ? !getCommunication_eng().equals(course.getCommunication_eng()) : course.getCommunication_eng() != null)
            return false;
        if (getLifelog_learning_skills_ita() != null ? !getLifelog_learning_skills_ita().equals(course.getLifelog_learning_skills_ita()) : course.getLifelog_learning_skills_ita() != null)
            return false;
        if (getLifelog_learning_skills_eng() != null ? !getLifelog_learning_skills_eng().equals(course.getLifelog_learning_skills_eng()) : course.getLifelog_learning_skills_eng() != null)
            return false;
        if (getExternal_material_ita() != null ? !getExternal_material_ita().equals(course.getExternal_material_ita()) : course.getExternal_material_ita() != null)
            return false;
        return getExternal_material_eng() != null ? getExternal_material_eng().equals(course.getExternal_material_eng()) : course.getExternal_material_eng() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + getCfu();
        result = 31 * result + (getSector() != null ? getSector().hashCode() : 0);
        result = 31 * result + (getLanguage() != null ? getLanguage().hashCode() : 0);
        result = 31 * result + getSemester();
        result = 31 * result + (getPrerequisite_ita() != null ? getPrerequisite_ita().hashCode() : 0);
        result = 31 * result + (getPrerequisite_eng() != null ? getPrerequisite_eng().hashCode() : 0);
        result = 31 * result + (getGoals_ita() != null ? getGoals_ita().hashCode() : 0);
        result = 31 * result + (getGoals_eng() != null ? getGoals_eng().hashCode() : 0);
        result = 31 * result + (getExame_mode_ita() != null ? getExame_mode_ita().hashCode() : 0);
        result = 31 * result + (getExame_mode_eng() != null ? getExame_mode_eng().hashCode() : 0);
        result = 31 * result + (getTeaching_mode_ita() != null ? getTeaching_mode_ita().hashCode() : 0);
        result = 31 * result + (getTeaching_mode_eng() != null ? getTeaching_mode_eng().hashCode() : 0);
        result = 31 * result + (getSyllabus_ita() != null ? getSyllabus_ita().hashCode() : 0);
        result = 31 * result + (getSyllabus_eng() != null ? getSyllabus_eng().hashCode() : 0);
        result = 31 * result + (getNote_ita() != null ? getNote_ita().hashCode() : 0);
        result = 31 * result + (getNote_eng() != null ? getNote_eng().hashCode() : 0);
        result = 31 * result + (getKnowledge_ita() != null ? getKnowledge_ita().hashCode() : 0);
        result = 31 * result + (getKnowledge_eng() != null ? getKnowledge_eng().hashCode() : 0);
        result = 31 * result + (getApplication_ita() != null ? getApplication_ita().hashCode() : 0);
        result = 31 * result + (getApplication_eng() != null ? getApplication_eng().hashCode() : 0);
        result = 31 * result + (getEvaluation_ita() != null ? getEvaluation_ita().hashCode() : 0);
        result = 31 * result + (getEvaluation_eng() != null ? getEvaluation_eng().hashCode() : 0);
        result = 31 * result + (getCommunication_ita() != null ? getCommunication_ita().hashCode() : 0);
        result = 31 * result + (getCommunication_eng() != null ? getCommunication_eng().hashCode() : 0);
        result = 31 * result + (getLifelog_learning_skills_ita() != null ? getLifelog_learning_skills_ita().hashCode() : 0);
        result = 31 * result + (getLifelog_learning_skills_eng() != null ? getLifelog_learning_skills_eng().hashCode() : 0);
        result = 31 * result + (getExternal_material_ita() != null ? getExternal_material_ita().hashCode() : 0);
        result = 31 * result + (getExternal_material_eng() != null ? getExternal_material_eng().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", cfu=" + cfu +
                ", sector='" + sector + '\'' +
                ", language='" + language + '\'' +
                ", semester=" + semester +
                ", prerequisite_ita='" + prerequisite_ita + '\'' +
                ", prerequisite_eng='" + prerequisite_eng + '\'' +
                ", goals_ita='" + goals_ita + '\'' +
                ", goals_eng='" + goals_eng + '\'' +
                ", exame_mode_ita='" + exame_mode_ita + '\'' +
                ", exame_mode_eng='" + exame_mode_eng + '\'' +
                ", teaching_mode_ita='" + teaching_mode_ita + '\'' +
                ", teaching_mode_eng='" + teaching_mode_eng + '\'' +
                ", syllabus_ita='" + syllabus_ita + '\'' +
                ", syllabus_eng='" + syllabus_eng + '\'' +
                ", note_ita='" + note_ita + '\'' +
                ", note_eng='" + note_eng + '\'' +
                ", knowledge_ita='" + knowledge_ita + '\'' +
                ", knowledge_eng='" + knowledge_eng + '\'' +
                ", application_ita='" + application_ita + '\'' +
                ", application_eng='" + application_eng + '\'' +
                ", evaluation_ita='" + evaluation_ita + '\'' +
                ", evaluation_eng='" + evaluation_eng + '\'' +
                ", communication_ita='" + communication_ita + '\'' +
                ", communication_eng='" + communication_eng + '\'' +
                ", lifelog_learning_skills_ita='" + lifelog_learning_skills_ita + '\'' +
                ", lifelog_learning_skills_eng='" + lifelog_learning_skills_eng + '\'' +
                ", external_material_ita='" + external_material_ita + '\'' +
                ", external_material_eng='" + external_material_eng + '\'' +
                '}';
    }

    /**
     * @return Stringa che descrive id, nome e codice
     */
    public String toStringForLog(){

        String logDescriptor = "name: " + name + ", code: " + code;

        return logDescriptor;

    }

}