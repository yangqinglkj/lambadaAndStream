package com.stream;

import com.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yangqing
 * @since 2020/5/15  15:03
 * Stream 终止操作
 **/
public class StreamBreakTest {
    private List<User> userList = Arrays.asList(
            new User("张三", 11, 1111.0, User.Status.FREE),
            new User("李四", 22, 2222.0, User.Status.BUSY),
            new User("王五", 33, 3333.0, User.Status.VACATION),
            new User("赵六", 44, 4444.0, User.Status.FREE),
            new User("田七", 55, 5555.0, User.Status.BUSY)
    );

    /*
        终止操作
     * allMatch：流中的元素是否能匹配给定的条件,返回boolean
     * anyMatch：流中是否有一个元素匹配给定的条件，返回boolean
     * noneMatch：确保流中有没有匹配给定条件的元素，返回boolean
     * findFirst：取出中间层操作后的第一条数据（一般用于排序后取第一条数据）
     * findAny：返回当前流中的任意元素
     * count：返回流中元素的计数
     * max：返回流中元素最大的值
     * min：返回流中元素最小的值
     */
    @Test
    public void test1() {
        //allMatch：流中的元素是否能匹配给定的条件,返回boolean
        boolean result = userList.stream()
                .allMatch((u) -> u.getStatus().equals(User.Status.BUSY));
        System.out.println("流中的元素是否能匹配给定的条件:" + result);

        //anyMatch：流中是否有一个元素匹配给定的条件，返回boolean
        boolean result2 = userList.stream()
                .anyMatch((u) -> u.getStatus().equals(User.Status.BUSY));
        System.out.println("流中是否有一个元素匹配给定的条件:" + result2);

        //noneMatch：确保流中有没有匹配给定条件的元素，返回boolean 双重false代表有
        boolean result3 = userList.stream()
                .noneMatch((u) -> u.getStatus().equals(User.Status.BUSY));
        System.out.println("确保流中有没有匹配给定条件的元素:" + result3);

        Optional<User> user = userList.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        user.ifPresent(value -> System.out.println("取出的第一条数据:" + value));

        Optional<User> any = userList.parallelStream()
                .filter((u) -> u.getStatus().equals(User.Status.BUSY))
                .findAny();
        System.out.println("返回当前流中的任意元素：" + any);

        long count = userList.stream()
                .count();
        System.out.println("流中一共有" + count + "个元素");

        Optional<User> max = userList.stream()
                .max((u1, u2) -> Double.compare(u1.getSalary(), u2.getSalary()));
        System.out.println("流中工资最多的人是:" + max);

        Optional<Double> salaryMin = userList.stream()
                .map(User::getSalary)
                .min(Double::compareTo);
        salaryMin.ifPresent(salary -> System.out.println("工资最少的是:" + salary));


        Optional<User> min = userList.stream()
                .min((u1, u2) -> Double.compare(u1.getAge(), u2.getAge()));
        System.out.println("流中年龄最小的是:" + min);
    }

    /**
     * 归约
     * reduce(T identity,BinaryOperator)：可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void testReduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream()
                /*
                 *起始值是0，从流中取出一个元素1
                 * 这时候x等于0，y等于1，然后 0+1 = 1
                 * 得到的结果再赋给x，再从流中取下一个元素
                 * 这时候x等于1，y等于2，然后 1+2 = 3
                 * 得到的结果再赋给x，再从流中取下一个元素
                 * 这时候x等于3，y等于3，然后 3+3 = 6
                 * ....直到没有元素
                 * 最后得到一个新流
                 */
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        //计算出user工资总和
        Optional<Double> op = userList.stream()
                .map(User::getSalary)
                .reduce(Double::sum);
        System.out.println("工资总和是：" + op.get());
    }

    /**
     * 收集器
     * collect  将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素汇总的方法
     */
    @Test
    public void testCollect(){
        //把user的名字提取出来，并放入一个新的集合中
            userList.stream()
                .map(User::getName)
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(System.out::println);
    }
}
