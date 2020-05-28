package com.n2.arrays;

import java.util.HashMap;
import java.util.Map;

public class Diagram1 {
  public int solution(String S) {
    // write your code in Java SE 8
    final Map<String, Integer> diagramCount = new HashMap<>(S.length());
    int len = S.length()-1;
    int highestDistance = -1;
    for (int i = 0; i < len; i++) {
      final String diagram = S.substring(i,i+2);
      final Integer identicalLowestDiagramCount = diagramCount.get(diagram);
      if (identicalLowestDiagramCount==null) { //new
        diagramCount.put(diagram,i);
      }else { //calculate the distance and refresh the highestDistance, if necessary
        int distance = i-identicalLowestDiagramCount;
        if (distance>highestDistance) {
          highestDistance = distance;
        }
      }
    }
    return highestDistance;
  }

  public static void main(String[] args) {
    String s1 = "aakmaakmakda";
    String s2 = "aaa";
    String s3 = "codility";
    Diagram1 diagram1 = new Diagram1();
    if (diagram1.solution(s1) == 7) {
      System.out.println("pass");
    }
    if (diagram1.solution(s2) == 1) {
      System.out.println("pass");
    }
    if (diagram1.solution(s3) == -1) {
      System.out.println("pass");
    }
  }
}
