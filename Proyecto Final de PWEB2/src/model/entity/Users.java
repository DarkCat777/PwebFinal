package model.entity;
//Long id
//String email
//Date birth
//Boolean status
//Date Create
//Boolean gender(genero)
//Long idRole

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Users{
		@PrimaryKey
		@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
		@Persistent private String email;
		@Persistent private Date birth;
		@Persistent private boolean status;
		@Persistent private Date create;
		@Persistent private boolean gender;
		@Persistent private Long idRole;
		public Users(String email, Date birth, boolean status, boolean gender, Long idRole) {
			super();
			this.email = email;
			this.birth = birth;
			this.status = status;
			this.create = new Date();
			this.gender = gender;
			this.idRole = idRole;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getBirth() {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		    String dateIn = DATE_FORMAT.format(this.birth);
			return dateIn;
		}
		public String getBirthJSP() {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		    String dateIn = DATE_FORMAT.format(this.birth);
			return dateIn;
		}
		public void setBirth(Date birth) {
			this.birth = birth;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public String getCreate() {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		    String dateIn = DATE_FORMAT.format(this.create);
			return dateIn;
		}
		public String getCreateJSP() {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		    String dateIn = DATE_FORMAT.format(this.create);
			return dateIn;
		}
		public void setCreate() {
			this.create = new Date();
		}
		public boolean isGender() {
			return gender;
		}
		public void setGender(boolean gender) {
			this.gender = gender;
		}
		public Long getIdRole() {
			return idRole;
		}
		public void setIdRole(Long idRole) {
			this.idRole = idRole;
		}	
}