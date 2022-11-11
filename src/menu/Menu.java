package menu;

import java.util.List;

public class Menu {
  private String title;
  private String description;
  private List<String> options;
  private int start;
  private char type;

  public Menu(String title) {
    this.title = title;
    this.description = "";
    this.start = 0;
    this.type = '1';
  }

  public Menu(String title, String description) {
    this.title = title;
    this.description = description;
    this.start = 0;
    this.type = '1';
  }

  public Menu(String title, String description, int start) {
    this.title = title;
    this.description = description;
    this.start = start;
    this.type = '1';
  }

  public Menu(String title, String description, char type) {
    this.title = title;
    this.description = description;
    this.start = 0;
    this.type = type;
  }

  public Menu(String title, String description, int start, char type) {
    this.title = title;
    this.description = description;
    this.start = start;
    this.type = type;
  }

  public Menu(String title, int start) {
    this.title = title;
    this.description = "";
    this.start = start;
    this.type = '1';
  }

  public Menu(String title, char type) {
    this.title = title;
    this.description = "";
    this.start = 0;
    this.type = type;
  }

  public Menu(String title, int start, char type) {
    this.title = title;
    this.description = "";
    this.start = start;
    this.type = type;
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

  public void addOption(String option) {
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

  public void print() {
    for (int i = 0; i < 50; i++)
      System.out.println("");
    System.out.println(title);
    if (this.description.length() > 0)
      System.out.println(description);

    char[] lowerCaser = {
        'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l',
        'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x',
        'y', 'z'
    };

    char[] upperCase = {
        'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z'
    };
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
