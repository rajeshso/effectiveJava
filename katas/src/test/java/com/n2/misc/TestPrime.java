package com.n2.misc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestPrime {

  @Test
  public void testPrime20() {
    Integer[] prime20 = getPrime(20);
    assertThat(prime20).containsExactlyInAnyOrder(2,3 ,5 ,7 ,11 ,13 ,17 ,19);
  }

  protected Integer[] getPrime(int i) {
    List<Integer> result = new ArrayList<>(i);
    boolean prime = true;
    while(i>1) {
      for(int j=2;j<i;j++) {
        if (i%j==0) {
          prime=false;
        }
      }
      if (prime) result.add(i);
      prime=true;
      i--;
    }
    final Integer[] integers = result.toArray(new Integer[0]);
    return integers;
  }
}
