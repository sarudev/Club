package tests;

import java.util.Map;
import java.util.Scanner;

import helpers.Files;
import helpers.JSONparser;
import helpers.MenuOptions;
import modules.Club;

public class Test {
  public static void main(String[] args) throws Exception {
    Club club = build();
    new MenuOptions(club).main();
    // Scanner sc = new Scanner(System.in);
    // boolean flag = false;
    // while (!flag && sc.hasNextLine()) {
    // String str = sc.nextLine();
    // if (str.equals("1"))
    // flag = true;
    // }
    // System.out.print("YOU'VE GOT THROUGH");
    // flag = false;
    // while (!flag && sc.hasNextLine()) {
    // String str = sc.nextLine();
    // if (str.equals("1"))
    // flag = true;
    // }
    // System.out.print("YOU'VE GOT THROUGH");
    // sc.close();
  }

  private static Club build() {
    Club club = null;
    try {
      String json = null;
      try {
        json = Files.read("data");
      } catch (Exception e) {
        throw new Exception("Fichero no encontrado.");
      }

      Map<String, Object> map = null;
      try {
        map = JSONparser.stringToHashMap(json);
      } catch (Exception e) {
        throw new Exception("Corrupted data.");
      }

      club = Club.recrearClub(map);
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
      System.out.println(
          "Puede recrear el fichero con datos por defecto con la opcion \"Recrear Fichero\" en \"Administracion\".");
    }
    if (club == null) {
      try {
        club = new Club("ERROR", "ERROR");
      } catch (Exception e) {
        System.out.println("Este error no debería suceder...\n" + e.getMessage());
      }
    }

    return club;
  }

}
