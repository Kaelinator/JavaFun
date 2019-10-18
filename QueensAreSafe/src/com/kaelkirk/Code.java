package com.kaelkirk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Makes writing code with code feel less meta
 */
public enum Code {

  MAIN(new ArrayList<String>(), 2);

  private ArrayList<String> lines;
  private Map<String, String> args;
  private int innerLine;

  Code(ArrayList<String> lines, int innerLine) {
    this.lines = lines;
    this.innerLine = innerLine;
    args = null;
  }

  /**
   * Embeds code into this code
   */
  public Code write(Code code) {
    lines.addAll(innerLine, code.toLines());
    innerLine += code.length();
    return this;
  }

  /**
   * Embeds line into this code
   */
  public Code write(String line) {
    lines.add(innerLine++, line);
    return this;
  }

  /**
   * @param args != null
   */
  public void setArgs(Map<String, String> args) {
    if (args == null)
      throw new IllegalArgumentException("Failed: args != null");

    this.args = args;
  }

  /**
   * returns the number of lines this Code requires
   */
  private int length() {
    return lines.size();
  }

  /**
   * uses templating to substitude in user-given args
   * 
   * @param args != null
   * @param lines != null
   */
  private ArrayList<String> substituteArgs(ArrayList<String> lines, Map<String, String> args) {
    if (args == null || lines == null)
      throw new IllegalArgumentException("Failed: args != null, lines != null");

    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);

      for (String param : args.keySet())
        line.replaceAll("{{" + param + "}}", args.get(param));

      if (line.matches("{{\\w+}}"))
        throw new IllegalArgumentException(this + " requires more arguments.");

      lines.set(i, line);
    }

    return lines;
  }

  public List<String> toLines() {
    return (args == null) ? lines : substituteArgs(lines, args);
  }
}