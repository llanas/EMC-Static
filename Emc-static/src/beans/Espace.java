package beans;

public class Espace  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nom;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}
	
	/* Mutateur */
	
	public void setId ( Long id ) {
		this.id = id;
	}
	
	public void setNom ( String nom ) {
		this.nom = nom;
	}
}
