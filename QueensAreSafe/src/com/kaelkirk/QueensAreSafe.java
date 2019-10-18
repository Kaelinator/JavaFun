package com.kaelkirk;

public class QueensAreSafe {
  public static void main(String[] args) {

    int n = parseN(args);
    System.out.printf("You chose %dx%d board.", n, n);
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

  public static void exitWithUsage() {
    System.out.println("Usage: queensaresafe N");
    System.exit(1);
  }
}