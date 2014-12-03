package Aplicacio;


import java.util.List;

import Domini.Client;
import Domini.Empleat;
import Domini.Estat;
import Domini.Comandes.LiniaComanda;

public interface IComanda {
 
 public void afegirLiniaComanda(LiniaComanda linia);
 
 public String getIdClient() ;

 public Empleat getVenedor();

 public void setVenedor(Empleat venedor);

 public Empleat getRepartidor() throws Exception;
 
 public void setRepartidor(Empleat repartidor) throws Exception;

 public Empleat getMossoMagatzemPreparador() throws Exception;

 public void setMossoMagatzemPreparador(Empleat mossoMagatzemPreparador) ;

 public Empleat getMossoMagatzemReponedor() ;

 public void setMossoMagatzemReponedor(Empleat mossoMagatzemReponedor);

 public float getImport_total();

 public void setImport_total(float import_total) throws Exception ;

 public Client getClient();
 
 public void setClient(Client client) throws Exception;

 public int getNumComanda() ;

 public void setNumComanda(int numComanda) throws Exception;

 public String getData_facturacio() throws Exception ;

 public void setData_facturacio(String data_facturacio) throws Exception ;

 public String getDataComanda() throws Exception ;

 public void setDataComanda(String dataComanda) throws Exception ;

 public Estat getEstat() throws Exception ;

 public void setEstat(Estat estat) throws Exception ;

 public List<LiniaComanda> getLinies() ;

 public void setLinies(List<LiniaComanda> linies) ;
 

}