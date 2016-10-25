package example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * In this test suite I want to test only BarService.
 * I don't care about FooService - the default NOOP implementation from TestConfiguration is fine
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@Import({TestConfiguration.class})
public class BarTest {

  @MockBean BarService barService;
  @Autowired MockMvc mockMvc;

  @Test
  public void shouldReturnStatus500() throws Exception {
    doThrow(new RuntimeException()).when(barService).bar();

    mockMvc.perform(get("/bar"))
            .andExpect(status().isInternalServerError());
  }

}
