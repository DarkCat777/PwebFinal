package model.entity;
/*
 * Long Id
 * Long ID role
 * Long ID url
 * Boolean Status
 */

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Access {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)private Long id;
	@Persistent private Long idRole;
	@Persistent private Long idUrl;
	@Persistent private boolean status;
	public Access(Long idRole, Long idUrl, boolean status) {
		this.idRole = idRole;
		this.idUrl = idUrl;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public Long getIdUrl() {
		return idUrl;
	}
	public void setIdUrl(Long idUrl) {
		this.idUrl = idUrl;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
