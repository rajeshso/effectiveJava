package com.n2.codility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestNethermind3 {

  public boolean solution(String[] B) {
    int rows = B.length;
    int cols = B[0].length();
    int assassinRow = -1, assassinCol = -1;
    boolean assassinFound = false;

    // Convert String array to char array for easier processing
    char[][] board = new char[rows][cols];
    for (int i = 0; i < rows; i++) {
      board[i] = B[i].toCharArray();
    }

    // Find the assassin's position
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (board[i][j] == 'A') {
          assassinRow = i;
          assassinCol = j;
          assassinFound = true;
          break;
        }
      }
    }

    // Check if the assassin wasn't found
    if (!assassinFound) {
      // Handle case where assassin is not present (optional: return false or throw exception)
      return false;
    }

    // Check if the assassin is in the bottom right corner
    if (assassinRow == rows - 1 && assassinCol == cols - 1) {
      return true;
    }

    // Check if the assassin is blocked by a guard
    for (int i = assassinRow; i < rows; i++) {
      for (int j = assassinCol; j < cols; j++) {
        if (board[i][j] == 'X') {
          return false;
        }
      }
    }

    // Check if the assassin can move down or right
    return (assassinRow < rows - 1 && board[assassinRow + 1][assassinCol] != '.') ||
        (assassinCol < cols - 1 && board[assassinRow][assassinCol + 1] != '.');
  }

  @Test
  public void testSimple1() {
    // Test case 1: Assassin can't see guard (bottom right blocked)
    String[] board1 = {"...", ">.."};
    System.out.println("Test Case 1: " + solution(board1)); // Output: False
    assertFalse(solution(board1));
  }
  @Test
  public void testSimple2() {
    // Test case 2: Assassin can reach bottom right unseen (guard's view limited)
    String[] board2 = {"...", ".X.", "AX^", ".XX."};
    System.out.println("Test Case 2: " + solution(board2)); // Output: True
    assertTrue(solution(board2));
  }
  @Test
  public void testSimple3() {
    // Test case 3: Assassin can't see guard (blocked on the way)
    String[] board3 = {"A.v", "..."};
    System.out.println("Test Case 3: " + solution(board3)); // Output: False
    assertFalse(solution(board3));

  }
}
