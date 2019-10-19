package com.kaelkirk.code;

/**
 * Method
 */
public class Method extends WriteableCode {

  public static final Method MAIN = new Method("main", true, "String[] args");

  public Method(String visibility, boolean isStatic, String returnType, String name, String args) {
    super(1, visibility + " " + (isStatic ? "static " : "") + returnType + " " + name + "(" + args + ") {", "}");
  }

  public Method(String name, boolean isStatic, String args) {
    this("public", isStatic, "void", name, args);
  }

  public Method(String name, String args) {
    this(name, false, args);
  }
}