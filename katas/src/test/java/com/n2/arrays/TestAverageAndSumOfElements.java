package com.n2.arrays;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestAverageAndSumOfElements {

    static Stream<org.junit.jupiter.params.provider.Arguments> arrayProvider() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(new int[]{1,1,2,3,4,5,6,6}, 3, 28),
            org.junit.jupiter.params.provider.Arguments.of(new int[]{2,2,2,2}, 2, 8),
            org.junit.jupiter.params.provider.Arguments.of(new int[]{5,10,15}, 10, 30)
        );
    }

    @ParameterizedTest
    @MethodSource("arrayProvider")
    void testAverageAndSum(int[] a, int expectedAvg, int expectedSum) {
        assertThat(average(a)).isEqualTo(expectedAvg);
        assertThat(sum(a)).isEqualTo(expectedSum);
        assertThat(myavg(a)).isEqualTo(expectedAvg);
    }

    private int sum(int[] a) {
        int count = 0;
        for (int i=0;i<a.length;i++) {
            count+=a[i];
        }
        return count;
    }

    private int average(int[] a) {
        return sum(a)/a.length;
    }

    private int myavg(int[] a) {
        final int sum = Arrays.stream(a).sum();
        return sum/a.length;
    }
}
