package Domini.FactoriaEmpleats;

import Domini.Empleat;
import Domini.Venedor;

public class FactoriaConcretaVenedor extends FactoriaAbstracteEmpleats{

	@Override
	public Empleat getEmpleat(String id, String nom, String cognom, String email, String password) {
		return new Venedor(id, nom, cognom, email, password);
	}

}
