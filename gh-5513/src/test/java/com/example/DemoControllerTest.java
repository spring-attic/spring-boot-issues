package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebIntegrationTest("server.port:0")
public class DemoControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public final void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
            .build();
    }

    private MockHttpServletRequestBuilder modifyBuilder(final MockHttpServletRequestBuilder builder) {
        return builder
            .contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void testDateTimeZone() throws Exception {
        String expected = "2016-03-28T00:15:45.341+06:00";

        this.mockMvc
            .perform(get("/test"))
            .andExpect(jsonPath("$.createdAt").value(expected));
    }
}
