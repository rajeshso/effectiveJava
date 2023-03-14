package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TestOrderStateService {
  public static <K, V extends Comparable<V> > TreeMap<K, V>
  valueSort(final TreeMap<K, V> map)
  {
    Comparator<K> valueComparator = new Comparator<K>() {
      // return comparison results of values of
      // two keys
      public int compare(K k1, K k2)
      {
        int comp = map.get(k2).compareTo(
            map.get(k1));
        if (comp == 0)
          return 1;
        else
          return comp;
      }
    };

    // SortedMap created using the comparator
    TreeMap<K, V> sorted = new TreeMap<K, V>(valueComparator);

    sorted.putAll(map);

    return sorted;
  }
  TreeMap<String, Integer> treeMap = new TreeMap<>();
  public void addOrder(String itemID) {
    treeMap.compute(itemID, (k,v)->(v == null) ? 0 : v+1);
  }

  public Collection<String> getTopOrders(int count) {
    treeMap = valueSort(treeMap);
    return treeMap.keySet().stream().limit(count)
        .collect(Collectors.toList());
  }
  @Test
  void simpleTest2() {
    List<String> orders1 = List.of("a","b","b","c","c","c","d","d","d","d","d","d");
    orders1.forEach(a->addOrder(a));
    assertThat(getTopOrders(3)).containsExactly("d","c","b");
  }
}
