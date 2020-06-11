package com.n2.codility;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/brackets/ A string S consisting
 * of N characters is considered to be properly nested if any of the following conditions is true:
 * <p>
 * S is empty; S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string; S has the
 * form "VW" where V and W are properly nested strings. For example, the string "{[()()]}" is
 * properly nested but "([)()]" is not.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(String S); }
 * <p>
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0
 * otherwise.
 * <p>
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the
 * function should return 0, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..200,000]; string S consists only of the following
 * characters: "(", "{", "[", "]", "}" and/or ")".
 */
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
