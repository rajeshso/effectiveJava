package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

//Given 1…..10, output should be 1,10,  2, 9,  3,8,  4,7 , 5,6
public class TestChangeSequence1 {

  int[] change1(int[] a) {
    int len = a.length;
    int[] result = new int[len];
    int forwardCounter = 0;
    int reverseCounter = len-1;
    int resultCounter = 0;
    int runs = (int) Math.ceil((double)len/2);
    for (int i = 0; i < runs; i++) {
      if ((forwardCounter)>len) break;
      result[resultCounter++] = a[forwardCounter++];
      if (reverseCounter<forwardCounter) break;
      result[resultCounter++] = a[reverseCounter--];
    }
    return result;
  }

  @Test
  void simpleChange1() {
    int[] range = createRange(1, 10);
    int[] expected = {1,9,  2, 8,  3,7,  4,6 , 5};
    int[] actual = change1(range);
    assertThat(actual).containsSequence(expected);
  }
  @Test
  void simpleChange2() {
    int[] range = createRange(1, 9);
    int[] expected = {1,8,  2, 7,  3,6,  4,5};
    int[] actual = change1(range);
    assertThat(actual).containsSequence(expected);
  }

  private static int[] createRange(int lo,int hi) {
    return IntStream.range(lo,hi).toArray();
/*  int[] input = new int[hi-lo];
    int counter = 0;
    for (int i = lo; i < hi; i++) {
      input[counter++] = i;
    }
    return input;*/
  }
}
