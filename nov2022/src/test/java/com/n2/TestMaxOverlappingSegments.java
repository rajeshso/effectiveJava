package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/16-greedy_algorithms/max_nonoverlapping_segments/
//https://www.youtube.com/watch?v=oMBGXAxNl3E
//Overlapping = Beginning of the second element is behind the end of the first element
//Find maximum number of non-overlapping elements - Activity selection problem
public class TestMaxOverlappingSegments {
  public int solution(int[] A, int[] B) {
    int len = A.length;
    List<Pair> pairs = new ArrayList<>(len);
    Comparator<Pair> byA = Comparator.comparingInt(Pair::start);
    for (int i = 0; i < len; i++) {
      pairs.add(new Pair(A[i],B[i]));
    }
    List<Pair> pairsSorted = pairs.stream().sorted(byA).toList();
    int result = 0;
    for (int i = 1; i < len; i++) {
        Pair pair1 = pairsSorted.get(i-1);
        Pair pair2 = pairsSorted.get(i);
      //Overlapping = Beginning of the second element is behind the end of the first element
      if ( (pair2.start > pair1.end) ) {
          result++;
          System.out.println(pair2);
        }
    }
    result+=1; //add the first one
    return result;
  }
  record Pair(int start, int end) { }

  public int solution1(int[] A, int[] B) {
    int lastEndSegment = Integer.MIN_VALUE;
    int chosenCount = 0;
    int len = A.length;
    for (int i = 0; i < len; i++) {
      //Overlapping = Beginning of the second element is behind the end of the first element
      if (A[i] > lastEndSegment) {
        chosenCount++;
        lastEndSegment = B[i];
      }
    }
    return chosenCount;
  }
    @Test
  void simpleTest() {
    int[] a = {1,3,7,9,9};
    int[] b = {5,6,8,9,10};
    final int result = solution(a, b);
    assertThat(result).isEqualTo(3);
  }
  @Test
  void efficientTest() {
    int[] a = {1,3,7,9,9};
    int[] b = {5,6,8,9,10};
    final int result = solution1(a, b);
    assertThat(result).isEqualTo(3);
  }
}
