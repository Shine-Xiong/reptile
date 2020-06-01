package reptile.pojo;

import java.io.Serializable;

public class Bookchaptermysql implements Serializable {
    private Integer chapter_id;
    private Integer chapter_booksid;
    private String chapter_title;
    private String chapter_text;
    private String chapter_creationtime;
    private String chapter_state;
    private String chapter_delete;
}
