package org.twopc.commit;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JdbcCommit {
    //创建xaid
    //执行
    //提交

    private Map<String ,Connection> map = new HashMap<>() ;
    DBConnect dbConnect = new DBConnect();

    public JdbcCommit() throws ClassNotFoundException {
    }

    public  String doInsert() throws Exception {
        String sql = "insert into USER(USERNAME,PASSWORD) values ('3','42')" ;
        String xaid =  new Random().nextInt(100)+21+"" ;

        Connection connection = dbConnect.getConnection();
        dbConnect.setAutoCommit(connection,false);
        int done  = dbConnect.preDoUpdate(connection,sql);
        if(done >0){
            dbConnect.doSelect(connection,"select * from USER");
        }
        //connection.rollback();
        map.put(xaid,connection);
        return  xaid ;
    }



    public  void commit(String xaid ) throws Exception {

        Connection connection = map.get(xaid) ;
        //connection.rollback();
        connection.commit();
        dbConnect.doSelect(connection,"select * from USER");
        map.remove(xaid);
        dbConnect.close(connection);
    }

    public static void main(String[] args) throws Exception {
        JdbcCommit jdbcCommit = new JdbcCommit();
        String xaid = jdbcCommit.doInsert();
        Thread.sleep(2000l);
        System.out.println("commit");
        jdbcCommit.commit(xaid);
    }





}
