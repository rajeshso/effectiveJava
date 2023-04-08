package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

//Use caterpillar method to calculate moving average
public class TestMovingAverage {
  public double[] solution(int[] a, int slidingWindow) {
    int length = a.length;
    if (slidingWindow>length) return new double[]{};
    int subArraySum = Arrays.stream(Arrays.copyOfRange(a, 0, slidingWindow)).sum();
    int tailIndex = 1;
    int headIndex = slidingWindow;
    double[] result = new double[length-slidingWindow+1];
    int resultIndex = 0;
    result[resultIndex++] = (double)subArraySum/slidingWindow;
    while (headIndex<length) {
      int beforeValue = a[tailIndex-1];
      int currentValue = a[headIndex];
      int currentSum = subArraySum - beforeValue + currentValue;
      result[resultIndex++] = (double)currentSum/slidingWindow;
      subArraySum = currentSum;
      tailIndex++;
      headIndex++;
    }
    return result;
  }
  @Test
  void simpleTest() {
    int[] a = {2,4,1,7,3,9,6,3,2,5,8,7,1};
    int slidingWindow = 5;
    double[] expected = {3.4,4.8,5.2,5.6,4.6,5,4.8,5,4.6};
    double[] actual = solution(a,slidingWindow);
    assertThat(actual).containsExactly(expected);
  }
}
