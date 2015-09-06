package parc.mlj.beans;

public class Location implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Address address;
	private String name;
	private Long desk;
	private Long floor;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Long getDesk() {
		return this.desk;
	}
	
	public Long getFloor() {
		return this.floor;
	}
	
	/* Mutateur */ 
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setAddress( Address address ) {
		this.address = address;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public void setDesk( Long desk ) {
		this.desk = desk;
	}
	
	public void setFloor( Long floor ) {
		this.floor = floor;
	}
	
	
}
