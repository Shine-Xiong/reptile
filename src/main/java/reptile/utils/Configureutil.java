package reptile.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configureutil {
    public static String getProperty(String name){
        String path=System.getProperty("user.dir");
        File file=new File(path);
        String path2=file.getParent();
        Properties roperties=new Properties();
        FileInputStream fils=null;
        try {
            fils=new FileInputStream(path2+"\\mail.properties");
            roperties.load(fils);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (fils!=null){
                try {
                    fils.close();
                }catch (IOException e){
                    e.fillInStackTrace();
                }
            }
        }
        return roperties.getProperty(name);
    }
}
