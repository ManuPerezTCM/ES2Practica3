package Aplicacio;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Domini.Article;
import Domini.Client;
import Domini.Comanda;
import Domini.Estat;
import Domini.Comandes.LiniaComanda;
import Persistencia.ArticleBBDD;
import Persistencia.ClientBBDD;
import Persistencia.ComandaBBDD;
import Persistencia.LiniaComandaBBDD;
//Aquesta classe obtindr� gaireb� tots els imports i s'encarrega de comunicar-se amb la vista i la persist�ncia. Cap altra classe es 
//podr� comunicar amb cap d'aquestes dues classes.
public class ControladorComanda {
	
	private	ComandaBBDD oConComanda;
	private ControladorLiniaComanda controladorLinia;
	private ClientBBDD oConClient;
	private ArticleBBDD oConArticle;
	private IComanda comanda;
	public ControladorComanda() throws Exception{
		oConClient=new ClientBBDD();
		oConComanda=new ComandaBBDD();
		oConArticle=new ArticleBBDD();
		controladorLinia=new ControladorLiniaComanda();
}

	public String[] dadesArticle(String codiArticle) throws Exception{	
		ArticleBBDD articleBBDD = new ArticleBBDD();
		try {
			Integer.valueOf(codiArticle);
			
		} catch (Exception e) {
			throw new Exception("El codi del article ha de ser num�ric");
		}
		Article a = articleBBDD.obtenirArticle( Integer.valueOf( codiArticle));
		
		if(a == null){
			throw new Exception("Aquest article no existeix");
		}
		String dades[] = new String[5];
		dades[0] =  String.valueOf( a.getNomArticle());
		dades[1] =   String.valueOf( a.getPreuUnitari());
		dades[2] = String.valueOf(a.getEstoc_actual());
		dades[3] = codiArticle;
		dades[4]=String.valueOf(a.getEstoc_actual());
		
		return dades;
	}
	
	//No fem servir mètodes boolean per a controlar errors, directament tirem errors controlats per a saber que son
	public int crearNovaComanda(String client, String data, String estat, float preuTotal) throws Exception{
		  
		  try {
		   Client cli=oConClient.obtenirClient(client);  
		   Estat est=Estat.valueOf(estat);
		   
		   //Anar bbdd recollir numero comanda
		   int numComanda=oConComanda.obtenirIdComandaBBDD();
		    comanda=new Comanda(numComanda,cli,data,est,preuTotal);
		    
		   return comanda.getNumComanda();
		  } catch (Exception e) {
		    throw new Exception("Error al crear la nova comanda - " + e.getMessage());
		  }
		 }
	
	public void afegirLiniaComanda(int idComanda, int article, int quantitat, float preu)throws Exception{
		try {
			// comanda= oConComanda.obtenirComandaBBDD(idComanda); //He posat oCon. devant per a que faci el mateix
			// Linia comanda les crea la comanda		// que el mètode de abaix.
			LiniaComanda linia;
			linia=new LiniaComanda(idComanda, article, quantitat, preu);
			comanda.afegirLiniaComanda(linia);
			controladorLinia.afegirLiniaComandaBBDD(linia);
		} catch (Exception e) {
			throw new Exception("Error al afegir la linia de la comanda - " + e.getMessage());
		}
	}

//	Aquest mètode no es igual que fer el obtenirComandaBBDD a la lina d'adalt?
//	private Comanda obtenirComanda(int idComanda) {
//		Comanda comanda;
//		comanda=oCon.obtenirComandaBBDD(idComanda);
//		return comanda;
//	}
	
	public void modificarEstatComanda(int idComanda, String est) throws Exception{
		Estat estat=Estat.valueOf(est);
		
		IComanda comanda = null;
		try {
			comanda = oConComanda.obtenirComandaBBDD(idComanda);
			comanda.setEstat(estat);
			oConComanda.modificarComandaBBDD(comanda);
		} catch (Exception e) {
			throw new Exception("Error al modificar la comanda." + e.getMessage());
		} //He posat oCon. devant per a que faci el mateix
		
		
	}
	
	public ArrayList codisComandes() throws Exception{
		  
		  ComandaBBDD comanesBBDD = new ComandaBBDD(); 
		 return oConComanda.llistaCodiComandes();
		  
		 
		 }
		 
		 public boolean  prepararComanda(int comanda){
		  
		  return false;
		  
		 }
		 
		 public ArrayList obtenirComandes_Estat_Pendent() throws Exception {
			  ComandaBBDD comanesBBDD = new ComandaBBDD();
			  ArrayList dades = oConComanda.ObtenirLlistaComandesPendents();
			  ArrayList<String[]> dades_finals = new ArrayList<String[]>();
			  int x = 0;
			  for (x = 0; x < dades.size(); x++) {
			   String dades1[] = new String[5];
			   dades1[0] = String .valueOf(((IComanda) dades.get(x)).getNumComanda());
			   dades1[1] = String.valueOf(((IComanda) dades.get(x)) .getDataComanda());
			   dades1[2] = String.valueOf(((IComanda) dades.get(x)) .getImport_total());
			   dades1[3] = String.valueOf(((IComanda) dades.get(x)).getEstat());
			   dades1[4] = String.valueOf(((IComanda) dades.get(x)).getIdClient());
			   dades_finals.add(dades1);
			  }
			  return dades_finals;
			 }



		public String[] obtenirComanda(int codiComanda) throws Exception {
			String[] dadesComanda=new String[2];
			try {
				ComandaBBDD comanesBBDD = new ComandaBBDD();
				comanda=oConComanda.obtenirComandaBBDD(codiComanda);
				dadesComanda[0]=(comanda.getIdClient());
				dadesComanda[1]=(comanda.getDataComanda());
				return dadesComanda;
			} catch (Exception e) {
				throw new Exception("Error al obtenir la comanda.");
			}
			
		}
		
		public void esborrarLiniesComanda(int idComanda) throws Exception{
			try {
				controladorLinia.esborrarLiniesComanda(idComanda);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		
		public void modificarComanda(int codiComanda,String client, String data, String estat, float preuTotal) throws Exception{
			try{
				IComanda comanda = oConComanda.obtenirComandaBBDD(codiComanda);
				comanda.setImport_total(preuTotal);
				oConComanda.modificarComandaBBDD(comanda);
			}catch (Exception e){
				throw new Exception("Error al modificar la comanda");
			}
			
			
		}

		public ArrayList<String[]> obtenirLiniesComanda(int codiComanda) throws Exception {
			ArrayList<String[]> liniesComanda=new ArrayList<String[]>();
			try {
				liniesComanda=controladorLinia.obtenirLiniesComanda(codiComanda);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return liniesComanda;
		}
		//Andreu (al no fer gesti� d'articles, no creiem necessari fer un controlador d'articles
		public void modificarEstocArticle(int idArticle, int estoc) throws Exception{
			try {
				oConArticle.actualitzarEstocArticle(idArticle, estoc);
			} catch (Exception e) {
				throw new Exception("Error al modificar l'estoc de l'article");
			}
		}
		
		public int obtenirEstocArticle(int idArticle) throws Exception{
			try {
				return oConArticle.obtenirEstocArticle(idArticle);
			} catch (Exception e) {
				throw new Exception("Error al obtenir l'estoc de l'article");
			}
		}
		 
	
}
