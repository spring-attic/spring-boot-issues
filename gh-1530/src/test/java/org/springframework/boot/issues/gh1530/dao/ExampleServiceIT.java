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
import org.springframework.boot.issues.gh1530.service.ExampleService;
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
public class ExampleServiceIT {

    @Autowired
    ExampleService exampleService;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    MessageDao messageDao;
    
    @Autowired
    MyModelDao myModelDao;

    public ExampleServiceIT() {
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
     * Test for ExampleService.
     * Everything is OK, if there is not exception in TransactionLoggerAspect!
     * But when TransactionLoggerAspect throws an Exception, then my 
     * ExampleService @Transactional annotated transaction doesn't rollback.
     * 
     * How to pass this test:
     * Leave commented out in TransactionLoggerAspect.java:
     * // throw new RuntimeException("Text exception: DB connection has been lost!");
     * 
     * How to get assertion error on this test:
     * Uncomment exception in TransactionLoggerAspect.java:
     * throw new RuntimeException("Text exception: DB connection has been lost!");
     * Result: "expected:<null> but was:<Hello World! Your input is: my_input>"
     * 
     */
    @Test
    public void testStatusOK() {
        System.out.println("exampleService testStatusOK");

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
            // catch exception beacuse I want to run test assertions.
            System.out.println("Test exception");
        }
        
        // service validation: myModel saved to db
        List<MyModel> myModelList = myModelDao.readAll();
        assertFalse(myModelList.isEmpty());
        assertEquals(output, myModelList.get(0).getMyValue());
        
        // transaction validation: transaction status is OK
        List<Transaction> trList = transactionDao.readAll();
        assertTrue(trList.size() == 1);
        assertEquals(Transaction.STATUS_OK, trList.get(0).getStatus());
        
        // message validation: message status is OK
        List<Message> messageList = messageDao.readAll();
        assertFalse(messageList.isEmpty());
        assertTrue(messageList.size() == 2);
        assertEquals(Message.STATUS_OK, messageList.get(0).getStatus());
        assertEquals(Message.STATUS_OK, messageList.get(1).getStatus());
        
        assertEquals(input, messageList.get(0).getMessage());
        assertEquals(output, messageList.get(1).getMessage());

        myModelDao.deleteAll();
        messageDao.deleteAll();
        transactionDao.deleteAll();
    }

}
