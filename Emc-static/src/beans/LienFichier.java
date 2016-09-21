package beans;

public class LienFichier implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private User user;
	private Fichier fichier;
	
	/* Accesseur */
	
	public User getUser() {
		return this.user;
	}
	
	public Fichier getFichier() {
		return this.fichier;
	}
	
	/* Mutateur */
	
	public void setUser( User user) {
		this.user = user;
	}
	
	public void setFichier( Fichier fichier ) {
		this.fichier = fichier;
	}
}
