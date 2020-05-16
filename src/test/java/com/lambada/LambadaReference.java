package com.lambada;

import com.entity.User;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author yangqing
 * @since 2020/5/15  10:21
 * 方法引用
 **/
public class LambadaReference {


    /**
     * 对象::实例方法名
     */
    @Test
    public void test1(){
        //普通写法
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("Hello");

        //对象::实例方法名
        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("World");
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test2(){
        //普通写法
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        int compare = comparator.compare(1, 2);
        System.out.println(compare);

        //类::静态方法名
        Comparator<Integer> comparator2 = Integer::compare;
        int compare2= comparator2.compare(4, 3);
        System.out.println(compare2);
    }

    /**
     * 类::实例方法名
     */
    @Test
    public void test3(){
        //普通写法
        BiPredicate<String,String> biPredicate = (x,y) -> x.equals(y);
        boolean test = biPredicate.test("abc", "abc");
        System.out.println(test);

        //类::实例方法名
        BiPredicate<String,String> biPredicate2 = String::equals;
        boolean test2 = biPredicate2.test("123", "123");
        System.out.println(test2);
    }

    /**
     * 构造器引用
     * 无参数构造器
     * 类名::new
     */
    @Test
    public void test4(){
        //普通写法
        Supplier<User> supplier = () -> new User();
        User user = supplier.get();
        //类名::new
        Supplier<User> supplier2 = User::new;
        User user2 = supplier2.get();
    }
}
