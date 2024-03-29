package modules;

import java.util.Map;

public class Profesor extends Persona {
  private int idCarnetProfesor;
  private double sueldo;

  public Profesor(String nombre, String apellido, int dni, int edad, int idCarnetProfesor, double sueldo)
      throws Exception {
    super(nombre, apellido, dni, edad);
    this.setIdCarnetProfesor(idCarnetProfesor);
    this.setSueldo(sueldo);
  }

  public int getIdCarnetProfesor() {
    return idCarnetProfesor;
  }

  private void setIdCarnetProfesor(int idCarnetProfesor) {
    this.idCarnetProfesor = idCarnetProfesor;
  }

  public double getSueldo() {
    return sueldo;
  }

  public void setSueldo(double sueldo) {
    this.sueldo = sueldo;
  }

  public Map<String, Object> toHashMap() {
    Map<String, Object> map = super.toHashMap();
    map.put("idCarnetProfesor", this.idCarnetProfesor);
    map.put("sueldo", this.sueldo);
    return map;
  }
}
