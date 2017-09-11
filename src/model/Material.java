package model;

import dao.data.DaoData;

import java.sql.Timestamp;

/**
 * Created by max on 25/05/17.
 */
public class Material {

    int id;
    String name;
    String description_ita;
    String description_eng;
    Timestamp data;
    Long size;
    String type;
    String route;

    public Material(DaoData daoData){
        this.id = 0;
        this.name = null;
        this.description_ita = null;
        this.description_eng = null;
        this.data = null;
        this.size = null;
        this.type = null;
        this.route = null;

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

    public String getDescription_ita() {
        return description_ita;
    }

    public void setDescription_ita(String description_ita) {
        this.description_ita = description_ita;
    }

    public String getDescription_eng() {
        return description_eng;
    }

    public void setDescription_eng(String description_eng) {
        this.description_eng = description_eng;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material material = (Material) o;

        if (getId() != material.getId()) return false;
        if (getName() != null ? !getName().equals(material.getName()) : material.getName() != null) return false;
        if (getDescription_ita() != null ? !getDescription_ita().equals(material.getDescription_ita()) : material.getDescription_ita() != null)
            return false;
        if (getDescription_eng() != null ? !getDescription_eng().equals(material.getDescription_eng()) : material.getDescription_eng() != null)
            return false;
        if (getData() != null ? !getData().equals(material.getData()) : material.getData() != null) return false;
        if (getSize() != null ? !getSize().equals(material.getSize()) : material.getSize() != null) return false;
        if (getType() != null ? !getType().equals(material.getType()) : material.getType() != null) return false;
        return getRoute() != null ? getRoute().equals(material.getRoute()) : material.getRoute() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription_ita() != null ? getDescription_ita().hashCode() : 0);
        result = 31 * result + (getDescription_eng() != null ? getDescription_eng().hashCode() : 0);
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getRoute() != null ? getRoute().hashCode() : 0);
        return result;
    }

    @Override
    public String   toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description_ita='" + description_ita + '\'' +
                ", description_eng='" + description_eng + '\'' +
                ", data=" + data +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", route='" + route + '\'' +
                '}';
    }
}
