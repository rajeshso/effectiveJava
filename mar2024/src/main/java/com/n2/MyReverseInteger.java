package com.n2;
class MyReverseInteger {
  static Integer reverse1(Integer i) {
    StringBuilder s = new StringBuilder(String.valueOf(Math.abs(i)));
    final int i1 = Integer.parseInt(s.reverse().toString());
    if (i<0) return -i1; else return i1;
  }

  static Integer reverse2(Integer i) {
    int reversed = 0;
    while (i!=0) {
      int singleDigit = i%10;
      i = i/10;
      reversed = (reversed*10) + singleDigit;
    }
    return reversed;
  }

  public static void main(String[] args) {
    System.out.println("12345 = "  + reverse1(12345));
    System.out.println("-12345 = "  + reverse1(-12345));
    System.out.println("12345 = "  + reverse2(12345));
    System.out.println("-12345 = "  + reverse2(-12345));
  }
}
