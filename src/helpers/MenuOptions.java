package helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    Menu main = new Menu("Bienvenido al club " + this.club.getNombre() + ".");
    this.menus.put("main", main);

    Menu main_rel_soc = new Menu("Relacionado Socios.");
    this.menus.put("main_rel_soc", main_rel_soc);

    Menu main_rel_prof = new Menu("Relacionado Profesores.");
    this.menus.put("main_rel_prof", main_rel_prof);

    Menu adm_rel_act = new Menu("Relacionado Actividades.");
    this.menus.put("adm_rel_act", adm_rel_act);

    Menu adm_rel_alq = new Menu("Relacionado Alquileres.");
    this.menus.put("adm_rel_alq", adm_rel_alq);

    Menu administracion = new Menu("Administracion.");
    this.menus.put("administracion", administracion);

    Menu adm_rel_soc = new Menu("Relacionado Socios.");
    this.menus.put("adm_rel_soc", adm_rel_soc);

    Menu adm_rel_prof = new Menu("Relacionado Profesores.");
    this.menus.put("adm_rel_prof", adm_rel_prof);

    Menu inscribir = new Menu("Inscribir [type].");
    this.menus.put("inscribir", inscribir);

    Menu main_rel_act = new Menu("Relacionado Actividades.");
    this.menus.put("main_rel_act", main_rel_act);

    Menu main_rel_alq = new Menu("Relacionado Alquileres.");
    this.menus.put("main_rel_alq", main_rel_alq);
  }

  public void main() throws Exception {
    Menu main = this.menus.get("main");
    String reason = "El fichero con los datos del Club estan corrompidos.\nPuede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero con datos por defecto\" en \"Administracion\".";
    if (main.getOptions().isEmpty()) {
      boolean blocked = this.club.getNombre().equalsIgnoreCase("error");
      main.addOption("Relacionado socios.", blocked, reason, (op) -> this.main_rel_soc());
      main.addOption("Relacionado Profesores.", blocked, reason, (op) -> this.main_rel_prof());
      main.addOption("Relacionado Actividades.", blocked, reason, (op) -> this.main_rel_act());
      main.addOption("Relacionado Alquileres.", blocked, reason, (op) -> this.main_rel_alq());
      main.addOption("Administracion", (op) -> this.administracion());
      main.addOption("Salir", (op) -> System.out.println("Gracias por usar nuestro programa :)"));
    }
    main.printTitle(this.club.getNombre().equalsIgnoreCase("error") ? 3 : 0);
    main.printOptions(2);
    main.selectOption(1);
  }

  private void main_rel_soc() throws Exception {
    Menu main_rel_soc = this.menus.get("main_rel_soc");
    if (main_rel_soc.getOptions().isEmpty()) {
      main_rel_soc.addOption("Inscribir Socio.", (op) -> this.inscribir(() -> this.main_rel_soc(), "socio", true));
      main_rel_soc.addOption("Agregar socio a una actividad.",
          (op) -> this.add_soc_prof_act(() -> this.main_rel_soc(), "socio"));
      main_rel_soc.addOption("Actividades en las que este un socio.",
          (op) -> this.soc_prof_in_act(() -> this.main_rel_soc(), "socio"));
      main_rel_soc.addOption("Eliminar socio de una actividad",
          (op) -> this.del_soc_prof_act(() -> this.main_rel_soc(), "socio"));
      main_rel_soc.addOption("Dar de baja Socio.", (op) -> this.baja(() -> this.main_rel_soc(), "socio"));
      main_rel_soc.addOption("Volver atras.", (op) -> this.main());
    }
    main_rel_soc.printTitle(0);
    main_rel_soc.printOptions(2);
    main_rel_soc.selectOption(1);
  }

  private void main_rel_prof() throws Exception {
    Menu main_rel_prof = this.menus.get("main_rel_prof");
    if (main_rel_prof.getOptions().isEmpty()) {
      main_rel_prof.addOption("Traer Profesor.", (op) -> this.show_soc_prof(() -> this.main_rel_prof(), "profesor"));
      main_rel_prof.addOption("Traer Profesores.",
          (op) -> this.show_socs_profs(() -> this.main_rel_prof(), "profesor"));
      main_rel_prof.addOption("Actividades en las que este un profesor.",
          (op) -> this.soc_prof_in_act(() -> this.main_rel_prof(), "profesor"));
      main_rel_prof.addOption("Volver atras.", (op) -> this.main());
    }
    main_rel_prof.printTitle(0);
    main_rel_prof.printOptions(2);
    main_rel_prof.selectOption(1);
  }

  private void main_rel_act() throws Exception {
    Menu main_rel_act = this.menus.get("main_rel_act");
    if (main_rel_act.getOptions().isEmpty()) {
      main_rel_act.addOption("Traer actividad.", (op) -> this.show_act(() -> this.main_rel_act()));
      main_rel_act.addOption("Traer actividades.", (op) -> this.show_acts(() -> this.main_rel_act()));
      main_rel_act.addOption("Agregar socio a una actividad.",
          (op) -> this.add_soc_prof_act(() -> this.main_rel_act(), "socio"));
      main_rel_act.addOption("Actividades en las que este un socio.",
          (op) -> this.soc_prof_in_act(() -> this.main_rel_act(), "socio"));
      main_rel_act.addOption("Actividades en las que este un profesor.",
          (op) -> this.soc_prof_in_act(() -> this.main_rel_act(), "profesor"));
      main_rel_act.addOption("Volver atras.", (op) -> this.main());
    }
    main_rel_act.printTitle(0);
    main_rel_act.printOptions(2);
    main_rel_act.selectOption(0);
  }

  private void main_rel_alq() throws Exception {
    Menu main_rel_alq = this.menus.get("main_rel_alq");
    if (main_rel_alq.getOptions().isEmpty()) {
      main_rel_alq.addOption("Traer alquiler.", (op) -> this.show_alq(() -> this.main_rel_alq()));
      main_rel_alq.addOption("Traer alquileres.", (op) -> this.show_alqs(() -> this.main_rel_alq()));
      main_rel_alq.addOption("Alquilar.", (op) -> this.alquilar(() -> this.main_rel_alq()));
      main_rel_alq.addOption("Volver atras.", op -> this.main());
    }
    main_rel_alq.printTitle(0);
    main_rel_alq.printOptions(2);
    main_rel_alq.selectOption(0);
  }

  private void administracion() throws Exception {
    Menu administracion = this.menus.get("administracion");
    String reason = "El fichero con los datos del Club estan corrompidos.\nPuede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero con datos por defecto\" en \"Administracion\".";
    if (administracion.getOptions().isEmpty()) {
      boolean blocked = this.club.getNombre().equalsIgnoreCase("error");
      administracion.addOption("Relacionado socios.", blocked, reason, (op) -> adm_rel_soc());
      administracion.addOption("Relacionado Profesores.", blocked, reason,
          (op) -> adm_rel_prof());
      administracion.addOption("Relacionado Actividades.", blocked, reason,
          (op) -> adm_rel_act());
      administracion.addOption("Relacionado Alquileres.", blocked, reason,
          (op) -> adm_rel_alq());
      administracion.addOption("Recrear Fichero con datos por defecto.", (op) -> {
        System.out.println("=".repeat(50));
        System.out.println("Recreando fichero...");
        Thread.sleep(500);
        try {
          recrearFicheroDefault();
          System.out.println("\nFichero recreado.\nPor favor reinicie el programa.");
        } catch (Exception e) {
          System.out.println("Esto no deberia haber sucedido... " + e.getMessage());
        }
        System.out.println("=".repeat(50));
      });
      administracion.addOption("Recrear Fichero con datos vacios.", (op) -> {
        System.out.println("=".repeat(50));
        System.out.println("Recreando fichero...");
        Thread.sleep(500);
        try {
          recrearFicheroVacio();
          System.out.println("\nFichero recreado.\nPor favor reinicie el programa.");
        } catch (Exception e) {
          System.out.println("Esto no deberia haber sucedido... " + e.getMessage());
        }
        System.out.println("=".repeat(50));
      });
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
      adm_rel_soc.addOption("Traer Socio.", (op) -> this.show_soc_prof(() -> this.adm_rel_soc(), "socio"));
      adm_rel_soc.addOption("Traer Socios.", (op) -> this.show_socs_profs(() -> this.adm_rel_soc(), "socio"));
      adm_rel_soc.addOption("Agregar socio a una actividad.",
          (op) -> this.add_soc_prof_act(() -> this.adm_rel_soc(), "socio"));
      adm_rel_soc.addOption("Actividades en las que este un socio.",
          (op) -> this.soc_prof_in_act(() -> this.adm_rel_soc(), "socio"));
      adm_rel_soc.addOption("Eliminar socio de una actividad",
          (op) -> this.del_soc_prof_act(() -> this.adm_rel_soc(), "socio"));
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
      adm_rel_prof.addOption("Traer Profesor.", (op) -> this.show_soc_prof(() -> this.adm_rel_prof(), "profesor"));
      adm_rel_prof.addOption("Traer Profesores.", (op) -> this.show_socs_profs(() -> this.adm_rel_prof(), "profesor"));
      adm_rel_prof.addOption("Agregar profesor a una actividad.",
          (op) -> this.add_soc_prof_act(() -> this.adm_rel_prof(), "profesor"));
      adm_rel_prof.addOption("Actividades en las que este un profesor.",
          (op) -> this.soc_prof_in_act(() -> this.adm_rel_prof(), "profesor"));
      adm_rel_prof.addOption("Eliminar profesor de una actividad",
          (op) -> this.del_soc_prof_act(() -> this.adm_rel_prof(), "profesor"));
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
                  this.club.agregarProfesor((String) obj.get("nombre"), (String) obj.get("apellido"),
                      (int) obj.get("dni"),
                      (int) obj.get("edad"), 80_000);
                } else if (type.equalsIgnoreCase("socio")) {
                  this.club.agregarSocio((String) obj.get("nombre"), (String) obj.get("apellido"), (int) obj.get("dni"),
                      (int) obj.get("edad"), 2_000);
                } else {
                  throw new Exception("INVALID!!!!");
                }
                System.out.println("Inscripcion exitosa!");
              } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Este error no deberia suceder... " + e.getMessage());
              }
              Files.write("data", this.club.toHashMap());
            } catch (Exception e) {
              e.printStackTrace();
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
        String dni = Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos.",
            (str) -> str.length() == 8);
        if ((type.equalsIgnoreCase("profesor") && this.club.traerProfesor(Integer.parseInt(dni)) != null)
            || (type.equalsIgnoreCase("socio") && this.club.traerSocio(Integer.parseInt(dni)) != null)) {
          System.out.println("Ya existe un " + type + " con ese dni.");
        } else {
          String edad = Files.scan("Edad? > ", Integer.TYPE, "El dato debe ser un numero mayor a 0.",
              (str) -> Integer.parseInt(str) > 0);
          obj.put("nombre", nombre);
          obj.put("apellido", apellido);
          obj.put("dni", Integer.parseInt(dni));
          obj.put("edad", Integer.parseInt(edad));
          obj.put("persona", new Persona(nombre, apellido, Integer.parseInt(dni), Integer.parseInt(edad)));
          String text = JSONparser.print(((Persona) obj.get("persona")).toHashMap(), 0);
          inscribir.setDescription(text.substring(0, text.length() - 1));
          pri.setBlocked(false);
        }
        Thread.sleep(750);
        System.out.println("\nVolviendo atras...");
        System.out.println("=".repeat(50));
        Thread.sleep(750);
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

  private void show_soc_prof(Lambda back, String type) throws Exception {
    System.out.println("=".repeat(50));
    String dni = Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos.",
        (str) -> str.length() == 8);
    System.out.println("\n\nBuscando " + type + "...");
    Thread.sleep(500);
    Persona soc = null;
    if (type.equalsIgnoreCase("profesor")) {
      soc = this.club.traerProfesor(Integer.parseInt(dni));
    } else if (type.equalsIgnoreCase("socio")) {
      soc = this.club.traerSocio(Integer.parseInt(dni));
    } else {
      throw new Exception("INVALID!!!!");
    }

    if (soc == null) {
      System.out.println("No existe un " + type + " con ese DNI!");
      Thread.sleep(500);
    } else {
      String text = JSONparser.print(soc.toHashMap(), 0);
      System.out.println(text.substring(0, text.length() - 1));
      Thread.sleep(1500);
    }
    System.out.println("Volviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(500);
    back.exec();
  }

  private void show_socs_profs(Lambda back, String type) throws Exception {
    System.out.println("=".repeat(50));
    System.out.println("Lista de " + type + (type.equalsIgnoreCase("socio") ? "s" : "es") + ":\n");
    if (type.equalsIgnoreCase("profesor")) {
      String text = JSONparser.print(this.club.toHashMap().get("lstProfesores"), 0);
      if (this.club.getLstProfesores().isEmpty()) {
        System.out.println("No hay profesores para mostrar...");
      } else {
        System.out.println(text.substring(0, text.length() - 1));
      }
    } else if (type.equalsIgnoreCase("socio")) {
      String text = JSONparser.print(this.club.toHashMap().get("lstSocios"), 0);
      if (this.club.getLstSocios().isEmpty()) {
        System.out.println("No hay socios para mostrar...");
      } else {
        System.out.println(text.substring(0, text.length() - 1));
      }
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
    String dni = Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos.",
        (str) -> str.length() == 8);
    if (type.equalsIgnoreCase("profesor")) {
      Profesor prof = this.club.traerProfesor(Integer.parseInt(dni));
      if (prof == null)
        System.out.println("\nNo existe un " + type + " con ese DNI!");
      else {
        try {
          System.out.println("\nIntentando eliminar " + type + "...");
          this.club.eliminarProfesor(prof.getIdCarnetProfesor());
          Thread.sleep(500);
          System.out
              .println("\n" + type.replace(String.valueOf(type.charAt(0)), String.valueOf(type.charAt(0)).toUpperCase())
                  + " eliminado correctamente.");
          Files.write("data", this.club.toHashMap());
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Este error no debería suceder... " + e.getMessage());
        }
      }
    } else if (type.equalsIgnoreCase("socio")) {
      Socio socio = this.club.traerSocio(Integer.parseInt(dni));
      if (socio == null)
        System.out.println("\nNo existe un socio con ese DNI!");
      else {
        try {
          System.out.println("\nIntentando eliminar " + type + "...");
          this.club.eliminarSocio(socio.getIdCarnetSocio());
          Thread.sleep(500);
          System.out.println("\nSocio eliminado correctamente.");
          Files.write("data", this.club.toHashMap());
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Este error no debería suceder... " + e.getMessage());
        }
      }
    } else {
      throw new Exception("INVALID!!!!");
    }
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(2500);
    back.exec();
  }

  private void adm_rel_act() throws Exception {
    Menu adm_rel_act = this.menus.get("adm_rel_act");
    if (adm_rel_act.getOptions().isEmpty()) {
      adm_rel_act.addOption("Agregar actividad.", (op) -> {
        System.out.println("=".repeat(50));
        String nombre = Files.scan("Nombre? > ", String.class);
        int cupos = Integer.parseInt(Files.scan("Cupos? > ", Integer.TYPE, "El dato debe ser un numero mayor a 0.",
            (str) -> Integer.parseInt(str) > 0));
        Actividad act = null;
        try {
          act = this.club.agregarActividad(nombre, cupos);
          System.out.println(JSONparser.print(act.toHashMap(), 0));
          System.out.println("Actividad creada exitosamente.");
          Files.write("data", club.toHashMap());
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Este error no deberia suceder... " + e.getMessage());
        }
        Thread.sleep(750);
        System.out.println("\nVolviendo atras...");
        System.out.println("=".repeat(50));
        Thread.sleep(750);
        this.adm_rel_act();
      });
      adm_rel_act.addOption("Traer actividad.", (op) -> this.show_act(() -> this.adm_rel_act()));
      adm_rel_act.addOption("Traer actividades.", (op) -> this.show_acts(() -> this.adm_rel_act()));
      adm_rel_act.addOption("Agregar profesor a una actividad.",
          (op) -> this.add_soc_prof_act(() -> this.adm_rel_act(), "profesor"));
      adm_rel_act.addOption("Actividades en las que este un profesor.",
          (op) -> this.soc_prof_in_act(() -> this.adm_rel_act(), "profesor"));
      adm_rel_act.addOption("Eliminar profesor de una actividad",
          (op) -> this.del_soc_prof_act(() -> this.adm_rel_act(), "profesor"));
      adm_rel_act.addOption("Agregar socio a una actividad.",
          (op) -> this.add_soc_prof_act(() -> this.adm_rel_act(), "socio"));
      adm_rel_act.addOption("Actividades en las que este un socio.",
          (op) -> this.soc_prof_in_act(() -> this.adm_rel_act(), "socio"));
      adm_rel_act.addOption("Eliminar socio de una actividad",
          (op) -> this.del_soc_prof_act(() -> this.adm_rel_act(), "socio"));
      adm_rel_act.addOption("Agregar horario a una actividad.", (op) -> {
        System.out.println("=".repeat(50));
        int id = Integer.parseInt(
            Files.scan("ID actividad? > ", Integer.TYPE, "La ID debe ser un numero mayor a 0.",
                (str) -> Integer.parseInt(str) >= 0));
        Thread.sleep(500);
        Actividad act = this.club.traerActividad(id);
        if (act == null) {
          System.out.println("\nNo existe una actividad con esa ID!");
        } else {
          String dia = Files.scan("Dia? > ", String.class, "El dia debe ser uno de la semana, sin acentos.", (str) -> {
            return (str.equalsIgnoreCase("lunes") ||
                str.equalsIgnoreCase("martes") ||
                str.equalsIgnoreCase("miercoles") ||
                str.equalsIgnoreCase("jueves") ||
                str.equalsIgnoreCase("viernes") ||
                str.equalsIgnoreCase("sabado") ||
                str.equalsIgnoreCase("domingo"));
          });
          int hora = Integer.parseInt(Files.scan("Hora? > ", Integer.TYPE,
              "La hora debe ser un numero entre 0 y 23 (contempla extremos).",
              (str) -> Integer.parseInt(str) >= 0 || Integer.parseInt(str) <= 23));
          int duracion = Integer.parseInt(Files.scan("Duracion? > ", Integer.TYPE,
              "La duracion no puede mayor a 4.",
              (str) -> Integer.parseInt(str) <= 4));
          try {
            this.club.agregarDiaYhorario(act, dia, hora, duracion);
            System.out.println(JSONparser.print(act.toHashMap(), 0));
            System.out.println("Dia y horario agregado exitosamente.");
          } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("Invalid dia y hora")) {
              System.out.println("\nEl dia y horario se esta superponiendo con otra actividad.");
            } else {
              e.printStackTrace();
              System.out.println("Este error no deberia suceder... " + e.getMessage());
            }
          }
        }
        Thread.sleep(750);
        System.out.println("\nVolviendo atras...");
        System.out.println("=".repeat(50));
        Thread.sleep(750);
        this.adm_rel_act();
      });
      adm_rel_act.addOption("Eliminar una actividad.", (op) -> {
        System.out.println("=".repeat(50));
        int id = Integer.parseInt(
            Files.scan("ID actividad? > ", Integer.TYPE, "La ID debe ser un numero mayor a 0.",
                (str) -> Integer.parseInt(str) >= 0));
        System.out.println("\n\nBuscando actividad...");
        Thread.sleep(500);
        Actividad act = this.club.traerActividad(id);
        if (act == null) {
          System.out.println("\nNo existe una actividad con esa ID!");
        } else {
          try {
            this.club.eliminarActividad(id);
            System.out.println("Actividad eliminada exitosamente.");
            Files.write("data", club.toHashMap());
          } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Este error no deberia suceder... " + e.getMessage());
          }
        }
        Thread.sleep(750);
        System.out.println("\nVolviendo atras...");
        System.out.println("=".repeat(50));
        Thread.sleep(750);
        this.adm_rel_act();
      });
      adm_rel_act.addOption("Volver atras", (op) -> this.administracion());
    }
    adm_rel_act.printTitle(0);
    adm_rel_act.printOptions(2);
    adm_rel_act.selectOption(0);
  }

  private void show_act(Lambda back) throws Exception {
    System.out.println("=".repeat(50));
    int id = Integer.parseInt(
        Files.scan("ID actividad? > ", Integer.TYPE, "La ID debe ser un numero mayor a 0.",
            (str) -> Integer.parseInt(str) >= 0));
    System.out.println("\n\nBuscando actividad...");
    Thread.sleep(500);
    Actividad act = this.club.traerActividad(id);
    if (act == null) {
      System.out.println("\nNo existe una actividad con esa ID!");
    } else {
      String text = JSONparser.print(act.toHashMap(), 0);
      System.out.println("\n" + text.substring(0, text.length() - 1));
    }
    Thread.sleep(750);
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(750);
    back.exec();
  }

  private void show_acts(Lambda back) throws Exception {
    System.out.println("=".repeat(50));
    String text = JSONparser.print(this.club.toHashMap().get("lstActividades"), 0);
    if (text.length() > 2) {
      System.out.println("Lista de actividades:\n");
      System.out.println(text.substring(0, text.length() - 1));
    } else {
      System.out.println("No hay activiades para mostrar.\n");
    }
    Thread.sleep(750);
    System.out.println("Volviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(750);
    back.exec();
  }

  private void add_soc_prof_act(Lambda back, String type) throws Exception {
    System.out.println("=".repeat(50));
    int dni = Integer
        .parseInt(Files.scan("DNI " + type + "? > ", Integer.TYPE,
            "El dato debe ser un numero de 8 digitos",
            (str) -> str.length() == 8));
    Persona per = null;
    if (type.equalsIgnoreCase("profesor")) {
      per = this.club.traerProfesor(dni);
    } else if (type.equalsIgnoreCase("socio")) {
      per = this.club.traerSocio(dni);
    }
    if (per == null) {
      System.out.println("No existe un " + type + " con ese DNI.");
    } else {
      int id = Integer.parseInt(
          Files.scan("ID actividad? > ", Integer.TYPE, "La ID debe ser un numero mayor a 0.",
              (str) -> Integer.parseInt(str) >= 0));
      Thread.sleep(500);
      Actividad act = this.club.traerActividad(id);
      if (act == null) {
        System.out.println("\nNo existe una actividad con esa ID!");
      } else {
        if ((type.equalsIgnoreCase("profesor") && act.traerProfesor(((Profesor) per).getIdCarnetProfesor()) != null)
            || (type.equalsIgnoreCase("socio") && act.traerSocio(((Socio) per).getIdCarnetSocio()) != null)) {
          System.out.println("Ya existe ese " + type + " en la actividad.");
        } else {
          if (type.equalsIgnoreCase("socio") && act.getLstSocios().size() == act.getCupos()) {
            System.out
                .println(
                    "No se puede agregar al socio porque se alcanzo el numero de cupos maximos para esta actividad.");
          } else {
            try {
              if (type.equalsIgnoreCase("profesor")) {
                act.agregarProfesor((Profesor) per);
              } else if (type.equalsIgnoreCase("socio")) {
                act.agregarSocio((Socio) per);
              }
              System.out.println("\n" + type + " agregado exitosamente.");
              Files.write("data", club.toHashMap());
            } catch (Exception e) {
              e.printStackTrace();
              System.out.println("Este error no deberia suceder... " + e.getMessage());
            }
            String text = JSONparser.print(act.toHashMap(), 0);
            System.out.println(text.substring(0, text.length() - 1));
          }
        }
      }
    }
    Thread.sleep(750);
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(750);
    back.exec();
  }

  private void soc_prof_in_act(Lambda back, String type) throws Exception {
    System.out.println("=".repeat(50));
    int dni = Integer
        .parseInt(Files.scan("DNI? > ", Integer.TYPE, "El dato debe ser un numero de 8 digitos.",
            (str) -> str.length() == 8));
    Persona per = null;
    if (type.equalsIgnoreCase("profesor")) {
      per = this.club.traerProfesor(dni);
    } else if (type.equalsIgnoreCase("socio")) {
      per = this.club.traerSocio(dni);
    }
    List<Actividad> lst = new ArrayList<Actividad>();
    if (per == null) {
      System.out.println("No existe un " + type + " con ese DNI.");
    } else {
      List<Actividad> acts = this.club.getLstActividades();

      for (int i = 0; i < acts.size(); i++) {
        if (type.equalsIgnoreCase("profesor")) {
          for (int k = 0; k < acts.get(i).getLstProfesores().size(); k++) {
            if (acts.get(i).getLstProfesores().get(k).getDni() == dni) {
              lst.add(acts.get(i));
            }
          }
        } else if (type.equalsIgnoreCase("socio")) {
          for (int k = 0; k < acts.get(i).getLstSocios().size(); k++) {
            if (acts.get(i).getLstSocios().get(k).getDni() == dni) {
              lst.add(acts.get(i));
            }
          }
        }
      }
    }
    String text = JSONparser.print(lst.stream().map((p) -> p.toHashMap()).collect(Collectors.toList()), 0);
    System.out.println(text.substring(0, text.length() - 1));
    Thread.sleep(750);
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(750);
    back.exec();
  }

  private void del_soc_prof_act(Lambda back, String type) throws Exception {
    System.out.println("=".repeat(50));
    int dni = Integer
        .parseInt(Files.scan("DNI " + type + "? > ", Integer.TYPE,
            "El dato debe ser un numero de 8 digitos.",
            (str) -> str.length() == 8));
    Persona per = null;
    if (type.equalsIgnoreCase("profesor")) {
      per = this.club.traerProfesor(dni);
    } else if (type.equalsIgnoreCase("socio")) {
      per = this.club.traerSocio(dni);
    }
    if (per == null) {
      System.out.println("No existe un " + type + " con ese DNI.");
    } else {
      int id = Integer.parseInt(
          Files.scan("ID actividad? > ", Integer.TYPE, "La ID no puede ser menor a 0.",
              (str) -> Integer.parseInt(str) >= 0));
      Thread.sleep(500);
      Actividad act = this.club.traerActividad(id);
      if (act == null) {
        System.out.println("\nNo existe una actividad con esa ID!");
      } else {
        try {
          boolean error = false;
          if ((type.equalsIgnoreCase("profesor") && act.traerProfesor(((Profesor) per).getIdCarnetProfesor()) == null)
              || (type.equalsIgnoreCase("socio") && act.traerSocio(((Socio) per).getIdCarnetSocio()) == null)) {
            error = true;
            System.out.println("No existe ese " + type + " es esa actividad.");
          } else if (type.equalsIgnoreCase("profesor")) {
            act.eliminarProfesor(((Profesor) per).getIdCarnetProfesor());
          } else if (type.equalsIgnoreCase("socio")) {
            act.eliminarSocio(((Socio) per).getIdCarnetSocio());
          }
          if (!error) {
            System.out.println("\n" + type + " eliminado exitosamente.");
            Files.write("data", club.toHashMap());
            String text = JSONparser.print(act.toHashMap(), 0);
            System.out.println(text.substring(0, text.length() - 1));
          }
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Este error no deberia suceder... " + e.getMessage());
        }
      }
    }
    Thread.sleep(750);
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(750);
    back.exec();
  }

  private void adm_rel_alq() throws Exception {
    Menu adm_rel_alq = this.menus.get("adm_rel_alq");
    if (adm_rel_alq.getOptions().isEmpty()) {
      adm_rel_alq.addOption("Agregar alquiler.", (op) -> {
        System.out.println("=".repeat(50));
        String nombre = Files.scan("Nombre? > ", String.class);
        int precio = Integer.parseInt(Files.scan("Precio? > ", Integer.TYPE, "El dato debe ser un numero mayor a 0.",
            (str) -> Integer.parseInt(str) >= 0));
        String dia = Files.scan("Dia? > ", String.class, "El dia debe ser uno de la semana, sin acentos.", (str) -> {
          return (str.equalsIgnoreCase("lunes") ||
              str.equalsIgnoreCase("martes") ||
              str.equalsIgnoreCase("miercoles") ||
              str.equalsIgnoreCase("jueves") ||
              str.equalsIgnoreCase("viernes") ||
              str.equalsIgnoreCase("sabado") ||
              str.equalsIgnoreCase("domingo"));
        });
        int hora = Integer.parseInt(Files.scan("Hora? > ", Integer.TYPE,
            "La hora debe ser un numero entre 0 y 23 (contempla extremos).",
            (str) -> Integer.parseInt(str) >= 0 || Integer.parseInt(str) <= 23));
        int duracion = Integer.parseInt(Files.scan("Duracion? > ", Integer.TYPE,
            "La duracion no puede mayor a 4.",
            (str) -> Integer.parseInt(str) <= 4));
        try {
          Alquiler alq = this.club.agregarAlquiler(nombre, precio);
          this.club.agregarDiaYhorario(alq, dia, hora, duracion);
          System.out.println(JSONparser.print(alq.toHashMap(), 0));
          System.out.println("Alquiler creado exitosamente..");
        } catch (Exception e) {
          if (e.getMessage().equalsIgnoreCase("Invalid dia y hora")) {
            System.out.println("\nEl dia y horario se esta superponiendo con otro alquiler.");
          } else {
            e.printStackTrace();
            System.out.println("Este error no deberia suceder... " + e.getMessage());
          }
        }
        Thread.sleep(750);
        System.out.println("\nVolviendo atras...");
        System.out.println("=".repeat(50));
        Thread.sleep(750);
        this.adm_rel_alq();
      });
      adm_rel_alq.addOption("Traer alquiler.", (op) -> this.show_alq(() -> this.adm_rel_alq()));
      adm_rel_alq.addOption("Traer alquileres.", (op) -> this.show_alqs(() -> this.adm_rel_alq()));
      adm_rel_alq.addOption("Alquilar.", (op) -> this.alquilar(() -> this.adm_rel_alq()));
      adm_rel_alq.addOption("Eliminar un alquiler.", (op) -> {
        System.out.println("=".repeat(50));
        int id = Integer.parseInt(
            Files.scan("ID alquiler? > ", Integer.TYPE, "La ID debe ser un numero mayor a 0.",
                (str) -> Integer.parseInt(str) >= 0));
        System.out.println("\n\nBuscando alquiler...");
        Thread.sleep(500);
        Alquiler alq = this.club.traerAlquiler(id);
        if (alq == null) {
          System.out.println("\nNo existe un alquiler con esa ID!");
        } else {
          try {
            this.club.eliminarAlquiler(id);
            System.out.println("Alquiler eliminado exitosamente.");
            Files.write("data", club.toHashMap());
          } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Este error no deberia suceder... " + e.getMessage());
          }
        }
        Thread.sleep(750);
        System.out.println("\nVolviendo atras...");
        System.out.println("=".repeat(50));
        Thread.sleep(750);
        this.adm_rel_alq();
      });
      adm_rel_alq.addOption("Volver atras.", op -> this.administracion());
    }
    adm_rel_alq.printTitle(0);
    adm_rel_alq.printOptions(2);
    adm_rel_alq.selectOption(1);
  }

  private void show_alq(Lambda back) throws Exception {
    System.out.println("=".repeat(50));
    int id = Integer.parseInt(
        Files.scan("ID alquiler? > ", Integer.TYPE, "La ID debe ser un numero mayor a 0.",
            (str) -> Integer.parseInt(str) >= 0));
    System.out.println("\n\nBuscando alquiler...");
    Thread.sleep(500);
    Alquiler alq = this.club.traerAlquiler(id);
    if (alq == null) {
      System.out.println("\nNo existe un alquiler con esa ID!");
    } else {
      String text = JSONparser.print(alq.toHashMap(), 0);
      System.out.println("\n" + text.substring(0, text.length() - 1));
    }
    Thread.sleep(750);
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(750);
    back.exec();
  }

  private void show_alqs(Lambda back) throws Exception {
    System.out.println("=".repeat(50));
    String text = JSONparser.print(this.club.toHashMap().get("lstAlquileres"), 0);
    if (text.length() > 2) {
      System.out.println("Lista de alquileres:\n");
      System.out.println(text.substring(0, text.length() - 1));
    } else {
      System.out.println("No hay alquileres para mostrar.\n");
    }
    Thread.sleep(750);
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(750);
    back.exec();
  }

  private void alquilar(Lambda back) throws Exception {
    System.out.println("=".repeat(50));
    int id = Integer.parseInt(
        Files.scan("ID alquiler? > ", Integer.TYPE, "La ID debe ser un numero mayor a 0.",
            (str) -> Integer.parseInt(str) >= 0));
    Alquiler alq = this.club.traerAlquiler(id);
    if (alq == null) {
      System.out.println("\nNo existe un alquiler con esa ID!");
    } else {
      if (alq.isAlquilado()) {
        System.out.println("Ese alquiler ya fue alquilado.");
      } else {
        alq.setAlquilado(true);
        String text = JSONparser.print(alq.toHashMap(), 0);
        System.out.println("\n" + text.substring(0, text.length() - 1));
        Files.write("data", club.toHashMap());
        System.out.println("Alquiler alquilado satisfactoriamente.");
      }
    }
    Thread.sleep(750);
    System.out.println("\nVolviendo atras...");
    System.out.println("=".repeat(50));
    Thread.sleep(750);
    back.exec();
  }

  private void recrearFicheroDefault() throws Exception {
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
    String text = JSONparser.print(club.toHashMap(), 0);
    System.out.println(text.substring(0, text.length() - 1));
  }

  private void recrearFicheroVacio() throws Exception {
    Club club = new Club("Barrio feliz", "Victor Hugo 1200");
    Files.write("data", club.toHashMap());
    String text = JSONparser.print(club.toHashMap(), 0);
    System.out.println(text.substring(0, text.length() - 1));
  }
}
