package com.example

import org.junit.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class WithoutAutoConfigureMockMvc1 {

    @Autowired
    lateinit var appContext: ApplicationContext

    @Test
    fun appContextShouldBeTheSame1() {
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
class WithoutAutoConfigureMockMvc2 {

    @Autowired
    lateinit var appContext: ApplicationContext

    @Test
    fun appContextShouldBeTheSame2() {
        if (previousApplicationContext == null) {
            previousApplicationContext = appContext
            Assume.assumeTrue(false)
        } else {
            Assert.assertTrue(previousApplicationContext === appContext)
        }
    }
}


private var previousApplicationContext: ApplicationContext? = null
