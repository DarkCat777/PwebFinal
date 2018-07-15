package model.entity;

import java.util.Date;

public class Administrator extends Users{

	public Administrator(String email, Date birth, boolean status, boolean gender, Long idRole) {
		super(email, birth, status, gender, idRole);
	}

}
