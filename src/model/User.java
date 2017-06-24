package model;

import dao.data.DaoData;

public class User{

    private int id;
    private String surname;
    private String name;
    private String email;
    private long number;
    private String curriculum_ita;
    private String curriculum_eng;
    private String receprion_hours_ita;
    private String receprion_hours_eng;
    private String password;

    public User(DaoData daoData){

        this.id = 0;
        this.surname = null;
        this.name = null;
        this.email = null;
        this.number = 0;
        this.curriculum_ita = null;
        this.curriculum_eng = null;
        this.receprion_hours_ita = null;
        this.receprion_hours_eng = null;
        this.password = null;

    }


    @Override
    public String toString() {
        return "User{" +
                "idCourse=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number=" + number +
                ", curriculum_ita='" + curriculum_ita + '\'' +
                ", curriculum_eng='" + curriculum_eng + '\'' +
                ", receprion_hours_ita='" + receprion_hours_ita + '\'' +
                ", receprion_hours_eng='" + receprion_hours_eng + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getCurriculum_ita() {
        return curriculum_ita;
    }

    public void setCurriculum_ita(String curriculum_ita) {
        this.curriculum_ita = curriculum_ita;
    }

    public String getCurriculum_eng() {
        return curriculum_eng;
    }

    public void setCurriculum_eng(String curriculum_eng) {
        this.curriculum_eng = curriculum_eng;
    }

    public String getReceprion_hours_ita() {
        return receprion_hours_ita;
    }

    public void setReceprion_hours_ita(String receprion_hours_ita) {
        this.receprion_hours_ita = receprion_hours_ita;
    }

    public String getReceprion_hours_eng() {
        return receprion_hours_eng;
    }

    public void setReceprion_hours_eng(String receprion_hours_eng) {
        this.receprion_hours_eng = receprion_hours_eng;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}