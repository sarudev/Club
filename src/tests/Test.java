package tests;

import java.util.Map;

import helpers.Files;
import helpers.JSONparser;
import helpers.Lambda;
import helpers.Menu;
import modules.*;

public class Test {

  public static void main(String[] args) throws Exception {
    Club club = build();

    Menu menu = new Menu("Bienvenido al club " + club.getNombre() + ".");
    menu.addOption("Inscribir socio.", () -> System.out.println("hola"));
    // menu.addOption("Agregar socio a actividad.");
    // menu.addOption("Actividades.");
    // menu.addOption("Alquileres.");
    // menu.addOption("Administracion");
    menu.print(3);
    menu.selectOption(5);
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

  private static Club build() {
    Club club = null;
    try {
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

      club = Club.recrearClub(map);
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
      System.out.println(
          "Puede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".");
    }
    if (club == null) {
      try {
        club = new Club("ERROR", "ERROR");
      } catch (Exception e) {
        System.out.println("Este error no debería suceder...\n" + e.getMessage());
      }
    }

    return club;
  }
}
