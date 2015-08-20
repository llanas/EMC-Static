package parc.mlj.beans;

public class TypeTicket {

	private Long id;
	private String name;
	private String stat;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStat() {
		return this.stat;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public void setStat( String stat ) {
		this.stat = stat;
	}
}
