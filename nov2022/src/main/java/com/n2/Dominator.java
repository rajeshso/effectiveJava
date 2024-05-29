package com.n2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

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
  int solutionTry(int[] a) {
    int size = a.length;
    int halfTheSize = size/2;
    Map<Integer, Integer> frequency = findFreq(a);
    // Find the element with frequency greater than half the size
    for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
      if (entry.getValue() > halfTheSize) {
        return entry.getKey(); // Return the dominator
      }
    }

    return -1;
  }

  private Map<Integer, Integer> findFreq(int[] a) {
    final Map<Integer, Long> integerLongMap = Arrays.stream(a).boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Comparator<Integer> valueComparator = (e1, e2) -> e1.compareTo(e2);
    TreeMap<Integer, Long> sortedByValue = new TreeMap<>(valueComparator);
    sortedByValue.putAll(integerLongMap);
    return sortedByValue.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> e.getValue().intValue()));
  }

  public static void main(String[] args) {
    Dominator dominator = new Dominator();
    int[] a = {1,2,4,1,2,3};
    System.out.println("dominator.solution(a) =" + dominator.solution(a));
    System.out.println("dominator.solutionEvenNicer(a) = " + dominator.solutionEvenNicer(a));
    System.out.println("dominator.solutionTry(a) = " + dominator.solutionTry(a));
  }
}
