package model.entity;
// ID long
//name String
//Date create
//Boolean status
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;
import java.text.SimpleDateFormat;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Role {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)private Long id;
	@Persistent private String name;
	@Persistent private Date create;
	@Persistent private boolean status;
	public Role(String name, boolean status) {
		super();
		this.name = name;
		this.create = new Date();
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreate() {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	    String create = DATE_FORMAT.format(this.create);
		return create;
	}
	public String getCreateJSP() {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	    String create = DATE_FORMAT.format(this.create);
		return create;
	}
	public void setCreate(Date create) {
		this.create = create;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}