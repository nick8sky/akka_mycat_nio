package sharding.sql.dao;

import sharding.sql.connect.DB;
import sharding.sql.parse.SqlParse;
import sharding.sql.route.RouteImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MethodProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  throws Throwable {

        Long id = (Long) args[0];

        System.out.println(method.getDeclaringClass());


        System.out.println(method.toGenericString());


        SqlParse sqlParse = new SqlParse();

        sqlParse.init();

        List<String> sqls = sqlParse.sqls ;

        String sql = sqls.get(0);

        sql = sql.replace("?", id+"");

        System.out.println("sql " +sql);

        DB db = new RouteImpl().routeSql(id) ;

        System.out.println("db " +db.toString());
        db.doSelect(db.getConnection(),sql);


        return null;
    }


}


