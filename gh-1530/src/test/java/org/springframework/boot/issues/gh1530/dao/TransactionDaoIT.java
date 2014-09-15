package org.springframework.boot.issues.gh1530.dao;

import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh1530.Application;
import org.springframework.boot.issues.gh1530.model.Transaction;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Transaction log_transaction table test. Testing our testing environment.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
public class TransactionDaoIT {

    @Autowired
    TransactionDao transactionDao;

    public TransactionDaoIT() {
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
        System.out.println("transaction testCreate");

        String transactionId = UUID.randomUUID().toString();
        Transaction tr = new Transaction(
                transactionId,
                Transaction.TYPE_MY_TRANSACTION,
                Transaction.STATUS_RUNNING);
        transactionDao.create(tr);
        Transaction newTr = transactionDao.read(transactionId);
        assertNotNull(newTr);
        assertEquals(tr.getTransactionId(), newTr.getTransactionId());
        
        transactionDao.deleteAll();
    }

    /**
     * Test of update method, of class TransactionDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("transaction testUpdate");
        String transactionId = UUID.randomUUID().toString();
        Transaction tr = new Transaction(
                transactionId,
                Transaction.TYPE_MY_TRANSACTION,
                Transaction.STATUS_RUNNING);
        transactionDao.create(tr);
        tr.setStatus(Transaction.STATUS_OK);
        transactionDao.update(tr);
        Transaction newTr = transactionDao.read(transactionId);
        assertNotNull(newTr);
        assertEquals(Transaction.STATUS_OK, newTr.getStatus());
        
        transactionDao.deleteAll();
    }

}
