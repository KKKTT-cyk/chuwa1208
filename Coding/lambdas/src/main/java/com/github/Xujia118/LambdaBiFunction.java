package com.github.Xujia118;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class LambdaBiFunction {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
        BiFunction<List<Integer>, Integer, List<Integer>> addToAll = (list, value) -> {
            List<Integer> result = new ArrayList<>(list);
            for (Integer i: list) {
                result.add(i + value);
            }
            return result;
        };

        System.out.println(apply(3, 5, add));              // 8
        System.out.println(apply(4, 6, multiply));         // 24
        System.out.println(apply("Hi", 3, repeat));        // HiHiHi

        List<Integer> nums = Arrays.asList(1, 2, 3);
        System.out.println(apply(nums, 10, addToAll));     // [11, 12, 13]

    }

    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }
}
