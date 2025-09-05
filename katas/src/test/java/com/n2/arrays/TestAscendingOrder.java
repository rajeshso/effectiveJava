package com.n2.arrays;

import static com.n2.arrays.AscendingOrder.arrangeAscending;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestAscendingOrder {

    static Stream<org.junit.jupiter.params.provider.Arguments> arrayProvider() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(new int[]{5,3,7,2,9,8}, new int[]{2,3,5,7,8,9}),
            org.junit.jupiter.params.provider.Arguments.of(new int[]{1,4,2,3}, new int[]{1,2,3,4}),
            org.junit.jupiter.params.provider.Arguments.of(new int[]{10,9,8,7}, new int[]{7,8,9,10})
        );
    }

    @ParameterizedTest
    @MethodSource("arrayProvider")
    void testArrangeAscending(int[] input, int[] expected) {
        assertThat(arrangeAscending(Arrays.copyOf(input, input.length))).containsExactly(expected);
        assertThat(myarrangeAscending1(Arrays.copyOf(input, input.length))).containsExactly(expected);
        assertThat(myarrangeAscending2(Arrays.copyOf(input, input.length))).containsExactly(expected);
        assertThat(myarrangeAscending3(Arrays.copyOf(input, input.length))).containsExactly(expected);
        assertThat(myarrangeAscending4(Arrays.copyOf(input, input.length))).containsExactly(expected);
        assertThat(myarrangeAscending5(Arrays.copyOf(input, input.length))).containsExactly(expected);
    }

    private int[] myarrangeAscending1(int[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) { //outer
            for (int j = i; j < len; j++) {//inner
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    System.out.println(a[i]);
                }
            }
        }
        return a;
    }

    private int[] myarrangeAscending2(int[] a) {
        Arrays.sort(a);
        return a;
    }

    private int[] myarrangeAscending3(int[] a) {
        return Arrays.stream(a).sorted().toArray();
    }

    // Using Collections.sort with boxed stream
    private int[] myarrangeAscending4(int[] a) {
        return Arrays.stream(a).boxed().sorted().mapToInt(Integer::intValue).toArray();
    }

    // Using List.of (Java 9+) and Collections.sort
    private int[] myarrangeAscending5(int[] a) {
        List<Integer> list = Arrays.stream(a).boxed().collect(java.util.stream.Collectors.toList());
        Collections.sort(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

}
