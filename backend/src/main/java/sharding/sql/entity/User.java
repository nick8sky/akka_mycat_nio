package sharding.sql.entity;


import lombok.Data;

@Data
public class User {

    private  Long id ;


    private  String name ;



    private  int age ;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
