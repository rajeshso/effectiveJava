package com.n2;
//https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
//https://www.udemy.com/course/beat-the-codility-coding-interview-in-java/learn/lecture/14143001#overview

import java.util.ArrayDeque;
import java.util.Deque;
//Assume that fishes start moving only from right to left
public class VoraciousFish {
  int solution(int[] weight, int[] directions) {
    int left = 0;
    int right = 1;
    int survivingFishSize = 0;
    int len = weight.length;
    Deque<Integer> survivingFish = new ArrayDeque<>();//Store only Right moving fishes
    for (int i = 0; i < len; i++) {
      int weightOfNewFish = weight[i];
      int directionOfNewFish = directions[i];
      if (directionOfNewFish == right) {
        survivingFish.push(weightOfNewFish);
      }else {
        final int winner = battleWithRightSurvivors(survivingFish, weightOfNewFish);
        if (winner!=-1) {
          survivingFishSize++;
        }
      }
    }
    return survivingFishSize+survivingFish.size();
  }

  //Direction of the newFish is always Right
  private int battleWithRightSurvivors(Deque<Integer> survivingFish, int weightOfNewFish) {
    for (Integer lastSurvivingFish : survivingFish) {
      if (lastSurvivingFish < weightOfNewFish) {
        survivingFish.pop();
      } else {
        break;
      }
    }
    if (survivingFish.isEmpty()) {
      return weightOfNewFish;
    }else {
      return -1;
    }
  }

  public static void main(String[] args) {
    VoraciousFish voraciousFish = new VoraciousFish();
    int[] weight = {4,3,2,1,5};
    int[] directions = {0,1,0,0,0};
    int survivors = voraciousFish.solution(weight, directions);
    System.out.println("survivors = "+survivors);

    weight = new int[]{2, 6, 4, 3, 1, 5};
    directions = new int[]{0,1,0,1,0,0};
    survivors = voraciousFish.solution(weight, directions);
    System.out.println("survivors = "+survivors);
  }
}
