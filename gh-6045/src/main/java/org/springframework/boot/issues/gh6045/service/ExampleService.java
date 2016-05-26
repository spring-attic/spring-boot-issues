package org.springframework.boot.issues.gh6045.service;

import org.springframework.boot.issues.gh6045.server.MyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Example service.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@Service
public class ExampleService {

    Logger logger = LoggerFactory.getLogger(ExampleService.class);

    MyServer myServer;

    @Autowired
    public ExampleService(MyServer myServer) {
        this.myServer = myServer;
        logger.info("ExampleService initialized.");

    }

    public void execute() {
    }
}
