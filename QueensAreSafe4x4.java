public class QueensAreSafe4x4 {
  public static void main(String[] args) {
    boolean[][] board = new boolean[4][4];
    for (int col0 = 0; col0 < board.length; col0++) {
      board[0][col0] = true;
      for (int col1 = 0; col1 < board.length; col1++) {
        board[1][col1] = true;
        for (int col2 = 0; col2 < board.length; col2++) {
          board[2][col2] = true;
          for (int col3 = 0; col3 < board.length; col3++) {
            board[3][col3] = true;
            System.out.println(col3);
            board[3][col3] = false;
          }
          board[2][col2] = false;
        }
        board[1][col1] = false;
      }
      board[0][col0] = false;
    }
  }
}
