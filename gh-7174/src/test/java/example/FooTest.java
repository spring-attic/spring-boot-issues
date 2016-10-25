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
 * In this test suite I want to test only FooService.
 * I don't care about BarService - the default NOOP implementation from TestConfiguration is fine
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@Import({TestConfiguration.class})
public class FooTest {

  @MockBean FooService fooService;
  @Autowired MockMvc mockMvc;

  @Test
  public void shouldReturnXYZ() throws Exception {
    final String expected = "xyz";
    when(fooService.foo()).thenReturn(expected);
    mockMvc.perform(get("/foo"))
            .andExpect(content().string(expected));
  }

}
