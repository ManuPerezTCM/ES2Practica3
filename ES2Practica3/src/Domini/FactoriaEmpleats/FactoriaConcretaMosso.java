package Domini.FactoriaEmpleats;

import Domini.Empleat;
import Domini.MossoMagatzem;

public class FactoriaConcretaMosso extends FactoriaAbstracteEmpleats{

	@Override
	public Empleat getEmpleat(String id, String nom, String cognom, String email, String password) {
		return new MossoMagatzem(id,nom,cognom,email, password);
	}

}
