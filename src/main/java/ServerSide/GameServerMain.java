package ServerSide;

import com.google.common.flogger.FluentLogger;
import io.netty.channel.ChannelFuture;

import java.net.InetSocketAddress;
import java.util.ResourceBundle;

public class GameServerMain {
    private final static FluentLogger logger = FluentLogger.forEnclosingClass();

    public static void main(String[] args) {
        final ResourceBundle gameConfig = ResourceBundle.getBundle("configuration");
        int port = Integer.valueOf(gameConfig.getString("port"));

        final GameServer endpoint = new GameServer();
        ChannelFuture future = endpoint.start(new InetSocketAddress(port));
        logger.atInfo().log("Server started");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                endpoint.destroy();
            }
        });

        future.channel().closeFuture().syncUninterruptibly();
    }
}
