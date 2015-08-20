package parc.mlj.beans;

public class User {

	private Long id;
	private TypeProfil profil;
	private Location location;
	private String function;
	private String login;
	private String password;
	private boolean gender;
	private String firstName;
	private String lastName;
	private String phone;
	
	/* Accesseur */
	
	public Long getId() {
		return this.id;
	}
	
	public TypeProfil getProfil() {
		return this.profil;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public String getFunction() {
		return this.function;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean getGender() {
		return this.gender;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	/* Mutateur */
	
	public void setId( long id ){
		this.id = id;
	}
	
	public void setProfil( TypeProfil profil ) {
		this.profil = profil;
	}
	
	public void setLocation( Location location ) {
		this.location = location;
	}
	
	public void setFunction( String function ) {
		this.function = function;
	}
	
	public void setLogin( String login ) {
		this.login = login;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
	
	public void setGender( boolean gender ) {
		this.gender = gender;
	}
	
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}
	
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}
	
	public void setPhone( String phone ) {
		this.phone = phone;
	}
}
