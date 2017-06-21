package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import model.Material;

/**
 * Created by max on 25/05/17.
 */
public interface MaterialDao extends DaoData {

    public Material getMaterial();

    public Material getMaterialById( int idMaterial) throws DaoException;

    public void storeMaterial ( Material material ) throws DaoException;

    public void deleteMaterial (Material material ) throws DaoException;
}
