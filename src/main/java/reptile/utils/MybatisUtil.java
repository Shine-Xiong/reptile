package reptile.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory factory;
    static{
        //1.获取核心配置文件的数据
        InputStream in;
        try{
            in = Resources.getResourceAsStream("mybatis-config.xml");
            //2.创建会话工厂
            factory = new SqlSessionFactoryBuilder().build(in);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getFactory(){
        return factory;
    }
}
