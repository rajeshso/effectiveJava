package com.n2.arrays;

import static com.n2.arrays.FindTheMissingElement.solution;
import static com.n2.arrays.FindTheMissingElement.solution1;
import static com.n2.arrays.FindTheMissingElement.solution2;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestFindTheMissingElement {

    static Stream<org.junit.jupiter.params.provider.Arguments> missingElementProvider() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(new int[]{1,4,5,3,2,8,6}, 7),
            org.junit.jupiter.params.provider.Arguments.of(new int[]{1,4,5,3,2,8,6,-1}, 0),
            org.junit.jupiter.params.provider.Arguments.of(new int[]{7,4,5,3,2,8,6}, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("missingElementProvider")
    void testFindTheMissingElement(int[] a, int expected) {
        assertThat(solution(a)).isEqualTo(expected);
        assertThat(solution2(a)).isEqualTo(expected);
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> missingElementProvider1() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(new int[]{1,4,5,3,2,8,6}, 7)
        );
    }

    @ParameterizedTest
    @MethodSource("missingElementProvider1")
    void testFindTheMissingElement1(int[] a, int expected) {
        assertThat(solution1(a)).isEqualTo(expected);
        assertThat(solution2(a)).isEqualTo(expected);
    }

    @Disabled
    void testFindTheMissingElementInFront1() {
        int a[] = new int[]{1,4,5,3,2,8,6,-1};
        assertThat(solution1(a)).isEqualTo(0);
    }

    @Disabled
    void testFindTheMissingElementInBack1() {
        int a[] = new int[]{7,4,5,3,2,8,6};
        assertThat(solution1(a)).isEqualTo(9);
    }


}
