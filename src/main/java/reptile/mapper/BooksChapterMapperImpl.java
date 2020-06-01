package reptile.mapper;

import reptile.utils.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BooksChapterMapperImpl extends Session implements BooksChapterMapper {
    public List<Map<String, Object>> QueryById(int id) {
        List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        try {
            session= SessionUtil.getSession();
            BooksChapterMapper books=session.getMapper(reptile.mapper.BooksChapterMapper.class);
            list=books.QueryById(id);
        }finally {
            if (session!=null){
                session.close();
            }
        }
        return list;
    }

    public int AddChapter(Map<String, Object> map) {
        int a;
        try {
            session=SessionUtil.getSession();
            BooksChapterMapper books=session.getMapper(reptile.mapper.BooksChapterMapper.class);
            a=books.AddChapter(map);
            session.commit();
        }finally {
            if (session!=null){
                session.close();
            }
        }
        return a;
    }
}
