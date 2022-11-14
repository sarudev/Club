package helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
  private String title;
  private String description;
  private List<Map<String, Lambda>> options;
  private int start;

  public Menu(String title) {
    this.title = title;
    this.description = "";
    this.start = 1;
    this.options = new ArrayList<Map<String, Lambda>>();
  }

  public Menu(String title, String description) {
    this.title = title;
    this.description = description;
    this.start = 1;
    this.options = new ArrayList<Map<String, Lambda>>();
  }

  public Menu(String title, String description, int start) throws Exception {
    this.title = title;
    this.description = description;
    this.start = start;
    this.options = new ArrayList<Map<String, Lambda>>();
  }

  public Menu(String title, int start) throws Exception {
    this.title = title;
    this.description = "";
    this.start = start;
    this.options = new ArrayList<Map<String, Lambda>>();
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Map<String, Lambda>> getOptions() {
    return this.options;
  }

  public void addOption(String option, Lambda lambda) throws Exception {
    Map<String, Lambda> map = new HashMap<String, Lambda>();
    map.put(option, lambda);
    this.options.add(map);
  }

  public void removeOption(String option) throws Exception {
    int idx = this.getOptionIndex(option);

    if (idx < 0)
      throw new Exception("Option does not exists.");

    this.options.remove(idx);
  }

  public void moveOption(String optionName, int toIndex) throws Exception {
    int idx = this.getOptionIndex(optionName);

    if (idx < 0 || idx >= this.options.size() || toIndex < 0 || toIndex >= this.options.size())
      throw new Exception("Option does not exists.");

    Map<String, Lambda> option = this.getOption(optionName);

    if (option == null)
      throw new Exception("Option does not exists.");

    this.options.remove(idx);
    this.options.add(toIndex, option);
  }

  public Map<String, Lambda> getOption(String name) {
    Map<String, Lambda> op = null;
    int i = 0;

    while (i < this.options.size() && op == null) {
      if (this.options.get(i).containsKey(name))
        op = this.options.get(i);
      i++;
    }

    return op;
  }

  public void print(int space) throws Exception {
    for (int i = 0; i < space; i++)
      System.out.println("");
    System.out.println(title);
    if (this.description.length() > 0)
      System.out.println("\n" + description);

    System.out.println();

    for (int i = 0; i < this.options.size(); i++) {
      String op = this.start + i + ". " + this.options.get(i).keySet().toArray()[0];
      System.out.println(op);
    }
  }

  public void selectOption(int space) throws Exception {
    Scanner sc = new Scanner(System.in);
    boolean ok = false;
    int optionValue = 0;

    System.out.println("");
    while (!ok) {
      System.out.print("Opcion? > ");
      String option = sc.nextLine();
      if (option.length() == 1) {
        char cOption = option.charAt(0);
        int value = 0;
        if (Character.isDigit(cOption))
          value = Integer.parseInt(Character.toString(cOption));
        if (value >= this.start && value < this.options.size() + this.start) {
          ok = true;
          optionValue = value;
        } else
          System.out.println("[INVALID OPTION]");
      } else {
        System.out.println("[INVALID OPTION]");
      }
    }

    sc.close();

    final int realOptionValue = optionValue - this.start;

    Map<String, Lambda> map = this.options.get(realOptionValue);
    Lambda lambda = map.get(map.keySet().toArray()[0]);

    for (int i = 0; i < space; i++)
      System.out.println("");

    lambda.exec();
  }

  private int getOptionIndex(String option) {
    if (this.options.isEmpty())
      return -1;

    int idx = 0;
    boolean found = false;

    while (idx < this.options.size() && !found) {
      if (this.options.get(idx).equals(option))
        found = true;
      idx++;
    }

    return idx == 0 ? -1 : idx;
  }
}
