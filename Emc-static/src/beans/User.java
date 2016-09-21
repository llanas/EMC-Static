package beans;

public class User implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Groupe groupe;
	private Profil profil;
	private String prenom;
	private String nom;
	private String mdp;
	private String phone;
	private String mobile;
	private String mail;
	private String fonction;
	private String champ;
	private String champ1;
	private String champ2;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	public Groupe getGroupe(){
		return this.groupe;
	}
	public Profil getProfil() {
		return this.profil;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public String getNom() {
		return this.nom;
	}
	public String getMdp() {
		return this.mdp;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getMobile() {
		return this.mobile;
	}
	public String getMail() {
		return this.mail;
	}
	public String getFonction() {
		return this.fonction;
	}
	public String getChamp() {
		return this.champ;
	}
	public String getChamp1() {
		return this.champ1;
	}
	public String getChamp2() {
		return this.champ2;
	}

	/* Mutateur */
	
	public void setId( Long id ) {
		this.id = id;
	}
	public void setGroupe( Groupe groupe ) {
		this.groupe = groupe;
	}
	public void setProfil( Profil profil ) {
		this.profil = profil;
	}
	public void setPrenom( String prenom ) {
		this.prenom = prenom;
	}
	public void setNom( String nom ) {
		this.nom = nom;
	}
	public void setMdp( String mdp ) {
		this.mdp = mdp;
	}
	public void setPhone( String phone ){
		this.phone = phone;
	}
	public void setMobile( String mobile ) {
		this.mobile = mobile;
	}
	public void setMail( String mail ) {
		this.mail = mail;
	}
	public void setFonction( String fonction ) {
		this.fonction = fonction;
	}
	public void setChamp( String champ ) {
		this.champ = champ;
	}
	public void setChamp1( String champ1 ) {
		this.champ1 = champ1;
	}
	public void setChamp2( String champ2 ) {
		this.champ2 = champ2;
	}
}
