package com.email.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.service.EmailService;

@RestController
@RequestMapping(value="/")
public class EmailController {	

	@Autowired
	EmailService servie;

	@GetMapping
	public void sendMail() {
		System.out.println("sending email....");
		try {			
			if(servie.generateMail()) {
				System.out.println("Mail sent successfully");
			}			
		}catch (Exception e) {
			System.out.println("Failed sending email.... "+ e.getMessage());
			
		}
	}
}
