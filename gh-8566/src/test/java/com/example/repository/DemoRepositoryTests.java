package com.example.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
public class DemoRepositoryTests {

    @Autowired
    private DemoRepository repository;

    @Test
    public void findNumber() {
        Assertions.assertThat(repository.findNumber()).isEqualTo(1);
    }

}
