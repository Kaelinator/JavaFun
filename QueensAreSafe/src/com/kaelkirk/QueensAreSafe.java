package com.kaelkirk;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QueensAreSafe {

  public static void main(String[] args) {

    int n = parseN(args);
    System.out.printf("You chose %dx%d board.", n, n);

    String name = String.format("QueensAreSafe%dx%d.java", n, n);
    List<String> code = generateCode(n);
    Path file = writeToFile(code, name);

    System.out.printf("Wrote file: %s", file.toAbsolutePath());
  }

  private static Path writeToFile(List<String> code, String name) {

    Path file = Paths.get(name);

    try {
      Files.write(file, code, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.err.println(e.getCause());
      System.err.println(e.getMessage());
      System.err.println(e.toString());
    }

    return file;
  }

  private static List<String> generateCode(int n) {
    ArrayList<String> lines = new ArrayList<>();
    lines.add("// TODO");
    return lines;
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