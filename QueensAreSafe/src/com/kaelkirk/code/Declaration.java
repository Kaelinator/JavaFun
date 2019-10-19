package com.kaelkirk.code;

/**
 * Declaration
 */
public class Declaration extends Code {

  public Declaration(String type, String identifier, String value) {
    super(String.format("%s %s = %s;", type, identifier, value));
  }

  public Declaration(String type, String identifier) {
    super(String.format("%s %s;", type, identifier));
  }
}