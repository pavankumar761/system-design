package org.example.lb.strategy;

import org.example.lb.model.BackendServer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

public class RoundRobinStrategy implements LoadBalancerStrategy {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public BackendServer selectServer(List<BackendServer> servers) {
        if (servers.isEmpty()) {
            throw new RuntimeException("No healthy servers");
        }
        int index = Math.abs(counter.getAndIncrement()) % servers.size();
        return servers.get(index);
    }

}
