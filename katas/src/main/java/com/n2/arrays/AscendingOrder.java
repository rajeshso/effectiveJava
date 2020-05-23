package com.n2.arrays;

public class AscendingOrder {
  public static int[] arrangeAscending(int[] a) {
    int len = a.length;
    for(int i=0;i<len;i++) {
      System.out.println("*** Outer Iteration "+i+ " ***");
      for(int j=i;j<len;j++) {
        System.out.println("****** Inner Iteration "+j+ " ******");
        System.out.println("a["+i+"] = "+ a[i] +" and a["+j+"] = "+ a[j]);
        if (a[i]>a[j]) {
          System.out.println("Swapping a["+i+"] = "+ a[i] +" and a["+j+"] = "+ a[j]);
          int temp = a[j];
          a[j]=a[i];
          a[i]=temp;
          System.out.println("After Swapping a["+i+"] = "+ a[i] +" and a["+j+"] = "+ a[j]);
          print(a);
        }
      }
    }
    return a;
  }
  private static void print(int[] a) {
    System.out.println("\n");
    for(int i=0;i<a.length;i++) {
      System.out.print(a[i]+" ,");
    }
    System.out.println("\n");
  }
}
