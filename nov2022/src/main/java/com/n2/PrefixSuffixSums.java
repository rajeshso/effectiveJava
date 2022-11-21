package com.n2;

import java.util.Arrays;

public class PrefixSuffixSums {
  int[] getPrefix(int[] a) {
    int len = a.length;
    int[] result = new int[a.length];
    result[0]=a[0];
    for (int i = 1; i < len; i++) {
      result[i]=result[i-1]+a[i];
    }
    return result;
  }
  int[] getSuffix(int[] a) {
    int len = a.length;
    int[] result = new int[a.length];
    result[len-1]=a[len-1];
    for (int i = len-2; i >= 0; i--) {
      result[i]=result[i+1]+a[i];
    }
    return result;
  }

  public static void main(String[] args) {
    PrefixSuffixSums prefixSuffixSums = new PrefixSuffixSums();
    int[] data = {6,3,1,7,4,3};
    System.out.println(Arrays.toString(prefixSuffixSums.getPrefix(data)));
    System.out.println(Arrays.toString(prefixSuffixSums.getSuffix(data)));

  }
}
