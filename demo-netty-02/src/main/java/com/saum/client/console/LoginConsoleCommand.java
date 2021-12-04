package com.saum.client.console;

import com.saum.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author saum
 * @Description:
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.print("请输入用户名登录：");
        String username = scanner.nextLine();
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword("123");

        channel.writeAndFlush(loginRequestPacket);
        // 等待用户登录响应
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
