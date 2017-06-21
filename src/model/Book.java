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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (age != book.age) return false;
        if (!code.equals(book.code)) return false;
        if (!author.equals(book.author)) return false;
        if (!title.equals(book.title)) return false;
        if (!volume.equals(book.volume)) return false;
        if (!editor.equals(book.editor)) return false;
        return link.equals(book.link);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + code.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + volume.hashCode();
        result = 31 * result + age;
        result = 31 * result + editor.hashCode();
        result = 31 * result + link.hashCode();
        return result;
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
}
