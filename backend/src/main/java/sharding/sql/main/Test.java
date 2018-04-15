package sharding.sql.main;

import sharding.sql.dao.Invoker;
import sharding.sql.dao.UserDao;
import sharding.sql.entity.User;

public class Test {


    @org.junit.Test
    public  void doSelect(){

        UserDao   userDaoImpl= (UserDao)new Invoker().getInstance(UserDao.class);

        Long id = 1L;
        User user = userDaoImpl.query(id);

        Long id2 = 2L;
        User user2 = userDaoImpl.query(id2);


    }
}
