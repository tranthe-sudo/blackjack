package ServerSide;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class GameServer {
    private final TableManager tableManager = new TableManager();
    private final EventLoopGroup group = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(InetSocketAddress port) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(createInitializer(tableManager));
        ChannelFuture future = bootstrap.bind(port);
        future.syncUninterruptibly();
        channel = future.channel();
        return future;
    }

    private ChannelInitializer<Channel> createInitializer(TableManager tableManager) {
        return new GameServerInitializer(tableManager);
    }

    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        group.shutdownGracefully();
    }

    public static void main(String[] args) {
        final GameServer endpoint = new GameServer();

        ChannelFuture future = endpoint.start(new InetSocketAddress(8888));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                endpoint.destroy();
            }
        });

        future.channel().closeFuture().syncUninterruptibly();
    }
}

