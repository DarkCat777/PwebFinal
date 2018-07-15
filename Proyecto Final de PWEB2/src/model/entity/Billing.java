package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.repackaged.org.joda.time.DateTimeZone;
import com.google.appengine.repackaged.org.joda.time.LocalDateTime;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Billing {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private Date dateIn;
	@Persistent private String customer;
	@Persistent private String address;
	@Persistent private boolean status;
	@Persistent private Long idUser;
	@Persistent private String descriptionProduct;
	@Persistent private double unitPriceProduct;
	@Persistent private double mountProduct;
	@Persistent private double total=0;
	
	public Billing(String customer, String address, boolean status,
		String descriptionProduct, double unitPriceProduct, double mountProduct,Long idUser) {
		this.dateIn = LocalDateTime.now(DateTimeZone.forID("America/Lima")).toDate();
		this.customer = customer;
		this.address = address;
		this.status = status;
		this.descriptionProduct = descriptionProduct;
		this.unitPriceProduct = unitPriceProduct;
		this.mountProduct = mountProduct;
		this.total=unitPriceProduct*mountProduct;
		this.idUser=idUser;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateIn() {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        String dateIn = DATE_FORMAT.format(this.dateIn);
		return dateIn;
	}

	public void setDateIn() {
		this.dateIn = LocalDateTime.now(DateTimeZone.forID("America/Lima")).toDate();
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescriptionProduct() {
		return descriptionProduct;
	}

	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}

	public double getUnitPriceProduct() {
		return unitPriceProduct;
	}

	public void setUnitPriceProduct(double unitPriceProduct) {
		this.unitPriceProduct = unitPriceProduct;
	}

	public double getMountProduct() {
		return mountProduct;
	}

	public void setMountProduct(double mountProduct) {
		this.mountProduct = mountProduct;
	}

	public double getTotal() {
		return total;
	}
	public void setTotal() {
		this.total=unitPriceProduct*mountProduct;
	}

	
}