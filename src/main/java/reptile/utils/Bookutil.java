package reptile.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import reptile.pojo.Books;
import reptile.pojo.Bookchapter;

public class Bookutil {
    //根据链接获取当前页面所有书籍
    public static List<String> getbooksurl(String url){
        if (url.length()>0) {
            try {
                Document document;
                document = Jsoup.connect(url).get();
                Elements id1 = document.getElementsByAttributeValue("style", "font-weight: normal;");
                List<String> booklist = new ArrayList();
                for (Element e : id1) {
                    String id = e.attr("abs:href");
                    booklist.add(id);
                }
                return booklist;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    //根据书籍url获取书籍相关信息
    public static Books getbookdetails(String bookurl){
        if (bookurl.length()>0){
            try {
                Document document;
                Books books=new Books();
                document=Jsoup.connect(bookurl).get();
                //根据H1标签获取书名
                String bookname=document.select("h1").text();
                books.setBookname(bookname);
                //根据div class获取div标签内容
                Elements e1=document.select(".info_des");
                //根据得到的内容 获取第一个dl标签的文本(作者)
                String e=e1.select("dl").first().text();
                //根据得到的文本截取:之前的字符串
                String name=e.substring(0,e.indexOf("："));
                //截取:之后的字符串得到作者
                String author=e.substring(name.length()+1,e.length());
                books.setBookautor(author);
                //根据标签class获得内容
                String introduce=e1.select(".intro").text();
                //获取小说详情并截取
                String intr=introduce.substring(0,introduce.indexOf("作者："+author));
                books.setBookdesl(intr.replace("【展开】【收起】",""));
                //根据标签class获得内容
                Elements e2=document.select(".tupian");
                //找到img标签属性src带,jpg结尾的标签
                Elements imgurl=e2.select("img[src$=.jpg]");
                //获得src里的标签连接(绝对链接)
                String url=imgurl.attr("abs:src");
                books.setBookimg(url);

                //根据class获得元素
                Elements urlid=document.select(".pc_list");
                int i=0;
                List<String> booksname=new ArrayList<String>();
                List<String> booksurl=new ArrayList<String>();
                //遍历元素 取第二个元素
                for (Element ee:urlid){
                    if (i==1){
                        Elements urls=ee.select("a[href]");
                        for(Element eee:urls){
                            //查找第一次出现改字符的位置
                            int d=eee.text().indexOf("第");
                            String title="";
                            //如果找到则截取
                            if(d!=-1){
                                title=eee.text().substring(d,eee.text().length());
                            }
                            int a=0;
                            //循环所有已存在章节
                            for (int j = 0; j < booksname.size(); j++) {
                                //判断是否重复
                                if (booksname.get(j).equals((title.equals("")?eee.text():title))){
                                    a++;
                                }
                            }
                            //如果=0说明没有重复直接添加
                            if (a==0){
                                booksname.add((title.equals(null)?eee.text():title));
                                booksurl.add(eee.attr("abs:href"));
                             //不添加
                            }else {
                                System.out.println("当前存在重复章节:"+eee.text());
                            }
                        }
                    }
                    i++;
                }
                books.setBooksnamelist(booksname);
                books.setBooksurl(booksurl);
                return books;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    //根据章节链接获得名称 内容
    public static Bookchapter getbookstext(List<String> url){
        if (url.size()>0){
            try {
                Document document;
                Bookchapter bookchapter = new Bookchapter();
                List<String> namelist=new ArrayList<String>();
                List<String> textlist=new ArrayList<String>();
                for (int i=0;i<url.size();i++) {
                    System.out.println("正在抓取第"+(i+1)+"章");
                    document = Jsoup.connect(url.get(i)).get();
                    String booksname = document.select("h1").text();
                    String bookstext = document.select("#content1").text();
                    bookstext = bookstext.replaceAll(" ", "&nbsp;");
                    bookstext = bookstext.replaceAll(" ", "\\\\n");
                    String a = bookstext.substring(0, bookstext.indexOf("\\"));
                    String b = bookstext.substring(a.length() + 2, bookstext.length());
                    int indxe=b.lastIndexOf("最新网址");
                    String c=b.substring(0,indxe-2);
                    //截取第之后的字符串
                    int d=booksname.indexOf("第");
                    if(d!=-1){
                        namelist.add(booksname.substring(d,booksname.length()));
                    }else {
                        namelist.add(booksname);
                    }
                    textlist.add(c);
                }
                bookchapter.setNamelist(namelist);
                bookchapter.setTextlist(textlist);
                return bookchapter;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
