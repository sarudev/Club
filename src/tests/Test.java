package tests;

import modelo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import menu.Menu;

public class Test {

  public static void main(String[] args) throws Exception {
    Club club = new Club("Barrio feliz", 120, "Victor Hugo 1200");
    Actividad act1 = club.agregarActividad("Futsal", 2);
    Actividad act2 = club.agregarActividad("Voley", 2);
    Alquiler alq1 = club.agregarAlquiler("Salon de fiestas", 10_000);
    Alquiler alq2 = club.agregarAlquiler("Salon de eventos", 20_000);
    try {
      club.agregarDiaYhorario(act1, "lunes", 8, 2);
      club.agregarDiaYhorario(act2, "lunes", 10, 2);
      club.agregarDiaYhorario(alq1, "viernes", 16, 2);
      club.agregarDiaYhorario(alq2, "viernes", 18, 2);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String str = club.toHashMap().toString();
    System.out.println(str);
    System.out.println(stringToHashMap(str));
  }

  private static List<String> convert(String str) {
    List<String> strArr = new ArrayList<String>();
    int i = 0;
    int savedIndex = 0;

    str = str.startsWith("[")
        ? str.substring(str.indexOf("[") + 1, str.length() - 1)
        : str.substring(1, str.length() - 1);

    while (i < str.length()) {
      if (str.charAt(i) == '{' || str.charAt(i) == '[') {
        int count = 1;
        int idx = i + 1;
        while (idx < str.length() && count != 0) {
          if (str.charAt(idx) == '{' || str.charAt(idx) == '[')
            count++;
          if (str.charAt(idx) == '}' || str.charAt(idx) == ']')
            count--;
          idx++;
        }
        i = idx - 1;
      }

      if (str.charAt(i) == ',' || i == str.length() - 1) {
        strArr
            .add(str.substring(savedIndex == 0 ? 0 : savedIndex + 2, i == str.length() - 1 ? i + 1 : i));
        savedIndex = i;
      }
      i++;
    }
    return strArr;
  }

  private static Map<String, Object> stringToHashMap(String str) {
    return (HashMap<String, Object>) trueStringToHashMap(str);
  }

  private static Object trueStringToHashMap(String str) {
    Map<String, Object> map = new HashMap<String, Object>();

    List<String> strArr = convert(str);

    if (str.startsWith("{")) {
      System.out.println("-> json {");
      strArr.forEach(item1 -> {
        String key = item1.substring(0, item1.indexOf("="));
        String value = item1.substring(item1.indexOf("=") + 1, item1.length());
        if (value.startsWith("[")) {
          System.out.println("-> " + key + ": " + value + " [");
          map.put(key, trueStringToHashMap(value));
          System.out.println("-> ]");
        } else {
          System.out.println("else - " + key + ": " + value);
          map.put(key, value);
        }
      });
      System.out.println("-> json }");
    } else if (str.startsWith("[")) {
      List<Object> values = new ArrayList<Object>();

      strArr.forEach(item1 -> {
        if (item1.startsWith("[") || item1.startsWith("{")) {
          Object temp = trueStringToHashMap(item1);
          System.out.println("++++++++++++ " + temp);
          values.add(temp);
        } else {
          System.out.println("############ " + item1);
          values.add(item1);
        }
      });

      System.out.println("-----------> " + values);

      return values;
    }

    return map;
  }
}
