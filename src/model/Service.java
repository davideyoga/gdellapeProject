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
