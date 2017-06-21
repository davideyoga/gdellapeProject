package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import freemarker.template.utility.DateUtil;
import model.PreparatoryCourse;

/**
 * Created by max on 29/05/17.
 */
public interface PreparatoryCourseDao extends DaoData{

    public PreparatoryCourse getPreparatoryCourse() throws DaoException;

    public void insertPreparatoryCourse(PreparatoryCourse preparatoryCourse) throws DaoException;

    public void updatePreparatoryCourse(PreparatoryCourse preparatoryCourse) throws DaoException;

    public PreparatoryCourse selectPreparatoryCourse(int course_id, int preparatory_course_id) throws DaoException;

    public void deletePreparatoryCourse (PreparatoryCourse preparatoryCourse) throws DaoException;

}
