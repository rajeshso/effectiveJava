package com.n2;

class ReverseInteger {
  protected static int reverse(int n) {
    int quotient=0, remainder=0, result =0;
    while (n!=0) {
      result = result * 10;
      quotient = n/10;
      remainder = n%10;
      result = result + remainder;
      n = quotient;
    }
    System.out.printf("\n number is %n, quotient is %d,  remainder is %d and result is %d ", n, quotient, remainder, result);

    return result;
  }
}

