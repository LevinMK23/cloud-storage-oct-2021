package com.geekbrains.model;

import java.io.Serializable;

public class AbstractMessage implements Serializable {

    private String message;

    public AbstractMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
