package com.n2.sains;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
//TODO: Review this solution2 again, understand the hasAscendingOrSameDigits method
/**
 * Sainsburys
 * First problem
 * Given a number , find all the numbers that have the same length. For example 2, should return numbers from 10 to 99
 * However, the output should only include the digits that are ascending or equal. For example, 12 is ascending, 11 is equal, 21 is not ascending
 *
 * Second problem
 * Get all the data from the following two urls
 * Create a Controller that will return the data from the two urls as a single json response
 * https://s3.eu-west-1.amazonaws.com/hackajob-assets1.p.hackajob/challenges/sainsbury_products/products_price_v2.json
 * https://s3.eu-west-1.amazonaws.com/hackajob-assets1.p.hackajob/challenges/sainsbury_products/products_v2.json
 */
public class TestFindNumbersWithAscendingDigits {

  public static Stream<Arguments> arguments() {
    return Stream.of(
        Arguments.of(2, "11,12,13,14,15,16,17,18,19,22,23,24,25,26,27,28,29,33,34,35,36,37,38,39,44,45,46,47,48,49,55,56,57,58,59,66,67,68,69,77,78,79,88,89,99,"),
        Arguments.of(3, "111,112,113,114,115,116,117,118,119,122,123,124,125,126,127,128,129,133,134,135,136,137,138,139,144,145,146,147,148,149,155,156,157,158,159,166,167,168,169,177,178,179,188,189,199,222,223,224,225,226,227,228,229,233,234,235,236,237,238,239,244,245,246,247,248,249,255,256,257,258,259,266,267,268,269,277,278,279,288,289,299,333,334,335,336,337,338,339,344,345,346,347,348,349,355,356,357,358,359,366,367,368,369,377,378,379,388,389,399,444,445,446,447,448,449,455,456,457,458,459,466,467,468,469,477,478,479,488,489,499,555,556,557,558,559,566,567,568,569,577,578,579,588,589,599,666,667,668,669,677,678,679,688,689,699,777,778,779,788,789,799,888,889,899,999,")
    );
  }

  public String solution(int number) {
    List<Integer> resultList = new ArrayList<>();
    //The following functions is critical to find the solution2
    int lowerBound = (int) Math.pow(10, number - 1);
    int upperBound = (int) Math.pow(10, number) - 1;

    System.out.println("Numbers with same length and ascending/equal digits as " + number + ":");
    for (int num = lowerBound; num <= upperBound; num++) {
      if (hasAscendingOrSameDigits(num)) {
        System.out.println(num);
        resultList.add(num);
      }
    }
    //return the result as a String with comma delimited
    return resultList.stream().map(String::valueOf).reduce("", (a, b) -> a + b + ",");
  }

  /**
   * hasAscendingOrSameDigits method: This method checks if the digits of a number are in ascending order or all the same.
   * It iterates through each digit of the number using a loop.
   * It keeps track of the previous digit (prevDigit).
   * For each current digit (currentDigit), it only checks if it's strictly greater than the previous digit. This allows numbers with all the same digits to pass the check.
   * If the loop completes without finding any strictly decreasing digits, the digits are considered ascending or all the same, and the method returns true.
   * This code will now include numbers like 10, 22, 33, etc. in the output for an input of 2.
   *
   * @param num
   * @return
   */
  public boolean hasAscendingOrSameDigits(int num) {
    int prevDigit = num % 10;
    num /= 10;
    while (num > 0) {
      int currentDigit = num % 10;
      if (currentDigit > prevDigit) {
        return false; // Strictly decreasing digits are not allowed
      }
      prevDigit = currentDigit;
      num /= 10;
    }
    return true;
  }
  @ParameterizedTest
  @MethodSource("arguments")
  public void testFindNumbersWithAscendingDigits(int number, String expected) {
    assertThat(solution(number)).isEqualTo(expected);
  }
}
