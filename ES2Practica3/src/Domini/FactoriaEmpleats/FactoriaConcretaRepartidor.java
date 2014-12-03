package Domini.FactoriaEmpleats;

import Domini.Empleat;
import Domini.Repartidor;

public class FactoriaConcretaRepartidor extends FactoriaAbstracteEmpleats{

	@Override
	public Empleat getEmpleat(String id, String nom, String cognom, String email, String password) {
		return new Repartidor(id, nom, cognom, email, password);
	}
	
}
