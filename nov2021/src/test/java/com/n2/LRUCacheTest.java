package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LRUCacheTest {

  @Test
  void createLRUCache() {
    int maxSize = 5;
    LRUCache lruCache = new LRUCache(maxSize);
    assertThat(lruCache).isNotNull();
    assertThat(lruCache.getDeque()).isEmpty();
    assertThat(lruCache.getSet()).isEmpty();
    assertThat(lruCache.getMaxSize()).isEqualTo(maxSize);
  }

  @Test
  void push1ElementShouldStayInLRUCache() {
    int maxSize = 5;
    LRUCache lruCache = new LRUCache(maxSize);
    lruCache.refer(3);
    assertThat(lruCache.getSet()).containsExactly(3);
    assertThat(lruCache.getDeque()).containsExactly(3);
  }

  @Test
  void push2ElementsShouldStayInLRUCache() {
    int maxSize = 5;
    LRUCache lruCache = new LRUCache(maxSize);
    lruCache.refer(3);
    lruCache.refer(2);
    assertThat(lruCache.getSet()).contains(3,2);
    assertThat(lruCache.getDeque()).containsExactly(2,3);
  }

  @Test
  void pushRepeatedElementsShouldStayInLRUCache() {
    int maxSize = 5;
    LRUCache lruCache = new LRUCache(maxSize);
    lruCache.refer(3);
    lruCache.refer(2);
    lruCache.refer(2);
    assertThat(lruCache.getSet()).contains(3,2);
    assertThat(lruCache.getDeque()).containsExactly(2,3);
  }

  @Test
  void pushElementsMoreThanMaxSizeShouldStayInLRUCache() {
    int maxSize = 3;
    LRUCache lruCache = new LRUCache(maxSize);
    lruCache.refer(3);
    lruCache.refer(2);
    lruCache.refer(1);
    lruCache.refer(0);
    assertThat(lruCache.getSet()).contains(2,1,0);
    assertThat(lruCache.getDeque()).containsExactly(0,1,2);
  }

}
