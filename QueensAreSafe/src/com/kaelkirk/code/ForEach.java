package com.kaelkirk.code;

/**
 * ForEach
 */
public class ForEach extends WriteableCode {

  public ForEach(String type, String identifier, String iterable) {
    super(1, String.format("for (%s %s : %s) {", type, identifier, iterable), "}");
  }
}