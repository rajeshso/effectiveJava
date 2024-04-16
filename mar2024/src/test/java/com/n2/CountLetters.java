package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
class CountLetters {
//Somasundaram -> s -> 2, o->1, m->2, a->3 ….. (streams, groupBy count)
  Map<Character, Long> countLettersSolution(String a) {
    return a.toLowerCase().chars()
        .mapToObj(b -> (char) b)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
  }
  @Test
  public void testCountLettersSolution() {
    String a = "Somasundaram";
    Map<Character, Long> result = countLettersSolution(a);
    assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of('a',3L, 'd',1L, 'm',2L, 'n',1L, 'o',1L, 'r',1L, 's',2L, 'u',1L));
  }
  //https://www.baeldung.com/java-groupingby-collector
  //https://medium.com/javarevisited/must-know-java-8-stream-interview-questions-for-java-developers-series-16-9d0579623a70
  enum BlogPostType {
    NEWS,
    REVIEW,
    GUIDE
  }
  class BlogPost {
    String title;
    String author;
    BlogPostType type;
    int likes;
  }
  class Tuple {
    BlogPostType type;
    String author;
  }
  //To group the blog posts in the blog post list by their type
  public Map<BlogPostType, List<BlogPost>> groupBlogPostByBlogPostType() {
    return null;
  }

}
