package com.example;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "rest/v1/demo")
public class DemoRestController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> get() {
		MyDto myDto = new MyDto("field1");
		myDto.add(linkTo(methodOn(DemoRestController.class).linked()).withRel("mylink"));
		return new ResponseEntity<>(myDto, HttpStatus.OK);
	}

	@RequestMapping(path = "/linked", method = RequestMethod.GET)
	public @ResponseBody MyLinkedRes linked() {
		return new MyLinkedRes("foo");
	}

}