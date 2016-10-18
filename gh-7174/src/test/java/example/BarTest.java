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
public class BarTest {

  @MockBean FooBarService service;
  @Autowired MockMvc mockMvc;

  @Test
  public void shouldReturnStatus500() throws Exception {
    doThrow(new RuntimeException()).when(service).bar();

    mockMvc.perform(get("/bar"))
            .andExpect(status().isInternalServerError());
  }

}
