package com.kaelkirk.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Makes writing code with code feel less meta
 */
public abstract class Code {

  protected static final String INDENTATION = "  ";

  protected ArrayList<String> lines;

  Code( String ...lines) {
    this.lines = new ArrayList<String>(Arrays.asList(lines));
  }

  /**
   * returns the number of lines this Code requires
   */
  protected int length() {
    return lines.size();
  }

  public List<String> toLines() {
    return lines;
  }

  protected List<String> toIndentedLines() {
    return lines.stream().map(str -> INDENTATION + str).collect(Collectors.toList());
  }
}