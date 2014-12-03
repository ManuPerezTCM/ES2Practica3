package Domini;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import Aplicacio.IComanda;
import Domini.Comandes.LiniaComanda;
//Amb aquesta classe comanda, ja no calen les dem�s classes, ja que simplement variar� l'estat de la comanda.
public class Comanda implements IComanda {
	
	private Empleat venedor;
	private Empleat repartidor;
	private Empleat mossoMagatzemPreparador;
	private Empleat mossoMagatzemReponedor;
	private float import_total;
	private Client client;
	private int numComanda;
	private String data_facturacio;
	private String dataComanda;
	private Estat estat;
	private List<LiniaComanda> linies;
	
	
	public Comanda(int num, Client client, String data, Estat estat,float importTotal){
		this.numComanda=num;
		this.client=client;
		this.dataComanda=data;
		this.estat=estat;
		this.import_total=importTotal;
		linies=new <LiniaComanda>ArrayList();
	}
	
	public Comanda( Client client, String data, Estat estat,float importTotal){
	
		this.client=client;
		this.dataComanda=data;
		this.estat=estat;
		this.import_total=importTotal;
		linies=new <LiniaComanda>ArrayList();
	}
	@Override
	public void afegirLiniaComanda(LiniaComanda linia){
		linies.add(linia);
	}
	@Override
	public String getIdClient() {
		return client.getDNI();
	}
	@Override
	public Empleat getVenedor() {
		return venedor;
	}
	@Override
	public void setVenedor(Empleat venedor) {
		this.venedor = venedor;
	}
	@Override
	public Empleat getRepartidor() {
		return repartidor;
	}
	@Override
	public void setRepartidor(Empleat repartidor) {
		this.repartidor = repartidor;
	}
	@Override
	public Empleat getMossoMagatzemPreparador() {
		return mossoMagatzemPreparador;
	}
	@Override
	public void setMossoMagatzemPreparador(Empleat mossoMagatzemPreparador) {
		this.mossoMagatzemPreparador = mossoMagatzemPreparador;
	}
	@Override
	public Empleat getMossoMagatzemReponedor() {
		return mossoMagatzemReponedor;
	}
	@Override
	public void setMossoMagatzemReponedor(Empleat mossoMagatzemReponedor) {
		this.mossoMagatzemReponedor = mossoMagatzemReponedor;
	}
	@Override
	public float getImport_total() {
		return import_total;
	}
	@Override
	public void setImport_total(float import_total) {
		this.import_total = import_total;
	}
	@Override
	public Client getClient() {
		return client;
	}
	@Override
	public void setClient(Client client) {
		this.client = client;
	}
	@Override
	public int getNumComanda() {
		return numComanda;
	}
	@Override
	public void setNumComanda(int numComanda) {
		this.numComanda = numComanda;
	}
	@Override
	public String getData_facturacio() {
		return data_facturacio;
	}
	@Override
	public void setData_facturacio(String data_facturacio) {
		this.data_facturacio = data_facturacio;
	}
	@Override
	public String getDataComanda() {
		return dataComanda;
	}
	@Override
	public void setDataComanda(String dataComanda) {
		this.dataComanda = dataComanda;
	}
	@Override
	public Estat getEstat() {
		return estat;
	}
	@Override
	public void setEstat(Estat estat) {
		this.estat = estat;
	}
	@Override
	public List<LiniaComanda> getLinies() {
		return linies;
	}
	@Override
	public void setLinies(List<LiniaComanda> linies) {
		this.linies = linies;
	}

}
