package com.kaelkirk;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.kaelkirk.code.Code;
import com.kaelkirk.code.CodeType;

public class QueensAreSafe {

  public static void main(String[] args) {

    int n = parseN(args);
    System.out.printf("You chose %dx%d board.\n", n, n);

    String name = String.format("QueensAreSafe%dx%d", n, n);
    List<String> code = generateCode(n, name);
    Path file = writeToFile(code, name);

    System.out.printf("Wrote file: %s", file.toAbsolutePath());
  }

  private static Path writeToFile(List<String> code, String name) {

    Path file = Paths.get(name + ".java");

    try {
      Files.write(file, code, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.err.println(e.getCause());
      System.err.println(e.getMessage());
      System.err.println(e.toString());
    }

    return file;
  }

  private static List<String> generateCode(int n, String name) {

    HashMap<String, String> classArgs = new HashMap<String, String>();
    classArgs.put("className", name);

    HashMap<String, String> board = new HashMap<>();
    board.put("declaredType", "boolean[][]");
    board.put("var", "board");
    board.put("type", "boolean[8][8]");

    return new Code(CodeType.CLASS)
      .setArgs(classArgs)
      .toLines();

    // return CodeType.CLASS.setArgs(classArgs).write(
    //   CodeType.MAIN.write(
    //     CodeType.NEW.setArgs(board),
    //     generateLoops(n)
    //   )
    // ).toLines();
  }

  // private static Code generateLoops(int n) {

  //   Code[] loops = new Code[n];

  //   for (int i = 0; i < n; i++) {
  //     HashMap<String, String> forLoop = new HashMap<>();
  //     forLoop.put("var", "col" + n);
  //     forLoop.put("max", "board.length");

  //     HashMap<String, String> assignment = new HashMap<>();
  //     assignment.put("var", "board[" + n + "][col" + n +"]");
  //     assignment.put("val", "true");

  //     loops[i] = CodeType.FOR_INT.setArgs(forLoop).write(
  //         CodeType.ASSIGN.setArgs(assignment)
  //       );
  //   }

  //   HashMap<String, String> printArgs = new HashMap<>();
  //   printArgs.put("contents", "Are the queens safe?");

  //   loops[n - 1].write(CodeType.PRINTLN.setArgs(printArgs));

  //   for (int i = n - 1; i > 0; i--) {

  //     HashMap<String, String> assignment = new HashMap<>();
  //     assignment.put("var", "board[" + n + "][col" + n +"]");
  //     assignment.put("val", "false");

  //     loops[i - 1].write(
  //       loops[i].write(CodeType.ASSIGN.setArgs(assignment))
  //     );
  //   }

  //   return loops[0];
  // }

  private static int parseN(String[] args) {
    if (args.length != 1)
      exitWithUsage();

    int n = -1;
    try {
      n = Integer.parseUnsignedInt(args[0]); // >= 0
    } catch (Exception e) {
      System.err.println("N must be an integer >= 0.");
      exitWithUsage();
    }

    return n;
  }

  private static void exitWithUsage() {
    System.out.println("Usage: queensaresafe N");
    System.exit(1);
  }
}