package Domini;

public abstract class Empleat {

	private String idEmpleat;
	private String nom;
	private String cognom;
	private String email;
	private String password;
	
	public Empleat(String idEmpleat, String nom, String cognom, String email, String password){
		this.idEmpleat = idEmpleat;
		this.nom = nom;
		this.cognom = cognom;
		this.email = email;
		this.password = password;
	}
	
	public String getIdEmpleat() {
		return idEmpleat;
	}

	public void setIdEmpleat(String idEmpleat) {
		this.idEmpleat = idEmpleat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public abstract String getTipus();
}
