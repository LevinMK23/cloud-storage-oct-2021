package com.geekbrains.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reducer {

    public static void main(String[] args) {
        Reducer reducer = new Reducer();
        // System.out.println(reducer.collectToMap());

        System.out.println(
                Stream.of(1, 2, 1, 1, 1, 2, 3, 2)
                        .collect(Collectors.toMap(
                                Function.identity(),
                                value -> 1,
                                Integer::sum
                                )
                        )
        );


        Map<Integer, List<User>> users = Stream.of(
                User.builder()
                        .age(12)
                        .name("Ivan")
                        .build(),
                User.builder()
                        .age(12)
                        .name("Oleg")
                        .build(),
                User.builder()
                        .age(15)
                        .name("Petr")
                        .build())
                .collect(Collectors.groupingBy(User::getAge));

        System.out.println(users);
    }

    private void sum(Consumer<Integer> consumer) {
        consumer.accept(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(x -> x % 2 == 1)
                        .reduce(1, (x, y) -> x * y)
        );
    }

    private List<Integer> collect() {
        return Stream.of(1, 2, 3, 4, 5)
                .filter(x -> x % 2 == 1)
                .reduce(new ArrayList<>(),
                        (list, value) -> {
                            list.add(value);
                            return list;
                        },
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        });
    }

    private Map<Integer, Integer> collectToMap() {
        return Stream.of(1, 1, 2, 3, 3, 3, 3, 4, 5)
                .reduce(new HashMap<>(),
                        (map, value) -> {
                            map.put(value, map.getOrDefault(value, 0) + 1);
                            return map;
                        },
                        (left, right) -> {
                            left.putAll(right);
                            return left;
                        }
                );
    }
}
