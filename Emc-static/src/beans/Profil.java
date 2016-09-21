package beans;

public class Profil implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nom;
	private int droit;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getDroit() {
		return this.droit;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setNom( String nom ) {
		this.nom = nom;
	}
	
	public void setDroit( int droit ) {
		this.droit = droit;
	}
}
