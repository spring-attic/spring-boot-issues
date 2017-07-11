package org.springboot.relaxedbinding;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dorongold on 7/11/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "PERSON_FIRST_NAME=from env" })
public class PropertiesTest {

    @Autowired
    OwnerProperties ownerProperties;

    @Test
    public void testFirstName() {
        final String firstName = ownerProperties.getFirstName();
        Assert.assertEquals("from env", firstName);
    }
}
