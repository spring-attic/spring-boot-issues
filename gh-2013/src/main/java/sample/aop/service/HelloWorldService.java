
package sample.aop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sample.aop.monitor.Aop;

@Component
@Aop
public class HelloWorldService {

	@Value("${name}")
	private String name;

	@Aop
	public String getHelloMessage() {
		return "Hello " + this.name;
	}

}
