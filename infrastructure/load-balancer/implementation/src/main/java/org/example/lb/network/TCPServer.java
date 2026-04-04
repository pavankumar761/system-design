package org.example.lb.network;

import org.example.lb.router.RequestRouter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author : Pavan Kumar
 * @created : 04/04/26, Saturday
 */

public class TCPServer {

    private final int port;
    private final RequestRouter router;

    public TCPServer(int port, RequestRouter router) {
        this.port = port;
        this.router = router;
    }

    public void start() throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Load Balancer running on " + port);

        while (true) {
            selector.select();

            Iterator<SelectionKey> keys =  selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if(key.isAcceptable()) {
                    accept(selector, serverSocketChannel);
                }
                if(key.isReadable()) {
                    handleClient(key);
                }
            }
        }
    }

    private void accept(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        System.out.println("Client connected: "
                + client.getRemoteAddress());
        client.register(selector, SelectionKey.OP_READ);
    }

    private void handleClient(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        SocketChannel backend = router.selectBackendChannel();
        if(backend == null){
            client.close();
            return;
        }
        ProxyPipe.pipe(client, backend);
    }
}
