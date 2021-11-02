package com.geekbrains.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ListMessage extends AbstractMessage {

    private final List<String> files;

    public ListMessage(Path dir) throws Exception {
        setType(CommandType.LIST_MESSAGE);
        files = Files.list(dir).map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
    }
}
