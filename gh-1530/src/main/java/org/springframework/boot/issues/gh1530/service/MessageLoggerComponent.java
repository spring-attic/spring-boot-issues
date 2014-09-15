package org.springframework.boot.issues.gh1530.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh1530.dao.MessageDao;
import org.springframework.boot.issues.gh1530.model.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Message logger service.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@Component
public class MessageLoggerComponent {

    Logger logger = LoggerFactory.getLogger(MessageLoggerComponent.class);

    @Autowired
    MessageDao messageDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logMessage(Message message) {
        logger.info("message create: {}", message);
        messageDao.create(message);
    }

}
