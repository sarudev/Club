package helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONparser {
  public static void print(Object obj, int indent) {
    int newIndent = indent == 0 ? 2 : indent;
    boolean start = newIndent == 2;
    if (obj instanceof Map) {
      if (start)
        System.out.println("{");
      Map<String, Object> map = (Map<String, Object>) obj;
      map.forEach((key, value) -> {
        if (value instanceof Map) {
          System.out.println(" ".repeat(newIndent) + key + ": {");
          print(value, newIndent + 2);
          System.out.println(" ".repeat(newIndent) + "},");
        } else if (value instanceof List) {
          List<Object> lst = (List<Object>) value;
          if (lst.isEmpty())
            System.out.println(" ".repeat(newIndent) + key + ": [],");
          else {
            System.out.println(" ".repeat(newIndent) + key + ": [");
            print(value, newIndent + 2);
            System.out.println(" ".repeat(newIndent) + "],");
          }
        } else if (value instanceof String) {
          String val = (String) value;
          if (val.startsWith("\""))
            System.out.println(" ".repeat(newIndent) + key + ": " + value + ",");
          else
            System.out.println(" ".repeat(newIndent) + key + ": \"" + value + "\",");
        } else {
          System.out.println(" ".repeat(newIndent) + key + ": " + value + ",");
        }
      });
      if (start)
        System.out.println("}");
    } else if (obj instanceof List) {
      if (start)
        System.out.println("[");
      List<Object> lst = (List<Object>) obj;
      lst.forEach(item -> {
        if (item instanceof Map) {
          System.out.println(" ".repeat(newIndent) + "{");
          print(item, newIndent + 2);
          System.out.println(" ".repeat(newIndent) + "},");
        } else if (item instanceof List) {
          System.out.println(" ".repeat(newIndent) + "[");
          print(item, newIndent + 2);
          System.out.println(" ".repeat(newIndent) + "],");
        } else {
          System.out.println(" ".repeat(newIndent) + item + ",");
        }
      });
      if (start)
        System.out.println("]");
    }
  }

  public static Map<String, Object> stringToHashMap(String str) throws Exception {
    return (Map<String, Object>) trueStringToHashMap(str);
  }

  private static Object trueStringToHashMap(String str) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();

    List<String> strArr = convert(str);

    if (str.startsWith("{")) {
      // System.out.println("-> {");
      strArr.forEach(item1 -> {
        String key = item1.substring(0, item1.indexOf("="));
        String value = item1.substring(item1.indexOf("=") + 1, item1.length());
        try {
          if (value.startsWith("[")) {
            // System.out.println("-> " + key + ": [");
            try {
              map.put(key, trueStringToHashMap(value));
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
            // System.out.println("-> ]");
          } else if (value.startsWith("{")) {
            // System.out.println("-> " + key + ": {");
            Object temp;
            try {
              temp = trueStringToHashMap(value);
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
            map.put(key, temp);
            // System.out.println("-> }");
            // System.out.println("XXXXXXXXXXXX " + temp);
          } else if (value.startsWith("\"")) {
            // System.out.println("else - " + key + ": " + value);
            map.put(key, value.substring(1, value.length() - 1));
          } else if (value.equalsIgnoreCase("true")) {
            // System.out.println("else - " + key + ": " + value);
            map.put(key, true);
          } else if (value.equalsIgnoreCase("false")) {
            // System.out.println("else - " + key + ": " + value);
            map.put(key, false);
          } else if (Character.isDigit(value.charAt(0)) && !value.contains(".")) {
            // System.out.println("else - " + key + ": " + value);
            map.put(key, Integer.parseInt(value));
          } else if (Character.isDigit(value.charAt(0)) && value.contains(".")) {
            // System.out.println("else - " + key + ": " + value);
            map.put(key, Double.parseDouble(value));
          } else {
            // System.out.println("else - " + key + ": " + value);
            // map.put(key, value);
            throw new Exception("Corrupted data.");
          }
        } catch (Exception e) {
          throw new RuntimeException("Corrupted data.");
        }
      });
      // System.out.println("-> }");
    } else if (str.startsWith("[")) {
      List<Object> values = new ArrayList<Object>();

      strArr.forEach(item1 -> {
        if (item1.startsWith("[") || item1.startsWith("{")) {
          Object temp;
          try {
            temp = trueStringToHashMap(item1);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
          // System.out.println("++++++++++++ " + temp);
          values.add(temp);
        } else {
          // System.out.println("############ " + item1);
          values.add(item1);
        }
      });

      // System.out.println("-----------> " + values);

      return values;
    }

    return map;
  }

  private static List<String> convert(String str) throws Exception {
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
}
