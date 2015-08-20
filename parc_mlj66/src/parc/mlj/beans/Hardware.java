package parc.mlj.beans;

import java.util.Date;

public class Hardware {

	private Long id;
	private Order order;
	private Location location;
	private TypeHardware type;
	private String name;
	private String state;
	private Date dateInstal;
	private String serial;
	private String serialCorhofi;
	private String serialInner;
	private Float price;
	private Date warranty;
	private String feature;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public TypeHardware getType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getState() {
		return this.state;
	}
	
	public Date getDateInstal() {
		return this.dateInstal;
	}
	
	public String getSerial() {
		return this.serial;
	}
	
	public String getSerialCorhofi() {
		return this.serialCorhofi;
	}
	
	public String getSerialInner() {
		return this.serialInner;
	}
	
	public Float getPrice() {
		return this.price;
	}
	
	public Date getWarranty() {
		return this.warranty;
	}
	
	public String getFeature() {
		return this.feature;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setOrder( Order order ) {
		this.order = order;
	}
	
	public void setLocation( Location location ) {
		this.location = location;
	}
	
	public void setType( TypeHardware type ) {
		this.type = type;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public void setState( String state ) {
		this.state = state;
	}
	
	public void setDateInstal( Date dateInstal ) {
		this.dateInstal = dateInstal;
	}
	
	public void setSerial( String serial ) {
		this.serial = serial;
	}
	
	public void setSerialCorhofi( String serialCorhofi ) {
		this.serialCorhofi = serialCorhofi;
	}
	
	public void setSerialInner( String serialInner ) {
		this.serialInner = serialInner;
	}
	
	public void setPrice( Float price ) {
		this.price = price;
	}
	
	public void setWarranty( Date warranty ) {
		this.warranty = warranty;
	}
	
	public void setFeature( String feature ) {
		this.feature = feature;
	}
}
