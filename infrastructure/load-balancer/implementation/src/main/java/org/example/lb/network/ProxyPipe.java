package org.example.lb.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author : Pavan Kumar
 * @created : 04/04/26, Saturday
 */

public class ProxyPipe {

    public static void pipe(SocketChannel client, SocketChannel backend) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        int read = client.read(buffer);
        if (read == -1){
            client.close();
            backend.close();
            return;
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            backend.write(buffer);
        }
    }

}
