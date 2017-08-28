package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("test")
public class TestProperites {

    private final Map<String, Container> environments = new HashMap<>();

    public Map<String, Container> getEnvironments() {
        return environments;
    }

    public static class Container {

        private final Map<String, List<String>> containers = new HashMap<>();

        public Map<String, List<String>> getContainers() {
            return containers;
        }
    }
}
