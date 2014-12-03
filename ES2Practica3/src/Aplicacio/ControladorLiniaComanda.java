package Aplicacio;

import java.util.ArrayList;

import Domini.Comandes.LiniaComanda;
import Persistencia.LiniaComandaBBDD;
//Andreu
public class ControladorLiniaComanda {
	private LiniaComandaBBDD oConLinia;
	//Andreu
	public ControladorLiniaComanda() throws Exception{
		
		oConLinia=new LiniaComandaBBDD();
		
	}
	//Andreu
	public void esborrarLiniesComanda(int idComanda) throws Exception{
		try {
			oConLinia.eliminarLiniesComanda(idComanda);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Andreu
	public ArrayList<String[]> obtenirLiniesComanda(int codiComanda) throws Exception {
		ArrayList<String[]> liniesComanda=new ArrayList<String[]>();
		try {
			liniesComanda=oConLinia.obtenirLiniesComanda(codiComanda);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return liniesComanda;
	}
	
	public void afegirLiniaComandaBBDD(LiniaComanda linia) throws Exception{
		  oConLinia.afegirLiniaComandaBBDD(linia);
		 }

}
