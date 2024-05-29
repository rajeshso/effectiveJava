package com.n2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;
import org.junit.jupiter.api.Test;

public class TestBrackets {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    char[] charArray = s.toCharArray();
    for (char c : charArray) {
      Character lastChar = null;
      if (!stack.isEmpty()) lastChar = stack.peek();
      if ((c == '(') || (c== '{' ) || (c=='[')) { //opening brackets
          stack.push(c);
      } else { // closing brackets
        if ( !stack.isEmpty() && isMatching(lastChar,c) ) {
          stack.pop();
        }else {
          return  false;
        }
      }
    }
    return stack.isEmpty();
  }
  private boolean isMatching(char start, char end) {
    return  (start == '(' && end == ')') ||
        (start == '{' && end == '}') ||
        (start == '[' && end == ']');
  }


//({[]}) this is also true
  @Test
  public void testSimple1() {
    String input = "({[]})";
    boolean actual = isValid(input);
    assertTrue(actual);
  }
  @Test
  public void testSimple2() {
    String input = "()";
    boolean actual = isValid(input);
    assertTrue(actual);
  }
  @Test
  public void testSimple3() {
    String input = "()[]{}";
    boolean actual = isValid(input);
    assertTrue(actual);
  }
  @Test
  public void testSimple4() {
    String input = "(]";
    boolean actual = isValid(input);
    assertFalse(actual);
  }
}
