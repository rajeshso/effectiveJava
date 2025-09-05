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
  public static int[] solution1(int[] a) {
    //get the len
    //init the result array with one more size
    //Start from the last digit
    //Add 1 and place the item in the result
    //carry over the remainder to the next digit
    //Add the current item and the carry over
    //return the result array
    int len = a.length;
    int[] result = new int[len];
    int carry = 1;
    for (int i = len-1; i >=0; i--) {
      System.out.println("a[i] = "+ a[i]);
      int sum = a[i] + carry;
      int lastDigit = sum % 10;
      int firstDigit = sum / 10;
      result[i] = lastDigit;
      carry = firstDigit;
      System.out.println("result[i] = "+ result[i]);
      System.out.println("carry = " + carry);
    }
    if (carry != 0) {
      int[] j = new int[a.length + 1];
      System.arraycopy(result, 0, j, 1, a.length);
      j[0] = carry;
      System.out.println(Arrays.toString(j));
      return j;
    }
    System.out.println(Arrays.toString(result));
    return result;
  }
  public static int[] solution3(int[] a) {
    //get the len
    //Start from the last digit
    //Add 1 and place the item in the result
    //carry over the remainder to the next digit
    //Add the current item and the carry over
    //return the result array
    int len = a.length;
    //int[] result = new int[len];
    int carry = 1;
    for (int i = len-1; i >=0; i--) {
      int sum = a[i] + carry;
      int lastDigit = sum % 10;
      int firstDigit = sum / 10;
      a[i] = lastDigit;
      carry = firstDigit;
    }
    if (carry != 0) {
      int[] j = new int[a.length + 1];
      System.arraycopy(a, 0, j, 1, a.length);
      j[0] = carry;
      System.out.println(Arrays.toString(j));
      return j;
    }
    System.out.println(Arrays.toString(a));
    return a;
  }
  public static int[] solution4(int[] a) {
    var carry = 1;
    for (int i = a.length-1; i >=0; i--) {
      var sum = a[i] + carry;
      a[i] = sum % 10;
      carry = sum / 10;
    }
    if (carry != 0) {
      int[] j = new int[a.length + 1];
      System.arraycopy(a, 0, j, 1, a.length);
      j[0] = carry;
      System.out.println(Arrays.toString(j));
      return j;
    }
    System.out.println(Arrays.toString(a));
    return a;
  }
  public static void main(String[] args) {
    // solution1(new int[] {1, 2, 3});
    // solution1(new int[] {1, 2, 9});
    // solution1(new int[] {9, 9, 9});

     solution4(new int[] {1, 2, 3});
     solution4(new int[] {1, 2, 9});
     solution4(new int[] {9, 9, 9});

  }
}
