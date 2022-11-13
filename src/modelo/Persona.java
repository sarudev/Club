package modelo;

import java.util.HashMap;
import java.util.Map;

public abstract class Persona {
  protected String nombre;
  protected String apellido;
  protected int dni;
  protected int edad;

  public Persona(String nombre, String apellido, int dni, int edad) {
    setNombre(nombre);
    setApellido(apellido);
    setDni(dni);
    setEdad(edad);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public int getDni() {
    return dni;
  }

  public void setDni(int dni) {
    this.dni = dni;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public Map<String, Object> toHashMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("nombre", this.nombre);
    map.put("apellido", this.apellido);
    map.put("dni", this.dni);
    map.put("edad", this.edad);
    return map;
  }
}
