package com.kaelkirk.code;

/**
 * Print
 */
public class Print extends Code {

  public Print(String contents) {
    super(String.format("System.out.print(%s);", contents));
  }
}