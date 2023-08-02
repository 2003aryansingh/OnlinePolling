package org.aryan.JavaOnlinePoll.service;

public class Auth {
	
	public boolean authenticate(String userId, String password) {
		if(password == null || password.trim() == "") {
			return false;
		} 
		return true;
	}
}
