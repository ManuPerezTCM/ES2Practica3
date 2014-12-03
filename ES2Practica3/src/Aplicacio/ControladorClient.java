package Aplicacio;

import java.util.LinkedList;

import Persistencia.ClientBBDD;

public class ControladorClient {
	
	private ClientBBDD clientBBDD;
	
	public ControladorClient() throws Exception{
		this.clientBBDD = new ClientBBDD();
	}
	
	public LinkedList<String> recuperarClientsPerVenedor(String idVenedor)throws Exception{
		try {
			return clientBBDD.obtenirClientsPerVenedor(idVenedor);
		} catch (Exception e) {
			throw new Exception("Error recuperarClientsPerVenedor - "+e.getMessage());
		}
	}
	
}
