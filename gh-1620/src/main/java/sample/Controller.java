package sample;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

	@RequestMapping("/")
	public Person person() {
		return new Person();
	}

}
