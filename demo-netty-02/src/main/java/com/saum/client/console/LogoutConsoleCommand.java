package com.saum.client.console;

import com.saum.protocol.request.LogoutRequestPacket;
import com.saum.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author saum
 * @Description:
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
