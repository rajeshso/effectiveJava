package com.n2.codilitypractice;

import java.util.Arrays;

//https://rafal.io/posts/codility-intersecting-discs.html
public class IntersectionProblem {

  static public int[] solution(int[] a, int[] b) {
    Arrays.sort(a);
    Arrays.sort(b);
    int aLen = a.length;
    int bLen = b.length;
    int aIndex = 0;
    int bIndex = 0;
    int[] result = new int[aLen];
    int rIndex = 0;
    while (aIndex<aLen && bIndex<bLen) {
      int aVal = a[aIndex];
      int bVal = b[bIndex];
      if (aVal>bVal) {
        bIndex++;
      }else if (aVal<bVal) {
        aIndex++;
      }else {
        result[rIndex] = aVal;
        rIndex++;
        aIndex++;
        bIndex++;
      }
    }
    return Arrays.stream(result).dropWhile(it->it == 0).toArray();
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(new int[]{3,6,1,4,8}, new int[]{7,1,6,4,5})));
    System.out.println(Arrays.toString(solution(new int[]{31,61,11,41,81}, new int[]{7,1,6,4,5})));
    System.out.println(Arrays.toString(solution(new int[]{3,6,1,4,8}, new int[]{3,6,1,4,8})));

  }
}
