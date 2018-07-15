package model.entity;

import java.util.Date;

public class Enterprise extends Users {

	public Enterprise(String email, Date birth, boolean status, boolean gender, Long idRole) {
		super(email, birth, status, gender, idRole);
	}
	
}
