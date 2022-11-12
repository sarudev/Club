package tests;

import modelo.Actividad;
import modelo.Club;
import menu.Menu;

public class Test {

	public static void main(String[] args) {
		Club club = new Club("Barrio feliz", 120, "Victor Hugo 1200");
		try {
			Actividad act = club.agregarActividad("Futsal", 2);
			club.agregarDiaYhorario(act, "lunes", 8, 2);
			club.agregarDiaYhorario(act, "lunes", 10, 2);
			System.out.println("listo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
