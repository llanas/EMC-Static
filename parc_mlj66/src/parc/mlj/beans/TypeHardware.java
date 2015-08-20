package parc.mlj.beans;

public class TypeHardware {

	public Long id;
	public String name;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
}
