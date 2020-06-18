package com.n2.misc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
    Given a list of words ["mat", "eats", "car", "arc", "tree", "east", "teas"],

    Write a function to sort into sets of words that are anagrams of each other (i.e. contain the same letters)

    Expected result [["mat"], ["eats", "east", "teas"], ["car", "arc"], ["tree"]]

*/
public class AnagramSorter {

    public static Collection<Set<String>> sortAnagrams(List<String> strings) {

        if (strings == null || strings.isEmpty()) {
            return Collections.EMPTY_SET;
        }

        Map<String, Set<String>> map = new HashMap<>();
        for (String s : strings) {
            if (s == null || s.trim().isEmpty()) {
                continue;
            }
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String key = Arrays.toString(array);
            Set set = map.getOrDefault(key, new HashSet<>());
            set.add(s);
            map.put(key, set);
        }
        return map.values();
    }

    public static void main(String[] args) {
        System.out
            .println(sortAnagrams(List.of("mat", "eats", "car", "arc", "tree", "east", "teas")));
    }
}
