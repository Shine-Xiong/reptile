package reptile.pojo;

import java.io.Serializable;
import java.util.List;

public class Bookchapter implements Serializable {
    //章节名称
    private List<String> namelist;
    //章节内容
    private List<String> textlist;

    public Bookchapter() {
    }

    public Bookchapter(List<String> namelist, List<String> textlist) {
        this.namelist = namelist;
        this.textlist = textlist;
    }

    public List<String> getNamelist() {
        return namelist;
    }

    public void setNamelist(List<String> namelist) {
        this.namelist = namelist;
    }

    public List<String> getTextlist() {
        return textlist;
    }

    public void setTextlist(List<String> textlist) {
        this.textlist = textlist;
    }

    @Override
    public String toString() {
        return "Bookchapter{" +
                "namelist=" + namelist +
                ", textlist=" + textlist +
                '}';
    }
}
