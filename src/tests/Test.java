package tests;

import modelo.Club;
import menu.Menu;

public class Test {

	public static void main(String[] args) {
		Club club = new Club("Barrio feliz", 120, "Victor Hugo 1200");
		Menu menu = new Menu("Bienvenido al club " + club.getNombre() + ".");
		menu.print();
	}

}
