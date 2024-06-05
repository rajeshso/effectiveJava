package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
  Map<String, Long> count4(String s) {
    Map<String,Long> result;
    result = Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    return result;
  }
  Map<Character, Integer> count5(String s) {
    return s.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.toMap(
            c -> c,//key Mapper
            c -> 1, // value mapper
            Integer::sum // merge function
        ));
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
  Map<Character, Integer> count6(String s) {
    final char[] charArray = s.toCharArray();
    final Map<Character, Integer> characterCountMap = IntStream.rangeClosed(0, charArray.length-1)
        .mapToObj(i -> Character.valueOf(charArray[i]))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e->1)));
    return characterCountMap;
  }
  @ParameterizedTest
  @MethodSource("providedGivenAndExpected")
  void simpleTestForCount(String given, Map<Character, Integer> expected) {
    Map<Character, Integer> count1 = count1(given);
  //  assertThat(count1(given)).containsAllEntriesOf(expected);
   // assertThat(count2(given)).containsAllEntriesOf(expected);
   // assertThat(count3(given)).containsAllEntriesOf(expected);
    //assertThat(count4(given)).containsAllEntriesOf(expected);
   // assertThat(count5(given)).containsAllEntriesOf(expected);
    assertThat(count6(given)).containsAllEntriesOf(expected);
  }
  private static Stream<Arguments> providedGivenAndExpected() {
    return Stream.of(
        Arguments.of("somasundaram", Map.of('s', 2,'o',1,'m',2,'a',3,'u',1,'n',1,'d',1,'r',1)),
        Arguments.of("rajesh", Map.of('r',1,'a',1,'j',1,'e',1,'s',1,'h',1))
    );
  }
}
