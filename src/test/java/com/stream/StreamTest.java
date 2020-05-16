package com.stream;

import com.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author yangqing
 * @since 2020/5/9  10:12
 * Stream三个操作步骤
 *      1、创建流
 *      2、中间一系列操作
 *      3、终止操作
 **/
public class StreamTest {
    private List<User> userList = Arrays.asList(
            new User("张三", 11, 1111.11, User.Status.BUSY),
            new User("李四", 22, 2222.22, User.Status.BUSY),
            new User("王五", 33, 3333.33, User.Status.BUSY),
            new User("赵六", 44, 4444.44, User.Status.BUSY),
            new User("田七", 55, 5555.55, User.Status.BUSY),
            new User("田七", 55, 5555.55, User.Status.BUSY),
            new User("田七", 55, 5555.55, User.Status.BUSY),
            new User("田七", 55, 5555.55, User.Status.BUSY)
    );

    /**
     * 创建流对象四种方式
     */
    @Test
    public void test() {
        //1 通过Collection系列集合提供的stream()或parallelStream方法创建
        Stream<User> stream = userList.stream();
        //2 通过Arrays 中的静态方法stream()创建数组流
        String[] arrays = new String[]{"1", "2", "3"};
        Stream<String> stream1 = Arrays.stream(arrays);
        //3 通过Stream类中的of()方法
        Stream<String> stream2 = Stream.of("aaa", "bbb", "ccc");
        //4 创建无限流
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
    }

    /**
     * 中间操作
     * filter：筛选
     * distinct：根据流所产生元素的hashcode equals方法实现的流，去重
     * limit：返回指定数量的流
     * skip：返回扔掉了前n个元素的流
     * map：用于映射每个元素到对应的结果
     * sorted：用于对流进行排序
     */
    @Test
    public void test2() {
        userList.stream()
                .filter((x) -> x.getAge() > 30)
//                .limit(2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 中间操作
     * 映射
     * map，接收Lambada，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void testMap() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        //提取名字
        userList.stream()
                .map(User::getName)
                .distinct()
                .forEach(System.out::println);

//        list.stream()
//                .map(StreamTest::filterCharacter)
//                .forEach((s) -> s.forEach(System.out::println));
            list.stream()
                    .flatMap(StreamTest::filterCharacter)
                    .forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 中间操作
     * 排序
     * sorted()：自然排序(Comparable)
     * sorted(Comparator com)：自定义排序
     */
    @Test
    public void testSorted(){
        userList.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .forEach(System.out::println);
    }


}
