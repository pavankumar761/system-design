package org.example;

import org.example.lb.core.LoadBalancer;
import org.example.lb.router.RequestRouter;
import org.example.lb.model.BackendServer;
import org.example.lb.model.ServerPool;
import org.example.lb.strategy.LeastConnectionsStrategy;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

public class Main {
    public static void main(String[] args) {

        ServerPool pool = new ServerPool();

        pool.addServer(new BackendServer("localhost",9001,5L));
        pool.addServer(new BackendServer("localhost",9002,5L));
        pool.addServer(new BackendServer("localhost",9003,5L));

        RequestRouter requestRouter = new RequestRouter(pool, new LeastConnectionsStrategy());

        LoadBalancer loadBalancer = new LoadBalancer(requestRouter);

        for(int i=0;i<10;i++){
            loadBalancer.acceptRequest("REQ-"+i);
        }
    }
}