package com.saum.client.console;

import com.saum.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author saum
 * @Description:
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入 groupId 加入群聊：");
        String groupId = scanner.next();
        joinGroupRequestPacket.setGorupId(groupId);

        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
