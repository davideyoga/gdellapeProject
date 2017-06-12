package model;

import dao.data.DaoData;

/**
 * @author  Davide Micarelli
 */
public class UserGroups {

    private int idUser;

    public UserGroups(DaoData daoData) {
        this.idUser = 0;
        this.idGroups = 0;
    }

    private int idGroups;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdGroups() {
        return idGroups;
    }

    public void setIdGroups(int idGroups) {
        this.idGroups = idGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroups that = (UserGroups) o;

        if (getIdUser() != that.getIdUser()) return false;
        return getIdGroups() == that.getIdGroups();
    }

    @Override
    public int hashCode() {
        int result = getIdUser();
        result = 31 * result + getIdGroups();
        return result;
    }

    @Override
    public String toString() {
        return "UserGroups{" + "idUser=" + idUser + ", idGroups=" + idGroups + '}';
    }
}
