package com.kaelkirk;

import java.util.ArrayList;
import java.util.List;

/**
 * Makes writing code with code feel less meta
 */
public enum Code {

  MAIN(new ArrayList<String>(), 2);

  private ArrayList<String> lines;
  private int innerLine;

  Code(ArrayList<String> lines, int innerLine) {
    this.lines = lines;
    this.innerLine = innerLine;
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
   * returns the number of lines this Code requires
   */
  private int length() {
    return lines.size();
  }

  public List<String> toLines() {
    return lines;
  }
}