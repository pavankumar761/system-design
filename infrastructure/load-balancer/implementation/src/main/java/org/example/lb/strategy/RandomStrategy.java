package org.example.lb.strategy;

import org.example.lb.model.BackendServer;

import java.util.List;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

public class RandomStrategy implements LoadBalancerStrategy {

    @Override
    public BackendServer selectServer(List<BackendServer> servers) {
        if (servers.isEmpty()) {
            throw new RuntimeException("No healthy servers");
        }
        int index = (int) (Math.random() * servers.size()) % servers.size();
        return servers.get(index);
    }
}
