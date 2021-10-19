package com.geekbrains.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoUtils {

    public static void main(String[] args) throws Exception {

        Path root = Paths.get("root");
        if (!Files.exists(root)) {
            Files.createDirectory(root);
        }

        for (int i = 1; i < 11; i++) {
            Path userDir  =root.resolve("user" + i);
            if (!Files.exists(userDir)) {
                Files.createDirectory(userDir);
            }
        }

        InputStream is = new FileInputStream(root.resolve("1.txt").toFile());
        OutputStream os = new FileOutputStream(root.resolve("dir1").resolve("copy.txt").toString());
        byte[] buffer = new byte[8129];
        int readBytes;
        while (true) {
            readBytes = is.read(buffer);
            if (readBytes == -1) {
                break;
            }
            os.write(buffer, 0, readBytes);
            System.out.println(new String(buffer, 0, readBytes));
        }
    }

}
