package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
public class TestVoraciousFish {

  public int solution(int[] A, int[] B) {
    Deque<Integer> stack = new ArrayDeque();
    int survivors = 0;
    int len = A.length;
    final int left = 0;
    final int right = 1;
    for (int i = 0; i < len; i++) {
      int weight = A[i];
      int direction = B[i];
      if (stack.isEmpty() && direction == left) {
        survivors++;
      } else if (direction == right) {
        stack.push(weight);
      } else { // weight left.. start the fight
        int fishOutOfStack = stack.isEmpty() ? -1 : stack.pop();
        while (fishOutOfStack != -1 && fishOutOfStack < weight) {
          fishOutOfStack = stack.isEmpty() ? -1 : stack.pop();
        }
        if (fishOutOfStack == -1) {
          survivors++;
        } else {
          stack.push(fishOutOfStack);
        }
      }
    }
    return stack.size() + survivors;
  }

  @Test
  public void testFish() {
    int[] A = {4, 3, 2, 1, 5};
    int[] B = {0, 1, 0, 0, 0};
    final int result = solution(A, B);
    assertThat(result).isEqualTo(2);
  }
}
