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

  public MenuOptions(Club club) throws Exception {
    this.club = club;
    this.menus = new HashMap<String, Menu>();
    Menu main = new Menu("Bienvenido al club " + club.getNombre() + ".");
    this.menus.put("main", main);
    Menu inscribirSocio = new Menu("Inscribir socio.");
    this.menus.put("inscribirSocio", inscribirSocio);
    Menu administracion = new Menu("Administracion.");
    this.menus.put("administracion", administracion);
  }

  public void main(int top) throws Exception {
    Menu main = this.menus.get("main");
    String reason = "El fichero con los datos del Club están corrompidos.\nPuede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".";
    if (main.getOptions().isEmpty()) {
      boolean blocked = this.club.getNombre().equalsIgnoreCase("error");
      Lambda l = (op) -> {
        System.out.println("no implementado xd");
        Thread.sleep(500);
        System.out.println("Volviendo al menú principal...");
        Thread.sleep(500);
        this.main(3);
      };
      main.addOption("Inscribir socio.", blocked, reason, (op) -> this.inscribirSocio());
      main.addOption("[WIP] Agregar socio a actividad.", blocked, reason, l);
      main.addOption("[WIP] Actividades.", blocked, reason, l);
      main.addOption("[WIP] Alquileres.", blocked, reason, l);
      main.addOption("Administracion", (op) -> this.administracion());
      main.addOption("Salir", (op) -> System.out.println("Gracias por usar nuestro programa :)"));
    }
    main.printTitle(this.club.getNombre().equalsIgnoreCase("error") ? 3 : top);
    main.printOptions(2);
    main.selectOption(2);
  }

  public void inscribirSocio() throws Exception {
    Menu inscribirSocio = this.menus.get("inscribirSocio");
    Map<String, Object> socio = new HashMap<String, Object>();
    if (inscribirSocio.getOptions().isEmpty()) {
      Option pri = inscribirSocio.addOption("Inscribir socio.", true, "Debe completar los datos primero.", (op) -> {
        try {
          System.out.println("Inscribiendo al socio...");
          Thread.sleep(500);
          club.agregarSocio((String) socio.get("nombre"), (String) socio.get("apellido"), (int) socio.get("dni"),
              (int) socio.get("edad"), 2_000);
          Files.write("data", club.toHashMap());
        } catch (Exception e) {
          System.out.println("Este error no deberia suceder... " + e.getMessage());
        } finally {
          System.out.println("Inscripcion exitosa!");
        }
        Thread.sleep(500);
        System.out.println("Volviendo al menu principal...");

        Thread.sleep(500);
        op.setBlocked(true);
        this.main(5);
      });
      inscribirSocio.addOption("Datos del socio.", (op) -> {
        System.out.println(
            "Por favor ingrese los datos del socio, tenga en cuenta que luego para anotarlo a una actividad necesitará el dni.\n\n");
        String nombre = Files.scan("Nombre? > ", String.class);
        String apellido = Files.scan("Apellido? > ", String.class);
        int dni = Integer.parseInt(Files.scan("DNI? > ", Integer.TYPE));
        int edad = Integer.parseInt(Files.scan("Edad? > ", Integer.TYPE));
        socio.put("nombre", nombre);
        socio.put("apellido", apellido);
        socio.put("dni", dni);
        socio.put("edad", edad);
        pri.setBlocked(false);
        this.inscribirSocio();
      });
      inscribirSocio.addOption("Volver atrás.", (op) -> {
        pri.setBlocked(true);
        this.main(3);
      });
      inscribirSocio.moveOption("Inscribir socio.", 1);
    }
    inscribirSocio.printTitle(4);
    inscribirSocio.printOptions(2);
    inscribirSocio.selectOption(3);
  }

  public void agregarSocioActividad() throws Exception {

  }

  public void actividades() throws Exception {

  }

  public void alquileres() throws Exception {

  }

  public void administracion() throws Exception {
    Menu administracion = this.menus.get("administracion");
    String reason = "El fichero con los datos del Club están corrompidos.\nPuede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".";
    if (administracion.getOptions().isEmpty()) {
      boolean blocked = this.club.getNombre().equalsIgnoreCase("error");
      Lambda l = (op) -> {
        System.out.println("no implementado xd");
        Thread.sleep(500);
        System.out.println("Volviendo al menú administracion...");
        Thread.sleep(500);
        this.administracion();
      };
      administracion.addOption("[WIP] Relacionado socios.", blocked, reason, l);
      administracion.addOption("[WIP] Relacionado Profesores.", blocked, reason, l);
      administracion.addOption("[WIP] Relacionado Actividades.", blocked, reason, l);
      administracion.addOption("[WIP] Relacionado Alquileres.", blocked, reason, l);
      administracion.addOption("Recrear Fichero.", (op) -> {
        System.out.println("Recreando fichero...");
        Thread.sleep(500);
        try {
          recrearFichero();
        } catch (Exception e) {
          System.out.println("Esto no deberia haber sucedido... " + e.getMessage());
        } finally {
          System.out.println("\nFichero recreado.\nPor favor reinicie el programa.");
        }
      });
      // volver atras
      administracion.addOption("Volver atrás.", (op) -> main(3));
    }
    administracion.printTitle(2);
    administracion.printOptions(2);
    administracion.selectOption(1);
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
