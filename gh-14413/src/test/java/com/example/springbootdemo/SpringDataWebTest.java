package com.example.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties(value = {SpringDataWebProperties.class})
public class SpringDataWebTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private SpringDataWebProperties properties;

    @Test
    public void customizePageableWithConfig() {
        PageableHandlerMethodArgumentResolver argumentResolver = this.context
                .getBean(PageableHandlerMethodArgumentResolver.class);
        // properties.pageable  equal to properties config
        assertThat(properties.getPageable().getPageParameter())
                .isEqualTo("p");
        assertThat(properties.getPageable().getSizeParameter())
                .isEqualTo("s");
        assertThat(properties.getPageable().isOneIndexedParameters())
                .isEqualTo(true);
        assertThat(properties.getPageable().getPrefix())
                .isEqualTo("abc");
        assertThat(properties.getPageable().getQualifierDelimiter())
                .isEqualTo("__");

        assertThat(properties.getPageable().getMaxPageSize())
                .isEqualTo(100);


        // properties.pageable not equal argumentResolver
        assertThat(ReflectionTestUtils.getField(argumentResolver, "pageParameterName"))
                .isNotEqualTo(properties.getPageable().getPageParameter());
        assertThat(ReflectionTestUtils.getField(argumentResolver, "sizeParameterName"))
                .isNotEqualTo(properties.getPageable().getSizeParameter());
        assertThat(ReflectionTestUtils.getField(argumentResolver, "oneIndexedParameters"))
                .isNotEqualTo(properties.getPageable().isOneIndexedParameters());
        assertThat(ReflectionTestUtils.getField(argumentResolver, "prefix"))
                .isNotEqualTo(properties.getPageable().getPrefix());
        assertThat(ReflectionTestUtils.getField(argumentResolver, "qualifierDelimiter"))
                .isNotEqualTo(properties.getPageable().getQualifierDelimiter());

        assertThat(ReflectionTestUtils.getField(argumentResolver, "maxPageSize"))
                .isNotEqualTo(properties.getPageable().getMaxPageSize());
    }


}
