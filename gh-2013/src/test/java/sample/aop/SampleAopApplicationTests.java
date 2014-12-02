
package sample.aop;

import org.junit.Test;

public class SampleAopApplicationTests {


	@Test
	public void testSuccess() throws Exception {
		Application.main(new String[0]);
	}
	@Test
	public void testFail() throws Exception {
		String[] args = {"--gogogo=fff"};
		Application.main(args);
	}


}
