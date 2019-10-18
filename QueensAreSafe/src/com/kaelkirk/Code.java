package com.kaelkirk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Makes writing code with code feel less meta
 */
public enum Code {

  /**
   * Class declaration <br />
   * required arguments: <br />
   * className - name of the class
   */
  CLASS(1, "public class &&className&& {", "}"),

  /**
   * Main method <br />
   * no required arguments.
   */
  MAIN(1, "public static void main(String[] args) {", "}"),

  /**
   * println statement <br />
   * required arguments: <br />
   * content - what to print
   */
  PRINTLN(1, "System.out.println(\"&&contents&&\");");

  private static String INDENTATION = "  ";

  private ArrayList<String> lines;
  private Map<String, String> args;
  private int innerLine;

  Code(int innerLine, String... lines) {
    this.lines = new ArrayList<String>(Arrays.asList(lines));
    this.innerLine = innerLine;
    args = null;
  }

  /**
   * Embeds code into this code
   */
  public Code write(Code code) {
    lines.addAll(innerLine, code.toIndentedLines());
    innerLine += code.length();
    return this;
  }

  /**
   * Embeds line into this code
   */
  public Code write(String line) {
    lines.add(innerLine++, INDENTATION + line);
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

      if (line.matches(".*&&\\w+&&.*"))
        throw new IllegalArgumentException(this + " requires more arguments.");

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