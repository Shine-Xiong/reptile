package reptile.utils;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionUtil {
    private static SqlSessionFactory factory;

    private SessionUtil(){}    //构造方法私有化，外面不能创建该类的实例
    public static SqlSession getSession(){
        return factory.openSession();
    }

    static{
        try {
            //创建一个Reader实例
            Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
            //获取会话工厂
            factory=new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
