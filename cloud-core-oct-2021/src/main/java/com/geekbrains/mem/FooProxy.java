package com.geekbrains.mem;

import java.time.Instant;

public class FooProxy extends Foo {

    @Override
    public int foo(int a, int b) {
        long start = System.currentTimeMillis();
        int res = super.foo(a, b);
        System.out.printf("%d + %d = %d\n", a, b, res);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation time: %d", end - start);
        return res;
    }
}
