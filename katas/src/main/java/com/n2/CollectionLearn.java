package com.n2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.ls.LSOutput;

public class CollectionLearn {
  public void computeIfAbsent() {
    Map<String, Integer> stringLength = new HashMap<>();
    //stringLength.put("John", 5);
    final Integer johnLen = stringLength.computeIfAbsent("John", s -> s.length());
    System.out.printf("\n johnLen %d", johnLen);
  }
  //https://dzone.com/articles/one-method-to-rule-them-all-mapmerge
  public static void compute() {
    //old way
    var words = List.of("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
    var map = new HashMap<String, Integer>();
    words.forEach(word -> {
      var prev = map.get(word);
      if (prev == null) {
        map.put(word, 1);
      } else {
        map.put(word, prev + 1);
      }
    });
    System.out.println(map);
    //new way 1
    map.clear();
    words.forEach(word -> {
      map.putIfAbsent(word,0);
      map.put(word, map.get(word)+1);
    });
    System.out.println(map);
    //new way 2
    map.clear();
    words.forEach( word -> {
      map.putIfAbsent(word, 0);
      map.computeIfPresent(word, (w, count)-> count+1);
    });
    System.out.println(map);
    //new way 3
    map.clear();
    words.forEach(word -> {
      map.compute(word, (w, count) -> count==null? 0: count+1);
    });
    System.out.println(map);
    //new way 4
    map.clear();
    words.forEach(word -> {
      map.merge(word, 1, (previousCount, one) -> previousCount + 1);
    });
    System.out.println(map);
  }

  public static void main(String[] args) {
    compute();
  }
}
