package com.kaelkirk.code;

/**
 * Method
 */
public class Method extends WriteableCode {

  public static final Method MAIN = new Method("main", true, "String[] args");

  public Method(String visibility, boolean isStatic, String returnType, String name, String params) {
    super(1, visibility + " " + (isStatic ? "static " : "") + returnType + " " + name + "(" + params + ") {", "}");
  }

  public Method(String name, boolean isStatic, String params) {
    this("public", isStatic, "void", name, params);
  }

  public Method(String name, String params) {
    this(name, false, params);
  }
}