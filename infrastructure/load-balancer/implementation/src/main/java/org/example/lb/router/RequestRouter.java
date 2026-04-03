package org.example.lb.router;

import org.example.lb.model.BackendServer;
import org.example.lb.model.ServerPool;
import org.example.lb.strategy.LoadBalancerStrategy;

import java.util.List;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

public class RequestRouter {

    private final ServerPool serverPool;
    private final LoadBalancerStrategy strategy;

    public RequestRouter(ServerPool serverPool, LoadBalancerStrategy strategy) {
        this.serverPool = serverPool;
        this.strategy = strategy;
    }

    public void routeRequest(String request) {
        List<BackendServer> servers = serverPool.getHealthyServers();
        BackendServer server = strategy.selectServer(servers);

        try {
            forward(server, request);
        } catch (Exception e) {
            retry(request);
        }
    }

    private void forward(BackendServer server, String request) {
        server.incrementConnections();
        System.out.println("Assigning request : " + request + " to server : " + server.getAddress());
        try { Thread.sleep(1000); }
        catch (InterruptedException ignored) {}
        server.decrementConnections();
    }

    private void retry(String request) {
        System.out.println("Retrying request : " + request);
        routeRequest(request);
    }
}
