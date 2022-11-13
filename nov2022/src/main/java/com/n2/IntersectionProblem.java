package com.n2;

import java.util.Arrays;

public class IntersectionProblem {
  int[] solution1(int[] a, int[] b) {
    int len = a.length;
    int[] result = new int[len];
    int resultIndex = 0;
    if (a.length != b.length) {
      return result;
    }
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (a[i] == b[j]) {
          result[resultIndex++] = a[i];
        }
      }
    }
    return Arrays.copyOfRange(result, 0, resultIndex);
  }
  //This one belongs to Quadratic complexity
  //O(a*b) a==b==n O(n^2) Quadratic complexity is one of the many complexities of a higher scale

  //Another complexity of higher scale is exponential complexity, O(k^n)
  //Log linear O(n log n)
  //Linear O(n)
  //Log O(log n)
  //Best is O(1) constant
  int[] solution2(int[] a, int[] b) { //O(a+b)
    int len = a.length;
    int[] result = new int[len];
    int aIndex = 0;
    int bIndex = 0;
    int resultIndex =0;
    if (a.length != b.length) {
      return result;
    }
    Arrays.sort(a);//O(a log a) log linear
    Arrays.sort(b);//O(b log b) log linear
    do { //O(a+b) = O(n)
      if (a[aIndex] > b[bIndex]) {
        bIndex++;
      } else if (a[aIndex]< b[bIndex]) {
        aIndex++;
      }else { //a[aIndex] == b[bIndex]
        result[resultIndex++] = a[aIndex];
        aIndex++;
        bIndex++;
      }
    }while (aIndex < len-1 || bIndex < len-1);
    return Arrays.copyOfRange(result, 0, resultIndex);
  }
  //O(n log n + n log n + n) = O(2 (n log n) + n) = O(n log n)
}
