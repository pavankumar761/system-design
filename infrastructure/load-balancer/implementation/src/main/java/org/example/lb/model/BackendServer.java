package org.example.lb.model;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

@Data
public class BackendServer {

    public enum HealthStatus {
        ALIVE, DEAD
    }

    private String host;

    private int port;

    private String address;

    private HealthStatus healthStatus = HealthStatus.ALIVE;

    private AtomicInteger activeConnections = new AtomicInteger(0);

    private Long maxConnections;

    public BackendServer(String host, int port, Long maxConnections) {
        this.host = host;
        this.port = port;
        this.maxConnections = maxConnections;
    }

    public boolean isHealthy() {
        return HealthStatus.ALIVE.equals(this.healthStatus);
    }

    public int getActiveConnections() {
        return activeConnections.get();
    }

    public void incrementConnections() {
        activeConnections.incrementAndGet();
    }

    public void decrementConnections() {
        activeConnections.decrementAndGet();
    }

    public boolean canConnect() {
        return this.isHealthy() && this.activeConnections.get() < maxConnections;
    }

    public String getAddress() {
        return host + ":" + port;
    }
}
