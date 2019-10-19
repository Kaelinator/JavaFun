package com.kaelkirk.code;

/**
 * Comment
 */
public class Comment extends WriteableCode {

  public Comment() {
    super(1, "/*", " */");
  }

  @Override
  public WriteableCode write(String... lines) {
    
    for (String line : lines)
      this.lines.add(innerLine++, " * " + line);

    return this;
  }
}