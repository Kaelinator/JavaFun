package com.kaelkirk.code;

/**
 * Println
 */
public class Println extends Code {

  public Println(String contents) {
    super(String.format("System.out.println(%s);", contents));
  }
}