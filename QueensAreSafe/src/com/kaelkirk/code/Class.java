package com.kaelkirk.code;

/**
 * Class
 */
public class Class extends WriteableCode {

  public Class(String name) {
    super(1, "public class " + name + " {", "}");
  }
}