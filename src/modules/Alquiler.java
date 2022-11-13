package modules;

import java.util.HashMap;
import java.util.Map;

public class Alquiler {
  private int idAlquiler;
  private String nombre;
  private double precio;
  private boolean alquilado;
  private DiaHorario diaYhorario;

  public Alquiler(String nombre, int idAlquiler, double precio) throws Exception {
    this.setIdAlquiler(idAlquiler);
    this.setNombre(nombre);
    this.setPrecio(precio);
  }

  public int getIdAlquiler() {
    return idAlquiler;
  }

  private void setIdAlquiler(int idAlquiler) {
    this.idAlquiler = idAlquiler;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) throws Exception {
    if (nombre.contains("=") || nombre.contains(",") || nombre.contains(".") || nombre.contains("{")
        || nombre.contains("}")
        || nombre.contains("[") || nombre.contains("]") || nombre.contains("\""))
      throw new Exception("El nombre no puede contener los siguientes caracteres: `={}[]`");
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public boolean isAlquilado() {
    return alquilado;
  }

  public void setAlquilado(boolean alquilado) {
    this.alquilado = alquilado;
  }

  public DiaHorario getDiaYhorario() {
    return diaYhorario;
  }

  public void setDiaYhorario(DiaHorario diaYhorario) {
    this.diaYhorario = diaYhorario;
  }

  public Map<String, Object> toHashMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("idAlquiler", this.idAlquiler);
    map.put("nombre", "\"" + this.nombre + "\"");
    map.put("precio", this.precio);
    map.put("alquilado", this.alquilado);
    map.put("diaYhorario", this.diaYhorario == null ? null : this.diaYhorario.toHashMap());
    return map;
  }
}
