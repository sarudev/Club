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

    Menu adm_rel_soc = new Menu("Relacionado Socios.");
    this.menus.put("adm_rel_soc", adm_rel_soc);

    Menu adm_rel_prof = new Menu("Relacionado Profesores.");
    this.menus.put("adm_rel_prof", adm_rel_prof);

    Menu adm_rel_act = new Menu("Relacionado Actividades.");
    this.menus.put("adm_rel_act", adm_rel_act);

    Menu adm_rel_alq = new Menu("Relacionado Alquileres.");
    this.menus.put("adm_rel_alq", adm_rel_alq);
  }

  public void main(int top) throws Exception {
    Menu main = this.menus.get("main");
    String reason = "El fichero con los datos del Club estan corrompidos.\nPuede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".";
    if (main.getOptions().isEmpty()) {
      boolean blocked = this.club.getNombre().equalsIgnoreCase("error");
      LambdaOne l = (op) -> {
        System.out.println("no implementado xd");
        Thread.sleep(500);
        System.out.println("Volviendo al menú principal...");
        Thread.sleep(500);
        this.main(3);
      };
      main.addOption("[WIP] Relacionado socios.", blocked, reason, l);
      main.addOption("[WIP] Relacionado Profesores.", blocked, reason, l);
      main.addOption("[WIP] Relacionado Actividades.", blocked, reason, l);
      main.addOption("[WIP] Relacionado Alquileres.", blocked, reason, l);
      main.addOption("[MID] Administracion", (op) -> this.administracion());
      main.addOption("Salir", (op) -> System.out.println("Gracias por usar nuestro programa :)"));
    }
    main.printTitle(this.club.getNombre().equalsIgnoreCase("error") ? 3 : top);
    main.printOptions(2);
    main.selectOption(2);
  }

  private void administracion() throws Exception {
    Menu administracion = this.menus.get("administracion");
    String reason = "El fichero con los datos del Club estan corrompidos.\nPuede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".";
    if (administracion.getOptions().isEmpty()) {
      boolean blocked = this.club.getNombre().equalsIgnoreCase("error");
      administracion.addOption("[MID] Relacionado socios.", blocked, reason, (op) -> adm_rel_soc());
      administracion.addOption("[WIP] Relacionado Profesores.", blocked, reason,
          (op) -> adm_rel_prof());
      administracion.addOption("[WIP] Relacionado Actividades.", blocked, reason,
          (op) -> adm_rel_act());
      administracion.addOption("[WIP] Relacionado Alquileres.", blocked, reason,
          (op) -> adm_rel_alq());
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
      administracion.addOption("Volver atras.", (op) -> main(3));
    }
    administracion.printTitle(2);
    administracion.printOptions(2);
    administracion.selectOption(1);
  }

  private void adm_rel_soc() throws Exception {
    Menu adm_rel_soc = this.menus.get("adm_rel_soc");
    if (adm_rel_soc.getOptions().isEmpty()) {
      adm_rel_soc.addOption("Inscribir Socio.", (op) -> this.inscribirSocio(() -> this.adm_rel_soc()));
      adm_rel_soc.addOption("Traer Socio.", (op) -> {
        String dni = Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos y no estar repetido.",
            (str) -> str.length() == 8);
        System.out.println("\n\nBuscando Socio...");
        Thread.sleep(500);
        Socio soc = club.traerSocio(Integer.parseInt(dni));
        if (soc == null) {
          System.out.println("No existe un socio con ese DNI!");
          Thread.sleep(500);
        } else {
          JSONparser.print(soc.toHashMap(), 0);
          Thread.sleep(1500);
        }
        System.out.println("Volviendo atras...");
        Thread.sleep(500);
        this.adm_rel_soc();
      });
      adm_rel_soc.addOption("[WIP] Traer Socios.", true, "No implementado.", (op) -> {
      });
      adm_rel_soc.addOption("[WIP] Dar de baja Socio.", true, "No implementado.", (op) -> {
      });
      adm_rel_soc.addOption("Volver atras.", (op) -> {
        this.administracion();
      });
    }
    adm_rel_soc.printTitle(2);
    adm_rel_soc.printOptions(2);
    adm_rel_soc.selectOption(1);

  }

  private void inscribirSocio(Lambda back) throws Exception {
    Menu inscribirSocio = this.menus.get("inscribirSocio");
    Map<String, Object> socio = new HashMap<String, Object>();
    if (inscribirSocio.getOptions().isEmpty()) {
      Option pri = inscribirSocio.addOption("Inscribir socio.", true, "Debe completar los datos primero.", (op) -> {
        try {
          System.out.println("Inscribiendo al socio...");
          Thread.sleep(500);
          try {
            club.agregarSocio((String) socio.get("nombre"), (String) socio.get("apellido"), (int) socio.get("dni"),
                (int) socio.get("edad"), 2_000);
            System.out.println("Inscripcion exitosa!");
          } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
          }
          Files.write("data", club.toHashMap());
        } catch (Exception e) {
          System.out.println("Este error no deberia suceder... " + e.getMessage());
        }
        Thread.sleep(500);
        System.out.println("Volviendo atras...");

        Thread.sleep(500);
        op.setBlocked(true);
        back.exec();
      });
      inscribirSocio.addOption("Datos del socio.", (op) -> {
        System.out.println(
            "Por favor ingrese los datos del socio, tenga en cuenta que luego para anotarlo a una actividad necesitara el dni.\n\n");
        String nombre = Files.scan("Nombre? > ", String.class);
        String apellido = Files.scan("Apellido? > ", String.class);
        String dni = Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos y no estar repetido.",
            (str) -> club.traerSocio(Integer.parseInt(str)) == null && str.length() == 8);
        String edad = Files.scan("Edad? > ", Integer.TYPE, "El dato debe ser un numero mayor a 0.",
            (str) -> Integer.parseInt(str) > 0);
        socio.put("nombre", nombre);
        socio.put("apellido", apellido);
        socio.put("dni", Integer.parseInt(dni));
        socio.put("edad", Integer.parseInt(edad));
        pri.setBlocked(false);
        this.inscribirSocio(back);
      });
      inscribirSocio.addOption("Volver atras.", (op) -> {
        pri.setBlocked(true);
        back.exec();
      });
      inscribirSocio.moveOption("Inscribir socio.", 1);
    }
    inscribirSocio.printTitle(4);
    inscribirSocio.printOptions(2);
    inscribirSocio.selectOption(3);
  }

  private void adm_rel_prof() throws Exception {

  }

  private void adm_rel_act() throws Exception {

  }

  private void adm_rel_alq() throws Exception {

  }

  private void recrearFichero() throws Exception {
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
    JSONparser.print(club.toHashMap(), 0);
  }
}
