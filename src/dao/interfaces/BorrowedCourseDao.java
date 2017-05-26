package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.BorrowedCourse;

/**
 * Created by max on 26/05/17.
 */
public interface BorrowedCourseDao extends DaoData {

    public BorrowedCourse getBorrowedCourse() throws DaoException;
    public void insertBorrowedCourse(BorrowedCourse borrowedCourse) throws DaoException;
    public void updateBorrowedCourse(BorrowedCourse borrowedCourse) throws DaoException;
    public BorrowedCourse selectBorrowedCourseById(int id_course, int id_borrowed) throws DaoException;
    public void deleteBorrowedCourse(BorrowedCourse borrowedCourse) throws DaoException;
}
