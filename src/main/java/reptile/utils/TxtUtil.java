package reptile.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class TxtUtil {
    public static String GetFolder(String name){
        String type=null;
        switch (Integer.valueOf(Configureutil.getProperty("TYPE"))){
            case 1:
                type="玄幻奇幻";
                break;
            case 2:
                type="武侠仙侠";
                break;
        }
        String filepath=Configureutil.getProperty("FILE")+type+"/"+name;
        File file=new File(filepath);
        //检查文件夹目录是否存在
        if (!file.exists()) {
            file.mkdirs();// mkdirs创建多级目录
        }
        return filepath;
    }
    public static String GetFile(String filepath,String name,String text) throws IOException{
        //?号替换_  windows不允许使用?号命名 Linux除/原则上都可以使用,但不建议使用特殊符号
        String textpath=filepath+"/"+name.replace("?","_")+".txt";
        File file=new File(textpath);
        FileWriter writer=null;
        try {
            //检查文件夹目录下的文本是否存在
            if (!file.exists()) {
                // 创建目标文本
                file.createNewFile();
            }
            writer=new FileWriter(file,false);
            writer.append(text);
            writer.flush();
            return textpath;
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (null!=writer){
                writer.close();
            }
        }
        return null;
    }
}
