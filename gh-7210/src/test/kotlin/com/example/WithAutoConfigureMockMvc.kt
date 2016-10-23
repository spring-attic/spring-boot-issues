package com.example

import org.junit.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class WithAutoConfigureMockMvc1 {

    @Autowired
    lateinit var appContext: ApplicationContext

    @Test
    fun appContextShouldBeTheSameButIsnt1() {
        if (previousApplicationContext == null) {
            previousApplicationContext = appContext
            Assume.assumeTrue(false)
        } else {
            Assert.assertTrue(previousApplicationContext === appContext)
        }
    }
}

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class WithAutoConfigureMockMvc2 {

    @Autowired
    lateinit var appContext: ApplicationContext

    @Test
    fun appContextShouldBeTheSameButIsnt2() {
        if (previousApplicationContext == null) {
            previousApplicationContext = appContext
            Assume.assumeTrue(false)
        } else {
            Assert.assertTrue(previousApplicationContext === appContext)
        }
    }
}


private var previousApplicationContext: ApplicationContext? = null
