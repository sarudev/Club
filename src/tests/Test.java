package tests;

import modelo.*;
import archivos.Files;
import menu.Menu;

public class Test {

  public static void main(String[] args) throws Exception {
    Club club = new Club("Barrio feliz", 120, "Victor Hugo 1200");
    Actividad act1 = club.agregarActividad("Futsal", 2);
    club.agregarDiaYhorario(act1, "lunes", 8, 2);
    club.agregarDiaYhorario(act1, "martes", 18, 2);
    Actividad act2 = club.agregarActividad("Voley", 2);
    club.agregarDiaYhorario(act2, "lunes", 10, 2);
    Alquiler alq1 = club.agregarAlquiler("Salon de fiestas", 10_000);
    club.agregarDiaYhorario(alq1, "viernes", 16, 2);
    Alquiler alq2 = club.agregarAlquiler("Salon de eventos", 20_000);
    club.agregarDiaYhorario(alq2, "viernes", 18, 2);

    Files.write("data", club.toHashMap());
    System.out.println(Files.read("data"));
  }
}
