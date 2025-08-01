package com.example.springkadaiform.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;


@Controller
public class ContactFormController {
	
	@GetMapping("/form")
	public String sendForm (Model model) {
		
		if (!model.containsAttribute("contactForm")) {
			// ビューにフォームクラスのインスタンスを渡す
			model.addAttribute("contactForm", new ContactForm());
		}
		return "contactFormView";
	}
	
	@GetMapping("/confirm")
	public String sendConfirm (Model model) {
		
		return "confirmView";
	}
	
	@PostMapping("/contact")
	public String check(RedirectAttributes redirectAttributes,
			@Validated ContactForm form, BindingResult result) {
		
		if (result.hasErrors()) {
			// フォームクラスをビューに受け渡す
			//エラーになる直前の入力内容を保持したいため、フォームクラスのインスタンスを渡す
			redirectAttributes.addFlashAttribute("contactForm", form);
			// バリデーション結果をビューに受け渡す
			// リダイレクト（別のURLへの遷移）を行う場合、バリデーション結果が消えてしまため
			// BindingResultに格納された結果（result）も渡す必要がある
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
					+ Conventions.getVariableName(form), result);
			
			return "redirect:/form";
			
		}else {
			redirectAttributes.addFlashAttribute("contactForm", form);
			return "redirect:/confirm";
		} 
	}
}
