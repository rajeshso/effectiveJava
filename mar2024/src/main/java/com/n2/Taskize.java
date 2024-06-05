package com.n2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Taskize {

  /**
   * Highlights specified keywords in a given text by wrapping them with a span element with a specified CSS class.
   *
   * @param text The text to search within.
   * @param keywords Space-separated keywords to highlight.
   * @param highlightClassName The CSS class name to use for highlighting.
   * @return The text with highlighted keywords.
   */
  public static String highlightedText(String text, String keywords, String highlightClassName) {
    // Split keywords into an array of words
    String[] words = keywords.split(" ");
    List<Span> parts = new ArrayList<>();
    int index = 0;
    int maxIterations = text.length() * words.length;  // Circuit Breaker - to prevent infinite loop
    // Loop to find and highlight each occurrence of the keywords in the text
    for (int count = 0; index < text.length() && count < maxIterations; count++) {
      printMemoryUsage();  // Added this line to print memory usage
      // Find the first matching keyword from the current index
      String word = findFirstMatchingKeyword(text, words, index);
      if (word == null) break;  // No more matches
      // Find the starting index of the found keyword in the text
      int startIndex = indexOfIgnoreCase(text, word, index);
      if (startIndex == -1) break;  // No more matches
      // Add the non-highlighted part of the text before the found keyword
      parts.add(new Span(text.substring(index, startIndex), null));
      // Move the index to the end of the found keyword
      index = startIndex + word.length();
      parts.add(new Span(text.substring(startIndex, index), highlightClassName));
    }
    // Add the highlighted part of the text (the found keyword)
    parts.add(new Span(text.substring(index), null));
    // Combine all parts into a single string and return it
    return String.join("", parts.stream().map(Span::toString).toList());
  }

  /**
   * Finds the first keyword that matches in the text starting from the given index.
   * Finds the first keyword that matches the text starting from a given index.
   * @param text The text to search within.
   * @param keywords Array of keywords to search for.
   * @param index The starting index to search from.
   * @return The first matching keyword.
   */
  private static String findFirstMatchingKeyword(String text, String[] keywords, int index) {
    // Create an IntStream from 0 to the length of keywords
    return IntStream.range(0, keywords.length)
        // Map each index to a KeywordMatch object containing the keyword index and its match index in the text
        .mapToObj(i -> new KeywordMatch(i, indexOfIgnoreCase(text, keywords[i], index)))// creates multiple (keywords.length) intermediate new KeywordMatch objects and streams them through various operations such as mapping, filtering, and finding the minimum match index.
        // Filter out KeywordMatch objects where the match index is -1 (no match found)
        .filter(m -> m.matchIndex != -1)
        // Find the KeywordMatch object with the smallest match index
        .min(Comparator.comparingInt(a -> a.matchIndex))
        // If a matching keyword is found, return the keyword, otherwise return the first keyword in the array
        .map(m -> keywords[m.keywordIndex])
        .orElse(keywords[0]);//this does not occur because of the check in the calling method
  }

  // Record class to store the keyword index and match index
  private record KeywordMatch(int keywordIndex, int matchIndex) {}

  /**
   * Checks if the text contains the keyword (case-insensitive).
   *  Checks if the text contains the keyword, ignoring case.
   *
   * ***** Alert: The indexOfIgnoreCase and containsIgnoreCase methods may keep matching the repetitive patterns, causing the loop to continue indefinitely. *****
   *
   *
   * @param haystack The text to search within.
   * @param needle The keyword to search for.
   * @return True if the keyword is found, false otherwise.
   */
  @Deprecated
  private static boolean containsIgnoreCase(String haystack, String needle) {
    return Pattern.compile(Pattern.quote(needle), Pattern.CASE_INSENSITIVE)//compiles a new regular expression pattern each time it is called.
        .matcher(haystack)
        .find();
  }

  /**
   * Finds the index of the keyword in the text starting from the given index (case-insensitive).
   * Finds the index of the keyword in the text, ignoring case.
   *
   * ***** Alert: The indexOfIgnoreCase and containsIgnoreCase methods may keep matching the repetitive patterns, causing the loop to continue indefinitely. *****
   *
   * @param haystack The text to search within.
   * @param needle The keyword to search for.
   * @param startIndex The starting index to search from.
   * @return The index of the keyword, or -1 if not found.
   */
  private static int indexOfIgnoreCase(String haystack, String needle, int startIndex) {
    Matcher matcher = Pattern.compile(Pattern.quote(needle), Pattern.CASE_INSENSITIVE)
        .matcher(haystack);//compiles a new regular expression pattern each time it is called and creates a Matcher object to search the text. This involves creating intermediate objects for pattern matching.
    boolean found = matcher.find(startIndex);

    if (!found) {
      return -1;
    }
    return matcher.start();
  }

  // Record class to represent a span of text with an optional CSS class name
  //A record that represents a span of text, optionally with a CSS class for highlighting.
  private record Span(String text, String className) {
    @Override
    public String toString() {
      if (this.className == null) {
        return "<span>" + this.text + "</span>";
      }
      return "<span class=\"" + this.className + "\">" + this.text + "</span>";
    }
  }
  public static void printMemoryUsage() {
    Runtime runtime = Runtime.getRuntime();
    long totalMemory = runtime.totalMemory();
    long freeMemory = runtime.freeMemory();
    long usedMemory = totalMemory - freeMemory;
    System.out.println("Used memory: " + usedMemory / (1024 * 1024) + " MB");
  }
  public static void main(String[] args) {
    Taskize test = new Taskize();
    String text = "aa bb aa";

    // Keywords with a few spaces - potential for infinite loop should be fixed
    String keywords = "a a a a a  \n \n";
    highlightedText(text, keywords, "hl");
  }
}
