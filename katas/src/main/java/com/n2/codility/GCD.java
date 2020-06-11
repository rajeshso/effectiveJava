package com.n2.codility;

/**
 * Find the GCD of 742, 518 C=  Modulo A/B C = Modulo B/C Till the denominator becomes Zero, the
 * numberator is the GCD
 */
public class GCD {

  static public int solution1(int a, int b) {
    if (b > a) {
      int temp = a;
      a = b;
      b = temp;
    }
    int c = a % b;
    if (c == 0) {
      return b;
    }
    while (c != 0) {
      a = b;
      b = c;
      c = a % b;
    }
    return b;
  }

  static public int solution2(int a, int b) {
    if (b > a) {
      int temp = a;
      a = b;
      b = temp;
    }
    int c = a % b;
    if (c == 0) {
      return b;
    } else {
      return solution2(b, c);
    }
  }

  static public int solution3(int a, int b) {
    return (b == 0) ? a : solution3(b, a % b);
  }

  public static void main(String[] args) {
    System.out.println("742,518 = " + solution1(742, 518));
    System.out.println("518,742 = " + solution1(518, 742));
    System.out.println("742,518 = " + solution2(742, 518));
    System.out.println("518,742 = " + solution2(742, 518));
    System.out.println("742,518 = " + solution3(742, 518));

  }
}
