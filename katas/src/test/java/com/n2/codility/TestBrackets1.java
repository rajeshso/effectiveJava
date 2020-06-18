package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/7-stacks_and_queues/brackets/
public class TestBrackets1 {

  public int solution1(String S) {
    Deque<Character> stack = new ArrayDeque<>();
    int len = S.length();
    final char[] chars = S.toCharArray();
    for (int i = 0; i < len; i++) {
      if (chars[i] == '{' || chars[i] == '[' || chars[i] == '(') {
        stack.push(chars[i]);
      } else if (chars[i] == '}' || chars[i] == ']' || chars[i] == ')') {
        if (stack.isEmpty()) {
          return -1;
        }
        final Character lastOpened = stack.pop();
        if (chars[i] == '}' && lastOpened != '{') {
          return -1;
        } else if (chars[i] == ']' && lastOpened != '[') {
          return -1;
        } else if (chars[i] == ')' && lastOpened != '(') {
          return -1;
        }
      } else {
        return -1;
      }
    }
    if (!stack.isEmpty()) {
      return -1;
    }
    return 0;
  }

  public int solution2(String S) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : S.toCharArray()) {
      if (c == '{' || c == '[' || c == '(') {
        stack.push(c);
      } else if (c == '}') {
        if (stack.isEmpty() || stack.pop() != '{') {
          return -1;
        }
      } else if (c == ')') {
        if (stack.isEmpty() || stack.pop() != '(') {
          return -1;
        }
      } else if (c == ']') {
        if (stack.isEmpty() || stack.pop() != '[') {
          return -1;
        }
      }
    }
    return stack.isEmpty() ? 0 : -1;
  }

  @Test
  public void testPositive1() {
    String S = "{[()()]}";
    assertThat(solution1(S)).isZero();
    assertThat(solution2(S)).isZero();
  }

  @Test
  public void testPositive2() {
    String S = "{[()()]}()";
    assertThat(solution1(S)).isZero();
    assertThat(solution2(S)).isZero();
  }

  @Test
  public void testNegative1() {
    String S = "{[()()]}}";
    assertThat(solution1(S)).isEqualTo(-1);
    assertThat(solution2(S)).isEqualTo(-1);
  }

}
