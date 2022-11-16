package helpers;

import java.util.HashMap;
import java.util.Map;

import modules.Actividad;
import modules.Alquiler;
import modules.Club;
import modules.Persona;
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

    Menu main_rel_soc = new Menu("Relacionado Socios.");
    this.menus.put("main_rel_soc", main_rel_soc);

    Menu main_rel_prof = new Menu("Relacionado Profesores.");
    this.menus.put("main_rel_prof", main_rel_prof);

    Menu inscribir = new Menu("Inscribir [type].");
    this.menus.put("inscribir", inscribir);

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

  public void main() throws Exception {
    Menu main = this.menus.get("main");
    String reason = "El fichero con los datos del Club estan corrompidos.\nPuede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".";
    if (main.getOptions().isEmpty()) {
      boolean blocked = this.club.getNombre().equalsIgnoreCase("error");
      LambdaOne l = (op) -> {
        System.out.println("no implementado xd");
        Thread.sleep(500);
        System.out.println("Volviendo al menú principal...");
        Thread.sleep(500);
        this.main();
      };
      main.addOption("Relacionado socios.", blocked, reason, (op) -> this.main_rel_soc());
      main.addOption("Relacionado Profesores.", blocked, reason, (op) -> this.main_rel_prof());
      main.addOption("[WIP] Relacionado Actividades.", blocked, reason, l);
      main.addOption("[WIP] Relacionado Alquileres.", blocked, reason, l);
      main.addOption("[MID] Administracion", (op) -> this.administracion());
      main.addOption("Salir", (op) -> System.out.println("Gracias por usar nuestro programa :)"));
    }
    main.printTitle(this.club.getNombre().equalsIgnoreCase("error") ? 3 : 0);
    main.printOptions(2);
    main.selectOption(1);
  }

  private void main_rel_soc() throws Exception {
    Menu main_rel_soc = this.menus.get("main_rel_soc");
    if (main_rel_soc.getOptions().isEmpty()) {
      main_rel_soc.addOption("Inscribir Socio.", (op) -> this.inscribir(() -> this.adm_rel_soc(), "socio", true));
      main_rel_soc.addOption("Dar de baja Socio.", (op) -> this.baja(() -> this.adm_rel_soc(), "socio"));
      main_rel_soc.addOption("Volver atras.", (op) -> this.main());
    }
    main_rel_soc.printTitle(0);
    main_rel_soc.printOptions(2);
    main_rel_soc.selectOption(1);
  }

  private void main_rel_prof() throws Exception {
    Menu main_rel_prof = this.menus.get("main_rel_prof");
    if (main_rel_prof.getOptions().isEmpty()) {
      main_rel_prof.addOption("Traer Profesor.", (op) -> this.traer(() -> this.main_rel_prof(), "profesor"));
      main_rel_prof.addOption("Traer Profesores.", (op) -> this.traers(() -> this.main_rel_prof(), "profesor"));
      main_rel_prof.addOption("Volver atras.", (op) -> this.main());
    }
    main_rel_prof.printTitle(0);
    main_rel_prof.printOptions(2);
    main_rel_prof.selectOption(1);
  }

  private void administracion() throws Exception {
    Menu administracion = this.menus.get("administracion");
    String reason = "El fichero con los datos del Club estan corrompidos.\nPuede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".";
    if (administracion.getOptions().isEmpty()) {
      boolean blocked = this.club.getNombre().equalsIgnoreCase("error");
      administracion.addOption("Relacionado socios.", blocked, reason, (op) -> adm_rel_soc());
      administracion.addOption("Relacionado Profesores.", blocked, reason,
          (op) -> adm_rel_prof());
      administracion.addOption("[WIP] Relacionado Actividades.", blocked, reason,
          (op) -> adm_rel_act());
      administracion.addOption("[WIP] Relacionado Alquileres.", blocked, reason,
          (op) -> adm_rel_alq());
      administracion.addOption("Recrear Fichero.", (op) -> {
        System.out.println("=".repeat(50));
        System.out.println("Recreando fichero...");
        Thread.sleep(500);
        try {
          recrearFichero();
        } catch (Exception e) {
          System.out.println("Esto no deberia haber sucedido... " + e.getMessage());
        } finally {
          System.out.println("\nFichero recreado.\nPor favor reinicie el programa.");
        }
        System.out.println("=".repeat(50));
      });
      // volver atras
      administracion.addOption("Volver atras.", (op) -> main());
    }
    administracion.printTitle(0);
    administracion.printOptions(2);
    administracion.selectOption(1);
  }

  private void adm_rel_soc() throws Exception {
    Menu adm_rel_soc = this.menus.get("adm_rel_soc");
    if (adm_rel_soc.getOptions().isEmpty()) {
      adm_rel_soc.addOption("Inscribir Socio.", (op) -> this.inscribir(() -> this.adm_rel_soc(), "socio", true));
      adm_rel_soc.addOption("Traer Socio.", (op) -> this.traer(() -> this.adm_rel_soc(), "socio"));
      adm_rel_soc.addOption("Traer Socios.", (op) -> this.traers(() -> this.adm_rel_soc(), "socio"));
      adm_rel_soc.addOption("Dar de baja Socio.", (op) -> this.baja(() -> this.adm_rel_soc(), "socio"));
      adm_rel_soc.addOption("Volver atras.", (op) -> this.administracion());
    }
    adm_rel_soc.printTitle(0);
    adm_rel_soc.printOptions(2);
    adm_rel_soc.selectOption(1);
  }

  private void adm_rel_prof() throws Exception {
    Menu adm_rel_prof = this.menus.get("adm_rel_prof");
    if (adm_rel_prof.getOptions().isEmpty()) {
      adm_rel_prof.addOption("Inscribir Profesor.",
          (op) -> this.inscribir(() -> this.adm_rel_prof(), "profesor", true));
      adm_rel_prof.addOption("Traer Profesor.", (op) -> this.traer(() -> this.adm_rel_prof(), "profesor"));
      adm_rel_prof.addOption("Traer Profesores.", (op) -> this.traers(() -> this.adm_rel_prof(), "profesor"));
      adm_rel_prof.addOption("Dar de baja Profesor.", (op) -> this.baja(() -> this.adm_rel_prof(), "profesor"));
      adm_rel_prof.addOption("Volver atras.", (op) -> this.administracion());
    }
    adm_rel_prof.printTitle(0);
    adm_rel_prof.printOptions(2);
    adm_rel_prof.selectOption(1);
  }

  private void inscribir(Lambda back, String type, boolean clear) throws Exception {
    Menu inscribir = this.menus.get("inscribir");
    inscribir.setTitle(inscribir.getTitle().replace("[type]", type));
    Map<String, Object> obj = new HashMap<String, Object>();
    if (clear) {
      inscribir.getOptions().clear();
      Option pri = inscribir.addOption("Inscribir " + type + ".", true, "Debe completar los datos primero.",
          (op) -> {
            System.out.println("=".repeat(50));
            try {
              System.out.println("Inscribiendo al " + type + "...");
              Thread.sleep(500);
              try {
                if (type.equalsIgnoreCase("profesor")) {
                  club.agregarProfesor((String) obj.get("nombre"), (String) obj.get("apellido"), (int) obj.get("dni"),
                      (int) obj.get("edad"), 80_000);
                } else if (type.equalsIgnoreCase("socio")) {
                  club.agregarSocio((String) obj.get("nombre"), (String) obj.get("apellido"), (int) obj.get("dni"),
                      (int) obj.get("edad"), 2_000);
                } else {
                  throw new Exception("INVALID!!!!");
                }
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
            System.out.println("=".repeat(50));

            Thread.sleep(500);
            op.setBlocked(true);
            inscribir.setDescription("");
            back.exec();
          });
      inscribir.addOption("Ingresar datos del " + type + ".", (op) -> {
        System.out.println("=".repeat(50));
        String explain = "Por favor ingrese los datos del " + type;
        explain += type.equalsIgnoreCase("socio")
            ? ", tenga en cuenta que luego para anotarlo a una actividad necesitara el dni.\n"
            : ".\n";
        System.out.println(explain);
        String nombre = Files.scan("Nombre? > ", String.class);
        String apellido = Files.scan("Apellido? > ", String.class);
        String dni = Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos y no estar repetido.",
            (str) -> club.traerSocio(Integer.parseInt(str)) == null && str.length() == 8);
        String edad = Files.scan("Edad? > ", Integer.TYPE, "El dato debe ser un numero mayor a 0.",
            (str) -> Integer.parseInt(str) > 0);
        obj.put("nombre", nombre);
        obj.put("apellido", apellido);
        obj.put("dni", Integer.parseInt(dni));
        obj.put("edad", Integer.parseInt(edad));
        obj.put("persona", new Persona(nombre, apellido, Integer.parseInt(dni), Integer.parseInt(edad)));
        inscribir.setDescription(JSONparser.print(((Persona) obj.get("persona")).toHashMap(), 0));
        pri.setBlocked(false);
        System.out.println(JSONparser.print(inscribir.toHashMap(), 0));
        System.out.println("=".repeat(50));
        this.inscribir(back, type, false);
      });
      inscribir.addOption("Volver atras.", (op) -> {
        pri.setBlocked(true);
        inscribir.setDescription("");
        back.exec();
      });
    }
    inscribir.moveOption("Inscribir " + type + ".", 1);
    inscribir.printTitle(0);
    inscribir.printDescription(1);
    inscribir.printOptions(2);
    inscribir.selectOption(1);
  }

  private void traer(Lambda back, String type) throws Exception {
    System.out.println("=".repeat(50));
    String dni = Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos y no estar repetido.",
        (str) -> str.length() == 8);
    System.out.println("\n\nBuscando " + type + "...");
    Thread.sleep(500);
    Persona soc = null;
    if (type.equalsIgnoreCase("profesor")) {
      soc = club.traerProfesor(Integer.parseInt(dni));
    } else if (type.equalsIgnoreCase("socio")) {
      soc = club.traerSocio(Integer.parseInt(dni));
    } else {
      throw new Exception("INVALID!!!!");
    }

    if (soc == null) {
      System.out.println("No existe un " + type + " con ese DNI!");
      Thread.sleep(500);
    } else {
      System.out.println(JSONparser.print(soc.toHashMap(), 0));
      Thread.sleep(1500);
    }
    System.out.println("Volviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(500);
    back.exec();
  }

  private void traers(Lambda back, String type) throws Exception {
    System.out.println("=".repeat(50));
    System.out.println("Lista de " + type + (type.equalsIgnoreCase("socio") ? "s" : "es") + ":\n");
    if (type.equalsIgnoreCase("profesor")) {
      System.out.println(JSONparser.print(club.toHashMap().get("lstProfesores"), 0));
    } else if (type.equalsIgnoreCase("socio")) {
      System.out.println(JSONparser.print(club.toHashMap().get("lstSocios"), 0));
    } else {
      throw new Exception("INVALID!!!!");
    }
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(2500);
    back.exec();
  }

  private void baja(Lambda back, String type) throws Exception {
    System.out.println("=".repeat(50));
    String dni = Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos y no estar repetido.",
        (str) -> str.length() == 8);
    if (type.equalsIgnoreCase("profesor")) {
      Profesor prof = club.traerProfesor(Integer.parseInt(dni));
      if (prof == null)
        System.out.println("\nNo existe un " + type + " con ese DNI!");
      else {
        try {
          System.out.println("\nIntentando eliminar " + type + "...");
          club.eliminarProfesor(prof.getIdCarnetProfesor());
          Files.write("data", club.toHashMap());
        } catch (Exception e) {
          System.out.println("Este error no debería suceder... " + e.getMessage());
        } finally {
          Thread.sleep(500);
          System.out
              .println("\n" + type.replace(String.valueOf(type.charAt(0)), String.valueOf(type.charAt(0)).toUpperCase())
                  + " eliminado correctamente.");
        }
      }
    } else if (type.equalsIgnoreCase("socio")) {
      Socio socio = club.traerSocio(Integer.parseInt(dni));
      if (socio == null)
        System.out.println("\nNo existe un socio con ese DNI!");
      else {
        try {
          System.out.println("\nIntentando eliminar socio...");
          club.eliminarSocio(socio.getIdCarnetSocio());
          Files.write("data", club.toHashMap());
        } catch (Exception e) {
          System.out.println("Este error no debería suceder... " + e.getMessage());
        } finally {
          Thread.sleep(500);
          System.out.println("\nSocio eliminado correctamente.");
        }
      }
    } else {
      throw new Exception("INVALID!!!!");
    }
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(2500);
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
    System.out.println(JSONparser.print(club.toHashMap(), 0));
  }
}
