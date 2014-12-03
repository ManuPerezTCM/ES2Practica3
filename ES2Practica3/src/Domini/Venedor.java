package Domini;

public class Venedor extends Empleat{
	
	private String tipus = "Venedor";
	private Zona zona;

	public Venedor(String idEmpleat, String nom, String cognom, String email, String password) {
		super(idEmpleat, nom, cognom, email, password);
	}

	@Override
	public String getTipus() {
		return this.tipus;
	}

}
