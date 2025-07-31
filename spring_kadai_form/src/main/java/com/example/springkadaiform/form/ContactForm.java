package com.example.springkadaiform.form;

public class ContactForm {
	
	private String name;
	private String email;
	private String message;
	
	public void nameCheck(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("お名前を入力してください。");
		} else {
			this.name = name;
		}
	}
	
	public void emailCheck(String email) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("メールアドレスを入力してください。");
		} else if( !email.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+$") ) { 
			throw new IllegalArgumentException("メールアドレスの入力形式が正しくありません。");
        } else {
        	this.email = email;
        }
	}
	
	public void messageCheck(String message) {
		if (message == null || message.isEmpty()) {
			throw new IllegalArgumentException("お問い合わせ内容を入力してください。");
		} else {
			this.message = message;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getMessage() {
		return message;
	}

}
