package Aplicacio;


import java.util.List;

import Domini.Client;
import Domini.Comanda;
import Domini.Empleat;
import Domini.Estat;
import Domini.MossoMagatzem;
import Domini.Repartidor;
import Domini.Comandes.LiniaComanda;
import Persistencia.ComandaBBDD;
import Persistencia.LiniaComandaBBDD;

public class ProxyComanda implements IComanda {
 private IComanda comandaReal;
 private ComandaBBDD comandaBbdd;
 private LiniaComandaBBDD liniaComandaBBDD;

 public ProxyComanda(int num, Client client, String data, Estat estat, float importTotal, ComandaBBDD comandabbdd, LiniaComandaBBDD liniaComandaBBDD) {

  this.comandaReal = new Comanda(num, client, data, estat, importTotal);
  this.comandaBbdd = comandabbdd;
  this.liniaComandaBBDD=liniaComandaBBDD;
 }

 @Override
 public void afegirLiniaComanda(LiniaComanda linia) {
  this.comandaReal.afegirLiniaComanda(linia);

 }

 public String getIdClient() {
  return comandaReal.getIdClient();

 }

 public Empleat getVenedor() {
  return getVenedor();
 }

 public void setVenedor(Empleat venedor) {
  this.comandaReal.setVenedor(venedor);
 }

 public Empleat getRepartidor() throws Exception {

  if (comandaReal.getRepartidor() == null) {
   Repartidor r = this.comandaBbdd.obtenirRepartidor(this.comandaReal.getNumComanda());
   this.comandaReal.setRepartidor(r);
  }

  return this.comandaReal.getRepartidor();
 }

 public void setRepartidor(Empleat repartidor) throws Exception {

  this.comandaBbdd.setRepartidor(this.comandaReal.getNumComanda(), repartidor);
  this.comandaReal.setRepartidor(repartidor);

 }

 public Empleat getMossoMagatzemPreparador() throws Exception {

  if (this.comandaReal == null) {
   MossoMagatzem mosso = this.comandaBbdd.getMossoMagatzem(this.comandaReal.getNumComanda());
   this.comandaReal.setMossoMagatzemPreparador(mosso);

  }

  return comandaReal.getMossoMagatzemPreparador();
 }

 public void setMossoMagatzemPreparador(Empleat mossoMagatzemPreparador) {
  // this.mossoMagatzemPreparador = mossoMagatzemPreparador;
 }

 public Empleat getMossoMagatzemReponedor() {
  return null;
 }

 public void setMossoMagatzemReponedor(Empleat mossoMagatzemReponedor) {
  // this.mossoMagatzemReponedor = mossoMagatzemReponedor;
 }

 public float getImport_total() {
  return comandaReal.getImport_total();
 }

 public void setImport_total(float import_total) throws Exception {
  float preu = this.comandaBbdd.setImportTotal(import_total, this.comandaReal.getNumComanda());
  this.comandaReal.setImport_total(preu);

 }
 public Client getClient() {
  return this.comandaReal.getClient();
 }

 public void setClient(Client client) throws Exception {

  this.comandaBbdd.setClient(client);
  this.comandaReal.setClient(client);

 }

 public int getNumComanda() {
  return this.comandaReal.getNumComanda();
 }

 public void setNumComanda(int numComanda) throws Exception {

  int num = this.comandaBbdd.setNumeroComanda(numComanda);
  this.comandaReal.setNumComanda(num);

 }

 public String getData_facturacio() throws Exception {

  if (this.comandaReal.getData_facturacio() == "") {
   String data = this.comandaBbdd.obtenirDataFacturacio(this.comandaReal.getNumComanda());
   this.comandaReal.setDataComanda(data);
  }
  return this.comandaReal.getDataComanda();
 }
 

 public void setData_facturacio(String data_facturacio) throws Exception {
  this.comandaBbdd.obtenirDataFacturacio(this.comandaReal.getNumComanda());
  this.comandaReal.setData_facturacio(data_facturacio);
 }

 public String getDataComanda() throws Exception {
  if(this.comandaReal.getDataComanda()== ""){
   String data = this.comandaBbdd.getDataComanda(this.comandaReal.getNumComanda());
   this.comandaReal.setDataComanda(data);
  }
  return this.comandaReal.getDataComanda();
 }

 public void setDataComanda(String dataComanda) throws Exception {
  this.comandaBbdd.setDataComanda(this.comandaReal.getNumComanda());
  this.comandaReal.setDataComanda(dataComanda);    
 }

 public Estat getEstat() throws Exception {
  throw new Exception();
 }
 public void setEstat(Estat estat) throws Exception {
  throw new Exception();
  
 }

 public List<LiniaComanda> getLinies() {

  if (this.comandaReal.getLinies().size() == 0) {

   List linies = null;
   try {
    linies = this.liniaComandaBBDD.obtenirLiniesComanda(this.comandaReal.getNumComanda());
   } catch (Exception e) {
    // TODO Auto-generated catch block
   }
   this.comandaReal.setLinies(linies);
  }

  return this.comandaReal.getLinies();
 }

 public void setLinies(List<LiniaComanda> linies) {
  try {
   this.comandaBbdd.afegirVariesLinies(linies);
   List l = liniaComandaBBDD.obtenirLiniesComanda(this.comandaReal.getNumComanda());

   this.comandaReal.setLinies(l);
  } catch (Exception e) {
   // TODO: handle exception
  }

 }
}