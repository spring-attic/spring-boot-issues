/*
 * Copyright 2013 Himanshu Bhardwaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.himanshu.poc.h2.springboot;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleCommandLineStarter.class)
@WebAppConfiguration
@IntegrationTest(value="server.port:0")
public class H2SpringBootTestIT {
	private Logger logger = LoggerFactory.getLogger(H2SpringBootTestIT.class);

	@Autowired
	private DummyTblDao dummyTblDao;

	@Autowired
	private PersonDao personDao;

	@Test
	public void testDummyTableDao() {
		logger.info("dummyTblDao " + dummyTblDao);
		Assert.assertNotNull(dummyTblDao);
		List<String> names = dummyTblDao.getNames();
		Assert.assertNotNull(names);
		Assert.assertTrue(names.size() == 3);
		logger.info(names.toString());
	}

	@Test
	public void testPersonDao() {
		logger.info("personDao " + personDao);
		Assert.assertNotNull(personDao);
		List<String> names = personDao.getNames();
		Assert.assertNotNull(names);
		Assert.assertTrue(names.size() == 3);
		logger.info(names.toString());
	}

}
