package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/15-caterpillar_method/count_distinct_slices/
public class TestCountDistinctSlices {

  public int solution(int[] a) {
    int length = a.length;
    int tailIndex = 0;
    int headIndex = 1;
    int distinctCount=0;
    while (headIndex<length+2) {
      int[] aSubArray = Arrays.copyOfRange(a, tailIndex, headIndex);
      if (isDistinct(aSubArray)) {
        System.out.println(Arrays.toString(aSubArray));
        distinctCount++;
        headIndex++;
      }else {
        tailIndex++;
        headIndex=tailIndex+1;
      }
    }
    return distinctCount;
  }

  public int solution1(int[] a) {
    int n = a.length;
    int distinctCount = 0;
    Set<Integer> seen = new HashSet<>();
    int tailIndex = 0;

    for (int headIndex = 0; headIndex < n; headIndex++) {
      while (tailIndex < n && !seen.contains(a[tailIndex])) {
        seen.add(a[tailIndex]);
        distinctCount += (tailIndex - headIndex) + 1;
        tailIndex++;
/*        if (distinctCount > 1_000_000_000) {
          return 1_000_000_000;
        }*/
      }
      seen.remove(a[headIndex]);
    }
    return distinctCount;
  }

  private boolean isDistinct(int[] a) {
    long originalCount = a.length;
    long distinctCount = Arrays.stream(a).distinct().count();
    return originalCount == distinctCount;
  }
  @Test
  void simpleTest() {
    int[] a = {3,4,5,5,2};
    int correctAnswer = 9;
    assertThat(solution(a)).isEqualTo(correctAnswer);
  }
}
