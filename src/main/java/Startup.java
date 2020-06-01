import reptile.mapper.BooksChapterMapper;
import reptile.mapper.BooksChapterMapperImpl;
import reptile.mapper.BooksMapper;
import reptile.mapper.BooksMapperImpl;
import reptile.pojo.Bookchapter;
import reptile.pojo.Books;
import reptile.pojo.Booksmysql;
import reptile.utils.Bookutil;
import reptile.utils.Configureutil;
import reptile.utils.TxtUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Startup {
    public static void main(String[] args) {
        List book=new ArrayList();
        //获得最大爬取页数
        int page= Integer.parseInt(Configureutil.getProperty("PAGING"));
        for (int i=1;i<=page;i++){
            //循环爬取页面
            book= Bookutil.getbooksurl(Configureutil.getProperty("URL")+i+".html");
            System.out.println("正在爬取页面:"+Configureutil.getProperty("URL")+i+".html");
            //判断是否爬取到数据
            if (book.size()!=0){
                System.out.println("当前页面书籍链接爬取完成");
                //循环页面的所有书籍链接
                for (int j = 0; j <book.size() ; j++) {
                    Books books=new Books();
                    //根据书籍链接获取书籍信息
                    books=Bookutil.getbookdetails(book.get(j).toString());
                    System.out.println("正在爬取的书籍:"+books.getBookname()+"####地址:"+book.get(j));
                    //判断是否获取到书籍信息
                    if(books.getBookname()!=null){
                        System.out.println("当前书籍信息抓取成功...");
                        //根据书籍名称查询数据库是否存在 (如果数据库存在2本相同小说名则需要循环处理)
                        List<Map<String,Integer>> listbook=new BooksMapperImpl().getName(books.getBookname());
                        if(listbook.size()==1){
                            //根据书籍id查询数据库已存在本小说的所有章节
                            List<Map<String,Object>> chapterlist=new BooksChapterMapperImpl().QueryById(listbook.get(0).get("books_id"));
                            //判断是否有存在章节
                            if(chapterlist.size()>0) {
                                //根据查询返回结果可以得知当前小说最大章节
                                int maximumchapter = chapterlist.size();
                                //如何当前爬取小说大于数据库查询小说数量 且当前小说名称=当前小说链接
                                if (books.getBooksnamelist().size() > maximumchapter && books.getBooksurl().size() > maximumchapter && books.getBooksnamelist().size() == books.getBooksurl().size()) {
                                    //创建list用于保存未添加的小说章节url
                                    List<String> url=new ArrayList<String>();
                                    //循环小说
                                    for (int k = 0; k < books.getBooksnamelist().size(); k++) {
                                        //条件判断 当前小说大于数据库小说才开始添加list
                                        if (k > (maximumchapter - 1)) {
                                            url.add(books.getBooksurl().get(k));
                                        }
                                    }
                                    //抓取小说章节内容
                                    Bookchapter bookchapter=Bookutil.getbookstext(url);
                                    if(bookchapter.getNamelist().size()>0){
                                        int a=0;
                                        //创建目标文件夹并返回路径
                                        String filepath= TxtUtil.GetFolder(books.getBookname());
                                        Map<String,Object> map=new HashMap<String, Object>();
                                        for (int k = 0; k < bookchapter.getNamelist().size(); k++) {
                                            String path=null;
                                            try {
                                                path=TxtUtil.GetFile(filepath,bookchapter.getNamelist().get(k),bookchapter.getTextlist().get(k));
                                                System.out.println(bookchapter.getNamelist().get(k)+"写入成功!路径:"+path);
                                            }catch (IOException e){
                                                System.out.println(bookchapter.getNamelist().get(k)+"写入失败");
                                                e.printStackTrace();
                                            }
                                            map.put("chapter_booksid",listbook.get(0).get("books_id"));
                                            map.put("chapter_title",bookchapter.getNamelist().get(k));
                                            map.put("chapter_text",path);
                                            //获取当前的系统时间戳(毫秒)
                                            long time=System.currentTimeMillis();
                                            String creationtime=String.valueOf(time);
                                            map.put("chapter_creationtime",creationtime);
                                            int b=new BooksChapterMapperImpl().AddChapter(map);
                                            if (b==1){
                                                a++;
                                            }
                                        }
                                        System.out.println("小说章节添加完成:总共"+(bookchapter.getNamelist().size()-1)+"章节----成功:"+a+"#失败:"+((bookchapter.getNamelist().size()-1)-a));
                                    }else {
                                        System.out.println("小说章节抓取失败");
                                    }
                                }
                            }else {
                                //抓取小说章节内容
                                Bookchapter bookchapter=Bookutil.getbookstext(books.getBooksurl());
                                if(bookchapter.getNamelist().size()>0) {
                                    int a = 0;
                                    String filepath= TxtUtil.GetFolder(books.getBookname());
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    for (int k = 0; k < bookchapter.getNamelist().size(); k++) {
                                        String path=null;
                                        try {
                                            path=TxtUtil.GetFile(filepath,bookchapter.getNamelist().get(k),bookchapter.getTextlist().get(k));
                                            System.out.println(bookchapter.getNamelist().get(k)+"写入成功!路径:"+path);
                                        }catch (IOException e){
                                            System.out.println(bookchapter.getNamelist().get(k)+"写入失败");
                                            e.printStackTrace();
                                        }
                                        map.put("chapter_booksid", listbook.get(0).get("books_id"));
                                        map.put("chapter_title", bookchapter.getNamelist().get(k));
                                        map.put("chapter_text", path);
                                        //获取当前的系统时间戳(毫秒)
                                        long time = System.currentTimeMillis();
                                        String creationtime = String.valueOf(time);
                                        map.put("chapter_creationtime", creationtime);
                                        int b = new BooksChapterMapperImpl().AddChapter(map);
                                        if (b == 1) {
                                            a++;
                                        }
                                    }
                                    System.out.println("小说章节添加完成:总共"+(bookchapter.getNamelist().size())+"章节----成功:"+a+"#失败:"+((bookchapter.getNamelist().size())-a));
                                }else {
                                    System.out.println("小说章节抓取失败");
                                }
                            }
                            //如果小说不存在
                        }else if (listbook.size()==0){
                            //设置小说需要的参数
                            Map<String,Object> map=new HashMap<String, Object>();
                            map.put("books_name",books.getBookname());
                            map.put("books_author",books.getBookautor());
                            map.put("books_introduce",books.getBookdesl());
                            map.put("books_img",books.getBookimg());
                            map.put("two_id",Configureutil.getProperty("TYPE"));
                            //获取当前的系统时间戳(毫秒)
                            long time = System.currentTimeMillis();
                            String creationtime = String.valueOf(time);
                            map.put("books_creationtime",creationtime);
                            //添加小说
                            int a=new BooksMapperImpl().insert(map);
                            if(a==1){
                                List<Map<String,Integer>> list=new BooksMapperImpl().getName(books.getBookname());
                                //如果大于0说明查到了小说id
                                if(list.size()>0){
                                    //根据链接爬取所有小说章节内容
                                    Bookchapter bookchapter=Bookutil.getbookstext(books.getBooksurl());
                                    //说明爬取到了内容
                                    if (bookchapter.getNamelist().size()>0){
                                        int b = 0;
                                        String filepath= TxtUtil.GetFolder(books.getBookname());
                                        Map<String,Object> map1=new HashMap<String, Object>();
                                        for (int k = 0; k <bookchapter.getNamelist().size() ; k++) {
                                            String path=null;
                                            try {
                                                path=TxtUtil.GetFile(filepath,bookchapter.getNamelist().get(k),bookchapter.getTextlist().get(k));
                                                System.out.println(bookchapter.getNamelist().get(k)+"写入成功!路径:"+path);
                                            }catch (IOException e){
                                                System.out.println(bookchapter.getNamelist().get(k)+"写入失败");
                                                e.printStackTrace();
                                            }
                                            map1.put("chapter_booksid",list.get(0).get("books_id"));
                                            map1.put("chapter_title",bookchapter.getNamelist().get(k));
                                            map1.put("chapter_text",path);
                                            //获取当前的系统时间戳(毫秒)
                                            long time1=System.currentTimeMillis();
                                            String creationtime1=String.valueOf(time1);
                                            map1.put("chapter_creationtime",creationtime1);
                                            int c=new BooksChapterMapperImpl().AddChapter(map1);
                                            if (c==1){
                                                b++;
                                            }
                                        }
                                        System.out.println("小说章节添加完成:总共"+(bookchapter.getNamelist().size())+"章节----成功:"+b+"#失败:"+((bookchapter.getNamelist().size())-b));
                                    }else {
                                        System.out.println("爬取章节内容失败....");
                                    }
                                }
                            }else {
                                System.out.println("书籍添加失败..");
                            }
                        }
                    }else {
                        System.out.println("当前书籍信息抓取失败...");
                    }
                }
            }else {
                System.out.println("当前页面未获得书籍链接");
            }
       }
        //BooksMapper BooksMapper=new BooksMapperImpl();
//        List<Booksmysql> list=new BooksMapperImpl().getName("水电费");
//        System.out.println(list.size());
//        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("books_name","啥啊啊1");
//        map.put("books_author","安慰安慰啊草拟吗");
//        map.put("books_introduce","安慰安慰啊1");
//        map.put("books_creationtime","14124124121");
//        int b=new BooksMapperImpl().insert(map);
//        System.out.println(b);


    }
}
