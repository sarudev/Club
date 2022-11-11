package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
  private String title;
  private String description;
  private List<String> options;
  private int start;

  public Menu(String title) {
    this.title = title;
    this.description = "";
    this.start = 1;
    this.options = new ArrayList<String>();
  }

  public Menu(String title, String description) {
    this.title = title;
    this.description = description;
    this.start = 1;
    this.options = new ArrayList<String>();
  }

  public Menu(String title, String description, int start) throws Exception {
    this.title = title;
    this.description = description;
    this.start = start;
    this.options = new ArrayList<String>();
  }

  public Menu(String title, int start) throws Exception {
    this.title = title;
    this.description = "";
    this.start = start;
    this.options = new ArrayList<String>();
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

  public List<String> getOptions() {
    return this.options;
  }

  public void addOption(String option) throws Exception {
    this.options.add(option);
  }

  public void removeOption(String option) throws Exception {
    int idx = this.getOptionIndex(option);

    if (idx < 0)
      throw new Exception("Option does not exists.");

    this.options.remove(idx);
  }

  public void moveOption(String option, int toIndex) throws Exception {
    int idx = this.getOptionIndex(option);

    if (idx < 0)
      throw new Exception("Option does not exists.");

    this.options.remove(idx);
    this.options.add(toIndex, option);
  }

  public void print() throws Exception {
    for (int i = 0; i < 50; i++)
      System.out.println("");
    System.out.println(title);
    if (this.description.length() > 0)
      System.out.println("\n" + description);

    System.out.println();

    for (int i = 0; i < this.options.size(); i++) {
      String op = i + this.start + ". " + this.options.get(i);
      System.out.println(op);
    }
  }

  public int selectOption() throws Exception {
    Scanner sc = new Scanner(System.in);
    boolean ok = false;
    int optionValue = 0;

    while (!ok) {
      System.out.print("Opcion? > ");
      String option = sc.nextLine();
      if (option.length() == 1) {
        char cOption = option.charAt(0);
        int value = 0;
        if (Character.isDigit(cOption)) {
          value = Integer.parseInt(Character.toString(cOption));
        }
        if (value >= this.start && value < this.options.size() + this.start) {
          ok = true;
          optionValue = value;
        }
      }
    }

    sc.close();
    return optionValue;
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

    return idx;
  }
}
