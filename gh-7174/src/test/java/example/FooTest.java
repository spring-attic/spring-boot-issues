package example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FooTest {

  @MockBean FooBarService service;
  @Autowired MockMvc mockMvc;

  @Test
  public void shouldReturnXYZ() throws Exception {
    final String expected = "xyz";
    when(service.foo()).thenReturn(expected);
    mockMvc.perform(get("/foo"))
            .andExpect(content().string(expected));
  }

}
