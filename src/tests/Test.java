package tests;

import modelo.Club;
import menu.Menu;

public class Test {

	public static void main(String[] args) {
		Club club = new Club("Barrio feliz", 120, "Victor Hugo 1200");
		try {
			Menu menu = new Menu("Bienvenido al club " + club.getNombre() + ".");
			menu.addOption("Actividad 1");
			menu.addOption("Actividad 2");
			menu.addOption("Actividad 3");
			menu.addOption("Actividad 4");
			menu.print();
			int option = menu.selectOption();
			System.out.println(option);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
