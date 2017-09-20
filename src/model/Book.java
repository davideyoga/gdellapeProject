package model;

import dao.data.DaoData;

/**
 * Created by max on 25/05/17.
 */
public class Book {
    int id;
    String code;
    String author;
    String title;
    String volume;
    int age;
    String editor;
    String link;

    public Book(DaoData daoData){
        this.id = 0;
        this.code = null;
        this.author = null;
        this.title = null;
        this.volume = null;
        this.age = 0;
        this.editor = null;
        this.link = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", volume='" + volume + '\'' +
                ", age=" + age +
                ", editor='" + editor + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getId() != book.getId()) return false;
        if (getAge() != book.getAge()) return false;
        if (getCode() != null ? !getCode().equals(book.getCode()) : book.getCode() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
        if (getVolume() != null ? !getVolume().equals(book.getVolume()) : book.getVolume() != null) return false;
        if (getEditor() != null ? !getEditor().equals(book.getEditor()) : book.getEditor() != null) return false;
        return getLink() != null ? getLink().equals(book.getLink()) : book.getLink() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getVolume() != null ? getVolume().hashCode() : 0);
        result = 31 * result + getAge();
        result = 31 * result + (getEditor() != null ? getEditor().hashCode() : 0);
        result = 31 * result + (getLink() != null ? getLink().hashCode() : 0);
        return result;
    }
}
