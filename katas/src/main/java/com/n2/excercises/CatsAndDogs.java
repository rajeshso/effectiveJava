package com.n2.excercises;

//http://www.javaproblems.com/2013/11/java-string-2-catdog-codingbat-solution.html

/**
 * Return true if the string "cat" and "dog" appear the same number of times in the given string.
 * <p>
 * catDog("catdog") → true catDog("catcat") → false catDog("1cat1cadodog") → true
 */
public class CatsAndDogs {

  public static boolean catDog(String str) {
    int catBalanced = 0;
    int len = str.length();
    int index = 0;
    while (index < len) {
      if (str.startsWith("c", index)) {
        if (str.startsWith("cat", index)) {
          catBalanced += 1;
          index += 3;
        } else {
          index += 1;
        }
      } else if (str.startsWith("d", index)) {
        if (str.startsWith("dog", index)) {
          catBalanced -= 1;
          index += 3;
        } else {
          index += 1;
        }
      } else {
        index += 1;
      }
    }
    return catBalanced == 0;
  }

  //better
  public static boolean catDog1(String str) {
    int catBalanced = 0;
    int len = str.length();
    int index = 0;
    while (index < len) {
      if (str.startsWith("cat", index)) {
        catBalanced += 1;
        index += 3;
      } else if (str.startsWith("dog", index)) {
        catBalanced -= 1;
        index += 3;
      } else {
        index += 1;
      }
    }
    return catBalanced == 0;
  }


  public static void main(String[] args) {
    System.out.println(catDog("catdog"));
    System.out.println(catDog("catcat"));
    System.out.println(catDog("1cat1cadodog"));

    System.out.println(catDog1("catdog"));
    System.out.println(catDog1("catcat"));
    System.out.println(catDog1("1cat1cadodog"));
  }

}
