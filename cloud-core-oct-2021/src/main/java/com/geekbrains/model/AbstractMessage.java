package com.geekbrains.model;

import java.io.Serializable;

public class AbstractMessage implements Serializable {

    private CommandType type;

    protected void setType(CommandType type) {
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }
}
