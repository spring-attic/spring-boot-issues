package org.springframework.boot.issues.gh5956;


import org.junit.runner.JUnitCore;

public class IntegrationTestSuite {

    public static void main(String[] args) {
        JUnitCore.runClasses(ApplicationTests.class);
    }

}
