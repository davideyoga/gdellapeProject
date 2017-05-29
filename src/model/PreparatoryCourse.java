package model;

import dao.data.DaoData;

/**
 * Created by max on 29/05/17.
 */
public class PreparatoryCourse {

    private int course_id;
    private int preparatory_course_id;


    public PreparatoryCourse(DaoData daoData){

        this.course_id = 0;
        this.preparatory_course_id = 0;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getPreparatory_course_id() {
        return preparatory_course_id;
    }

    public void setPreparatory_course_id(int preparatory_course_id) {
        this.preparatory_course_id = preparatory_course_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreparatoryCourse that = (PreparatoryCourse) o;

        if (course_id != that.course_id) return false;
        return preparatory_course_id == that.preparatory_course_id;
    }

    @Override
    public int hashCode() {
        int result = course_id;
        result = 31 * result + preparatory_course_id;
        return result;
    }

    @Override
    public String toString() {
        return "PreparatoryCourse{" +
                "course_id=" + course_id +
                ", preparatory_course_id=" + preparatory_course_id +
                '}';
    }
}
