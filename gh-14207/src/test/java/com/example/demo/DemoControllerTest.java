package com.example.demo;

import com.example.demo.ValidDto.ShouldFail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DemoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        ValidDtoValidator.lastGroup = null;
    }

    @Test
    public void works() {
        // given
        assertThat(ValidDtoValidator.lastGroup).isNull();

        // when
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/works",
                HttpMethod.PUT,
                createRequestBody(),
                String.class, "");

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // and
        Class<ShouldFail>[] validatorGroup = ValidDtoValidator.lastGroup;

        assertThat(validatorGroup).isNotNull();
        assertThat(validatorGroup.length).isEqualTo(0);
    }

    @Test
    public void shouldFail() {
        // given
        assertThat(ValidDtoValidator.lastGroup).isNull();

        // when
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/should-fail",
                HttpMethod.PUT,
                createRequestBody(),
                String.class, "");

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // and
        Class<ShouldFail>[] validatorGroup = ValidDtoValidator.lastGroup;

        assertThat(validatorGroup).isNotNull();
        assertThat(validatorGroup.length).isEqualTo(1);
        assertThat(validatorGroup[0].getName()).isEqualTo(ShouldFail.class.getName());
    }

    private HttpEntity<SomeDto> createRequestBody() {
        return new HttpEntity<>(SomeDto.builder()
                .firstName("first")
                .lastName("last")
                .build());
    }
}
