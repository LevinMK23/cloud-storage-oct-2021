package com.geekbrains.network;

import com.geekbrains.model.AbstractMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientMessageHandler extends SimpleChannelInboundHandler<AbstractMessage> {

    private Callback callback;

    public ClientMessageHandler(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractMessage msg) throws Exception {
        log.debug("Received: {}", msg);
        callback.callback(msg);
    }
}
