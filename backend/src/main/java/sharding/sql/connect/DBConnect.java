package sharding.sql.connect;

import java.sql.*;

public class DBConnect {
    public static final String url = "jdbc:mysql://47.97.179.224/temp";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "1017~Fulin";

    public PreparedStatement pst = null;

    public DBConnect() throws ClassNotFoundException {
        Class.forName(name);//指定连接类型
    }


    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);//获取连接
    }


    int preDoUpdate(Connection connection,String sql) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(sql);//准备执行语句
        int x =  pst.executeUpdate();//执行语句，得到结果集
        pst.cancel();
        return  x;
    }


    void doSelect(Connection connection,String sql,Long id) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(sql);//准备执行语句
        pst.setLong(1, id);//设置条件id

        ResultSet ret = pst.executeQuery();//执行语句，得到结果集
        while (ret.next()) {
            String uid = ret.getString(1);
            String ufname = ret.getString(2);
            String pwd = ret.getString(3);

            System.out.println(uid + "\t" + ufname + "\t" + pwd  );
        }//显示数据
    }


    void setAutoCommit(Connection connection, boolean auto) throws SQLException {
        connection.setAutoCommit(auto);
    }


    void commit(Connection connection) throws SQLException {
        connection.commit();
    }


    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void test() throws SQLException {
        Connection connection = getConnection();
        String sql = "select * from USER";
        PreparedStatement pst = connection.prepareStatement(sql);//准备执行语句
        ResultSet ret = pst.executeQuery();//执行语句，得到结果集
        while (ret.next()) {
            String uid = ret.getString(1);
            String ufname = ret.getString(2);
            String pwd = ret.getString(3);

            System.out.println(uid + "\t" + ufname + "\t" + pwd  );
        }//显示数据
        ret.close();
        pst.close();
        connection.close();//关闭连接
    }

    public static void main(String[] args) throws  Exception {
        new DBConnect().test();
    }
}
