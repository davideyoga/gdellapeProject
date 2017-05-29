package model;

import dao.data.DaoData;

/**
 * @Creator Davide Micarelli
 */
public class Log {

    private int id;
    private int idUser;
    private String description;

    public Log(DaoData daoData) {
        this.id = 0;
        this.idUser = 0;
        this.description = null;
    }

    @Override
    public String toString() {
        return "Log{" + "id=" + id + ", idUser=" + idUser + ", description='" + description + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (getId() != log.getId()) return false;
        if (getIdUser() != log.getIdUser()) return false;
        return getDescription() != null ? getDescription().equals(log.getDescription()) : log.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getIdUser();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
