package com.geekbrains.mem;

public class MemTest {

    public static void main(String[] args) {
        // meta space
        MemExample.i = 5;

        // JIT just int time compiler
        // JRE java runtime environment

        MemExample example = new MemExample("123");
        String s = new String("1234");
        example.foo(s, 5);
        System.out.println(s);

        User user = new User();
        user.setName("Ivan");
        example.change(user);

        System.out.println(user);

        Integer x = 5;
        Integer y = new Integer(5);

        System.out.println(x == y);

        String str  ="aaab";
        String str1 = new String("aaab");

        System.out.println(str == str1);
        System.out.println(str == str1.intern());

        Object o = "124";

        ((Integer) o).longValue();
        System.out.println(o.getClass());
    }

}
