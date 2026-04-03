package org.example.lb.strategy;

import org.example.lb.model.BackendServer;

import java.util.Comparator;
import java.util.List;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

public class LeastConnectionsStrategy implements LoadBalancerStrategy {

    @Override
    public BackendServer selectServer(List<BackendServer> servers) {

        return servers.stream()
                .min(Comparator.comparingInt(
                        BackendServer::getActiveConnections))
                .orElseThrow();
    }

}
