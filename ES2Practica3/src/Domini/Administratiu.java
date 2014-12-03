package Domini;

public class Administratiu extends Empleat{
	
	private String tipus = "Administratiu";

	public Administratiu(String idEmpleat, String nom, String cognom,
			String email, String password) {
		super(idEmpleat, nom, cognom, email, password);
	}

	@Override
	public String getTipus() {
		return this.tipus;
	}

}
