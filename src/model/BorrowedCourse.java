package model;

import dao.data.DaoData;

/**
 * Created by max on 26/05/17.
 */
public class BorrowedCourse {

    int course_id;
    int course_borrowed_id;

    public BorrowedCourse(DaoData daoData){
        this.course_id=0;
        this.course_borrowed_id=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowedCourse that = (BorrowedCourse) o;

        if (course_id != that.course_id) return false;
        return course_borrowed_id == that.course_borrowed_id;
    }

    @Override
    public int hashCode() {
        int result = course_id;
        result = 31 * result + course_borrowed_id;
        return result;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getCourse_borrowed_id() {
        return course_borrowed_id;
    }

    public void setCourse_borrowed_id(int course_borrowed_id) {
        this.course_borrowed_id = course_borrowed_id;
    }
}
