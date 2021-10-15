package com.geekbrains.nio;

import java.nio.ByteBuffer;

public class Buffers {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(5);

        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println((char) buffer.get());
        }

        buffer.rewind();

        while (buffer.hasRemaining()) {
            System.out.println((char) buffer.get());
        }
    }
}
