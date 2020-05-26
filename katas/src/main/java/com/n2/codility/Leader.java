package com.n2.codility;

import java.util.Arrays;
import java.util.Stack;

public class Leader {

  //O(n^n)
  public static int solution(int[] a) {
    int len = a.length;
    int leader = -1, count = 0;
    int bIndex = -1;
    for (int i = 0; i < len; i++) {
      int b = a[i];
      int bCount = 0;
      for (int j = i; j < len; j++) {
        if (a[j] == b) {
          bCount += 1;
        }
      }
      if (bCount > len / 2) {
        count = bCount;
        leader = b;
        bIndex = i;
      }
    }
    return bIndex;
  }

  //O(n log n)
  //Hint: In a sorted array, the middle element "could" be a leader
  //TODO: Return index
  public static int solution1(int[] a) {
    int len = a.length;
    Arrays.sort(a);
    int mid = a[len / 2];
    int count = 0;
    for (int i = 0; i < len; i++) {
      if (a[i] == mid)
        count += 1;
    }
    if (count > (len / 2))
      return len / 2;
    else
      return -1;
  }

  //Hint: Remove two different numbers living adjacent. The last remaining number is the leader
  //O(n+n)
  //TODO: Return index
  public static int solution2(int[] a) {
    int len = a.length;
    int count = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < len; i++) {
      if (stack.isEmpty()) {
        stack.push(a[i]);
      }else {
        int lastItem = stack.pop();
        if (lastItem==a[i]) {
          stack.push(lastItem);
          stack.push(a[i]);
        }
      }
    }
    boolean emptyStack = stack.isEmpty();
    if (!emptyStack) {
      int possibleLeader = stack.pop();
      for (int i : a) {
        if (i == possibleLeader) count+=1;
      }
    }
    if (count> len/2) return stack.pop(); else return -1;
  }

  //performant of all
  public static int solution3(int[] a) {
    int candidate = 0;
    int count = 0;
    int consecutiveCandidate = 0;
    for ( int c : a) {
      if (count == 0) {
        candidate = c;
        count+=1;
      }else {
        consecutiveCandidate = c;
        if (candidate==consecutiveCandidate) {
          count+=1;
        }else {
          candidate = consecutiveCandidate;
          count-=1;
        }
      }
    }
    int candidateCount = 0;
    int index = 0;
    if (count>0) {
      for ( int i=0;i<a.length;i++) {
        if (a[i]==candidate) {
          candidateCount+=1;
          index = i;
        }
      }
    }
    if (candidateCount> (a.length/2) ) return index; else return -1;
  }

  public static void main(String[] args) {
    System.out.println(solution3(new int[]{2,4,3,3,3,2,3}));
    System.out.println(solution3(new int[]{1,2,3,4,5,6,7}));
  }
}
