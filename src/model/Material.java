package model;

import dao.data.DaoData;

import java.sql.Timestamp;

/**
 * Created by max on 25/05/17.
 */
public class Material {

    int id;
    String description_ita;
    String description_eng;
    Timestamp data;
    Double size;
    String type;

    public Material(DaoData daoData){
        this.id = 0;
        this.description_ita = null;
        this.description_eng = null;
        this.data = null;
        this.size = null;
        this.type = null;
    }

    @Override
    public String toString() {
        return "Material{" +
                "idCourse=" + id +
                ", description_ita='" + description_ita + '\'' +
                ", description_eng='" + description_eng + '\'' +
                ", data=" + data +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material material = (Material) o;

        if (id != material.id) return false;
        if (!description_ita.equals(material.description_ita)) return false;
        if (!description_eng.equals(material.description_eng)) return false;
        if (!data.equals(material.data)) return false;
        if (!size.equals(material.size)) return false;
        return type.equals(material.type);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + description_ita.hashCode();
        result = 31 * result + description_eng.hashCode();
        result = 31 * result + data.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
