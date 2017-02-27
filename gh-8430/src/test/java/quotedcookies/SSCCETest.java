package quotedcookies;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import de.malkusch.quotedcookies.SSCCE;

@SpringBootTest(classes = SSCCE.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class SSCCETest {

	@Test
	public void noQuotes() throws IOException {
		String response = sendRequestWithCookie("foo=bar");
		assertEquals("bar", response);
	}

	@Test
	public void withQuotes() throws IOException {
		String response = sendRequestWithCookie("foo=\"bar\"");
		assertEquals("bar", response);
	}

	@Test
	public void withQuotesAndVersion() throws IOException {
		String response = sendRequestWithCookie("$Version=1;foo=\"bar\"");
		assertEquals("bar", response);
	}

	private static final String sendRequestWithCookie(String cookieHeader) throws IOException {
		URL controller = new URL("http://localhost:8080/");
		URLConnection connection = controller.openConnection();
		connection.setRequestProperty("Cookie", cookieHeader);
		try (InputStream response = connection.getInputStream()) {
			return IOUtils.toString(connection.getInputStream(), "UTF-8");
		}
	}

}
