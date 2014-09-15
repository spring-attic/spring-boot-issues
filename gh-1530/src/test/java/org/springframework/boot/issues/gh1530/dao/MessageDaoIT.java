package org.springframework.boot.issues.gh1530.dao;

import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh1530.Application;
import org.springframework.boot.issues.gh1530.model.Message;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Message log_message table test. Testing our testing environment.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
public class MessageDaoIT {

    @Autowired
    MessageDao messageDao;

    public MessageDaoIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class TransactionDao.
     */
    @Test
    public void testCreate() {
        System.out.println("message testCreate");
        messageDao.deleteAll();

        String transactionId = UUID.randomUUID().toString();
        Message message = new Message(
                transactionId,
                "my message",
                Message.STATUS_OK);
        messageDao.create(message);
        List<Message> newMessageList = messageDao.readAll();
        assertFalse(newMessageList.isEmpty());
        assertEquals(message.getTransactionId(), newMessageList.get(0).getTransactionId());

        messageDao.deleteAll();
    }

    /**
     * Test of readAll method, of class MessageDao.
     */
    @Test
    public void testReadAll() {
        System.out.println("message testReadAll");
        messageDao.deleteAll();

        String transactionId = UUID.randomUUID().toString();
        Message message = new Message(
                transactionId,
                "my message",
                Message.STATUS_OK);
        messageDao.create(message);
        message.setStatus(Message.STATUS_ERROR);
        messageDao.create(message);
        List<Message> newMessageList = messageDao.readAll();
        assertFalse(newMessageList.isEmpty());
        assertTrue(newMessageList.size() == 2);
        for (Message m : newMessageList) {
            assertEquals(transactionId, m.getTransactionId());
        }

        messageDao.deleteAll();
    }

}
