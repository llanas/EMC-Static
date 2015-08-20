package parc.mlj.beans;

import java.util.Date;

public class Order {

	private Long id;
	private Partner partner;
	private Long num;
	private Float price;
	private Date date;
	
	/* Accesseur */ 
	
	public Long getId() {
		return this.id;
	}
	
	public Partner getPartner() {
		return this.partner;
	}
	
	public Long getNum() {
		return this.num;
	}
	
	public Float getPrice() {
		return this.price;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setPartner( Partner partner ) {
		this.partner = partner;
	}
	
	public void setNum( Long num ) {
		this.num = num;
	}
	
	public void setPrice( Float price ) {
		this.price = price;
	}
	
	public void setDate( Date date ) {
		this.date = date;
	}
	
}
