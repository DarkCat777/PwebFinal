package model.entity;

import java.util.Date;

import javax.jdo.annotations.Persistent;

public class Client extends Users {
	@Persistent private String name;
	@Persistent private String lastName;
	@Persistent private int edad;
	@Persistent private int dni;
	@Persistent private int ruc;
	public Client(String email, Date birth, boolean status, boolean gender, Long idRole, String name, String lastName,
			int edad, int dni, int ruc) {
		super(email, birth, status, gender, idRole);
		this.name = name;
		this.lastName = lastName;
		this.edad = edad;
		this.dni = dni;
		this.ruc = ruc;
	}

	

}
