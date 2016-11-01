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
public class ExampleApplicationTest {
  @MockBean RepositoryService repositoryService;
  @Autowired MockMvc mockMvc;

  @Test
  public void shouldReturnDescription() throws Exception {
    final String expected = "expected description";
    when(repositoryService.description(anyString(), anyString()))
            .thenReturn(expected);
    mockMvc.perform(get("/repo/foo/bar/desc")).andExpect(content().string(expected));
  }
}
