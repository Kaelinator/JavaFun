package com.kaelkirk.code;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Makes writing code with code feel less meta
 */
public enum CodeType {

  /**
   * Class declaration
   * 
   * @param className name of the class
   */
  CLASS(1, "public class &&className&& {", "}"),

  /**
   * Main method no required arguments.
   */
  MAIN(1, "public static void main(String[] args) {", "}"),

  /**
   * println statement
   * 
   * @param content what to print
   */
  PRINTLN(1, "System.out.println(&&contents&&);"),

  /**
   * declaration with initialization for an object
   * 
   * @param declaredType the declared type of the object
   * @param var          identifier
   * @param type         the dynamic type of the object
   */
  NEW(1, "&&declaredType&& &&var&& = new &&type&&;"),

  /**
   * for int statement
   * 
   * @param var identifier to loop over
   * @param max maximum value (var < max)
   */
  FOR_INT(1, "for (int &&var&& = 0; &&var&& < &&max&&; &&var&&++) {", "}"),

  /**
   * empty code block
   */
  BLOCK(0, ""),

  /**
   * assign a variable a value
   * 
   * @param var identifier to change the value of
   * @param val value to assign to var
   */
  ASSIGN(1, "&&var&& = &&val&&;");
  
  protected final ArrayList<String> lines;
  protected final int innerLine;

  CodeType(int innerLine, String... lines) {
    this.lines = new ArrayList<String>(Arrays.asList(lines));
    this.innerLine = innerLine;
  }

}