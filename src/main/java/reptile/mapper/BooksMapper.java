package reptile.mapper;

import reptile.pojo.Booksmysql;

import java.util.List;
import java.util.Map;

public interface BooksMapper {
    /**
     * 根据书名查询ID
     * @param name
     * @return
     */
    public List<Map<String,Integer>> getName(String name);

    /**
     * 添加书籍信息
     * @param map
     * @return
     */
    public int insert(Map<String,Object> map);


}
