package Domini;

public class Client {
	
	private String DNI;
	private String nom;
	private String cognom;
	private String direccio;
	private String telefon;
	private String email;
	private int area;
	
	public Client(String DNI, String nom, String cognom, String direccio, String telefon, String email, int idArea){
		this.setDNI(DNI);
		this.setNom(nom);
		this.setCognom(cognom);
		this.setDireccio(direccio);
		this.setTelefon(telefon);
		this.setEmail(email);
		this.setIdArea(idArea);
		
	}
	
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCognom1() {
		return cognom;
	}
	public void setCognom(String cognom1) {
		this.cognom = cognom;
	}
	public String getDireccio() {
		return direccio;
	}
	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setIdArea(int idArea) {
		this.area=idArea;
		
	}
	
	public int getIdArea(){
		return this.area;
	}
	
}
