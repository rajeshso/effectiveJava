package com.n2.misc;
//https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
//TODO: Yet to be completed
/**
 * LRU cache stand for Least Recently Used Cache. which evict least
 * recently used entry. As Cache purpose is to provide fast and
 * efficient way of retrieving data. it need to meet certain
 * requirement.
 *
 * Some of the Requirement are
 * Fixed Size: Cache needs to have some bounds to limit memory usages.
 * Fast Access: Cache Insert and lookup operation should be fast ,
 *  preferably O(1) time.
 * Replacement of Entry in case , Memory Limit is reached: A cache
 *  should have efficient algorithm to evict the entry when memory
 *  is full.
 *
 * In case of LRU cache we evict least recently used entry so we have
 * to keep track of recently used entries, entries which have not
 * been used from long time and which have been used recently. plus
 * lookup and insertion operation should be fast enough.
 */
public class LRUCache {

  class Entry {
    int key;
    int value;
    Entry left;
    Entry right;
  }

  private HashMap<Integer, Entry> hashMap;
  private Entry start, end;
  private final static int LRU_SIZE = 5;

  public LRUCache() {
    this.hashMap = new HashMap<>(LRU_SIZE);
  }

  public int get(int key) {
    if (hashMap.containsKey(key)) {
      Entry entry = hashMap.get(key);
      final int value = entry.value;
      addToTop(entry);
      return value;
    }
    return -1;
  }

  public void put(int key, int value ) {
    if (hashMap.containsKey(key)) { //just update the value and access point
      Entry entry = hashMap.get(key);
      entry.value = value;
      addToTop(entry);
    }else { //new entry
      Entry newNode = new Entry();
      newNode.key = key;
      newNode.value = value;
      newNode.left = null;
      newNode.right = null;
      if (hashMap.size() > LRU_SIZE) {
        //remove the last node and update the end
        removeNode(end);
      }
      //Add the new node and put this in the start
      addToTop(newNode);
      //enter the node in the hashmap
      hashMap.put(key, newNode);
    }
  }

  private void removeNode(Entry entry) {
    if (entry.right == null) { //if entry is the last node
      final Entry newEnd = entry.left;
      newEnd.right = null;
      end = newEnd;
    }else if (entry.left == null) { // if entry is the first node
      final Entry newStart = entry.right;
      newStart.left = null;
      start = newStart;
    }else { // if the entry is the middle node
      Entry left = entry.left;
      Entry right = entry.right;
      left.right = right;
      right.left = left;
    }
  }

  private void addToTop(Entry entry) {
    if (end == null)
      end = entry;
    if (start == null)
      start = entry;
    if (entry.left == null) {
      start = entry;
      return; //do nothing , entry is already in the top
    }else if (entry.right == null) { // if entry is at the end
      Entry newEnd = entry.left;
      newEnd.right = null;
      end = newEnd;
      start.left = entry;
      entry.left = null;
      entry.right = start;
      start = entry;
    }else { // if entry is in the middle
      Entry left = entry.left;
      Entry right = entry.right;
      left.right = right;
      right.left = left;
      start.left = entry;
      entry.right = start;
      entry.left = null;
      start = entry;
    }
  }

  public Map<Integer, Entry> getHashMap() {
    return Collections.unmodifiableMap(hashMap);
  }

  public Entry getStart() {
    return start;
  }

  public Entry getEnd() {
    return end;
  }
}
