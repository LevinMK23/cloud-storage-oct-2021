package com.geekbrains.nio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FilesTest {
    public static void main(String[] args) throws IOException {
        Files.writeString(
                Path.of("root", "1.txt"),
                "Hello world!",
                StandardOpenOption.CREATE
        );
    }
}
