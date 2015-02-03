package org.springframework.boot.issues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSingleton {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

}
