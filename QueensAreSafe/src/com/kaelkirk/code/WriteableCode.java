package com.kaelkirk.code;

import com.kaelkirk.code.Code;

/**
 * WriteableCode
 */
public class WriteableCode extends Code {

  private int innerLine;
  
  WriteableCode(int innerLine, String ...lines) {
    super(lines);
    this.innerLine = innerLine;
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
}