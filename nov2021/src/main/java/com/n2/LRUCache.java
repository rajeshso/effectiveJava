package com.n2;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class LRUCache {
  private final HashSet<Integer> set;
  private final LinkedList<Integer> deque;
  private final int maxSize;

  public LRUCache(int maxSize) {
    this.maxSize = maxSize;
    deque = new LinkedList();
    set = new HashSet<>(maxSize);
  }

  public void refer(int x) {
    //Resize the deque: Check if x is present or absent
    //if absent
       //if absent and if the set reached maxSize, remove the last
       //if absent and if the set has not reached maxSixe, carry on
    //if present
       //remove from deque
    if (!set.contains(x)) { //absent
      if (set.size() == maxSize) { //resize
        Integer lastelement = deque.removeLast();
        set.remove(lastelement);
      }
    }else { //present
      // prepare reorder
      deque.remove((Integer) x);
    }
    //push x to the top
    deque.push(x);
    set.add(x);
  }
  void display() {
    System.out.println("deque = "+deque);
    System.out.println("set = "+set);
  }
  public HashSet<Integer> getSet() {
    return set;
  }

  public Deque<Integer> getDeque() {
    return deque;
  }

  public int getMaxSize() {
    return maxSize;
  }
}
