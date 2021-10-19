package com.geekbrains.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class NioUtils {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("root");
        // 1/2/.. -> 1
//        System.out.println(path.getParent());
//        if (path.getParent() != null) {
//            path = path.getParent();
//        }
        WatchService watchService = FileSystems.getDefault().newWatchService();

        new Thread(() -> {
            while (true) {
                WatchKey poll = null;
                try {
                    poll = watchService.take();
                    List<WatchEvent<?>> watchEvents = poll.pollEvents();
                    for (WatchEvent<?> watchEvent : watchEvents) {
                        System.out.println(watchEvent.context());
                        System.out.println(watchEvent.kind());
                    }
                    poll.reset();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        path.register(watchService, ENTRY_MODIFY, ENTRY_DELETE, ENTRY_CREATE);


    }
}
