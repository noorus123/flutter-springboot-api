package com.app.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.service.SequenceService;
import com.app.family.utils.sequencer.DatabaseSequencer;
import com.app.family.utils.sequencer.SequenceGeneratorService;

@RestController
@RequestMapping("/") //http://localhost:8080/family/
public class IndexController {
	
	@Autowired
	SequenceService sequenceService;

	@GetMapping
    public String sayHello() {
        return "Hello and Welcome to the application.";
    }
	@RequestMapping(value = "/{name}")
    public String sayHelloName(@PathVariable("name") String name) {
        return "Hello "+name+" and Welcome to the application !!!!";
    }
	
	@RequestMapping(value = "/generateSequence")
    public String generateSequence() {
		return sequenceService.getFamilyNextSequence();
    }
	
	
}
