package com.n2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

  static Stream<Arguments> bracketProvider() {
    return Stream.of(
      Arguments.of("({[]})", true),
      Arguments.of("()", true),
      Arguments.of("()[]{}", true),
      Arguments.of("([)]", false),
      Arguments.of("((({{{[[[]]]}}})))", true),
      Arguments.of("([{}])", true),
      Arguments.of("([{})]", false),
      Arguments.of("", true),
      Arguments.of("(", false),
      Arguments.of(")", false)
    );
  }

  @ParameterizedTest
  @MethodSource("bracketProvider")
  void testIsValid(String input, boolean expected) {
    if (expected) {
      assertTrue(isValid(input));
    } else {
      assertFalse(isValid(input));
    }
  }
}
