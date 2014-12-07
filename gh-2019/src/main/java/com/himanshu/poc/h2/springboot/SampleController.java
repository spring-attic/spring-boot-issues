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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@Autowired
	PersonDao personDao;
	
	@RequestMapping(method=RequestMethod.GET, value="/sample/echo/{name}")
	public String echoName(@PathVariable("name")String name) {
		System.out.println("Name : "+name);
		return "Hello "+name;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sample/echo/all")
	public String echoAllNames() {
		System.out.println("personDao : "+personDao.getNames());
		return "Hello "+personDao.getNames();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/secure/sample/test")
	public String echoSecureTest() {
		System.out.println("Secure test");
		return "Hello "+personDao.getNames();
	}
	
	

}
