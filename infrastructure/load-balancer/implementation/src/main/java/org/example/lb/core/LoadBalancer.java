package org.example.lb.core;

import org.example.lb.router.RequestRouter;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

public class LoadBalancer {

    private final RequestRouter router;

    public LoadBalancer(RequestRouter router) {
        this.router = router;
    }

    public void acceptRequest(String request) {
        router.routeRequest(request);
    }

}
