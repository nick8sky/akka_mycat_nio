package sharding.sql.route;

import sharding.sql.connect.DB;
import sharding.sql.connect.Mysql_30016;
import sharding.sql.connect.Mysql_30017;


public class RouteImpl  {





   public DB routeSql(Long  id) throws ClassNotFoundException {
         if(id % 2 ==1){
             return  new Mysql_30016() ;
         }else {
             return  new Mysql_30017();
         }
    }

}
