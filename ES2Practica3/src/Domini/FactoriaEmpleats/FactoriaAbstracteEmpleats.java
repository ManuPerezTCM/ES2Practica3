package Domini.FactoriaEmpleats;

import Domini.Empleat;


public abstract class FactoriaAbstracteEmpleats {

	public static Empleat crearEmpleat(String idEmpleat, String nom, String cognom, String email, String tipus, String password) throws Exception{
		try {
			String classe = "Domini.FactoriaEmpleats.FactoriaConcreta";
			FactoriaAbstracteEmpleats factoria;
			factoria = (FactoriaAbstracteEmpleats) Class.forName(classe+tipus).newInstance();
			Empleat retorn = factoria.getEmpleat(idEmpleat,nom,cognom,email, password);
			return retorn;
		} catch (Exception e) {
			throw new Exception("Error crearEmpleat - "+e.getMessage());
		}
	}
	
	public abstract Empleat getEmpleat(String id, String nom, String cognom, String email, String password);
}
