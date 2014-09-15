package org.springframework.boot.issues.gh1530.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh1530.dao.TransactionDao;
import org.springframework.boot.issues.gh1530.model.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transaction logger service.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@Component
public class TransactionLoggerComponent {

    Logger logger = LoggerFactory.getLogger(TransactionLoggerComponent.class);

    @Autowired
    TransactionDao transactionDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTransaction(Transaction transaction) {
        logger.info("transaction create: {}", transaction);
        transactionDao.create(transaction);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTransaction(Transaction transaction) {
        logger.info("transaction update: {}", transaction);
        transactionDao.update(transaction);
    }

}
