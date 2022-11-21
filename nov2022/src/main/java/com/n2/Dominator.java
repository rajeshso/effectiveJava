package com.n2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

//https://app.codility.com/programmers/lessons/8-leader/dominator/
//https://www.udemy.com/course/beat-the-codility-coding-interview-in-java/learn/lecture/14143087#overview
//Hint https://www.udemy.com/course/beat-the-codility-coding-interview-in-java/learn/lecture/14143085#overview (hint)
//Remember the important hint - You have to remove two dissimilar elements of the array to achieve the desired o(n)
public class Dominator {
  //Hint: If you continuosly remove two consecutively different elements of an array, the remaining digit or same digits is the dominator
  public int solution(int[] a) {
    int len = a.length;
    Stack<Integer> stack = new Stack<>();
    for (int i1: a) {
      if (stack.empty()) {
        stack.push(i1);
      } else if (stack.peek() == i1) {
        stack.push(i1);
      }else {
        stack.pop();
      }
    }
    if (!stack.empty()) {
      int possibleDominator = stack.peek();
      final long count = Arrays.stream(a).filter(i -> i == possibleDominator).count();
      return count > len/2 ? possibleDominator: -1; //The stack will contain only the similar items
    } else {
      return -1;
    }
  }
  int solutionEvenNicer(int[] a) {
    int consecutiveSize = 0;
    int candidate = 0;
    int len = a.length;
    for(int item : a) {
      if (consecutiveSize == 0) {
        candidate = item;
        consecutiveSize = 1;
      }else if (candidate == item) {
        consecutiveSize++;
      }else {
        consecutiveSize--;
      }
    }
    int possibleDominator = candidate;
    final long count = Arrays.stream(a).filter(i -> i == possibleDominator).count();
    return count > len/2 ? possibleDominator: -1; //The stack will contain only the similar items
  }
  int solutionWrong(int[] a) {
    int size = a.length;
    int halfTheSize = size/2;
    List<Integer> dominators = new ArrayList<>();
    Map<Integer, Integer> freq = findFreq(a);
    final Set<Entry<Integer, Integer>> entries = freq.entrySet();
    final Iterator<Entry<Integer, Integer>> iterator = entries.iterator();

    return 1;
  }

  private Map<Integer, Integer> findFreq(int[] a) {
    //Arrays.stream(a).boxed().collect(Coll)
    //Arrays.stream(a).collect()
    Map<Integer, Integer> result = new HashMap<>();
    for (int j : a) {
      final Integer value = result.get(j);
      if (value == null) {
        result.put(j, 1);
      } else {
        result.put(j, value + 1);
      }
    }
    System.out.println(result);
    return result;
  }

  public static void main(String[] args) {
    Dominator dominator = new Dominator();
    int[] a = {1,2,4,1,2,3};
    dominator.solution(a);
  }
}
