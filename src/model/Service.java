package model;

import dao.data.DaoData;

/**
 * Created by davide on 23/05/17.
 */
public class Service {

    private int id;
    private String name;
    private String description;

    public Service(DaoData daoData){
        this.id = 0;
        this.name = null;
        this.description = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (getId() != service.getId()) return false;
        if (getName() != null ? !getName().equals(service.getName()) : service.getName() != null) return false;
        return getDescription() != null ? getDescription().equals(service.getDescription()) : service.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
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
