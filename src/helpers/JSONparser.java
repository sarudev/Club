package helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONparser {
  @SuppressWarnings("unchecked")
  public static String print(Object obj, int indent) {
    String[] text = { "" };
    int newIndent = indent == 0 ? 2 : indent;
    boolean start = newIndent == 2;
    if (obj instanceof Map) {
      if (start)
        text[0] += "{\n";
      Map<String, Object> map = (Map<String, Object>) obj;
      map.forEach((key, value) -> {
        if (value instanceof Map) {
          text[0] += " ".repeat(newIndent) + key + ": {\n";
          text[0] += print(value, newIndent + 2);
          text[0] += " ".repeat(newIndent) + "},\n";
        } else if (value instanceof List) {
          List<Object> lst = (List<Object>) value;
          if (lst.isEmpty())
            text[0] += " ".repeat(newIndent) + key + ": [],\n";
          else {
            text[0] += " ".repeat(newIndent) + key + ": [\n";
            text[0] += print(value, newIndent + 2);
            text[0] += " ".repeat(newIndent) + "],\n";
          }
        } else if (value instanceof String) {
          String val = (String) value;
          if (val.startsWith("\""))
            text[0] += " ".repeat(newIndent) + key + ": " + value + ",\n";
          else
            text[0] += " ".repeat(newIndent) + key + ": \"" + value + "\",\n";
        } else {
          text[0] += " ".repeat(newIndent) + key + ": " + value + ",\n";
        }
      });
      if (start)
        text[0] += "}\n";
    } else if (obj instanceof List) {
      if (start)
        text[0] += "[\n";
      List<Object> lst = (List<Object>) obj;
      lst.forEach(item -> {
        if (item instanceof Map) {
          text[0] += " ".repeat(newIndent) + "{\n";
          text[0] += print(item, newIndent + 2);
          text[0] += " ".repeat(newIndent) + "},\n";
        } else if (item instanceof List) {
          text[0] += " ".repeat(newIndent) + "[\n";
          text[0] += print(item, newIndent + 2);
          text[0] += " ".repeat(newIndent) + "],\n";
        } else {
          text[0] += " ".repeat(newIndent) + item + ",\n";
        }
      });
      if (start)
        text[0] += "]\n";
    }

    return text[0];
  }

  @SuppressWarnings("unchecked")
  public static Map<String, Object> stringToHashMap(String str) throws Exception {
    return (Map<String, Object>) trueStringToHashMap(str);
  }

  private static Object trueStringToHashMap(String str) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();

    List<String> strArr = convert(str);

    if (str.startsWith("{")) {
      strArr.forEach(item1 -> {
        String key = item1.substring(0, item1.indexOf("="));
        String value = item1.substring(item1.indexOf("=") + 1, item1.length());
        try {
          if (value.startsWith("[")) {
            try {
              map.put(key, trueStringToHashMap(value));
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
          } else if (value.startsWith("{")) {
            Object temp;
            try {
              temp = trueStringToHashMap(value);
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
            map.put(key, temp);
          } else if (value.startsWith("\"")) {
            map.put(key, value.substring(1, value.length() - 1));
          } else if (value.equalsIgnoreCase("true")) {
            map.put(key, true);
          } else if (value.equalsIgnoreCase("false")) {
            map.put(key, false);
          } else if (Character.isDigit(value.charAt(0)) && !value.contains(".")) {
            map.put(key, Integer.parseInt(value));
          } else if (Character.isDigit(value.charAt(0)) && value.contains(".")) {
            map.put(key, Double.parseDouble(value));
          } else {
            throw new Exception("Corrupted data.");
          }
        } catch (Exception e) {
          throw new RuntimeException("Corrupted data.");
        }
      });
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
          values.add(temp);
        } else {
          values.add(item1);
        }
      });

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
