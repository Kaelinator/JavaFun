package com.kaelkirk;

/**
 * QueensAreSafeExample
 */
public class QueensAreSafeExample {

  public static void main(String[] args) {

    boolean[][] board = new boolean[8][8];

    for (int col0 = 0; col0 < board.length; col0++) {
      board[0][col0] = true;
      for (int col1 = 0; col1 < board.length; col1++) {
        board[1][col1] = true;
        for (int col2 = 0; col2 < board.length; col2++) {
          board[2][col2] = true;
          for (int col3 = 0; col3 < board.length; col3++) {
            board[3][col3] = true;
            for (int col4 = 0; col4 < board.length; col4++) {
              board[4][col4] = true;
              for (int col5 = 0; col5 < board.length; col5++) {
                board[5][col5] = true;
                for (int col6 = 0; col6 < board.length; col6++) {
                  board[6][col6] = true;
                  for (int col7 = 0; col7 < board.length; col7++) {
                    board[7][col7] = true;
                    if (queensAreSafe(board))
                      printBoard(board);
                    board[7][col7] = false;
                  }
                  board[6][col6] = false;
                }
                board[5][col5] = false;
              }
              board[4][col4] = false;
            }
            board[3][col3] = false;
          }
          board[2][col2] = false;
        }
        board[1][col1] = false;
      }
      board[0][col0] = false;
    }
  }

  private static void printBoard(boolean[][] board) {
    for (boolean[] row : board) {
      for (boolean cell : row)
        System.out.print((cell) ? "Q " : ". ");
      System.out.println();
    }
    System.out.println();
  }

  public static boolean queensAreSafe(boolean[][] board) {

    int[][] queenCoordinates = findQueens(board);

    if (queenCoordinates == null)
      return false;

    // check each queen against one another
    for (int i = 0; i < queenCoordinates.length && queenCoordinates[i] != null; i++) {
      int[] queenA = queenCoordinates[i];
      for (int j = i + 1; j < queenCoordinates.length && queenCoordinates[j] != null; j++) {
        int[] queenB = queenCoordinates[j];

        boolean row = (queenA[0] == queenB[0]);
        boolean column = (queenA[1] == queenB[1]);
        boolean positiveDiagonal = (queenA[0] + queenA[1]) == (queenB[0] + queenB[1]);
        boolean negativeDiagonal = (queenA[0] - queenA[1]) == (queenB[0] - queenB[1]);

        if (row || column || positiveDiagonal || negativeDiagonal)
          return false;
      }
    }

    return true;
  }

  private static int[][] findQueens(boolean[][] board) {

    int[][] queens = new int[board.length][];
    int lastQueenIndex = 0;

    for (int row = 0; row < board.length; row++)
      for (int col = 0; col < board.length; col++)
        if (board[row][col] && lastQueenIndex < queens.length)
          queens[lastQueenIndex++] = new int[] { row, col };
        else if (board[row][col])
          return null;

    return queens;
  }

}