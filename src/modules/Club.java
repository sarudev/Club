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
    Profesor repe = this.traerProfesor(dni);

    if (repe != null)
      throw new Exception("Ya existe un socio con ese dni.");

    int id = this.lstProfesores.size();

    Profesor prof = new Profesor(nombre, apellido, dni, edad, id, sueldo);

    this.lstProfesores.add(prof);

    return prof;
  }

  public Profesor agregarProfesor(int id, String nombre, String apellido, int dni, int edad, double sueldo)
      throws Exception {
    Profesor pro = this.traerProfesorId(id);

    if (pro != null)
      throw new Exception("Ya existe un profesor con esa id.");

    Profesor prof = new Profesor(nombre, apellido, dni, edad, id, sueldo);

    this.lstProfesores.add(prof);

    return prof;
  }

  public Profesor traerProfesor(int dni) {
    int i = 0;
    Profesor prof = null;

    while (i < this.lstProfesores.size() && prof == null) {
      if (this.lstProfesores.get(i).getDni() == dni)
        prof = this.lstProfesores.get(i);
      i++;
    }

    return prof;
  }

  public Profesor traerProfesorId(int id) {
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
    Profesor prof = this.traerProfesorId(id);

    if (prof == null)
      throw new Exception("No existe ese profesor.");

    this.lstProfesores.remove(id);

    return prof;
  }

  public Socio agregarSocio(String nombre, String apellido, int dni, int edad, double cuota)
      throws Exception {
    Socio repe = this.traerSocio(dni);

    if (repe != null)
      throw new Exception("Ya existe un socio con ese dni.");

    int id = this.lstSocios.size();

    Socio soc = new Socio(nombre, apellido, dni, edad, id, cuota);

    this.lstSocios.add(soc);

    return soc;
  }

  public Socio agregarSocio(int id, String nombre, String apellido, int dni, int edad, double cuota) throws Exception {
    Socio pro = this.traerSocioId(id);

    if (pro != null)
      throw new Exception("Ya existe un profesor con esa id.");

    Socio prof = new Socio(nombre, apellido, dni, edad, id, cuota);

    this.lstSocios.add(prof);

    return prof;
  }

  public Socio traerSocio(int dni) {
    int i = 0;
    Socio soc = null;

    while (i < this.lstSocios.size() && soc == null) {
      if (this.lstSocios.get(i).getDni() == dni)
        soc = this.lstSocios.get(i);
      i++;
    }

    return soc;
  }

  public Socio traerSocioId(int id) {
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
    Socio soc = this.traerSocioId(id);

    if (soc == null)
      throw new Exception("No existe ese socio.");

    for (int i = 0; i < this.lstActividades.size(); i++) {
      Actividad act = this.lstActividades.get(i);
      for (int k = 0; k < act.getLstSocios().size(); k++) {
        Socio s = act.getLstSocios().get(k);
        if (s.getIdCarnetSocio() == id) {
          act.eliminarSocio(id);
        }
      }
    }

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

  @SuppressWarnings("unchecked")
  public static Club recrearClub(Map<String, Object> map) throws Exception {
    if (map.keySet().size() < 6 ||
        map.get("nombre") == null || !(map.get("nombre") instanceof String) ||
        map.get("direccion") == null || !(map.get("direccion") instanceof String) ||
        map.get("lstProfesores") == null || !(map.get("lstProfesores") instanceof List) ||
        map.get("lstSocios") == null || !(map.get("lstSocios") instanceof List) ||
        map.get("lstActividades") == null || !(map.get("lstActividades") instanceof List) ||
        map.get("lstAlquileres") == null || !(map.get("lstAlquileres") instanceof List))
      throw new Exception("Corrupted data.");

    Club club = new Club((String) map.get("nombre"), (String) map.get("direccion"));

    ((List<Map<String, Object>>) map.get("lstProfesores")).forEach(prof -> {
      try {
        if (prof.keySet().size() < 6 ||
            prof.get("nombre") == null || !(prof.get("nombre") instanceof String) ||
            prof.get("apellido") == null || !(prof.get("apellido") instanceof String) ||
            prof.get("edad") == null || !(prof.get("edad") instanceof Integer) ||
            prof.get("dni") == null || !(prof.get("dni") instanceof Integer) ||
            prof.get("idCarnetProfesor") == null || !(prof.get("idCarnetProfesor") instanceof Integer) ||
            prof.get("sueldo") == null || !(prof.get("sueldo") instanceof Double))
          throw new Exception("Corrupted data.");

        club.agregarProfesor((int) prof.get("idCarnetProfesor"), (String) prof.get("nombre"),
            (String) prof.get("apellido"), (int) prof.get("dni"),
            (int) prof.get("edad"), (double) prof.get("sueldo"));
      } catch (Exception e) {
        throw new RuntimeException("Corrupted data.");
      }
    });

    ((List<Map<String, Object>>) map.get("lstSocios")).forEach(soc -> {
      try {
        if (soc.keySet().size() < 6 ||
            soc.get("nombre") == null || !(soc.get("nombre") instanceof String) ||
            soc.get("apellido") == null || !(soc.get("apellido") instanceof String) ||
            soc.get("edad") == null || !(soc.get("edad") instanceof Integer) ||
            soc.get("dni") == null || !(soc.get("dni") instanceof Integer) ||
            soc.get("idCarnetSocio") == null || !(soc.get("idCarnetSocio") instanceof Integer) ||
            soc.get("cuota") == null || !(soc.get("cuota") instanceof Double))
          throw new Exception("Corrupted data.");

        club.agregarSocio((int) soc.get("idCarnetSocio"), (String) soc.get("nombre"), (String) soc.get("apellido"),
            (int) soc.get("dni"),
            (int) soc.get("edad"), (double) soc.get("cuota"));
      } catch (Exception e) {
        throw new RuntimeException("Corrupted data.");
      }
    });

    ((List<Map<String, Object>>) map.get("lstActividades")).forEach(act -> {
      try {
        if (act.keySet().size() < 6 ||
            act.get("idActividad") == null || !(act.get("idActividad") instanceof Integer) ||
            act.get("nombre") == null || !(act.get("nombre") instanceof String) ||
            act.get("cupos") == null || !(act.get("cupos") instanceof Integer) ||
            act.get("lstProfesores") == null || !(act.get("lstProfesores") instanceof List) ||
            act.get("lstSocios") == null || !(act.get("lstSocios") instanceof List) ||
            act.get("diasYhorarios") == null || !(act.get("diasYhorarios") instanceof List))
          throw new Exception("Corrupted data.");

        Actividad act1 = club.agregarActividad((String) act.get("nombre"), (int) act.get("cupos"));
        ((List<Map<String, Object>>) act.get("diasYhorarios")).forEach(dYh -> {
          try {
            if (dYh.keySet().size() < 3 ||
                dYh.get("dia") == null || !(dYh.get("dia") instanceof String) ||
                dYh.get("hora") == null || !(dYh.get("hora") instanceof Integer) ||
                dYh.get("duracion") == null || !(dYh.get("duracion") instanceof Integer))
              throw new Exception("Corrupted data.");

            club.agregarDiaYhorario(act1, (String) dYh.get("dia"), (int) dYh.get("hora"), (int) dYh.get("duracion"));
          } catch (Exception e) {
            throw new RuntimeException("Corrupted data.");
          }
        });
        ((List<Map<String, Object>>) act.get("lstProfesores")).forEach(prof -> {
          try {
            if (prof.keySet().size() < 6 ||
                prof.get("nombre") == null || !(prof.get("nombre") instanceof String) ||
                prof.get("apellido") == null || !(prof.get("apellido") instanceof String) ||
                prof.get("edad") == null || !(prof.get("edad") instanceof Integer) ||
                prof.get("dni") == null || !(prof.get("dni") instanceof Integer) ||
                prof.get("idCarnetProfesor") == null || !(prof.get("idCarnetProfesor") instanceof Integer) ||
                prof.get("sueldo") == null || !(prof.get("sueldo") instanceof Double))
              throw new Exception("Corrupted data.");

            Profesor profe = club.traerProfesorId((int) prof.get("idCarnetProfesor"));
            if (profe == null)
              throw new Exception("Corrupted data.");
            act1.agregarProfesor(profe);
          } catch (Exception e) {
            throw new RuntimeException("Corrupted data.");
          }
        });
        ((List<Map<String, Object>>) act.get("lstSocios")).forEach(soc -> {
          try {
            if (soc.keySet().size() < 6 ||
                soc.get("nombre") == null || !(soc.get("nombre") instanceof String) ||
                soc.get("apellido") == null || !(soc.get("apellido") instanceof String) ||
                soc.get("edad") == null || !(soc.get("edad") instanceof Integer) ||
                soc.get("dni") == null || !(soc.get("dni") instanceof Integer) ||
                soc.get("idCarnetSocio") == null || !(soc.get("idCarnetSocio") instanceof Integer) ||
                soc.get("cuota") == null || !(soc.get("cuota") instanceof Double))
              throw new Exception("Corrupted data.");

            Socio socio = club.traerSocioId((int) soc.get("idCarnetSocio"));
            if (socio == null)
              throw new Exception("Corrupted data.");
            act1.agregarSocio(socio);
          } catch (Exception e) {
            throw new RuntimeException("Corrupted data.");
          }
        });
      } catch (Exception e) {
        throw new RuntimeException("Corrupted data.");
      }
    });

    ((List<Map<String, Object>>) map.get("lstAlquileres")).forEach(alq -> {
      try {
        if (alq.keySet().size() < 5 ||
            alq.get("idAlquiler") == null || !(alq.get("idAlquiler") instanceof Integer) ||
            alq.get("nombre") == null || !(alq.get("nombre") instanceof String) ||
            alq.get("precio") == null || !(alq.get("precio") instanceof Double) ||
            alq.get("alquilado") == null || !(alq.get("alquilado") instanceof Boolean) ||
            alq.get("diaYhorario") == null || !(alq.get("diaYhorario") instanceof Map))
          throw new Exception("Corrupted data.");

        Alquiler alq1 = club.agregarAlquiler((String) alq.get("nombre"), (double) alq.get("precio"));
        alq1.setAlquilado((boolean) alq.get("alquilado"));

        Map<String, Object> dYh = (Map<String, Object>) alq.get("diaYhorario");
        if (dYh.keySet().size() < 3 ||
            dYh.get("dia") == null || !(dYh.get("dia") instanceof String) ||
            dYh.get("hora") == null || !(dYh.get("hora") instanceof Integer) ||
            dYh.get("duracion") == null || !(dYh.get("duracion") instanceof Integer))
          throw new Exception("Corrupted data.");
        club.agregarDiaYhorario(alq1, (String) dYh.get("dia"), (int) dYh.get("hora"), (int) dYh.get("duracion"));
      } catch (Exception e) {
        throw new RuntimeException("Corrupted data.");
      }
    });

    return club;
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
