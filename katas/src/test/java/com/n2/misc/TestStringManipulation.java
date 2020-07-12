package com.n2.misc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestStringManipulation {

  //How many words are there in the text?
  @Test
  public void testNoOfWords() {
    StringManipulation l = new StringManipulation("The Great Saint");
    assertThat(l.wordCount()).isEqualTo(3);
  }

  @Test
  public void testNoOfWordsWithFullstop() {
    StringManipulation l = new StringManipulation("The Great Saint.And the Great Man");
    assertThat(l.wordCount()).isEqualTo(7);
  }

  @Test
  public void testNoOfWordsWithEmptyString() {
    StringManipulation l = new StringManipulation("");
    assertThat(l.wordCount()).isEqualTo(0);
  }

  //How many sentences are there in the text?
  @Test
  public void testNoOfSentences() {
    StringManipulation l = new StringManipulation("I love java.I love java too");
    assertThat(l.sentenceCount()).isEqualTo(2);
  }
  @Test
  public void testNoOfSentencesGivenNoFullStops() {
    StringManipulation l = new StringManipulation("I love java");
    assertThat(l.sentenceCount()).isEqualTo(1);
  }
  @Test
  public void testNoOfSentencesGivenEmptyString() {
    StringManipulation l = new StringManipulation("");
    assertThat(l.sentenceCount()).isEqualTo(0);
  }
  @Test
  public void testNoOfSentencesWithSingleFullStop() {
    StringManipulation l = new StringManipulation("I love java.");
    assertThat(l.sentenceCount()).isEqualTo(1);
  }
  //What is the length of the longest word?
  @Test
  public void testLongestWord() {
    StringManipulation l = new StringManipulation("I love java.");
    assertThat(l.longestWord()).isEqualTo(4);
  }
  @Test
  public void testLongestWordWithFullStop() {
    StringManipulation l = new StringManipulation("I luv java.");
    assertThat(l.longestWord()).isEqualTo(4);
  }
  @Test
  public void testLongestWordWithBlankString() {
    StringManipulation l = new StringManipulation(" ");
    assertThat(l.longestWord()).isEqualTo(0);
  }
  //TODO: Which six words occur the most in the text? has to be correctly implemented using caterpillar method

  //What percentage of the words only occur once?
  @Test
  public void testPercentageOfWordsOccurOnce() {
    StringManipulation l = new StringManipulation("A B C D E F G H I A");
    assertThat(l.percentOfSingleOccuringWords()).isEqualTo(80.00);
  }
  @Test
  public void testPercentageOfWordsOccurOnceFor100Percent() {
    StringManipulation l = new StringManipulation("A B C D E F G H I J");
    assertThat(l.percentOfSingleOccuringWords()).isEqualTo(100.00);
  }
  @Test
  public void testPercentageOfWordsOccurOnceFor0Percent() {
    StringManipulation l = new StringManipulation("A B C D E A B C D E");
    assertThat(l.percentOfSingleOccuringWords()).isEqualTo(0.00);
  }

  //What is the average number of words per sentence?
  @Test
  public void testAvgNumberOfWordsPerSentence() {
    StringManipulation l = new StringManipulation("I love java.I love java");
    assertThat(l.averageNoOfWordsPerSentence()).isEqualTo(3);
  }
  @Test
  public void testAvgNumberOfWordsPerSentenceWithSingleSentence() {
    StringManipulation l = new StringManipulation("I love java");
    assertThat(l.averageNoOfWordsPerSentence()).isEqualTo(3);
  }
  @Test
  public void testAvgNumberOfWordsPerSentenceWithDifferentNumbers() {
    StringManipulation l = new StringManipulation("I love java. I love java, scala and kotlin");
    assertThat(l.averageNoOfWordsPerSentence()).isEqualTo(4);
  }

  //Which three two-word phrases occur the most in the text?
  @Test
  public void testThreeFrequentTwoWords() {
    StringManipulation l = new StringManipulation("A A A A A A B B B B B B C C C C C C");
    assertThat(l.threeFrequent2Words()).contains("a a","b b", "c c");
  }
}
