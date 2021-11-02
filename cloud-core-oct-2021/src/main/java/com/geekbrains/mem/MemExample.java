package com.geekbrains.mem;

import java.util.ArrayList;
import java.util.Arrays;

public class MemExample {

    static {
        i = 5;
    }

    public static Integer i;          // static
    private int x;                    // primitive
    private Integer y;                // Integer pool, Double, Float
    private String s;                 // String pool
    private ArrayList<Integer> list;  // Object
    private Object notFinalObj;
    private final Object finalObj;

    public MemExample(Object finalObj) {
        this.finalObj = finalObj;
    }

    public void foo(Object obj, int p) {
        p++;
        System.out.println(p);
        obj = Arrays.asList(1, 2, 3);
        Object o = new Object();
    }

    public void change(User user) {
        user.setName("Oleg");
    }

}
