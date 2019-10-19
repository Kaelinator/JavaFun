package com.kaelkirk.code;

/**
 * Return
 */
public class Return extends Code {

  public Return(String what) {
    super(String.format("return %s;", what));
  }
}