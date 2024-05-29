package com.n2;

import static com.n2.Taskize.highlightedText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
//test cases to validate the functionality of the highlightedText method.
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
    // Large text and keywords to simulate high memory usage
    String text = "Real time data processing and RESTful microservices in Scala (Typelevel stack, Kafka, Cassandra, Kubernetes, GCP, AWS).\n"
        + "• Good working knowledge of Akka HTTP and Akka Streams is required to support existing services.\n"
        + "• Looking into how our personalisation services can evolve with machine learning.\n"
        + "• Having the freedom to self-organise as part of a cross functional agile team.\n"
        + "• Refining the team's processes to continuously integrate and working towards a deliverable application.\n"
        + "• Championing best practices such as Pair Programming and TDD in order to develop clean, resilient code that performs at serious scale.\n"
        + "• Coaching and providing feedback to fellow developers.\n"
        + "• Growing our engineering culture which is focussed on DevOps and GitOps principles.";
    text = text.repeat(1000); // Repeat to make the input significantly large

    // Keywords that are long and repetitive
    String keywords = "keyword1 keyword2 keyword3 keyword4 keyword5 keyword6 keyword7 keyword8 keyword9 keyword10";
    keywords = keywords + " " + keywords.repeat(50); // Create a very long and repetitive keyword string

    // Expect an OutOfMemoryError or handle it gracefully if the method can manage such input
    String finalText = text;
    String finalKeywords = keywords;
    assertThrows(OutOfMemoryError.class, () -> {
      highlightedText(finalText, finalKeywords, "hl");
    });
  }
}
