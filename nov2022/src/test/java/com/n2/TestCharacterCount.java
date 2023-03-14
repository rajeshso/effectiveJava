package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TestCharacterCount {
  Map<Character,Integer> count1(String s) {
    int length = s.length();
    Map<Character,Integer> result = new HashMap<>(length);
    char[] chars = s.toLowerCase().toCharArray();
    for (int i = 0; i < length; i++) {
      Character c = chars[i];
      if (result.containsKey(c)) {
        result.put(c, result.get(c)+1);
      }else {
        result.put(c,1);
      }
    }
    return result;
  }
  Map<String,Long> count2(String s) {
    String word = s;
    Map<String, Long> charCount = word.codePoints().mapToObj(Character::toString)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    return charCount;
  }
  Map<String,Integer> count3(String s) {
    String word = s;
    Map<String,Integer> result = new HashMap<>(s.length());
    Arrays.stream(word.split("")).forEach(a -> result.compute(a, (k, v) -> v == null ? 1 : v + 1));
    return result;
  }
  
  @Test
  void simpleTestForCount1() {
    Map<Character, Integer> count1 = count1("somasundaram");
    Map<Character, Integer> expected = Map.of('s', 2,'o',1,'m',2,'a',3,'u',1,'n',1,'d',1,'r',1);
    assertThat(count1).containsAllEntriesOf(expected);
  }
  @Test
  void simpleTestForCount2() {
    Map<String, Long> count1 = count2("somasundaram");
    Map<String, Long> expected = Map.of("s", (long)2,"o",(long)1,"m",(long)2,"a",(long)3,"u",(long)1,"n",(long)1,"d",(long)1,"r",(long)1);
    assertThat(count1).containsAllEntriesOf(expected);
  }
  @Test
  void simpleTestForCount3() {
    Map<String, Integer> count1 = count3("somasundaram");
    Map<String, Integer> expected = Map.of("s", 2,"o",1,"m",2,"a",3,"u",1,"n",1,"d",1,"r",1);
    assertThat(count1).containsAllEntriesOf(expected);
  }
}
