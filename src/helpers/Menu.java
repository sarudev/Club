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

  public Option addOption(String option, boolean blocked, String reason, Lambda lambda) throws Exception {
    Option op = new Option(option, blocked, reason, lambda);
    this.options.add(op);
    return op;
  }

  public Option addOption(String option, Lambda lambda) throws Exception {
    Option op = new Option(option, false, "", lambda);
    this.options.add(op);
    return op;
  }

  public void removeOption(String optionName) throws Exception {
    int idx = this.getOptionIndex(optionName);

    if (idx < 0)
      throw new Exception("Option does not exists.");

    this.options.remove(idx);
  }

  public void moveOption(String optionName, int toIndex) throws Exception {
    if (toIndex < 0 || toIndex > this.options.size())
      throw new Exception("Option does not exists.");

    Option option = this.getOption(optionName);

    if (option == null)
      throw new Exception("Option does not exists.");

    this.removeOption(optionName);
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
      Option op = this.options.get(i);
      System.out.println(this.start + i + ". " + (op.isBlocked() ? "[BLOCKED] " : "") + op.getName());
    }
  }

  public void selectOption(int emptyLinesBottom) throws Exception {
    int optionValue = -1;

    System.out.println("");

    while (optionValue == -1) {
      System.out.print("Opcion? > ");
      String option = Files.getSc().nextLine();
      if (option.length() == 1) {
        char cOption = option.charAt(0);
        int value = 0;
        if (Character.isDigit(cOption))
          value = Integer.parseInt(Character.toString(cOption));
        if (value >= this.start && value < this.options.size() + this.start) {
          if (!this.options.get(value - this.start).isBlocked())
            optionValue = value;
          else
            System.out.println("[BLOCKED OPTION] Reason: " + this.options.get(value - this.start).getReason() + "\n");
        } else
          System.out.println("[INVALID OPTION]");
      } else {
        System.out.println("[INVALID OPTION]");
      }
    }

    Option op = this.options.get(optionValue - this.start);
    Lambda lambda = op.getLambda();

    for (int i = 0; i < emptyLinesBottom; i++)
      System.out.println("");

    lambda.exec(op);
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

    return idx == 0 ? -1 : idx - 1;
  }
}

class Option {
  private String name;
  private Lambda lambda;
  private boolean blocked;
  private String reason;

  public Option(String name, boolean blocked, String reason, Lambda lambda) {
    this.name = name;
    this.lambda = lambda;
    this.blocked = blocked;
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

  public void setReason(String reason) {
    this.reason = reason;
  }

  public boolean isBlocked() {
    return this.blocked;
  }

  public void setBlocked(boolean blocked) throws Exception {
    if (this.reason.length() < 1)
      throw new Exception("Before setting blocked to true, you need to add a reason.");
    this.blocked = blocked;
  }

  @Override
  public String toString() {
    return "Option [name=" + name + ", lambda=" + lambda + ", blocked=" + blocked + ", reason=" + reason + "]";
  }

}
