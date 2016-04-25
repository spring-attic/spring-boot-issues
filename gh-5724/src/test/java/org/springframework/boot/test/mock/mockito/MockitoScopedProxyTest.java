package org.springframework.boot.test.mock.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoScopedProxyTest {

    @MockBean
    private BeanB mockBeanB;

    @Test
    public void testStuff() {
        System.out.println(mockBeanB);
    }
}