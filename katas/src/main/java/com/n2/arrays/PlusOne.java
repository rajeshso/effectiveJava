package com.n2.arrays;
//https://www.geeksforgeeks.org/adding-one-to-number-represented-as-array-of-digits/
//https://codereview.stackexchange.com/questions/43343/add-one-to-a-number-represented-as-an-array-of-digits

import java.util.Arrays;

/**
 * Adding one to number represented as array of digits Given a non-negative number represented as an
 * array of digits, add 1 to the number ( increment the number represented by the digits ). The
 * digits are stored such that the most significant digit is first element of array.
 * <p>
 * Examples :
 * <p>
 * Input : [1, 2, 4] Output : [1, 2, 5]
 * <p>
 * Input : [9, 9, 9] Output : [1, 0, 0, 0]
 */
public class PlusOne {

  public static int[] solution(int[] a) {
    int[] result = plusOne(a);
    return result;
  }

  private static int[] plusOne(int[] a) {
    int len = a.length - 1;
    int carry = 1;
    while (len >= 0) {
      int componentToAdd = a[len];
      int sum = componentToAdd + carry;
      //split the last digit and the first digit
      int lastDigit = sum % 10;
      int firstDigit = sum / 10;
      //place digit in the nextPosition
      a[len] = lastDigit;
      //carry the first digit
      carry = firstDigit;
      len--;
    }
    if (carry != 0) {
      int[] j = new int[a.length + 1];
      System.arraycopy(a, 0, j, 1, a.length);
      System.out.println(Arrays.toString(j));
      j[0] = carry;
      a = j;
    }
    return a;
  }
}
