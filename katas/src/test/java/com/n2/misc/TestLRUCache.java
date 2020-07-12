package com.n2.misc;

import static org.assertj.core.api.Assertions.assertThat;

import com.n2.misc.LRUCache.Entry;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestLRUCache {

  @Test
  public void testPutValueOnEmptyCache() {
    LRUCache lruCache = new LRUCache();
    lruCache.put(1,1);
    final Map<Integer, Entry> hashMap = lruCache.getHashMap();
    assertThat(hashMap.size()).isOne();
    final Entry entry = hashMap.get(1);
    assertThat(entry).isNotNull();
    assertThat(entry.value).isOne();
    assertThat(entry.key).isOne();
    assertThat(entry.left).isNull();
    assertThat(entry.right).isNull();
    assertThat(lruCache.getStart()).isEqualTo(entry);
    assertThat(lruCache.getEnd()).isEqualTo(entry);
  }
  @Test
  public void testGetValueOnEmptyCache() {
    LRUCache lruCache = new LRUCache();
    final Map<Integer, Entry> hashMap = lruCache.getHashMap();
    assertThat(hashMap.size()).isZero();
    assertThat(lruCache.getStart()).isNull();
    assertThat(lruCache.getEnd()).isNull();
  }
  @Test
  public void testSecondPutValueOnEmptyCache() {
    LRUCache lruCache = new LRUCache();
    lruCache.put(1,1);
    lruCache.put(2,2);
    final Map<Integer, Entry> hashMap = lruCache.getHashMap();
    assertThat(hashMap.size()).isEqualTo(2);
    final Entry entry1 = hashMap.get(1);
    final Entry entry2 = hashMap.get(2);
    assertThat(entry1).isNotNull();
    assertThat(entry1.value).isOne();
    assertThat(entry1.key).isOne();
    assertThat(entry1.left).isNull();
//    assertThat(entry1.right).isEqualTo(entry2);
//    assertThat(lruCache.getStart()).isEqualTo(entry1);
//    assertThat(lruCache.getEnd()).isEqualTo(entry2);
  }
}
