package com.n2;

import java.util.Arrays;

public class RememberByHeartTips {

  /**
   * toCharArray() //get char array of a String
   * charAt(int x) //get a char at the specific index
   * length() //string length
   * length //array size
   * substring(int beginIndex)
   * substring(int beginIndex, int endIndex)
   * Integer.valueOf()//string to integer
   * String.valueOf()/integer to string
   * Arrays.sort()  //sort an array
   * Arrays.toString(char[] a) //convert to string
   * Arrays.copyOf(T[] original, int newLength)
   * System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
   */
  public static void main(String[] args) {
    String s = "Rajesh";
    System.out.println("s.substring(0) for "+ s + " = " +s.substring(0));
    System.out.println("s.substring(2,4) for "+ s + " = " +s.substring(2,4));
    int[] arrayCopy = Arrays.copyOf(new int[]{1,2,3,4,5}, 2);
    System.out.println("Arrays.copyOf");
    print(arrayCopy);
    int[] src = new int[]{1,2,3,4,5};
    int[] dest = new int[3];
    System.arraycopy(src,2,dest, 2,1);
    print(dest);
  }
  private static void print(int[] a) {
    System.out.println("\n");
    for(int i=0;i<a.length;i++) {
      System.out.print(a[i]+" ,");
    }
    System.out.println("\n");
  }
}
