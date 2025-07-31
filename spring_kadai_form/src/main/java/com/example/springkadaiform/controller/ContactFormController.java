package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {
	
	@ModelAttribute("contactForm")
    public ContactForm contactForm() {
        return new ContactForm();
    }
	
	@GetMapping("/confirm")
	public String checkSuccess() {
		
		return "confirmView";
	}
	
	@GetMapping("/form")
	public String checkFailure() {
		
		return "contactFormView";
	}
	
	
	
	@PostMapping("/contact")
	public String receiveCheck(RedirectAttributes redirectAttributes,
			
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("message") String message) {
		
		boolean hasError = false;
		ContactForm contactForm = new ContactForm();
		
		try {
			contactForm.nameCheck(name);
		}catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("nameFailureMessage", e.getMessage());
			hasError = true;
		}
		
		try {
			contactForm.emailCheck(email);
		}catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("emailFailureMessage", e.getMessage());
			hasError = true;
		}
		
		try {
			contactForm.messageCheck(message);
		}catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("messageFailureMessage", e.getMessage());
			hasError = true;
		}
		
		if (hasError) {
			redirectAttributes.addFlashAttribute("contactForm", contactForm);
			return "redirect:/form";
		}
		
		redirectAttributes.addFlashAttribute("contactForm", contactForm);
	     return "redirect:/confirm";
	}
}
