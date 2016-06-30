package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//v 1.4.0
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
//v 1.3.5
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(DemoApplication.class)
public class DemoApplicationIT {

	@Test
	public void contextLoads() {
	}

}
