package model;

import dao.data.DaoData;

/**
 * Created by davide on 26/05/17.
 */
public class GroupsService {

    private int idGroups;
    private int idService;


    public GroupsService(DaoData daoData) {
        this.idGroups = 0;
        this.idService = 0;
    }

    public int getIdGroups() {
        return idGroups;
    }

    public void setIdGroups(int idGroups) {
        this.idGroups = idGroups;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsService that = (GroupsService) o;

        if (getIdGroups() != that.getIdGroups()) return false;
        return getIdService() == that.getIdService();
    }

    @Override
    public int hashCode() {
        int result = getIdGroups();
        result = 31 * result + getIdService();
        return result;
    }

    @Override
    public String toString() {
        return "GroupsService{" + "idGroups=" + idGroups + ", idService=" + idService + '}';
    }
}
