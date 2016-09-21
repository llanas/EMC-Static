package beans;

import java.util.Date;

public class Fichier implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Espace espace;
	private String lien;
	private String nom;
	private Date date;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public Espace getEspace() {
		return this.espace;
	}
	
	public String getLien() {
		return this.lien;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public void setEspace( Espace espace ) {
		this.espace = espace;
	}
	
	public void setLien( String lien ) {
		this.lien = lien;
	}
	
	public void setNom( String nom ) {
		this.nom = nom;
	}
	
	public void setDate( Date date ) {
		this.date = date;
	}
}
