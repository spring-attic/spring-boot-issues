package gh2212;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

	@RequestMapping("/foo")
	public String foo() {
		return "foo";
	}

}
