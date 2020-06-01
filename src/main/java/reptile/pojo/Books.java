package reptile.pojo;

import java.io.Serializable;
import java.util.List;

public class Books implements Serializable {

    private String booksId;
    //小说名称
    private String bookname;
    //小说作者
    private String bookautor;
    //小说图片
    private String bookimg;
    //小说简介
    private String bookdesl;
    //小说章节名称
    private List<String> booksnamelist;
    //小说章节链接
    private List<String> booksurl;

    public Books() {
    }

    public String getBooksId() {
        return booksId;
    }

    public void setBooksId(String booksId) {
        this.booksId = booksId;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookautor() {
        return bookautor;
    }

    public void setBookautor(String bookautor) {
        this.bookautor = bookautor;
    }

    public String getBookimg() {
        return bookimg;
    }

    public void setBookimg(String bookimg) {
        this.bookimg = bookimg;
    }

    public String getBookdesl() {
        return bookdesl;
    }

    public void setBookdesl(String bookdesl) {
        this.bookdesl = bookdesl;
    }

    public List<String> getBooksnamelist() {
        return booksnamelist;
    }

    public void setBooksnamelist(List<String> booksnamelist) {
        this.booksnamelist = booksnamelist;
    }

    public List<String> getBooksurl() {
        return booksurl;
    }

    public void setBooksurl(List<String> booksurl) {
        this.booksurl = booksurl;
    }

    @Override
    public String toString() {
        return "Books{" +
                "booksId='" + booksId + '\'' +
                ", bookname='" + bookname + '\'' +
                ", bookautor='" + bookautor + '\'' +
                ", bookimg='" + bookimg + '\'' +
                ", bookdesl='" + bookdesl + '\'' +
                ", booksnamelist=" + booksnamelist +
                ", booksurl=" + booksurl +
                '}';
    }
}
