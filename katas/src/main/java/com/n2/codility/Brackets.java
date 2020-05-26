package com.n2.codility;

import java.util.ArrayDeque;
import java.util.Deque;

public class Brackets {
  private static final char SQUARE_OPEN = '[';
  private static final char SQUARE_CLOSE = ']';
  private static final char ROUND_OPEN = '(';
  private static final char ROUND_CLOSE = ')';
  private static final char CURLY_OPEN = '{';
  private static final char CURLY_CLOSE = '}';
  public static boolean solution(String a) {
    final char[] chars = a.toCharArray();
    Deque<Character> stack = new ArrayDeque<Character>();
    for (char c : chars) {
      if (c ==SQUARE_OPEN || c == ROUND_OPEN || c == CURLY_OPEN) {
        stack.push(c);
      }else if (c == SQUARE_CLOSE) {
        if (stack.peekFirst().equals(SQUARE_OPEN)) {
          stack.pop();
        }else {
          return false;
        }
      }else if (c == ROUND_CLOSE) {
        if ( stack.peekFirst().equals(ROUND_OPEN) ) {
          stack.pop();
        }else {
          return false;
        }
      }else if (c == CURLY_CLOSE ) {
        if ( stack.peekFirst().equals(CURLY_OPEN)) {
          stack.pop();
        }else {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    System.out.println(solution("[({]})]()"));
    System.out.println(solution("[({[})](])"));
    System.out.println(solution("[({})]()"));
  }
}
