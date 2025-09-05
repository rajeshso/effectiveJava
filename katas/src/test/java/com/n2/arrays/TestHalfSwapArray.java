package com.n2.arrays;

import static com.n2.arrays.HalfSwapArray.solution;
import static com.n2.arrays.HalfSwapArray.solution2;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestHalfSwapArray {

    static Stream<org.junit.jupiter.params.provider.Arguments> halfSwapProvider() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(
                new int[] {1,2,3,4,5,6,7,8,9,10}, new int[]{6,7,8,9,10,1,2,3,4,5}),
            org.junit.jupiter.params.provider.Arguments.of(
                new int[] {1,2,3,4,5,6,7,8,9}, new int[]{6,7,8,9,1,2,3,4,5})
        );
    }

    @ParameterizedTest
    @MethodSource("halfSwapProvider")
    void testHalfSwapArray(int[] arr, int[] expected) {
        assertThat(solution(arr)).isEqualTo(expected);
        assertThat(solution2(arr)).isEqualTo(expected);
    }
}
