package Domini;

public class Area {

 private int id_area;
 private String nomArea;
 private Zona zona;


 public Area(int id_area, String nomArea, Zona zona) throws Exception {
 
  setId_area(id_area);
  setNomArea(nomArea);
  setZona(zona);
 }

 public int getId_area() {
  return id_area;
 }

 public void setId_area(int id_area) throws Exception {
  if (id_area < 0) throw new Exception("El codi de area ha de ser més gran que 0");
  this.id_area = id_area;
 }

 public String getNomArea() {
  return nomArea;
 }

 public void setNomArea(String nomArea) throws Exception {
  if (nomArea == "") throw new Exception("Es necesitaa un nom de area");
  this.nomArea = nomArea;
 }

 public Zona getZona() {
  return zona;
 }

 public void setZona(Zona zona) throws Exception {
  if (zona == null) throw new Exception("Es necesita una zona");

  this.zona = zona;
 }

}