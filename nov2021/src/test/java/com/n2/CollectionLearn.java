package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CollectionLearn {

  Map<String, Integer> countWordsOld(List<String> words) {
    var result = new HashMap<String, Integer>();
    words.forEach(w -> {
          if (result.containsKey(w)) {
            result.put(w, result.get(w) + 1);
          } else {
            result.put(w, 1);
          }
        }
    );
    return result;
  }

  Map<String, Integer> countWordsNew1(List<String> words) {
    var result = new HashMap<String, Integer>();
    words.forEach(w-> {
      result.putIfAbsent(w, 0);
      result.put(w, result.get(w)+1);
    });
    return result;
  }

  Map<String, Integer> countWordsNew2(List<String> words) {
    var result = new HashMap<String, Integer>();
    words.forEach(w-> {
      result.putIfAbsent(w, 0);
      result.computeIfPresent(w, (word, count) -> count+1);
    });
    return result;
  }

  Map<String, Integer> countWordsNew3(List<String> words) {
    var result = new HashMap<String, Integer>();
    words.forEach(w-> result.compute(w, (word, count)-> count==null? 1: count+1));
    return result;
  }

  Map<String, Integer> countWordsNew4(List<String> words) {
    var result = new HashMap<String, Integer>();
    words.forEach(w-> result.merge(w, 1, (previousCount, defaultValue)->previousCount+1));
    return result;
  }

  @Test
  void countWordsTest() {
    var words = List.of("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
    Map<String, Integer> result = countWordsOld(words);
    Map<String, Integer> expected = Map.of("Foo", 3, "Bar", 1, "Buzz", 2, "Fizz",2);
    assertThat(result).containsAllEntriesOf(expected);
    assertThat(countWordsNew1(words)).containsAllEntriesOf(expected);
    assertThat(countWordsNew2(words)).containsAllEntriesOf(expected);
    assertThat(countWordsNew3(words)).containsAllEntriesOf(expected);
    assertThat(countWordsNew4(words)).containsAllEntriesOf(expected);
  }
}
