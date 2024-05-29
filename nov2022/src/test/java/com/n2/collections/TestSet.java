package com.n2.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TestSet {


  private List<Integer> removeDuplicates(List<Integer> numbers) {
    return numbers.stream().distinct().toList();
  }

  private List<Integer> findCommonElements(List<Integer> list1, List<Integer> list2) {
    return list1.stream().filter(list2::contains).toList();
  }

  private int countUniqueChars(String str) {
    return (int) str.chars().distinct().count();
  }

  private Set<Integer> findSymmetricDifference(Set<Integer> set1, Set<Integer> set2) {
    Set<Integer> uniqueSet1 = set1.stream().filter(a->!set2.contains(a)).collect(Collectors.toSet());
    Set<Integer> uniqueSet2 = set2.stream().filter(a->!set1.contains(a)).collect(Collectors.toSet());
    uniqueSet1.addAll(uniqueSet2);
    return uniqueSet1;
  }

  @Test
  public void testRemoveDuplicates_positiveCase() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 1, 5);
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> result = removeDuplicates(numbers);
    assertEquals(expected, result);
  }

  @Test
  public void testRemoveDuplicates_negativeCase() {
    List<Integer> numbers = List.of();
    List<Integer> expected = List.of();
    List<Integer> result = removeDuplicates(numbers);
    assertEquals(expected, result);
  }
  @Test
  public void testFindCommonElements_positiveCase() {
    List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
    List<Integer> expected = Arrays.asList(3, 4, 5);
    List<Integer> result = findCommonElements(list1, list2);
    assertEquals(expected, result);
  }



  @Test
  public void testFindCommonElements_negativeCase() {
    List<Integer> list1 = Arrays.asList(1, 2, 3);
    List<Integer> list2 = Arrays.asList(4, 5, 6);
    List<Integer> expected = List.of();
    List<Integer> result = findCommonElements(list1, list2);
    assertEquals(expected, result);
  }
  @Test
  public void testCountUniqueChars_positiveCase() {
    String str = "Hello, World!";
    int expected = 10;
    int result = countUniqueChars(str);
    assertEquals(expected, result);
  }

  @Test
  public void testCountUniqueChars_negativeCase() {
    String str = "";
    int expected = 0;
    int result = countUniqueChars(str);
    assertEquals(expected, result);
  }
  @Test
  public void testFindSymmetricDifference_positiveCase() {
    Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
    Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
    Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 6, 7));
    Set<Integer> result = findSymmetricDifference(set1, set2);
    assertEquals(expected, result);
  }

  @Test
  public void testFindSymmetricDifference_negativeCase() {
    Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
    Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 2, 3));
    Set<Integer> expected = new HashSet<>();
    Set<Integer> result = findSymmetricDifference(set1, set2);
    assertEquals(expected, result);
  }
}
