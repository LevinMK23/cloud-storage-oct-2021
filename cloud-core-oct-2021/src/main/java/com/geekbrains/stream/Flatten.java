package com.geekbrains.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class Flatten {

    public static void main(String[] args) {
        Integer[][] array = new Integer[][]{
                {1, 2, 3},
                {},
                {4},
                {5, 6, 7, 8},
                {},
                {},
                {9, 10}
        };


        int[] result = flatten(array);
        System.out.println(Arrays.toString(result));

    }

    public static int[] flatten(Integer[][] array) {
        return Arrays.stream(array)
                .flatMap(Stream::of)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
