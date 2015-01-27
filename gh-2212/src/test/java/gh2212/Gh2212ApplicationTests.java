package gh2212;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import gh2212.Gh2212Application;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Gh2212Application.class)
@WebAppConfiguration
public class Gh2212ApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

		MimetypesFileTypeMap mimetypesFileTypeMap = (MimetypesFileTypeMap) FileTypeMap.getDefaultFileTypeMap();
		mimetypesFileTypeMap.addMimeTypes("text/html html");
	}

	@Test
	public void accessTemplateDirectly() throws Exception {
		this.mockMvc.perform(get("/foo").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}

	@Test
	@DirtiesContext
	public void accessHtmlPageThenTemplate() throws Exception {
		// Causes a mapping for text/html to .html to be created in ServletPathExtensionContentNegotiationStrategy
		this.mockMvc.perform(get("/index.html").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
		// Triggers resolution of the foo template and foo.tpl is tried.
		// MarkupTemplateEngine.TemplateResource.parse("foo.tpl") turns "foo.tpl" into "foo.tpl" which doesn't
		// exist and we move on. foo.tpl.html is then tried due to the mapping established by the request above
		// and this request's accept header. MarkupTemplateEngine.TemplateResource.parse("foo.tpl.html") turns
		// "foo.tpl.html" into "foo.html" which exists. An attempt is made to compile foo.html as Groovy and it
		// fails.
		this.mockMvc.perform(get("/foo").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}

}
