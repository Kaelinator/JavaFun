package com.kaelkirk.code;

/**
 * Assignment
 */
public class Assignment extends Code {

  public Assignment(String identifier, String value) {
    super(String.format("%s = %s;", identifier, value));
  }
}