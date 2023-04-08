package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
// Highest value of coin or note denominations, calculate number of tins of paint to buy based on area
public class TestGreedyAlgorithm {

  public Map<Integer, Integer> solution1(int area, Integer[] paintSizes) {
    Arrays.sort(paintSizes, Collections.reverseOrder());
    Map<Integer, Integer> result = new HashMap<>();
    int counter = 0;
    while (counter<paintSizes.length) {
      Integer tin = paintSizes[counter];
      int numberOfTins = area/tin;
      if (numberOfTins>0) result.put(tin, numberOfTins);
      int remainingArea = area%tin;
      area = remainingArea;
      counter++;
    }
    if (result.isEmpty() && area>0) {
      result.put(paintSizes[paintSizes.length-1],1);
    }
    return result;
  }
  @Test
  void simpleTest() {
    int area= 17;
    Integer[] sizes = {6,3,1};
    Map<Integer,Integer> expected = Map.of(6,2,3,1,1,2);
    Map<Integer, Integer> result = solution1(area, sizes);
    assertThat(result).containsAllEntriesOf(expected);
  }
  @Test
  void lessAreaTest() {
    int area= 3;
    Integer[] sizes = {6,3,1};
    Map<Integer,Integer> expected = Map.of(3,1);
    Map<Integer, Integer> result = solution1(area, sizes);
    assertThat(result).containsAllEntriesOf(expected);
  }
  @Test
  void muchLesserAreaTest() {
    int area= 1;
    Integer[] sizes = {6,3};
    Map<Integer,Integer> expected = Map.of(3,1);
    Map<Integer, Integer> result = solution1(area, sizes);
    assertThat(result).containsAllEntriesOf(expected);
  }
  @Test
  void simpleNoteTest() {
    int note= 67;
    Integer[] denominations = {1,2,5,10,20,50,100};
    Map<Integer,Integer> expected = Map.of(50,1,10,1,5,1,2,1);
    Map<Integer, Integer> result = solution1(note, denominations);
    assertThat(result).containsAllEntriesOf(expected);
  }
}
