package com.stream;

import com.entity.Person;
import com.entity.User;
import org.junit.Test;

import java.util.*;
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
//            new User("田七", 55, 5555.0, User.Status.BUSY)
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
                 * 起始值是0，从流中取出一个元素1
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
    public void testCollect() {
        //把user的名字提取出来，并放入一个新的集合中
        //收集到list集合中
        userList.stream()
                .map(User::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //收集到set集合中
        userList.stream()
                .map(User::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        //收集到map集合中
        Map<String, Integer> collect = userList.stream()
                .collect(Collectors.toMap(User::getName, User::getAge));
        collect.forEach((key, value) -> System.out.println(key + value));


        //自定义集合
        userList.stream()
                .map(User::getName)
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(System.out::println);

    }

    /**
     * 收集器
     * collect  将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素汇总的方法
     */
    @Test
    public void testCollect2() {
        //收集总数
        Long collect = userList.stream()
                .collect(Collectors.counting());
        System.out.println("总数为：" + collect);

        //收集工资平均值
        Double avgSalary = userList.stream()
                .collect(Collectors.averagingDouble(User::getSalary));
        System.out.println("平均工资为：" + avgSalary);

        //收集工资的总和
        Double sumSalary = userList.stream()
                .collect(Collectors.summingDouble(User::getSalary));
        System.out.println("工资总和为：" + sumSalary);

        //收集工资最多的人
        Optional<User> maxSalaryUser = userList.stream()
                .collect(Collectors.maxBy((u1, u2) -> Double.compare(u1.getSalary(), u2.getSalary())));
        maxSalaryUser.ifPresent(value -> System.out.println("工资最多得人是：" + value));

        //收集工资最少的人
        Optional<User> minSalary = userList.stream()
                .collect(Collectors.minBy((u1, u2) -> Double.compare(u1.getSalary(), u2.getSalary())));
        minSalary.ifPresent(value -> System.out.println("工资最少的人是：" + value));

    }

    /**
     * 按照状态分组
     */
    @Test
    public void testGroupBy() {
        Map<User.Status, List<User>> map = userList.stream()
                .collect(Collectors.groupingBy(User::getStatus));
        for (Map.Entry<User.Status, List<User>> result : map.entrySet()) {
            System.out.println(result.getKey() + "组有：" + result.getValue());
        }
//        Iterator<Map.Entry<User.Status, List<User>>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry<User.Status, List<User>> next = iterator.next();
//            System.out.println(next.getKey()+"组有："+next.getValue());
//        }
    }

    /**
     * 多级分组
     * 先按照状态再按照年龄
     */
    @Test
    public void testManyGroupBy() {
        Map<User.Status, Map<String, List<User>>> map = userList.stream()
                .collect(Collectors.groupingBy(User::getStatus, Collectors.groupingBy(
                        (u) -> {
                            if (u.getAge() <= 20) {
                                return "少年";
                            } else if (u.getAge() <= 30) {
                                return "青年";
                            } else {
                                return "中年";
                            }
                        }
                )));
        for (Map.Entry<User.Status, Map<String, List<User>>> mapEntry : map.entrySet()) {
            for (Map.Entry<String, List<User>> mapValues : mapEntry.getValue().entrySet()) {
                System.out.println(mapEntry.getKey() + "状态组中有：" + mapValues.getKey() + "年龄组中有：" + mapValues.getValue());
            }

        }
    }

    /**
     * 分区
     */
    @Test
    public void testPartition() {
        Map<Boolean, List<User>> map = userList.stream()
                .collect(Collectors.partitioningBy((u) -> u.getAge() > 30));
//        for (Map.Entry<Boolean, List<User>> mapEntry : map.entrySet()) {
//            System.out.println(mapEntry.getKey()+"区有："+mapEntry.getValue());
//        }
        map.forEach((x, y) -> System.out.println(x + "区有：" + y));
    }

    /**
     * 生成一个用于求元素和的Collector，首先通过给定的mapper将元素转换类型，然后再求和。参数的作用就是将元素转换为指定的类型，最后结果与转换后类型一致
     */
    @Test
    public void test() {
        DoubleSummaryStatistics collect = userList.stream()
                .collect(Collectors.summarizingDouble(User::getSalary));
        System.out.println("工资总和:" + collect.getSum());
        System.out.println("平均工资:" + collect.getAverage());
        System.out.println("工资数量:" + collect.getCount());
        System.out.println("最多工资:" + collect.getMax());
        System.out.println("最少工资:" + collect.getMin());
    }

    @Test
    public void testJoining() {
        String collect = userList.stream()
                .map(User::getName)
                .collect(Collectors.collectingAndThen(Collectors.joining(","), x -> x + "a"));
        System.out.println(collect);
    }

    @Test
    public void test2(){
        List<Person> list = Arrays.asList(
                new Person("shenzhen", "张", 1),
                new Person("beijing", "王", 2),
                new Person("shanghai", "李", 3),
                new Person("beijing", "赵", 4));


//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        Double result = list.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(v -> v),
//                s -> s * s));
        Comparator<Person> comparing = Comparator.comparing(Person::getSum);

        // 根据条件过滤分组后，获取最小的
        Comparator<Person> sumComparator = Comparator.comparing(Person::getSum);
        Map<Boolean, Person> maxBoolMap = list.stream().collect(
                Collectors.partitioningBy(input -> input.getSum() >= 2,
                        Collectors.collectingAndThen(Collectors.minBy(sumComparator), Optional::get)));
        System.out.println(maxBoolMap);


    }


}


