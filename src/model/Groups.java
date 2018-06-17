package model;

import dao.data.DaoData;

public class Groups{

    int id;
    String name;
    String description;

    public Groups(DaoData daoData){
        this.id = 0;
        this.name = null;
        this.description = null;
    }

    public String toStringForLog() {
        return "name='" + name;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Groups groups = (Groups) o;

        if (getId() != groups.getId()) return false;
        if (getName() != null ? !getName().equals(groups.getName()) : groups.getName() != null) return false;
        return getDescription() != null ? getDescription().equals(groups.getDescription()) : groups.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}