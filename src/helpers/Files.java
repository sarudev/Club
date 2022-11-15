package helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class Files {
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

  public static String scanner(String text) {
    Scanner sc = new Scanner(System.in);
    boolean flag = false;

    String str = "----------";
    while (!flag) {
      System.out.print(text);
      str = sc.nextLine();
      flag = true;
    }

    sc.close();

    return str;
  }
}
