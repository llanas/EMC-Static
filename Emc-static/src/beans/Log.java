package beans;

public class Log implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private User user;
	private Fichier fichier;
	private int date;
	private int etat;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Fichier getFichier() {
		return this.fichier;
	}
	
	public int getDate() {
		return this.date;
	}
	
	public int getEtat() {
		return this.etat;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setUser( User user ) {
		this.user = user;
	}
	
	public void setFichier( Fichier fichier ) {
		this.fichier = fichier;
	}
	
	public void setDate( int date ) {
		this.date = date;
	}
	
	public void setEtat( int etat ) {
		this.etat = etat;
	}
 
}
