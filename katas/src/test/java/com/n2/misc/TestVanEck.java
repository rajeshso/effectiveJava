package com.n2.misc;
import static org.assertj.core.api.Assertions.*;

import com.n2.misc.VanEck;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestVanEck {

  @Disabled
  public void testFindDistance() {
    int[] sequence = new int[]{0,0};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isOne();
  }

  @Test
  public void testFindDistanceForNewNumber() {
    int[] sequence = new int[]{0,0,1};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isEqualTo(0);
  }

  @Test
  public void testFindDistanceForSequenceLessThan2() {
    int[] sequence = new int[]{0};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isEqualTo(0);
  }

  @Test
  public void testFindDistanceForSmallSequence() {
    int[] sequence = new int[]{0,0,1,0};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isEqualTo(2);
  }

  @Test
  public void testFindDistanceForFiveDigitSequence() {
    int[] sequence = new int[]{0,0,1,0,2};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isEqualTo(0);
  }
  @Test
  public void testFindDistanceForSixDigitSequence() {
    int[] sequence = new int[]{0,0,1,0,2,0};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isEqualTo(2);
  }
  @Test
  public void testFindDistanceForSevenDigitSequence() {
    int[] sequence = new int[]{0,0,1,0,2,0,2};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isEqualTo(2);
  }
  @Test
  public void testFindDistanceForEightDigitSequence() {
    int[] sequence = new int[]{0,0,1,0,2,0,2,2};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isEqualTo(1);
  }
  @Test
  public void testFindDistanceForNineDigitSequence() {
    int[] sequence = new int[]{0,0,1,0,2,0,2,2,1};
    VanEck vanEck = new VanEck();
    int result = vanEck.findDistance(sequence);
    assertThat(result).isEqualTo(6);
  }

}
