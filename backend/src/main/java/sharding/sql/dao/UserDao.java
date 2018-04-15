package sharding.sql.dao;

import sharding.sql.annotation.Select;
import sharding.sql.entity.User;

public interface UserDao {

    @Select("select id ,name ,age from user where id = ?")
    User query(Long id);

}
