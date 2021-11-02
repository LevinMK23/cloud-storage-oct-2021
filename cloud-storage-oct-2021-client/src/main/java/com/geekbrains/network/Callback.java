package com.geekbrains.network;

import java.io.IOException;

import com.geekbrains.model.AbstractMessage;

public interface Callback {

    void callback(AbstractMessage msg) throws IOException;

}
