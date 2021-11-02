package com.geekbrains.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FileRequest extends AbstractMessage {

    private final String name;

    public FileRequest(String name) {
        this.name = name;
        setType(CommandType.FILE_REQUEST);
    }
}
