package ServerSide;


import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String wsUri;
    private static final File INDEX;


    // Get the filepath to index.html file
    static {
        URL location = HttpRequestHandler.class
                .getProtectionDomain()
                .getCodeSource().getLocation();

        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e ) {
            throw new IllegalStateException("Unable to locate index.html", e);
        }

    }

    HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception{

        // Check the URI if it equal a specific String. In this case this.wsUri == "/ws"
        if ( wsUri.equalsIgnoreCase(request.uri())) {
            // Pass the request to WebsocketHandler
            // call retain to prevent msg from get cleaned up by SimpleChannelInboundHandler
            ctx.fireChannelRead(request.retain());
        } else {
            if (HttpUtil.is100ContinueExpected(request)) {
                // Http's conformity, Client sends a 100 Continue flag before
                // start sending a real request or content
                send100Continue(ctx);
            }

            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultFullHttpResponse(
                    request.protocolVersion(),
                    HttpResponseStatus.OK
            );

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if ( keepAlive ) {
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }

            ctx.write(response);
            if ( ctx.pipeline().get(SslHandler.class) == null ) {
                // This will utilize zer0-copy to perform the transmission
                // Alternatively use ChunkedNioFile
                ctx.write( new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }

            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

            if ( !keepAlive ) {
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }

    }

    private void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.CONTINUE
        );
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}