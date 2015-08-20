package parc.mlj.beans;

public class Address {

	public Long id;
	public Partner partner;
	public String name;
	public Long num;
	public String typeRoad;
	public String road;
	public String city;
	public Long code;
	public String country;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public Partner getPartner() { 
		return this.partner;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Long getNum() {
		return this.num;
	}
	
	public String getTypeRoad() {
		return this.typeRoad;
	}
	
	public String getRoad() {
		return this.road;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public Long getCode() {
		return this.code;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	/* Mutateur */ 
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setPartner( Partner partner ) {
		this.partner = partner;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public void setNum( Long num ) {
		this.num = num;
	}
	
	public void setTypeRoad( String typeRoad ) {
		this.typeRoad = typeRoad;
	}
	
	public void setRoad( String road ) {
		this.road = road;
	}
	
	public void setCity( String city ) {
		this.city = city;
	}
	
	public void setCode ( Long code ) {
		this.code = code;
	}
	
	public void setCountry( String country ) {
		this.country = country;
	}
	
}
