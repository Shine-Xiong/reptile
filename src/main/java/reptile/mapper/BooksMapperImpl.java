package reptile.mapper;

import org.apache.ibatis.session.SqlSession;
import reptile.pojo.Booksmysql;
import reptile.utils.MybatisUtil;
import reptile.utils.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BooksMapperImpl extends Session implements BooksMapper {

    public List<Map<String,Integer>> getName(String name) {
        List<Map<String,Integer>> list=new ArrayList<Map<String, Integer>>();
        try {
            session= SessionUtil.getSession();
            BooksMapper booksmapper=session.getMapper(reptile.mapper.BooksMapper.class);
            list=booksmapper.getName(name);
        }finally {
            if (session!=null){
                session.close();
            }
        }
        return list;
    }

    public int insert(Map<String, Object> map) {
        int a;
        try {
            session=SessionUtil.getSession();
            BooksMapper booksmapper=session.getMapper(reptile.mapper.BooksMapper.class);
            a=booksmapper.insert(map);
            session.commit();
        }finally {
            if (session!=null){
                session.close();
            }
        }
        return a;
    }

}
