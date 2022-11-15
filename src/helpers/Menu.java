package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
  private String title;
  private String description;
  private List<Option> options;
  private int start;

  public Menu(String title) {
    this.title = title;
    this.description = "";
    this.start = 1;
    this.options = new ArrayList<Option>();
  }

  public Menu(String title, String description) {
    this.title = title;
    this.description = description;
    this.start = 1;
    this.options = new ArrayList<Option>();
  }

  public Menu(String title, String description, int start) throws Exception {
    this.title = title;
    this.description = description;
    this.start = start;
    this.options = new ArrayList<Option>();
  }

  public Menu(String title, int start) throws Exception {
    this.title = title;
    this.description = "";
    this.start = start;
    this.options = new ArrayList<Option>();
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

  public List<Option> getOptions() {
    return this.options;
  }

  public Option addOption(String option, String reason, Lambda lambda) throws Exception {
    Option op = new Option(option, reason, lambda);
    this.options.add(op);
    return op;
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

    Option option = this.getOption(optionName);

    if (option == null)
      throw new Exception("Option does not exists.");

    this.options.remove(idx);
    this.options.add(toIndex, option);
  }

  public Option getOption(String name) {
    Option op = null;
    int i = 0;

    while (i < this.options.size() && op == null) {
      if (this.options.get(i).getName().equals(name))
        op = this.options.get(i);
      i++;
    }

    return op;
  }

  public void printTitle(int emptyLinesTop) {
    for (int i = 0; i < emptyLinesTop; i++)
      System.out.println("");
    System.out.print(title);
    if (this.description.length() > 0)
      System.out.println("\n" + description);
  }

  public void printOptions(int emptyLinesTop) throws Exception {
    for (int i = 0; i < emptyLinesTop; i++)
      System.out.println("");

    System.out.println();

    for (int i = 0; i < this.options.size(); i++) {
      String op = this.start + i + ". " + this.options.get(i).getName();
      System.out.println(op);
    }
  }

  public void selectOption(int emptyLinesBottom) throws Exception {
    int optionValue = -1;

    System.out.println("");
    // System.out.println("1");
    while (optionValue == -1) {
      System.out.println("2");
      String option = Files.scanner("Opcion? > ");
      if (option.length() == 1) {
        char cOption = option.charAt(0);
        int value = 0;
        if (Character.isDigit(cOption))
          value = Integer.parseInt(Character.toString(cOption));
        if (value >= this.start && value < this.options.size() + this.start)
          optionValue = value;
        else
          System.out.println("[INVALID OPTION]");
      } else {
        System.out.println("[INVALID OPTION]");
      }
    }
    // System.out.println("3");

    if (optionValue - this.start > -1) {
      Option op = this.options.get(optionValue - this.start);
      Lambda lambda = op.getLambda();

      for (int i = 0; i < emptyLinesBottom; i++)
        System.out.println("");

      lambda.exec();
    }
    // System.out.println("4");
  }

  private int getOptionIndex(String option) {
    if (this.options.isEmpty())
      return -1;

    int idx = 0;
    boolean found = false;

    while (idx < this.options.size() && !found) {
      if (this.options.get(idx).getName().equals(option))
        found = true;
      idx++;
    }

    return idx == 0 ? -1 : idx;
  }
}

class Option {
  private String name;
  private Lambda lambda;
  private boolean blocked;
  private String reason;

  public Option(String name, String reason, Lambda lambda) {
    this.name = name;
    this.lambda = lambda;
    this.blocked = false;
    this.reason = reason;
  }

  public String getName() {
    return this.name;
  }

  public Lambda getLambda() {
    return this.lambda;
  }

  public String getReason() {
    return this.reason;
  }

  public boolean isBlocked() {
    return this.blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }
}
