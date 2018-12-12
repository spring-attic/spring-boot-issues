package com.example.executorissue;

import com.example.executorissue.config.ExecutorConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@Import(ExecutorConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EchoControllerWithCustomExecutorTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void echo() {
        webClient.get().uri("/echo").exchange();
    }
}
