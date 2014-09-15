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
import org.springframework.boot.issues.gh1530.model.MyModel;
import org.springframework.boot.issues.gh1530.model.Transaction;
import org.springframework.boot.issues.gh1530.service.WrongExampleService;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Message log_message table test.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
public class WrongExampleServiceIT {

    @Autowired
    WrongExampleService exampleService;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    MessageDao messageDao;

    @Autowired
    MyModelDao myModelDao;

    public WrongExampleServiceIT() {
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
     * Exception in Service tier, it works fine: if you don't throw exception in
     * TransactionLoggerAspect!
     */
    @Test
    public void testStatusException() {
        System.out.println("WrongExampleService testStatusException");

        String input = "my_input";

        // prepare business transaction for audit
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(
                transactionId,
                Transaction.TYPE_MY_TRANSACTION,
                Transaction.STATUS_RUNNING);

        // prepare request message, always STATUS_OK
        Message requestMessage = new Message(
                transactionId,
                input,
                Message.STATUS_OK);

        String output = null;
        try {
            output = exampleService.execute(transaction, requestMessage, input);
        } catch (Exception ex) {
            // catch exception beacuse I want to run assertions.
            System.out.println("Test exception");
        }

        // service validation: myModel saved to db
        List<MyModel> myModelList = myModelDao.readAll();
        /**
         * Exception in Service tier, so I don't want to see any records.
         * It works fine!
         */
        assertTrue(myModelList.isEmpty());
        
        // transaction validation
        List<Transaction> trList = transactionDao.readAll();
        assertTrue(trList.size() == 1);
        
        /**
         * Transaction status EXCEPTION. It is ok.
         */
        assertEquals(Transaction.STATUS_EXCEPTION, trList.get(0).getStatus());

        // message validation
        List<Message> messageList = messageDao.readAll();
        assertFalse(messageList.isEmpty());
        assertTrue(messageList.size() == 2);
        assertEquals(Message.STATUS_OK, messageList.get(0).getStatus());
        
        /**
         * Output status is EXCEPTION. It is ok.
         */
        assertEquals(Message.STATUS_EXCEPTION, messageList.get(1).getStatus());

        assertEquals(input, messageList.get(0).getMessage());
        assertEquals("Test exception", messageList.get(1).getMessage());

        myModelDao.deleteAll();
        messageDao.deleteAll();
        transactionDao.deleteAll();
    }

}
