package com.geekbrains.stream;

@FunctionalInterface
public interface Foo<T> {

    void foo(T arg1, T arg2);

    default void bar() {

    }
}
