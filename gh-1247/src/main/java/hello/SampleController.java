package hello;

import java.util.UUID;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("/")
	@ResponseBody
	String home() {
		try {
			UUID referenceId = UUID.randomUUID();
			MDC.put("referenceId", referenceId);
			String message = "Hello World! " + referenceId;
			LOGGER.info(message);
			return message;
		} finally {
			MDC.remove("referenceId");
		}
		
	}

}