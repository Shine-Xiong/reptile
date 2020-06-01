import reptile.mapper.BooksChapterMapperImpl;
import reptile.mapper.BooksMapper;
import org.apache.ibatis.session.SqlSession;
import reptile.mapper.BooksMapperImpl;
import reptile.pojo.Books;
import reptile.pojo.Booksmysql;
import reptile.utils.Bookutil;
import reptile.utils.MybatisUtil;

import java.util.*;

public class BookStart {
    public static void main(String[] args) {
        Books books= Bookutil.getbookdetails("http://www.xqishuta.com/du/73/73761/");
        int a=1;
        for (int i = 0; i <books.getBooksnamelist().size() ; i++) {
            System.out.println(books.getBooksnamelist().get(i)+"链接:"+books.getBooksurl().get(i));
            a++;
        }
        System.out.println(books.getBooksnamelist().size());
        System.out.println(a);

        //List<String> list=new ArrayList<String>();
//        list.add("1213");
//        list.add("12312");
//        list.add("1231231");
//        list.add("123123123");
//        list.add("123123123");
//        int a=0;
//        for (int i = 0; i <list.size() ; i++) {
//            if (list.get(i).equals("c ")){
//                a++;
//            }
//        }
//        if (a==0){
//            list.add("操");
//        }else{
//            System.out.println("当前存在重复章节:");
//        }
//        System.out.println(a);
//        System.out.println(list.toString());
//        for (int i = 0; i <a.length() ; i++) {
//            int b=a.indexOf("?");
//            if (b!=-1){
//
//            }
//        }

//        List<Map<String,Integer>> list=new BooksMapperImpl().getName("我擦");
//        System.out.println(list.size());
//        System.out.println( list.get(1).get("books_id"));

//        List<Map<String,Object>> list=new BooksChapterMapperImpl().QueryById(1);
//        System.out.println(list.get(1).get("chapter_id")+""+list.get(1).get("chapter_title"));
//        List book=new ArrayList();
//        Books books=new Books();
//        int page= Integer.parseInt(Configureutil.getProperty("PAGING"));
//        for (int i=1;i<=page;i++){
//            book=Bookutil.getbooksurl(Configureutil.getProperty("URL")+i+".html");
//
//        }
        /**
        SqlSession session= MybatisUtil.getFactory().openSession();
        BooksMapper booksMapper=session.getMapper(BooksMapper.class);
        List<Booksmysql> list=booksMapper.getName("驱动器无");
        int a=booksMapper.delete(6);
        System.out.println("删除了"+a);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("books_id",105);
        map.put("books_name","啥啊啊1");
        map.put("books_author","安慰安慰啊1");
        map.put("books_introduce","安慰安慰啊1");
        map.put("books_creationtime","14124124121");
        int b=booksMapper.insert(map);

        System.out.println("添加了"+b);
        if (list.size()!=0){
            System.out.println(list.get(0).getBooks_id());
        }else {
            System.out.println("不存在");
        }
        session.commit();

        SqlSession session2= MybatisUtil.getFactory().openSession();
        BooksMapper booksMapper2=session.getMapper(BooksMapper.class);
        List<Booksmysql> list1 = booksMapper2.getList();
//        for (int i = 0; i <list1.size() ; i++) {
//            Booksmysql booksmysql = list.get(i);
//            System.out.println(booksmysql);
//        }
        for (Booksmysql booksmysql :list1    ) {
            System.out.println(booksmysql);
        }
        session2.commit();
         */
//        Bookchapter bookchapter=new Bookchapter();
//        books =Bookutil.getbookdetails(book.get(7).toString());
//        bookchapter=Bookutil.getbookstext(books.getBooksurl());
//        for (int i=0;i<bookchapter.getNamelist().size();i++){
//            System.out.println(bookchapter.getNamelist().get(i));
//            System.out.println(bookchapter.getTextlist().get(i));
//        }


    }
}
