package com.saum.server.handler;

import com.saum.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author saum
 * @Description:
 */
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            log.error("尚未登录，请先登录...");
            ctx.channel().close();
        } else {
            // 如果校验成功,需要调用super.channelRead把读到的数据向下传递，传递给后续指令处理器
            // 检验通过之后，就不再需要登录校验了
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }
}
