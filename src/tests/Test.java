package tests;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import helpers.Files;
import helpers.JSONparser;
import helpers.Menu;
import modules.*;

public class Test {

  public static void main(String[] args) throws Exception {
    Club club = null;
    try {
      club = recrearClub();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(
          "Puede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".");
    }
    if (club == null) {
      // crear club con input
    }
    // recrearFichero();
    System.out.println("ready");
    // Menu menu = new Menu("Bienvenido al club " + /* club.getNombre() + */ ".");
    // menu.addOption("Actividad 1");
    // menu.addOption("Actividad 2");
    // menu.addOption("Actividad 3");
    // menu.addOption("Actividad 4");
    // menu.print();
    // int option = menu.selectOption();
    // System.out.println(option);
    // recrearFichero();
  }

  private static void recrearFichero() throws Exception {
    Club club = new Club("Barrio feliz", "Victor Hugo 1200");
    Socio soc_gonza = club.agregarSocio("Gonzalo", "Vedia", 44394976, 20, 2_000);
    Socio soc_sergio = club.agregarSocio("Sergio", "Cadima", 41546475, 23, 2_000);
    Socio soc_seba = club.agregarSocio("Sebastian", "Bozza", 44708641, 19, 2_000);
    Profesor prof_jose = club.agregarProfesor("Jose", "Coria", 44880935, 19, 80_000);
    Profesor prof_alejo = club.agregarProfesor("Alejo", "Aeraki", 42231718, 23, 80_000);
    Profesor prof_rodri = club.agregarProfesor("Rodrigo", "Brito", 43390009, 21, 80_000);
    Actividad volley = club.agregarActividad("Volley", 8);
    club.agregarDiaYhorario(volley, "martes", 8, 2);
    club.agregarDiaYhorario(volley, "jueves", 8, 2);
    volley.agregarProfesor(prof_jose);
    volley.agregarSocio(soc_gonza);
    volley.agregarSocio(soc_sergio);
    Actividad basket = club.agregarActividad("Basket", 10);
    club.agregarDiaYhorario(basket, "lunes", 8, 2);
    club.agregarDiaYhorario(basket, "miercoles", 8, 2);
    basket.agregarProfesor(prof_alejo);
    basket.agregarSocio(soc_sergio);
    basket.agregarSocio(soc_seba);
    Actividad futsal = club.agregarActividad("Futsal", 13);
    club.agregarDiaYhorario(futsal, "martes", 16, 2);
    club.agregarDiaYhorario(futsal, "jueves", 16, 2);
    futsal.agregarProfesor(prof_rodri);
    futsal.agregarSocio(soc_seba);
    futsal.agregarSocio(soc_gonza);
    Actividad handball = club.agregarActividad("Handball", 12);
    club.agregarDiaYhorario(handball, "viernes", 8, 2);
    Alquiler fiestas = club.agregarAlquiler("Fiestas", 50_000);
    fiestas.setAlquilado(true);
    club.agregarDiaYhorario(fiestas, "viernes", 8, 2);
    Alquiler eventos = club.agregarAlquiler("Eventos", 70_000);
    club.agregarDiaYhorario(eventos, "sabado", 8, 2);

    Files.write("data", club.toHashMap());
  }

  private static Club recrearClub() throws Exception {
    String json = null;
    try {
      json = Files.read("data");
    } catch (Exception e) {
      throw new Exception("Fichero no encontrado.");
    }
    Map<String, Object> map = null;

    try {
      map = JSONparser.stringToHashMap(json);
    } catch (Exception e) {
      throw new Exception("Corrupted data.");
    }

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

        club.agregarProfesor((String) prof.get("nombre"), (String) prof.get("apellido"), (int) prof.get("dni"),
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

        club.agregarSocio((String) soc.get("nombre"), (String) soc.get("apellido"), (int) soc.get("dni"),
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

            Profesor profe = club.traerProfesor((int) prof.get("idCarnetProfesor"));
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

            Socio socio = club.traerSocio((int) soc.get("idCarnetSocio"));
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

    JSONparser.print(club.toHashMap(), 0);

    return null;
  }
}
