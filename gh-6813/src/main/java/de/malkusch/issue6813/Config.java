package de.malkusch.issue6813;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {

    @Bean
    List<URL> list() {
        return IntStream.range(0, 10000).mapToObj(this::buildURL).collect(Collectors.toList());
    }

    private URL buildURL(int i) {
        try {
            return new URL(String.format("http://www%d.example.org", i));
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

}