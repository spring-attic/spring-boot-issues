package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Gh4660Application.class)
@WebIntegrationTest("server.port:0")
public class Gh4660ApplicationTests {
    @Value("${local.server.port}") private int port;
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testOptionalRequestPart() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        restTemplate.postForEntity("http://localhost:" + port + "/optionalRequestPart", new HttpEntity<>(getTestFile(), headers), String.class);
        restTemplate.postForEntity("http://localhost:" + port + "/optionalRequestPart", new HttpEntity<>(getTestFile(), headers), String.class);
    }

    private MultiValueMap<String, Object> getTestFile() {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", new ByteArrayResource("... image content ...".getBytes()) {
            @Override
            public String getFilename() {
                return "test.jpg";
            }
        });
        return parts;
    }
}
