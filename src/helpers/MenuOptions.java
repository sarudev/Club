package helpers;

import java.util.HashMap;
import java.util.Map;

import modules.Actividad;
import modules.Alquiler;
import modules.Club;
import modules.Profesor;
import modules.Socio;

public class MenuOptions {
  private Club club;
  private Map<String, Menu> menus;

  public MenuOptions(Club club) {
    this.club = club;
    this.menus = new HashMap<String, Menu>();
    Menu main = new Menu("Bienvenido al club " + club.getNombre() + ".");
    this.menus.put("main", main);
    Menu inscribirSocio = new Menu("Inscribir socio.");
    this.menus.put("inscribirSocio", inscribirSocio);
  }

  public void main() throws Exception {
    Menu main = this.menus.get("main");
    main.addOption("Inscribir socio.", "", () -> this.inscribirSocio());
    // menu.addOption("Agregar socio a actividad.");
    // menu.addOption("Actividades.");
    // menu.addOption("Alquileres.");
    // menu.addOption("Administracion");
    main.printTitle(3);
    main.printOptions(2);
    main.selectOption(0);
  }

  public void inscribirSocio() throws Exception {
    // System.out.println("a");
    Menu inscribirSocio = this.menus.get("inscribirSocio");
    Map<String, Object> socio = new HashMap<String, Object>();
    inscribirSocio.addOption("Datos del socio.", "", () -> {
      String nombre = Files.scanner("Nombre? >");
      String apellido = Files.scanner("Apellido? >");
      int dni = Integer.parseInt(Files.scanner("DNI? >"));
      int edad = Integer.parseInt(Files.scanner("Edad? >"));
      System.out.println(nombre);
      System.out.println(apellido);
      System.out.println(dni);
      System.out.println(edad);
    });
    inscribirSocio.printTitle(1);
    inscribirSocio.printOptions(2);
    inscribirSocio.selectOption(0);
    System.out.println("b");

  }

  public void agregarSocioActividad() throws Exception {

  }

  public void actividades() throws Exception {

  }

  public void alquileres() throws Exception {

  }

  public void recrearFichero() throws Exception {
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
}
