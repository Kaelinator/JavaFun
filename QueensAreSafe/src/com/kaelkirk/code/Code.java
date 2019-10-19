package com.kaelkirk.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Makes writing code with code feel less meta
 */
public class Code {

  private static final String INDENTATION = "  ";

  private ArrayList<String> lines;
  private Map<String, String> args;
  private int innerLine;

  public Code(CodeType type) {
    this.lines = type.lines;
    this.innerLine = type.innerLine;
    args = null;
  }

  /**
   * Embeds code(s) into this Code
   */
  public Code write(Code... code) {

    for (Code block : code) {
      lines.addAll(innerLine, block.toIndentedLines());
      innerLine += block.length();
    }

    return this;
  }

  /**
   * Embeds line(s) into this code
   */
  public Code write(String... lines) {

    for (String line : lines)
      this.lines.add(innerLine++, INDENTATION + line);

    return this;
  }

  /**
   * @param args != null
   */
  public Code setArgs(Map<String, String> args) {
    if (args == null)
      throw new IllegalArgumentException("Failed: args != null");

    this.args = args;
    return this;
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
   * @param args  != null
   * @param lines != null
   */
  private ArrayList<String> substituteArgs(ArrayList<String> lines, Map<String, String> args) {
    if (args == null || lines == null)
      throw new IllegalArgumentException("Failed: args != null, lines != null");

    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);

      for (String param : args.keySet()) {
        line = line.replaceAll("&&" + param + "&&", args.get(param));
      }

      lines.set(i, line);
    }

    return lines;
  }

  public List<String> toLines() {
    return (args == null) ? lines : substituteArgs(lines, args);
  }

  private List<String> toIndentedLines() {
    lines = (args == null) ? lines : substituteArgs(lines, args);
    return lines.stream().map(str -> INDENTATION + str).collect(Collectors.toList());
  }
}