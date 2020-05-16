package com.lambada;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author yangqing
 * @since 2020/5/14  10:30
 * java8内置的四大核心函数式接口
 * <p>
 * Consumer<T>：消费型接口
 * void accept(T t);
 * <p>
 * Supplier<T>：供给型接口
 * T get();
 * <p>
 * Function(T,R)：函数型接口
 * R apply(T t);
 * <p>
 * Predicate<T>：断言型接口
 * boolean test(T t)
 **/
public class LambadaFunctionalInterface {

    /**
     * 消费型接口
     */
    public void consumer(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }

    @Test
    public void testConsumer() {
        consumer(100, (x) -> System.out.println("消费了" + x + "元"));
    }

    /**
     * 供给型接口
     * 产生指定个数整数，放入集合中
     * @return
     */
    public List<Integer> supplier(Integer number, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
           list.add(supplier.get());
        }
        return list;
    }
    @Test
    public void testSupplier(){
        List<Integer> integers = supplier(10, () -> (int) (Math.random() * 100));
        integers.forEach(System.out::println);
    }

    /**
     * 函数型接口
     * 传入一个参数，返回一个对象
     * 处理字符串
     */
    public String handlerStr(String str, Function<String,String> function){
       return function.apply(str);
    }

    @Test
    public void testFunction(){
        String newStr = handlerStr("abcdefg", String::toUpperCase);
        System.out.println(newStr);
        String newStr2 = handlerStr("123456789",(str) -> str.substring(0,5));
        System.out.println(newStr2);
    }

    /**
     * 断言型接口
     * 将满足条件的字符串，放入集合中
     */
    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if (predicate.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

    @Test
    public void testPredicate(){
        List<String> list = Arrays.asList("aaa", "bbb", "cc", "dd");
        List<String> newList = filterStr(list, (s) -> s.length() >= 3);
        for (String str : newList) {
            System.out.println(str);
        }
        newList.forEach(System.out::println);
    }
}
