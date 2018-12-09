package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "com.example.my")
@Component
public class MyProperties {
  private String name;
  private String description;
  private final Server server = new Server();
  private final List<Server> servers = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Server getServer() {
    return server;
  }

  public List<Server> getServers() {
    return servers;
  }

  static final class Server {
    private String name;
    private int port;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getPort() {
      return port;
    }

    public void setPort(int port) {
      this.port = port;
    }
  }

}
