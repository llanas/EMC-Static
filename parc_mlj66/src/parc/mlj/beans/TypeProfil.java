package parc.mlj.beans;

public class TypeProfil {

	private Long id;
	private String name;
	private int right;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getRight() {
		return this.right;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public void setRight( int right ) {
		this.right = right;
	}
}
