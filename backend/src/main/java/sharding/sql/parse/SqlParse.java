package sharding.sql.parse;

import sharding.sql.annotation.Select;
import sharding.sql.dao.UserDao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SqlParse {

    public List<String> sqls = new ArrayList<>();


    public void init( )  {

        doScanner("sharding.sql.dao");

        parseSelect();

    }


    private void doScanner(String packageName) {


    }





    private void parseSelect() {
        Class<? extends Object> userDaoClass = UserDao.class ;
        Method[] methods = userDaoClass.getMethods();
        for (Method method : methods) {
            if(!method.isAnnotationPresent(Select.class)){
                continue;
            }
            Select annotation = method.getAnnotation(Select.class);
            String sql = annotation.value();
            System.out.println("sql " +sql);
            sqls.add(sql);
        }
    }


}
