package org.example.lb.strategy;

import org.example.lb.model.BackendServer;

import java.util.List;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

public interface LoadBalancerStrategy {

    BackendServer selectServer(List<BackendServer> servers);
}
