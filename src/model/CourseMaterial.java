package model;

import dao.data.DaoData;

/**
 * Created by max on 30/05/17.
 */
public class CourseMaterial {

    private int course_id;
    private int material_id;

    public CourseMaterial(DaoData daoData){
        this.course_id = 0;
        this.material_id = 0;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseMaterial that = (CourseMaterial) o;

        if (course_id != that.course_id) return false;
        return material_id == that.material_id;
    }

    @Override
    public int hashCode() {
        int result = course_id;
        result = 31 * result + material_id;
        return result;
    }

    @Override
    public String toString() {
        return "CourseMaterial{" +
                "course_id=" + course_id +
                ", material_id=" + material_id +
                '}';
    }
}
