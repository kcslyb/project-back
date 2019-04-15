package cn.kcs.test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * @description: lambda
 * @author: kcs
 * @create: 2019-01-23 09:56
 **/
public class Lambda {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(21, 23, 45, 45, 76, 23);
        OptionalDouble max = list.stream()
                .map(integer -> new Point(integer, integer))
                .mapToDouble(p -> p.distance(0, 0))
                .max();
        long count = list.stream().count();
        Optional<Integer> first = list.stream().findFirst();
        System.out.println(list);
        System.out.println(first);
        System.out.println(max);
        System.out.println(count);
    }
}
