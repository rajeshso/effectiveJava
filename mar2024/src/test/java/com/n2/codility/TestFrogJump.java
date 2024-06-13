package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * A small frog wants to get to the other side of the road. The frog is currently located at
 * position X and wants to get to a position greater than or equal to Y.
 * The small frog always jumps a fixed distance, D.
 *
 * Count the minimal number of jumps that the small frog must perform to reach its target.
 *
 * Write a function:
 *
 * class Solution { public int solution1(int X, int Y, int D); }
 *
 * that, given three integers X, Y and D, returns the minimal number of jumps
 * from position X to a position equal to or greater than Y.
 *
 * For example, given:
 *
 *   X = 10
 *   Y = 85
 *   D = 30
 * the function should return 3, because the frog will be positioned as follows:
 *
 * after the first jump, at position 10 + 30 = 40
 * after the second jump, at position 10 + 30 + 30 = 70
 * after the third jump, at position 10 + 30 + 30 + 30 = 100
 * Write an efficient algorithm for the following assumptions:
 *
 * X, Y and D are integers within the range [1..1,000,000,000];
 * X ≤ Y.
 * One possible solution1 could be
 * public int solution1(int X, int Y, int D) {
 *     int distance = Y - X;
 *     int jumps = distance / D;
 *     if (distance % D != 0) {
 *       jumps++;
 *     }
 *     return jumps;
 *   }
 */
public class TestFrogJump {
  public int solution1(int X, int Y, int D) {
    //Divide (Y-X)/(D), lets say major jump
    int distance = (Y-X)/(D);
    //Find the remainder (Y-X)%(D), lets say minor jumps if not zero, its one, else its zero
    int remainder = (Y-X)%(D) > 0 ? 1 : 0;
    //The total number of hops = major jumps + minor jumps
    //return the total number of hops
    return distance + remainder;
  }
  //more descriptive
  public int solution2(int startingPosition, int targetPosition, int jumpDistance) {
    // Distance to be covered
    int distance = targetPosition - startingPosition;
    // Major jumps possible
    int majorJumps = distance / jumpDistance;
    // Check if a minor jump is needed to reach the target
    int minorJumps = distance % jumpDistance == 0 ? 0 : 1;
    // Total jumps required
    return majorJumps + minorJumps;
  }
  @ParameterizedTest
  @MethodSource("arguments")
  public void testFrogJump(int X, int Y, int D, int expected) {
    assertThat(solution1(X, Y, D)).isEqualTo(expected);
    assertThat(solution2(X, Y, D)).isEqualTo(expected);
  }
  private static Stream<Arguments> arguments() {
    return Stream.of(
        Arguments.of(10, 85, 30, 3),
        Arguments.of(10, 10, 30, 0),
        Arguments.of(10, 100, 30, 3)
    );
  }
}
