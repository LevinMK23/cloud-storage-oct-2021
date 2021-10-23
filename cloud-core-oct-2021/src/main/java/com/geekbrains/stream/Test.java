package com.geekbrains.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    static void sum(int a, int b) {
        System.out.println(a + b);
    }

    public static void main(String[] args) throws IOException {
        Foo<Integer> sum = Test::sum;
        sum.foo(1, 2);
        System.out.println(sum.getClass());
        Foo<Integer> foo = new Foo<Integer>() {
            @Override
            public void foo(Integer arg1, Integer arg2) {

            }
        };

        System.out.println(foo.getClass());

        // forEach, peek
        Consumer<String> printer = System.out::println;
        printer.accept("Hello world");
        printer.andThen(s -> System.out.println(s + " " + s))
                .accept("OK");

        // dropWhile, filter -> Stream, (allMatch, anyMatch, noMatch) -> boolean
        Predicate<Integer> isOdd = x -> x % 2 == 1 && x > 12;
        Predicate<Integer> isOdd2 = x -> x % 2 == 1;
        Predicate<Integer> and = isOdd2.and(x -> x > 12);
        isOdd.test(15);

        // map, flatMap
        Function<Integer, String> f = String::valueOf;
        f.apply(12);

        Supplier<ArrayList<Integer>> listSupplier = ArrayList::new;

        Stream.of(1, 2, 3, 4, 5, 6, 7)
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);

        Stream.of(1, 2, 3, 4, 5, 6, 7)
                .filter(x -> x % 2 == 0)
                .map(x -> x * 2)
                .forEach(x -> System.out.print(x + " "));

        System.out.println();

        List<String> list = Stream.of("A", "B", "C")
                .collect(Collectors.toList());

        String str = Stream.of("A", "B", "C")
                .collect(Collectors.joining(", "));

        System.out.println(list);
        System.out.println(str);

        List<String> collect = Files.list(Paths.get("./"))
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());


    }
}
