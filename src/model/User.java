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
        this.surname = "";
        this.name = "";
        this.email = "";
        this.number = 0;
        this.curriculum_ita = "";
        this.curriculum_eng = "";
        this.receprion_hours_ita = "";
        this.receprion_hours_eng = "";
        this.password = "";

    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
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

        if (getId() != user.getId()) return false;
        if (getNumber() != user.getNumber()) return false;
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getCurriculum_ita() != null ? !getCurriculum_ita().equals(user.getCurriculum_ita()) : user.getCurriculum_ita() != null)
            return false;
        if (getCurriculum_eng() != null ? !getCurriculum_eng().equals(user.getCurriculum_eng()) : user.getCurriculum_eng() != null)
            return false;
        if (getReceprion_hours_ita() != null ? !getReceprion_hours_ita().equals(user.getReceprion_hours_ita()) : user.getReceprion_hours_ita() != null)
            return false;
        if (getReceprion_hours_eng() != null ? !getReceprion_hours_eng().equals(user.getReceprion_hours_eng()) : user.getReceprion_hours_eng() != null)
            return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (int) (getNumber() ^ (getNumber() >>> 32));
        result = 31 * result + (getCurriculum_ita() != null ? getCurriculum_ita().hashCode() : 0);
        result = 31 * result + (getCurriculum_eng() != null ? getCurriculum_eng().hashCode() : 0);
        result = 31 * result + (getReceprion_hours_ita() != null ? getReceprion_hours_ita().hashCode() : 0);
        result = 31 * result + (getReceprion_hours_eng() != null ? getReceprion_hours_eng().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
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


    public String toStringForLog(){

        return "surname: " + surname + ", name: " + name + ", email: " + email  ;

    }
}