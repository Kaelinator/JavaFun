package com.kaelkirk;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.kaelkirk.code.Assignment;
import com.kaelkirk.code.Class;
import com.kaelkirk.code.Code;
import com.kaelkirk.code.Declaration;
import com.kaelkirk.code.ForInt;
import com.kaelkirk.code.Method;
import com.kaelkirk.code.Println;
import com.kaelkirk.code.WriteableCode;

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

    return new Class(name).write( //
        Method.MAIN.write( //
            new Declaration("boolean[][]", "board", "new boolean[" + n + "][" + n + "]"), //
            generateLoops(n) //
        ) //
    ).toLines();
  }

  private static Code generateLoops(int n) {

    WriteableCode[] loops = new WriteableCode[n];

    for (int i = 0; i < n; i++) {
      String itrVar = "col" + i;
      loops[i] = new ForInt(itrVar, "board.length").write( //
          new Assignment("board[" + i + "][" + itrVar + "]", "true") //
      );
    }

    loops[n - 1].write( //
        new Println("col" + (n - 1)), //
        new Assignment("board[" + (n - 1) + "][col" + (n - 1) + "]", "false") //
    );

    for (int i = n - 2; i >= 0; i--) {
      String itrVar = "col" + i;
      loops[i].write( //
        loops[i + 1], //
        new Assignment("board[" + i + "][" + itrVar + "]", "false") //
      );
    }

    return loops[0];
  }

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