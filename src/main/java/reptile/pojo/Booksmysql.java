package reptile.pojo;

import java.io.Serializable;

public class Booksmysql implements Serializable {
    private Integer books_id;
    private String books_name;
    private String books_author;
    private String books_introduce;
    private String books_creationtime;
    private String books_state;
    private String books_delete;

    public Booksmysql() {
    }

    public Integer getBooks_id() {
        return books_id;
    }

    public void setBooks_id(Integer books_id) {
        this.books_id = books_id;
    }

    public String getBooks_name() {
        return books_name;
    }

    public void setBooks_name(String book_name) {
        this.books_name = book_name;
    }

    public String getBooks_author() {
        return books_author;
    }

    public void setBooks_author(String books_author) {
        this.books_author = books_author;
    }

    public String getBooks_introduce() {
        return books_introduce;
    }

    public void setBooks_introduce(String books_introduce) {
        this.books_introduce = books_introduce;
    }

    public String getBooks_creationtime() {
        return books_creationtime;
    }

    public void setBooks_creationtime(String books_creationtime) {
        this.books_creationtime = books_creationtime;
    }

    public String getBooks_state() {
        return books_state;
    }

    public void setBooks_state(String books_state) {
        this.books_state = books_state;
    }

    public String getBooks_delete() {
        return books_delete;
    }

    public void setBooks_delete(String books_delete) {
        this.books_delete = books_delete;
    }

    @Override
    public String toString() {
        return "Booksmysql{" +
                "books_id=" + books_id +
                ", books_name='" + books_name + '\'' +
                ", books_author='" + books_author + '\'' +
                ", books_introduce='" + books_introduce + '\'' +
                ", books_creationtime='" + books_creationtime + '\'' +
                ", books_state='" + books_state + '\'' +
                ", books_delete='" + books_delete + '\'' +
                '}';
    }
}
