package parc.mlj.beans;

import java.util.Date;

public class Ticket implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private User user;
	private Hardware hardware;
	private TypeTicket type;
	private Date date;
	private String comment;
	private String state;

	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Hardware getHarware() {
		return this.hardware;
	}
	
	public TypeTicket getType() {
		return this.type;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public String getState() {
		return this.state;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setUser( User user ) {
		this.user = user;
	}
	
	public void setHardware( Hardware hardware ) {
		this.hardware = hardware;
	}
	
	public void setType( TypeTicket type ) {
		this.type = type;
	}
	
	public void setDate( Date date ) {
		this.date = date;
	}
	
	public void setComment( String comment ) {
		this.comment = comment;
	}
	
	public void setState( String state ) {
		this.state = state;
	}
}
