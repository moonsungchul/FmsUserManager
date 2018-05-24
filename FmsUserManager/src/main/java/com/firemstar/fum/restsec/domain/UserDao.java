package com.firemstar.fum.restsec.domain;

public class UserDao {
	
	public User getByLogin(String login) {
		switch (login) {
			case "admin":
				return new User("admin", "Administrator", "admin", "ADMIN");
			case "special":
				return new User("special", "Special Expert", "special", "ROLE_SPECIAL");
			case "user1":
				return new User("user1", "User Uno", "user1");
			case "Aladdin":
				return new User("Aladdin", "Aladdin", "open sesame");
			default:
				return null;
		}
	}

}
