package helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class Files {
  private static Scanner sc = new Scanner(System.in);

  public static Scanner getSc() {
    return sc;
  }

  public static void write(String nombre, Map<String, Object> map) throws Exception {
    PrintWriter salida = new PrintWriter(new FileWriter(nombre + ".txt"));
    salida.print(map.toString());
    salida.close();
  }

  public static String read(String nombre) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(nombre + ".txt"));
    String json = br.readLine();
    br.close();
    return json;
  }

  public static String scan(String text, Class<?> wantedType) {
    Map<Class<?>, Predicate<String>> map = new HashMap<>();
    map.put(Integer.TYPE, s -> {
      try {
        Integer.parseInt(s);
        return true;
      } catch (Exception e) {
        return false;
      }
    });
    boolean ok = false;
    String str = "";

    while (!ok) {
      System.out.print(text);
      str = Files.sc.nextLine();
      if ((wantedType.equals(String.class) || map.get(wantedType).test(str)) && str.length() > 0)
        ok = true;
      else {
        System.out.println("[INVALID ENTRY]");
      }
    }

    return str;
  }
}
