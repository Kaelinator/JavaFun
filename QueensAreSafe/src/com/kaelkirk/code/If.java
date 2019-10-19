package com.kaelkirk.code;

/**
 * If
 */
public class If extends WriteableCode {

  public If(String condition) {
    super(1, String.format("if (%s) {", condition), "}");
  }
}