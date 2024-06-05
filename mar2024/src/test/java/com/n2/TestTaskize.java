package com.n2;

import static com.n2.Taskize.highlightedText;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
public class TestTaskize {

  @Test
  public void testNoKeywords() {
    // No highlights
    assertEquals("<span>foo bar baz</span>", highlightedText("foo bar baz", "blort", "hl"));
  }

  @Test
  public void testSingleKeyword() {
    // Highlights the keyword "bar"
    assertEquals("<span>foo </span><span class=\"hl\">bar</span><span> baz</span>", highlightedText("foo bar baz", "bar", "hl"));
  }

  @Test
  public void testMultipleKeywords() {
    // Highlights multiple keywords "bar" and "blort"
   // assertEquals("<span>foo </span><span class=\"hl\">bar</span><span> baz</span>", highlightedText("foo bar baz", "bar blort", "hl"));
    assertEquals("<span>foo </span><span class=\"hl\">bar</span><span> baz </span><span class=\"hl\">blort</span><span></span>", highlightedText("foo bar baz blort", "bar blort", "hl"));
  }
  @Test
  public void testMultipleOccurrences() {
    // Highlights multiple occurrences of the keyword "bar"
    assertEquals("<span>foo </span><span class=\"hl\">bar</span><span> baz </span><span class=\"hl\">bar</span><span></span>", highlightedText("foo bar baz bar", "bar", "hl"));
    assertEquals("<span>Th</span><span class=\"hl\">is</span><span> </span><span class=\"hl\">is</span><span> a test</span>", highlightedText("This is a test", "is", "hl"));
  }
  @RepeatedTest(2)
  public void testCaseInsensitive() {
    // Highlights keywords in a case-insensitive manner
    assertEquals("<span>Raj foo </span><span class=\"hl\">BAR</span><span> baz</span>", highlightedText("Raj foo BAR baz", "bar", "hl"));
    assertEquals("<span>Th</span><span class=\"hl\">IS</span><span> </span><span class=\"hl\">is</span><span> a test</span>", highlightedText("ThIS is a test", "is", "hl"));
  }
  @RepeatedTest(2)
  public void testOutOfMemoryError() {
    // Minimal text
    String text = "aa bb aa";

    // Keywords with a few spaces - List Growth: The while loop in highlightedText keeps processing and adding to the parts list without termination
    String keywords = "a a a a a  \n \n";
    String finalText = text;
    String finalKeywords = keywords;
    assertDoesNotThrow(() -> Taskize.highlightedText(finalText, finalKeywords, "hl"));
  }

}
