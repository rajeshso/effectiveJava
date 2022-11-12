package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class DiagramTest {
  int solution(String a) {
    int len = a.length();
    int maxDistance = -1;
    Set<String> parsedDiagram = new HashSet<>(len/2);
    for (int i = 0; i < len; i++) {
      //parse a diagram
      if (i+2>len) continue;
      String diagram = a.substring(i,i+2);
      if (parsedDiagram.contains(diagram)) {
        continue;
      }else {
        //persist the parsed diagram, so that it is not referenced again
        parsedDiagram.add(diagram);
      }
      //count the diagrams and the starting indices
      int maxDistanceOfADiagram =0;
      for (int j = i; j < len; j++) {
        if (j+2>len) continue;
        String repeatDiagram = a.substring(j,j+2);
        if (repeatDiagram.equals(diagram)) {
          int distance = j-i;
          maxDistanceOfADiagram = Math.max(distance, maxDistanceOfADiagram);
        }
      }
      // update the max counter
      maxDistance = Math.max(maxDistance, maxDistanceOfADiagram);
    }
    return maxDistance == 0? -1 : maxDistance;
  }

  @Test
  void test1() {
    String s1 = "aakmaakmakda";
    String s2 = "aaa";
    String s3 = "codility";

    assertThat(solution(s1)).isEqualTo(7);
    assertThat(solution(s2)).isEqualTo(1);
    assertThat(solution(s3)).isEqualTo(-1);
  }
}
