package org.springframework.boot.issues.gh6045.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh6045.service.ExampleService;
import org.springframework.stereotype.Service;

/**
 * This service throws an exception for testing Service tier rollback.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@Service
public class MyServer {

    Logger logger = LoggerFactory.getLogger(MyServer.class);

    @Autowired
    ExampleService exampleService;

    public MyServer() {
        logger.info("MyServer initialized.");
    }

    public void execute() {
    }
}
