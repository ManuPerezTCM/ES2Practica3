package Domini.FactoriaEmpleats;

import Domini.Administratiu;
import Domini.Empleat;

public class FactoriaConcretaAdministratiu extends FactoriaAbstracteEmpleats{

	@Override
	public Empleat getEmpleat(String id, String nom, String cognom, String email, String password) {
		return new Administratiu(id,nom,cognom,email, password);
	}

}
