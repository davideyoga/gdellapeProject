package model;

import dao.data.DaoData;

import java.sql.Timestamp;

/**
 * @author Davide Micarelli
 */
public class Log {

    private int id;
    private int idUser;
    private String description;
    private Timestamp date;

    public Log(DaoData daoData) {
        this.id = 0;
        this.idUser = 0;
        this.description = null;
        this.date = null;
    }

    @Override
    public String toString() {
        return "Log{" + "idCourse=" + id + ", idUser=" + idUser + ", description='" + description + '\'' + ", date=" + date + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (getId() != log.getId()) return false;
        if (getIdUser() != log.getIdUser()) return false;
        if (getDescription() != null ? !getDescription().equals(log.getDescription()) : log.getDescription() != null)
            return false;
        return getDate() != null ? getDate().equals(log.getDate()) : log.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getIdUser();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
