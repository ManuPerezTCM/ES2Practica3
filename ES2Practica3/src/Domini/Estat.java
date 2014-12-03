package Domini;

public enum Estat {
	REPOSADA ("REPOSADA"),
	PENDENT ("PENDENT"),
	PREPARADA ("PREPARADA"),
	LLIURADA ("LLIURADA"),
	RETORNADA ("RETORNADA"),
	ANULADA ("ANULADA");
	
	private final String estat;
	
	Estat(String estat){
		this.estat=estat;
	}

	public String getNom() {
		return this.estat;
	}
	
	
	

}
