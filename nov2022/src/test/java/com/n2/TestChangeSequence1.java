package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//Given 1…..10, output should be 1,10,  2, 9,  3,8,  4,7 , 5,6
public class TestChangeSequence1 {

  int[] solution1(int[] a) {
    int len = a.length;
    int[] result = new int[len];
    int forwardCounter = 0;
    int reverseCounter = len - 1;
    int resultCounter = 0;
    int runs = (int) Math.ceil((double) len / 2);
    for (int i = 0; i < runs; i++) {
      if ((forwardCounter)>len) break;
      result[resultCounter++] = a[forwardCounter++];
      if (reverseCounter<forwardCounter) break;
      result[resultCounter++] = a[reverseCounter--];
    }
    return result;
  }

  //Given 1…..10, output should be 1,10,  2, 9,  3,8,  4,7 , 5,6
  int[] solution2(int[] a) {
    int len = a.length;
    int forwardIndex = 0;
    int reverseIndex = len - 1;
    int[] result = new int[len];
    int resultIndex = 0;
    for (int i = 0; i < len; i++) {
      result[resultIndex++] = a[forwardIndex];
      if (resultIndex==len) break;
      result[resultIndex++] = a[reverseIndex];
      forwardIndex++;
      reverseIndex--;
      if (forwardIndex > reverseIndex) {
        break;
      }
      if (forwardIndex >= len) {
        break;
      }
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("provideRangeAndExpected")
  void testSimpleChange2(int[] given, int[] expected) {
    assertThat(solution1(given)).containsSequence(expected);
    assertThat(solution2(given)).containsSequence(expected);
  }

  private static Stream<Arguments> provideRangeAndExpected() {
    return Stream.of(
        Arguments.of(createRange(1, 10), new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5}),
        Arguments.of(createRange(1, 9), new int[]{1, 8, 2, 7, 3, 6, 4, 5})
    );
  }

  private static int[] createRange(int lo, int hi) {
    return IntStream.range(lo, hi).toArray();
  }
}
