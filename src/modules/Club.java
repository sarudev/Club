package modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Club {
  private String nombre;
  private String direccion;
  private List<Profesor> lstProfesores;
  private List<Socio> lstSocios;
  private List<Actividad> lstActividades;
  private List<Alquiler> lstAlquileres;

  public Club(String nombre, String direccion) throws Exception {
    this.setNombre(nombre);
    this.setDireccion(direccion);
    this.lstProfesores = new ArrayList<Profesor>();
    this.lstSocios = new ArrayList<Socio>();
    this.lstActividades = new ArrayList<Actividad>();
    this.lstAlquileres = new ArrayList<Alquiler>();
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

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) throws Exception {
    if (direccion.contains("=") || direccion.contains(",") || direccion.contains(".") || direccion.contains("{")
        || direccion.contains("}")
        || direccion.contains("[") || direccion.contains("]") || direccion.contains("\""))
      throw new Exception("La direccion no puede contener los siguientes caracteres: `={}[]`");
    this.direccion = direccion;
  }

  public List<Profesor> getLstProfesores() {
    return this.lstProfesores;
  }

  public List<Socio> getLstSocios() {
    return this.lstSocios;
  }

  public List<Actividad> getLstActividades() {
    return this.lstActividades;
  }

  public List<Alquiler> getLstAlquileres() {
    return this.lstAlquileres;
  }

  public Profesor agregarProfesor(String nombre, String apellido, int dni, int edad, double sueldo) throws Exception {
    int id = this.lstProfesores.size();

    Profesor prof = new Profesor(nombre, apellido, dni, edad, id, sueldo);

    this.lstProfesores.add(prof);

    return prof;
  }

  public Profesor traerProfesor(int id) {
    int i = 0;
    Profesor prof = null;

    while (i < this.lstProfesores.size() && prof == null) {
      if (this.lstProfesores.get(i).getIdCarnetProfesor() == id)
        prof = this.lstProfesores.get(i);
      i++;
    }

    return prof;
  }

  public Profesor traerProfesor(String nombre, String apellido) {
    int i = 0;
    Profesor prof = null;

    while (i < this.lstProfesores.size() && prof == null) {
      if (this.lstProfesores.get(i).getNombre().equals(nombre)
          && this.lstProfesores.get(i).getApellido().equals(apellido))
        prof = this.lstProfesores.get(i);
      i++;
    }

    return prof;
  }

  public Profesor eliminarProfesor(int id) throws Exception {
    Profesor prof = this.traerProfesor(id);

    if (prof == null)
      throw new Exception("No existe ese profesor.");

    this.lstProfesores.remove(id);

    return prof;
  }

  public Socio agregarSocio(String nombre, String apellido, int dni, int edad, double cuota)
      throws Exception {
    int id = this.lstSocios.size();

    Socio soc = new Socio(nombre, apellido, dni, edad, id, cuota);

    this.lstSocios.add(soc);

    return soc;
  }

  public Socio traerSocio(int id) {
    int i = 0;
    Socio soc = null;

    while (i < this.lstSocios.size() && soc == null) {
      if (this.lstSocios.get(i).getIdCarnetSocio() == id)
        soc = this.lstSocios.get(i);
      i++;
    }

    return soc;
  }

  public Socio traerSocio(String nombre, String apellido) {
    int i = 0;
    Socio soc = null;

    while (i < this.lstSocios.size() && soc == null) {
      if (this.lstSocios.get(i).getNombre().equals(nombre)
          && this.lstSocios.get(i).getApellido().equals(apellido))
        soc = this.lstSocios.get(i);
      i++;
    }

    return soc;
  }

  public Socio eliminarSocio(int id) throws Exception {
    Socio soc = this.traerSocio(id);

    if (soc == null)
      throw new Exception("No existe ese profesor.");

    this.lstSocios.remove(id);

    return soc;
  }

  public Actividad agregarActividad(String nombre, int cupos) throws Exception {
    int id = this.lstActividades.size();

    Actividad act = new Actividad(id, nombre, cupos);

    this.lstActividades.add(act);

    return act;
  }

  public Actividad traerActividad(int id) {
    int i = 0;
    Actividad act = null;

    while (i < this.lstActividades.size() && act == null) {
      if (this.lstActividades.get(i).getIdActividad() == id)
        act = this.lstActividades.get(i);
      i++;
    }

    return act;
  }

  public Actividad eliminarActividad(int id) throws Exception {
    Actividad act = this.traerActividad(id);

    if (act == null)
      throw new Exception("Invalid id");

    this.lstActividades.remove(id);

    return act;
  }

  public DiaHorario agregarDiaYhorario(Actividad act, String dia, int hora, int duracion) throws Exception {
    DiaHorario dYh = new DiaHorario(dia, hora, duracion);
    int i = 0;

    while (i < this.lstActividades.size()) {
      int k = 0;
      if (!this.lstActividades.get(i).getDiasYhorarios().isEmpty()) {
        List<DiaHorario> actDyH = this.lstActividades.get(i).getDiasYhorarios();
        while (k < actDyH.size()) {
          if (dYh.isBetween(actDyH.get(k))) {
            throw new Exception("Invalid dia y hora");
          }
          k++;
        }
      }
      i++;
    }

    act.agregarDiaYhorario(dYh);

    return dYh;
  }

  public Alquiler agregarAlquiler(String nombre, double precio) throws Exception {
    int id = this.lstAlquileres.size();

    Alquiler act = new Alquiler(nombre, id, precio);

    this.lstAlquileres.add(act);

    return act;
  }

  public Alquiler traerAlquiler(int id) {
    int i = 0;
    Alquiler act = null;

    while (i < this.lstAlquileres.size() && act == null) {
      if (this.lstAlquileres.get(i).getIdAlquiler() == id)
        act = this.lstAlquileres.get(i);
      i++;
    }

    return act;
  }

  public Alquiler eliminarAlquiler(int id) throws Exception {
    Alquiler alq = this.traerAlquiler(id);

    if (alq == null)
      throw new Exception("Invalid id");

    this.lstAlquileres.remove(id);

    return alq;
  }

  public DiaHorario agregarDiaYhorario(Alquiler alq, String dia, int hora, int duracion) throws Exception {
    DiaHorario dYh = new DiaHorario(dia, hora, duracion);
    int i = 0;

    while (i < this.lstAlquileres.size()) {
      if (this.lstAlquileres.get(i).getDiaYhorario() != null) {
        if (dYh.isBetween(this.lstAlquileres.get(i).getDiaYhorario())) {
          throw new Exception("Invalid dia y hora");
        }
      }
      i++;
    }

    alq.setDiaYhorario(dYh);

    return dYh;
  }

  public Map<String, Object> toHashMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("nombre", "\"" + this.nombre + "\"");
    map.put("direccion", "\"" + this.direccion + "\"");
    map.put("lstProfesores", this.lstProfesores.stream().map((i) -> i.toHashMap()).collect(Collectors.toList()));
    map.put("lstSocios", this.lstSocios.stream().map((i) -> i.toHashMap()).collect(Collectors.toList()));
    map.put("lstActividades", this.lstActividades.stream().map((i) -> i.toHashMap()).collect(Collectors.toList()));
    map.put("lstAlquileres", this.lstAlquileres.stream().map((i) -> i.toHashMap()).collect(Collectors.toList()));
    return map;
  }
}
