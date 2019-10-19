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
import com.kaelkirk.code.ForEach;
import com.kaelkirk.code.ForInt;
import com.kaelkirk.code.If;
import com.kaelkirk.code.Method;
import com.kaelkirk.code.Print;
import com.kaelkirk.code.Println;
import com.kaelkirk.code.PrivateStaticMethod;
import com.kaelkirk.code.PrivateStaticVoidMethod;
import com.kaelkirk.code.Return;
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
        ), //
        new PrivateStaticVoidMethod("printBoard", "boolean[][] board").write( //
            new ForEach("boolean[]", "row", "board").write( //
                new ForEach("boolean", "cell", "row").write( //
                    new Print("cell ? 'Q' : '.'"), //
                    new Print("\" \"")), //
                new Println() //
            ), //
            new Println() //
        ), //
        new PrivateStaticMethod("boolean", "queensAreSafe", "boolean[][] board").write( //
            new Declaration("int[][]", "queenCoordinates", "findQueens(board)"), //
            new If("queenCoordinates == null").write( //
                new Return("false") //
            ), //
            new ForInt("i", "0", "i < queenCoordinates.length && queenCoordinates[i] != null").write( //
                new Declaration("int[]", "queenA", "queenCoordinates[i]"), //
                new ForInt("j", "i + 1", "j < queenCoordinates.length && queenCoordinates[j] != null").write( //
                    new Declaration("int[]", "queenB", "queenCoordinates[j]"), //
                    new Declaration("boolean", "row", "queenA[0] == queenB[0]"),
                    new Declaration("boolean", "column", "queenA[1] == queenB[1]"),
                    new Declaration("boolean", "positiveDiagonal",
                        "(queenA[0] + queenA[1]) == (queenB[0] + queenB[1])"),
                    new Declaration("boolean", "negativeDiagonal",
                        "(queenA[0] - queenA[1]) == (queenB[0] - queenB[1])"),
                    new If("row || column || positiveDiagonal || negativeDiagonal").write( //
                        new Return("false") //
                    ) //
                ) //
            ), //
            new Return("true") //
        ), //
        new PrivateStaticMethod("int[][]", "findQueens", "boolean[][] board").write( //
            new Declaration("int[][]", "queens", "new int[board.length][];"), //
            new Declaration("int", "lastQueenIndex", "0"), //
            new ForInt("row", "board.length").write( //
                new ForInt("col", "board.length").write( //
                    new If("board[row][col] && lastQueenIndex < queens.length").write( //
                        new Assignment("queens[lastQueenIndex++]", "new int[] {row, col}") //
                    ), //
                    new If("board[row][col]").write( //
                        new Return("null") //
                    ) //
                ) //
            ), //
            new Return("queens")) //
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
        new If("queensAreSafe(board)").write("printBoard(board);"), //
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