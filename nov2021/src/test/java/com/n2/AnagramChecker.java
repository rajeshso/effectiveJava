package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AnagramChecker {

  @Test
  void check() {
    assertThat(isAnagram("Mother In Law", "Hitler Woman")).isTrue();
    assertThat(isAnagram("Keep", "Peek")).isTrue();
    assertThat(isAnagram("rttre", "Peek")).isFalse();
  }

  private boolean isAnagram(String a1, String a2) {
    a1 = a1.replaceAll(" ","");
    a2 = a2.replaceAll(" ","");
    int len1 = a1.length();
    int len2 = a2.length();
    if (len1!=len2) return false;

    char[] chars1 = a1.toLowerCase().toCharArray();
    char[] chars2 = a2.toLowerCase().toCharArray();

    Arrays.sort(chars1);
    Arrays.sort(chars2);

    return Arrays.equals(chars1,chars2);
  }
}
