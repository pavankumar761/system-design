package org.example.lb.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.example.lb.model.BackendServer.HealthStatus.ALIVE;

/**
 * @author : Pavan Kumar
 * @created : 03/04/26, Friday
 */

@Data
public class ServerPool {

    private List<BackendServer> servers;

    public void addServer(BackendServer server) {
        if(Objects.isNull(server)){
            return;
        }
        if(Objects.isNull(this.servers)){
            this.servers = new ArrayList<>();
        }
        this.servers.add(server);
    }

    public void removeServer(BackendServer server) {
        if(Objects.isNull(server) || Objects.isNull(this.servers) || this.servers.isEmpty()){
            return;
        }
        this.servers.remove(server);
    }

    public List<BackendServer> getHealthyServers() {
        if (Objects.isNull(this.servers)) {
            return new ArrayList<>();
        }
        return this.servers.stream().filter(BackendServer::canConnect).collect(Collectors.toList());
    }
}
