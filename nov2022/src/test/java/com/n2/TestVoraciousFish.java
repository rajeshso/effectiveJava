package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestVoraciousFish {
  @Test
  void test1() {
    VoraciousFish voraciousFish = new VoraciousFish();
    int[] weight = {4,3,2,1,5};
    int[] directions = {0,1,0,0,0};
    int survivors = voraciousFish.solution(weight, directions);
    System.out.println("survivors = "+survivors);
    assertThat(survivors).isEqualTo(2);
  }

  @Test
  void test2() {
    VoraciousFish voraciousFish = new VoraciousFish();
    int[] weight = new int[]{2, 6, 4, 3, 1, 5};
    int[] directions = new int[]{0,1,0,1,0,0};
    int survivors = voraciousFish.solution(weight, directions);
    System.out.println("survivors = "+survivors);
    assertThat(survivors).isEqualTo(2);
  }
  @Test
  void test3() {
    VoraciousFish voraciousFish = new VoraciousFish();
    int[] weight = new int[]{4,8,2,6,7};
    int[] directions = new int[]{0,1,1,0,0};
    int survivors = voraciousFish.solution(weight, directions);
    System.out.println("survivors = "+survivors);
    assertThat(survivors).isEqualTo(2);
  }
  @Test
  void test4() {
    VoraciousFish voraciousFish = new VoraciousFish();
    int[] weight = new int[]{4,3,2,1,5};
    int[] directions = new int[]{0,1,0,0,0};
    int survivors = voraciousFish.solution(weight, directions);
    System.out.println("survivors = "+survivors);
    assertThat(survivors).isEqualTo(2);
  }
}
