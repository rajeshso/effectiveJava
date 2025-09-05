package com.n2.arrays;

import static com.n2.arrays.AscendingOrder.arrangeAscending;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class TestAscendingOrder {

  @Test
  public void testSingleDigitIntegers() {
    int[] a = {5,3,7,2,9,8};
    assertThat(arrangeAscending(a)).containsExactly(2,3,5,7,8,9);
  }

  @Test
  public void testMySingleDigitIntegers() {
    int[] a = {5,3,7,2,9,8};
    assertThat(myarrangeAscending1(a)).containsExactly(2,3,5,7,8,9);
    assertThat(myarrangeAscending2(a)).containsExactly(2,3,5,7,8,9);
    assertThat(myarrangeAscending3(a)).containsExactly(2,3,5,7,8,9);
    assertThat(myarrangeAscending4(a)).containsExactly(2,3,5,7,8,9);
    assertThat(myarrangeAscending5(a)).containsExactly(2,3,5,7,8,9);
  }

  private int[] myarrangeAscending1(int[] a) {
    int len = a.length;
    for (int i = 0; i < len; i++) { //outer
      for (int j = i; j < len; j++) {//inner
        if (a[i] > a[j]) {
          int temp = a[i];
          a[i] = a[j];
          a[j] = temp;
          System.out.println(a[i]);
        }
      }
    }
    return a;
  }

  private int[] myarrangeAscending2(int[] a) {
    Arrays.sort(a);
    return a;
  }

  private int[] myarrangeAscending3(int[] a) {
    return Arrays.stream(a).sorted().toArray();
  }

  // Using Collections.sort with boxed stream
  private int[] myarrangeAscending4(int[] a) {
    return Arrays.stream(a).boxed().sorted().mapToInt(Integer::intValue).toArray();
  }

  // Using List.of (Java 9+) and Collections.sort
  private int[] myarrangeAscending5(int[] a) {
    List<Integer> list = Arrays.stream(a).boxed().collect(java.util.stream.Collectors.toList());
    Collections.sort(list);
    return list.stream().mapToInt(Integer::intValue).toArray();
  }

}
