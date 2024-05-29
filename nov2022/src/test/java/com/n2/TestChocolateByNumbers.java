package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/12-euclidean_algorithm/chocolates_by_numbers/
//Use Eculidean algorithm and the GCD divided by LCM method
public class TestChocolateByNumbers {
  public int solution(int N, int M) {
    List<Integer> eatenPositions = new ArrayList<>();
    int nextPosition;
    int currentPosition = 0;
    eatenPositions.add(currentPosition);//Let us eat the first chocolate
    nextPosition = (currentPosition+M)%N;// if you ate chocolate number currentPosition, then you will next eat the chocolate with number (currentPosition+M)% modulo N (remainder of division).
    while(!eatenPositions.contains(nextPosition)) {
      eatenPositions.add(nextPosition);
      currentPosition = nextPosition;
      nextPosition = (currentPosition+M)%N;
    }
    return eatenPositions.size();
  }
  public int solution1(int N, int M) {
    int lcm =  (N*M)/gcd(N,M);
    return lcm/M;
  }
  private int gcd(int a, int b) {
    if (b==0)
      return a;
    else
      return gcd(b,a%b);
  }

  @Test
  void simpleTest() {
    assertThat(solution(10,4)).isEqualTo(5);
  }
  @Test
  void betterTest() {
    assertThat(solution1(10,4)).isEqualTo(5);
  }
}
