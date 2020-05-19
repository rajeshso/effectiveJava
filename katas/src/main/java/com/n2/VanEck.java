package com.n2;

public class VanEck {
  //TODO: To work out
  public int[] vanEck(int n) {
    int[] result = new int[n];
    result[0] = 0;
    result[1] = 0;
    result[2] = 1;
    for(int i=3;i<n;i++) {
      int distance = findDistance(result);
      result[i] = distance;
    }
    return result;
  }
  public int findDistance(int[] sequence) {
    int resultInteger = 0;
    if (sequence.length<2) return resultInteger;
    int sequenceSize = sequence.length-2;
    int lastIndex = sequence.length-1;
    int number = sequence[lastIndex];
    while(sequenceSize!=0) {
      if (sequence[sequenceSize] == number) {
        resultInteger = lastIndex - sequenceSize;
        break;
      }
      sequenceSize--;
    }
    return resultInteger;
  }

  public static void main(String[] args) {
    final var vanEck = new VanEck();
    int result[] = vanEck.vanEck(10);
    System.out.println("result is ");
    for (int i = 0; i < result.length; i++) {
      System.out.print(result[i] + " ");
    }
  }
}
