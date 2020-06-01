package reptile.mapper;

import java.util.List;
import java.util.Map;

public interface BooksChapterMapper {

    /**
     * 根据书籍id查询对应小说全部章节
     * @param id
     * @return
     */
    public List<Map<String,Object>> QueryById(int id);

    /**
     * 添加小说章节
     * @param map
     * @return
     */
    public int AddChapter(Map<String,Object> map);
}
