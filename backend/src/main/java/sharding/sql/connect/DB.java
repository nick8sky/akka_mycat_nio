package sharding.sql.connect;

import java.sql.Connection;
import java.sql.SQLException;

public interface DB {


    Connection getConnection() throws SQLException;



    void doSelect(Connection connection,String sql)  throws SQLException;
}
