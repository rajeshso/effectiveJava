package com.n2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/brackets/
 * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 *
 * S is empty;
 * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * For example, the string "{[()()]}" is properly nested but "([)()]" is not.
 *
 * Write a function:
 *
 * class Solution { public int solution(String S); }
 *
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
 *
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..200,000];
 * string S is made only of the following characters: "(", "{", "[", "]", "}" and/or ")".
 */
public class Brackets {

  char[] openers = {'(','{','['};
  char[] closers = {')','}',']'};

  int solution(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    final char[] chars = s.toCharArray();
    for (char aChar : chars) {
      if ((aChar =='{') || (aChar == '[') || (aChar=='(') ) {
        stack.push(aChar);
      } else if ((stack.peek() == '{') && (aChar == '}')) {
        stack.pop();
      }else if ((stack.peek() == '(') && (aChar == ')')) {
        stack.pop();
      }else if ((stack.peek() == '[') && (aChar == ']')) {
        stack.pop();
      }else {
        return 0;
      }
    }
    if (stack.size()==0) {
      return 1;
    }else {
      return 0;
    }
  }

  public static void main(String[] args) {
    Brackets b = new Brackets();
    System.out.println("{[()()]}  " + b.solution("{[()()]}"));
  //  System.out.println("([)()]  " + b.solution("([)()]"));
  //  System.out.println("[]()  " + b.solution("[]()"));
  }
}
