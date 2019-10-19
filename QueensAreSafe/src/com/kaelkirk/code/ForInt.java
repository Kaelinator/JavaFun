package com.kaelkirk.code;

/**
 * ForInt
 */
public class ForInt extends WriteableCode {

  public ForInt(String type, String identifier, String initialValue, String comparison, String max, String operation) {
    super(1, String.format("for (%s %s = %s; %s %s %s; %s%s) {", type, identifier, initialValue, identifier, comparison,
        max, identifier, operation), "}");
  }

  public ForInt(String type, String identifier, String initialValue, String condition, String operation) {
    super(1, String.format("for (%s %s = %s; %s; %s%s) {", type, identifier, initialValue, condition, identifier, operation), "}");
  }

  public ForInt(String identifier, String initialValue, String condition) {
    this("int", identifier, initialValue, condition, "++");
  }

  public ForInt(String identifier, String max) {
    this("int", identifier, "0", "<", max, "++");
  }
}