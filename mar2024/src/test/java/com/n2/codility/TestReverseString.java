package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//Reverse string O(1) space complexity and No built-in function can be used
public class TestReverseString {

  @ParameterizedTest
  @MethodSource("sampleData")
  public void testSimple(String a) {
    assertThat(solution(a)).isEqualTo(new StringBuilder(a).reverse().toString());
  }
  private static Stream<Arguments> sampleData() {
    return Stream.of(
        Arguments.of("equal"),//5
        Arguments.of("even"),//4
        Arguments.of("1"),//1
        Arguments.of(""));//0 len
  }

  public String solution(String a) {
    final char[] charArray = a.toCharArray();
    reverseSwap(charArray, 0, charArray.length - 1);
    return String.valueOf(charArray);
  }
  private char[] reverseSwap(char[] a, int start, int end) {
    while(start<end) {
      System.out.println("start = " +a[start] +" end = "+a[end]);
      char temp = a[start];
      a[start] = a[end];
      a[end] = temp;
      start++;
      end--;
    }
    return a;
  }
}
