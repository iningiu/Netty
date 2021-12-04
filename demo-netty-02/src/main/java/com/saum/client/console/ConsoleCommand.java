package com.saum.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author saum
 * @Description: 控制台命令执行接口
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
