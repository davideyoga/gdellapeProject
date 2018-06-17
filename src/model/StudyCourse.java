package model;

import dao.data.DaoData;

/**
 * @author Davide Micarelli
 */
public class StudyCourse {

    private int id;
    private String code;
    private String name;
    private String description_ita;
    private String description_eng;
    private String department_ita;
    private String department_eng;
    private int level_ita;
    private int level_eng;
    private int duration;
    private String classes; //class e' una parola riservata in java
    private String seat;
    private String accessType_ita;
    private String accessType_eng;
    private String language_ita;
    private String language_eng;

    public StudyCourse( DaoData daoData){
        this.id = 0;
        this.code = null;
        this.name = null;
        this.description_ita = null;
        this.description_eng = null;
        this.department_ita = null;
        this.department_eng = null;
        this.level_ita = 0;
        this.level_eng = 0;
        this.duration = 0;
        this.classes = null; //class e' una parola riservata in java
        this.seat = null;
        this.accessType_ita = null;
        this.accessType_eng = null;
        this.language_ita = null;
        this.language_eng = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription_ita() {
        return description_ita;
    }

    public void setDescription_ita(String description_ita) {
        this.description_ita = description_ita;
    }

    public String getDescription_eng() {
        return description_eng;
    }

    public void setDescription_eng(String description_eng) {
        this.description_eng = description_eng;
    }

    public String getDepartment_ita() {
        return department_ita;
    }

    public void setDepartment_ita(String department_ita) {
        this.department_ita = department_ita;
    }

    public String getDepartment_eng() {
        return department_eng;
    }

    public void setDepartment_eng(String department_eng) {
        this.department_eng = department_eng;
    }

    public int getLevel_ita() {
        return level_ita;
    }

    public void setLevel_ita(int level_ita) {
        this.level_ita = level_ita;
    }

    public int getLevel_eng() {
        return level_eng;
    }

    public void setLevel_eng(int level_eng) {
        this.level_eng = level_eng;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getAccessType_ita() {
        return accessType_ita;
    }

    public void setAccessType_ita(String accessType_ita) {
        this.accessType_ita = accessType_ita;
    }

    public String getAccessType_eng() {
        return accessType_eng;
    }

    public void setAccessType_eng(String accessType_eng) {
        this.accessType_eng = accessType_eng;
    }

    public String getLanguage_ita() {
        return language_ita;
    }

    public void setLanguage_ita(String language_ita) {
        this.language_ita = language_ita;
    }

    public String getLanguage_eng() {
        return language_eng;
    }

    public void setLanguage_eng(String language_eng) {
        this.language_eng = language_eng;
    }

    @Override
    public String toString() {
        return "StudyCourse{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description_ita='" + description_ita + '\'' +
                ", description_eng='" + description_eng + '\'' +
                ", department_ita='" + department_ita + '\'' +
                ", department_eng='" + department_eng + '\'' +
                ", level_ita=" + level_ita +
                ", level_eng=" + level_eng +
                ", duration=" + duration +
                ", classes='" + classes + '\'' +
                ", seat='" + seat + '\'' +
                ", accessType_ita='" + accessType_ita + '\'' +
                ", accessType_eng='" + accessType_eng + '\'' +
                ", language_ita='" + language_ita + '\'' +
                ", language_eng='" + language_eng + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyCourse that = (StudyCourse) o;

        if (getId() != that.getId()) return false;
        if (getLevel_ita() != that.getLevel_ita()) return false;
        if (getLevel_eng() != that.getLevel_eng()) return false;
        if (getDuration() != that.getDuration()) return false;
        if (getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getDescription_ita() != null ? !getDescription_ita().equals(that.getDescription_ita()) : that.getDescription_ita() != null)
            return false;
        if (getDescription_eng() != null ? !getDescription_eng().equals(that.getDescription_eng()) : that.getDescription_eng() != null)
            return false;
        if (getDepartment_ita() != null ? !getDepartment_ita().equals(that.getDepartment_ita()) : that.getDepartment_ita() != null)
            return false;
        if (getDepartment_eng() != null ? !getDepartment_eng().equals(that.getDepartment_eng()) : that.getDepartment_eng() != null)
            return false;
        if (getClasses() != null ? !getClasses().equals(that.getClasses()) : that.getClasses() != null) return false;
        if (getSeat() != null ? !getSeat().equals(that.getSeat()) : that.getSeat() != null) return false;
        if (getAccessType_ita() != null ? !getAccessType_ita().equals(that.getAccessType_ita()) : that.getAccessType_ita() != null)
            return false;
        if (getAccessType_eng() != null ? !getAccessType_eng().equals(that.getAccessType_eng()) : that.getAccessType_eng() != null)
            return false;
        if (getLanguage_ita() != null ? !getLanguage_ita().equals(that.getLanguage_ita()) : that.getLanguage_ita() != null)
            return false;
        return getLanguage_eng() != null ? getLanguage_eng().equals(that.getLanguage_eng()) : that.getLanguage_eng() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription_ita() != null ? getDescription_ita().hashCode() : 0);
        result = 31 * result + (getDescription_eng() != null ? getDescription_eng().hashCode() : 0);
        result = 31 * result + (getDepartment_ita() != null ? getDepartment_ita().hashCode() : 0);
        result = 31 * result + (getDepartment_eng() != null ? getDepartment_eng().hashCode() : 0);
        result = 31 * result + getLevel_ita();
        result = 31 * result + getLevel_eng();
        result = 31 * result + getDuration();
        result = 31 * result + (getClasses() != null ? getClasses().hashCode() : 0);
        result = 31 * result + (getSeat() != null ? getSeat().hashCode() : 0);
        result = 31 * result + (getAccessType_ita() != null ? getAccessType_ita().hashCode() : 0);
        result = 31 * result + (getAccessType_eng() != null ? getAccessType_eng().hashCode() : 0);
        result = 31 * result + (getLanguage_ita() != null ? getLanguage_ita().hashCode() : 0);
        result = 31 * result + (getLanguage_eng() != null ? getLanguage_eng().hashCode() : 0);
        return result;
    }

    public String toStringForLog() {
        return  "name=" + name +
                ", code=" + code ;
    }
}
