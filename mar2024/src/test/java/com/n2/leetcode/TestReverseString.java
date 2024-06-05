package com.n2.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 *
 * You must do this by modifying the input array in-place with O(1) extra memory.
 * Example 1:
 *
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class TestReverseString {

  public static Stream<Arguments> arguments() {
    return Stream.of(
        Arguments.of(new char[]{'h','e','l','l','o'}, new char[]{'o','l','l','e','h'}),
        Arguments.of(new char[]{'H','a','n','n','a','h'}, new char[]{'h','a','n','n','a','H'})
    );
  }
  //Requirement is "You must do this by modifying the input array in-place with O(1) extra memory."
  //But this method does not modify the existing array
  public char[] reverseString1(char[] s) {
    int len = s.length;
    char[] result = new char[len];
    int resultCounter =0;
    for (int i = len-1; i >=0 ; i--) {
      result[resultCounter] = s[i];
      resultCounter++;
    }
    return result;
  }
  //Use Cyclic rotation
  public char[] reverseString2(char[] s) {
    int len = s.length;
    int sourceIndex = 0;
    int targetIndex = 0;
    List<Integer> replacedIndices = new ArrayList<>();
    int counter =0;
    while (replacedIndices.size()<len) {
      if (counter==0) {
        sourceIndex = 0;
        targetIndex = (sourceIndex+len-1)%len;
        counter++;
        char temp = s[sourceIndex];
        s[sourceIndex] = s[targetIndex];
        s[targetIndex] = temp;
        replacedIndices.add(sourceIndex);
        replacedIndices.add(targetIndex);
      }else {
        sourceIndex = counter;
        targetIndex = (sourceIndex+len-1)%len;
        counter++;
        char temp = s[sourceIndex];
        s[sourceIndex] = s[targetIndex];
        s[targetIndex] = temp;
        replacedIndices.add(sourceIndex);
        replacedIndices.add(targetIndex);
      }
    }
    return s;
  }

  @ParameterizedTest
  @MethodSource("arguments")
  public void test(char[] s, char[] expected) {
   // assertThat(reverseString1(s)).isEqualTo(expected);
    assertThat(reverseString2(s)).isEqualTo(expected);
  }

}
