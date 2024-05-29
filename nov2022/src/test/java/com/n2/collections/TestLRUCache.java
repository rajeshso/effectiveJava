package com.n2.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Outline for LRUCache Program:
 * LRUCache Class:
 * Constructor:
 * Takes capacity as input and initializes the LRUCache with the given capacity.
 * Initializes a HashMap to store key-value pairs.
 * Initializes a Doubly Linked List to maintain the order of keys.
 * get(key):
 * Check if the key exists in the HashMap.
 * If it exists:
 * Move the corresponding node to the front of the linked list to indicate it's the most recently used.
 * Return the value associated with the key.
 * If it doesn't exist:
 * Return -1.
 * put(key, value):
 * Check if the key already exists in the HashMap.
 * If it exists:
 * Update the value in the HashMap.
 * Move the corresponding node to the front of the linked list.
 * If it doesn't exist:
 * Check if the LRUCache is full.
 * If it's full, remove the least recently used node from the linked list and the corresponding entry from the HashMap.
 * Add the new key-value pair to the HashMap.
 * Add a new node to the front of the linked list.
 * Doubly Linked List Node Class:
 * Each node will have pointers to the previous and next nodes, as well as a key-value pair.
 */
public class TestLRUCache {

  class LRUCacheMethod1 {
    private final int capacity;
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUCacheMethod1(int capacity) {
      this.capacity = capacity;
      this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
          return size() > capacity;
        }
      };
    }

    public int get(int key) {
      return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
      cache.put(key, value);
    }

    public int capacity() {
      return capacity;
    }
  }

  class LRUCacheMethod2 {
    private final int capacity;
    private final HashMap<Integer,Integer> cacheMap; // to store key-value pairs.
    private final Deque<Integer> deque;//to maintain the order of keys.
    LRUCacheMethod2(int capacity) {
      this.capacity = capacity;
      this.cacheMap = new LinkedHashMap<>();
      this.deque = new LinkedList<>();
    }
    public int get(int key) {
      if (cacheMap.containsKey(key)) {
        deque.remove(key);
        deque.addFirst(key);
        return cacheMap.get(key);
      }
      return -1;
    }
    public void put(int key, int value) {
      if (cacheMap.containsKey(key)) {
        deque.remove(key);
      }else if (cacheMap.size() >= capacity) {
        int leastUsedKey = deque.removeLast();
        cacheMap.remove(leastUsedKey);
      }
      cacheMap.put(key, value);
      deque.addFirst(key); //Add the key to the front
    }
    public int capacity() {
      return capacity;
    }
  }
  @Test
  public void testLRUCacheInitialization1() {
    LRUCacheMethod1 cache = new LRUCacheMethod1(3);
    assertEquals(3, cache.capacity());
  }

  @Test
  public void testGetExistingKey1() {
    LRUCacheMethod1 cache = new LRUCacheMethod1(3);
    cache.put(1, 10);
    cache.put(2, 20);
    assertEquals(10, cache.get(1));
  }

  @Test
  public void testGetNonExistingKey1() {
    LRUCacheMethod1 cache = new LRUCacheMethod1(3);
    assertEquals(-1, cache.get(1));
  }

  @Test
  public void testPut1() {
    LRUCacheMethod1 cache = new LRUCacheMethod1(2);
    cache.put(1, 10);
    cache.put(2, 20);
    cache.put(3, 30);
    assertEquals(-1, cache.get(1)); // 1 is removed due to capacity limit
    assertEquals(20, cache.get(2));
    assertEquals(30, cache.get(3));
  }

  @Test
  public void testLRUCacheCapacityLimit1() {
    LRUCacheMethod1 cache = new LRUCacheMethod1(2);
    cache.put(1, 10);
    cache.put(2, 20);
    cache.get(1); // 1 becomes most recently used
    cache.put(3, 30); // 2 is removed due to capacity limit
    assertEquals(-1, cache.get(2)); // 2 is removed
    assertEquals(10, cache.get(1)); // 1 is still in cache
    assertEquals(30, cache.get(3)); // 3 is in cache
  }

  @Test
  public void testLRUCacheOrder1() {
    LRUCacheMethod1 cache = new LRUCacheMethod1(3);
    cache.put(1, 10);
    cache.put(2, 20);
    cache.put(3, 30);
    cache.get(1); // 1 becomes most recently used
    cache.put(4, 40); // 2 is removed due to capacity limit
    assertEquals(-1, cache.get(2)); // 2 is removed
    assertEquals(10, cache.get(1)); // 1 is still in cache
    assertEquals(30, cache.get(3)); // 3 is still in cache
    assertEquals(40, cache.get(4)); // 4 is in cache
  }
  //Method 2
  @Test
  public void testLRUCacheInitialization2() {
    LRUCacheMethod2 cache = new LRUCacheMethod2(3);
    assertEquals(3, cache.capacity());
  }
  @Test
  public void testGetExistingKey2() {
    LRUCacheMethod2 cache = new LRUCacheMethod2(3);
    cache.put(1, 10);
    cache.put(2, 20);
    assertEquals(10, cache.get(1));
  }
  @Test
  public void testGetNonExistingKey2() {
    LRUCacheMethod2 cache = new LRUCacheMethod2(3);
    assertEquals(-1, cache.get(1));
  }
  @Test
  public void testPut2() {
    LRUCacheMethod2 cache = new LRUCacheMethod2(2);
    cache.put(1, 10);
    cache.put(2, 20);
    cache.put(3, 30);
    assertEquals(-1, cache.get(1)); // 1 is removed due to capacity limit
    assertEquals(20, cache.get(2));
    assertEquals(30, cache.get(3));
  }
  @Test
  public void testLRUCacheCapacityLimit2() {
    LRUCacheMethod2 cache = new LRUCacheMethod2(2);
    cache.put(1, 10);
    cache.put(2, 20);
    cache.get(1); // 1 becomes most recently used
    cache.put(3, 30); // 2 is removed due to capacity limit
    assertEquals(-1, cache.get(2)); // 2 is removed
    assertEquals(10, cache.get(1)); // 1 is still in cache
    assertEquals(30, cache.get(3)); // 3 is in cache
  }
  @Test
  public void testLRUCacheOrder2() {
    LRUCacheMethod2 cache = new LRUCacheMethod2(3);
    cache.put(1, 10);
    cache.put(2, 20);
    cache.put(3, 30);
    cache.get(1); // 1 becomes most recently used
    cache.put(4, 40); // 2 is removed due to capacity limit
    assertEquals(-1, cache.get(2)); // 2 is removed
    assertEquals(10, cache.get(1)); // 1 is still in cache
    assertEquals(30, cache.get(3)); // 3 is still in cache
    assertEquals(40, cache.get(4)); // 4 is in cache
  }
}
