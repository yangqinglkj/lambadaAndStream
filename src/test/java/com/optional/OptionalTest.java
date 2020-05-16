package com.optional;

import com.entity.User;
import org.junit.Test;

import java.util.Optional;

/**
 * @author yangqing
 * @since 2020/5/9  16:35
 **/
public class OptionalTest {

    @Test
    public void test1(){
        Optional<User> user = Optional.of(new User());

        Optional<Object> empty = Optional.empty();


        Optional<User> op = Optional.ofNullable(new User());
//        op.ifPresent(x->System.out.println("有数据"));

//        Optional<User> op2 = Optional.ofNullable(null);
//        User user1 = op2.orElse(new User("zhangsan",18,1.1));
//        System.out.println(user1);

        User user1 = op.orElseGet(User::new);
        System.out.println(user1);
    }
}
