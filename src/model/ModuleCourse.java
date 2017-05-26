package model;

import dao.data.DaoData;
import dao.exception.DaoException;

/**
 * Created by max on 26/05/17.
 */
public class ModuleCourse {

    private int course_id;
    private int course_module_id;

    public ModuleCourse(DaoData daoData){
        this.course_id = 0;
        this.course_module_id = 0;
    }

    public int getCourse_id(){
        return course_id;
    }

    public void setCourse_id(int course_id){
        this.course_id = course_id;
    }

    public int getCourse_module_id() {
        return course_module_id;
    }

    public void setCourse_module_id(int course_module_id) {
        this.course_module_id = course_module_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleCourse that = (ModuleCourse) o;

        if (course_id != that.course_id) return false;
        return course_module_id == that.course_module_id;
    }

    @Override
    public int hashCode() {
        int result = course_id;
        result = 31 * result + course_module_id;
        return result;
    }
}
