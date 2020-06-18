package com.n2.codility;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//Time start :19:30
//Time pause: 20:01
//Time restart : 20:35
//https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
public class VoraciousFish {


  public static int solution(int[] weight, int[] directions) {
    final int left = 0;
    final int right = 1;
    int survivors =0;
    Deque<Integer> survivorsStack = new ArrayDeque<Integer>(weight.length);
    Deque<Integer> weightOfSurvivorsStack = new ArrayDeque<Integer>(weight.length);

    for (int i = 0; i < directions.length; i++)  {
      if (directions[i] == left && survivorsStack.isEmpty()) {
        survivors+=1;
      }if (directions[i] == right && survivorsStack.isEmpty()) {
        survivorsStack.push(directions[i]);
        weightOfSurvivorsStack.push(weight[i]);
      }else if (!survivorsStack.isEmpty() && directions[i] == left && survivorsStack.peekFirst()==left) {
        survivorsStack.push(directions[i]);
        weightOfSurvivorsStack.push(weight[i]);
      }else if (!survivorsStack.isEmpty() && directions[i] == right && survivorsStack.peekFirst()==right) {
        survivorsStack.push(directions[i]);
        weightOfSurvivorsStack.push(weight[i]);
      }else if (!survivorsStack.isEmpty() && directions[i] == left && survivorsStack.peekFirst()==right) {
        //check, pop or push
        int weightOfLeft = weight[i];
        int weightOfRight = weightOfSurvivorsStack.peekFirst();
        if (weightOfLeft > weightOfRight) {
          survivorsStack.pop();
          weightOfSurvivorsStack.pop();
          survivorsStack.push(i);
          weightOfSurvivorsStack.push(weight[directions[i]]);
        }else {
          //do nothing
          System.out.println("do nothing");
        }
      }else if (!survivorsStack.isEmpty() && directions[i] == right && survivorsStack.peekFirst()==left) {
        survivorsStack.push(directions[i]);
        weightOfSurvivorsStack.push(weight[i]);
      }
    }
    survivors+=survivorsStack.size();
    return survivors;
  }

  public static int solution1(int[] weight, int[] directions) {
    Stack<Integer> stack = new Stack<>();
    final int left = 1;
    int survivors = 0;
    for (int i = 0; i < weight.length; i++) {
      if (directions[i] == left) {
        stack.push(weight[i]);
      }else {
        int weightDown = stack.isEmpty() ? -1 : stack.pop();
        while (weightDown != -1 && weightDown<weight[i]) {
          weightDown = stack.isEmpty() ? -1 : stack.pop();
        }
        if (weightDown == -1) {
          survivors+=1;
        }else {
          stack.push(weightDown);
        }
      }
    }
    return stack.size()+survivors;
  }
  public static void main(String[] args) {
    int[] weight = {4, 3, 2, 1, 5};
    int[] directions = {0, 1, 0, 0, 0};
    System.out.println(solution(weight, directions));
    weight = new int[]{4, 8, 2, 6, 7};
    directions = new int[]{0, 1, 0, 0, 0};
    System.out.println(solution1(weight, directions));
  }
}
