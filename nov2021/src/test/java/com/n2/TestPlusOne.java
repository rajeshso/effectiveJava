package com.n2;
//https://www.geeksforgeeks.org/adding-one-to-number-represented-as-array-of-digits/
//https://codereview.stackexchange.com/questions/43343/add-one-to-a-number-represented-as-an-array-of-digits

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestPlusOne {

  private int[] solution(int[] a) {
    int carry = 1;
    int len = a.length-1;
    while (len>=0) {
      int componentToAdd = a[len];
      int sum = componentToAdd+carry;
      int lastDigit = sum%10;
      int firstDigit = sum/10;
      a[len] = lastDigit;
      carry = firstDigit;
      len--;
    }
    if (carry!=0) {
      int[] j = new int[a.length+1];
      System.arraycopy(a,0,j,1,a.length);
      j[0]=carry;
      a=j;
    }
    return a;
  }

  @Test
  public void addOneSimple() {
    int[] a = {1, 2, 4};
    int[] expected = {1, 2, 5};
    assertThat(solution(a)).containsExactly(expected);
  }

  @Test
  public void addOneWithSimpleCarrier() {
    int[] a = {1, 2, 9};
    int[] expected = {1, 3, 0};
    assertThat(solution(a)).containsExactly(expected);
  }

  @Test
  public void addOneWithDoubleCarrier() {
    int[] a = {1, 9, 9};
    int[] expected = {2, 0, 0};
    assertThat(solution(a)).containsExactly(expected);
  }

  @Test
  public void addOneWithResultantSizeOfArrayBigger() {
    int[] a = {9, 9, 9};
    int[] expected = {1, 0, 0, 0};
    assertThat(solution(a)).containsExactly(expected);
  }
}
