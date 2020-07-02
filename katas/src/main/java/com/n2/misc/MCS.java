package com.n2.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * example 1 [ (A, B), (B) , (A,C) ] => (A,B) or (B,C)
 */
public class MCS {

  public static List<String> mcs(List<List<String>> ls) {
    List<String> finalResult = new LinkedList<>();
    for (int i = 0; i < ls.get(0).size(); i++) {
      List<String> result = new LinkedList<>();
      for (List<String> mclds : ls) {
        Collections.sort(mclds);
        if (result.isEmpty()) {
          result.add(mclds.get(i));//try each in turn
        } else {
          List<String> common = new LinkedList<>(result); //AB
          common.retainAll(mclds);//A
          if (common.isEmpty()) {
            result.add(mclds.get(0));//getting first again
          }
        }
        //check if current result is smaller than previous found
        if (finalResult.isEmpty() || result.size() < finalResult.size()) {
          finalResult = new LinkedList<>(result);
        }
      }
    }
    return finalResult;
  }

  public static void main(String[] args) {
    List<List<String>> testList1 = new ArrayList<>(
        List.of(new ArrayList<>(List.of("A", "B")), new ArrayList<>(List.of("B")),
            new ArrayList<>(List.of("A", "C"))));
    List<List<String>> testList2 = new ArrayList<>(
        List.of(new ArrayList<>(List.of("A")), new ArrayList<>(List.of("D", "C")),
            new ArrayList<>(List.of("B"))));
    List<List<String>> testList3 = new ArrayList<>(
        List.of(new ArrayList<>(List.of("A", "C")), new ArrayList<>(List.of("C", "D")),
            new ArrayList<>(List.of("C", "F"))));
    System.out.println(mcs(testList3));
  }
}
