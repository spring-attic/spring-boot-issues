package milosz.hangup.demo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private lateinit var client: TestRestTemplate

	@Test
	fun webClientHangsUp() {

		for (i in 1..10) {
			val body = client.getForEntity("/home", String::class.java).body
        }
	}

}
