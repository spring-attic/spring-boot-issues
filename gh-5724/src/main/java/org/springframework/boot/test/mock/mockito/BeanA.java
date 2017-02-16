package org.springframework.boot.test.mock.mockito;

public class BeanA {

    private final BeanB beanB;

    public BeanA(BeanB beanB) {
        this.beanB = beanB;
    }
}
