package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

//Application of Caterpillar method to check if a subarray has a total of a given value
public class TestSubArrayTotal {
  public boolean soultion(int[] a, int k) {
    int len = a.length;
    int tailIndex = 0;//note both indices start at 0
    int headIndex = 0;
    int currentSum = a[0];
    if (currentSum==k) return true;
    while (headIndex<len) {
      if (currentSum==k) {
        System.out.println("tailIndex =" +tailIndex+ " headIndex = "+headIndex);
        return true;
      }
      else if (currentSum<k) {
        headIndex++;
        currentSum = currentSum + a[headIndex];
      }else if (currentSum>k){
        currentSum = currentSum - a[tailIndex];
        tailIndex++;
      }
    }
    return false;
  }

  @Test
  void simpleTest() {
    int[] a = {2,4,1,7,3,9,6,3,2,5,8,7,1};
    int k= 16;
    assertThat(soultion(a,k)).isTrue();
  }
}
