package Domini;

public class Repartidor extends Empleat{
	
	private String tipus = "Repartidor";
	private Area area;

	public Repartidor(String idEmpleat, String nom, String cognom, String email, String password) {
		super(idEmpleat, nom, cognom, email, password);
	}

	@Override
	public String getTipus() {
		return this.tipus;
	}

}
