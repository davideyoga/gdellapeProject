package model;

import dao.data.DaoData;

/**
 * Created by max on 30/05/17.
 */
public class CourseBook {

    private int course_id;
    private int book_id;

    public CourseBook(DaoData daoData){
        this.book_id = 0;
        this.course_id = 0;

    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseBook that = (CourseBook) o;

        if (course_id != that.course_id) return false;
        return book_id == that.book_id;
    }

    @Override
    public int hashCode() {
        int result = course_id;
        result = 31 * result + book_id;
        return result;
    }
}
