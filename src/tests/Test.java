package tests;

import helpers.Files;
import helpers.JSONparser;
import helpers.Menu;
import modules.*;

public class Test {

  public static void main(String[] args) throws Exception {
    Club club = new Club("Barrio feliz", "Victor Hugo 1200");
    Socio soc1 = club.agregarSocio("Jose1", "Coria1", 44880931, 11, 1_001);
    Socio soc2 = club.agregarSocio("Jose2", "Coria2", 44880932, 12, 1_002);
    Socio soc3 = club.agregarSocio("Jose3", "Coria3", 44880933, 13, 1_003);
    Profesor prof = club.agregarProfesor("Alejandro", "Golozo", 12345678, 30, 50_000);
    Actividad act1 = club.agregarActividad("Futsal", 2);
    club.agregarDiaYhorario(act1, "lunes", 8, 2);
    club.agregarDiaYhorario(act1, "martes", 18, 2);
    act1.agregarProfesor(prof);
    act1.agregarSocio(soc1);
    act1.agregarSocio(soc2);
    Actividad act2 = club.agregarActividad("Voley", 2);
    club.agregarDiaYhorario(act2, "lunes", 10, 2);
    Alquiler alq1 = club.agregarAlquiler("Salon de fiestas", 10_000);
    club.agregarDiaYhorario(alq1, "viernes", 16, 2);
    Alquiler alq2 = club.agregarAlquiler("Salon de eventos", 20_000);
    club.agregarDiaYhorario(alq2, "viernes", 18, 2);
    Menu menu = new Menu("Bienvenido al club " + club.getNombre() + ".");
    menu.addOption("Actividad 1");
    menu.addOption("Actividad 2");
    menu.addOption("Actividad 3");
    menu.addOption("Actividad 4");
    menu.print();
    int option = menu.selectOption();
    System.out.println(option);
  }
}
