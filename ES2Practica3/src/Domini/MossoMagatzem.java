package Domini;

public class MossoMagatzem extends Empleat{
	
	private String tipus = "MossoMagatzem";

	public MossoMagatzem(String idEmpleat, String nom, String cognom,
			String email, String password) {
		super(idEmpleat, nom, cognom, email, password);
	}

	@Override
	public String getTipus() {
		return this.tipus;
	}


}
