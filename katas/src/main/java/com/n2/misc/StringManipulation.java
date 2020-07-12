package com.n2.misc;

import static java.util.Comparator.comparing;
import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//TODO: Yet to be completed
/**
 * https://www.jaktech.co.uk/interview-questions/ovo-energy-software-engineer-interview-question/
 *
 * You are given the following text:
 *
 * lorem ipsum dolor sit amet consectetur lorem ipsum et mihi quoniam et adipiscing elit.sed quoniam et advesperascit et mihi ad villam revertendum est nunc quidem hactenus ex rebus enim timiditas non ex vocabulis nascitur.nummus in croesi divitiis obscuratur pars est tamen divitiarum.nam quibus rebus efficiuntur voluptates eae non sunt in potestate sapientis.hoc mihi cum tuo fratre convenit.qui ita affectus beatum esse numquam probabis duo reges constructio interrete.de hominibus dici non necesse est.eam si varietatem diceres intellegerem ut etiam non dicente te intellego parvi enim primo ortu sic iacent tamquam omnino sine animo sint.ea possunt paria non esse.quamquam tu hanc copiosiorem etiam soles dicere.de quibus cupio scire quid sentias.universa enim illorum ratione cum tota vestra confligendum puto.ut nemo dubitet eorum omnia officia quo spectare quid sequi quid fugere debeant nunc vero a primo quidem mirabiliter occulta natura est nec perspici nec cognosci potest.videmusne ut pueri ne verberibus quidem a contemplandis rebus perquirendisque deterreantur sunt enim prima elementa naturae quibus auctis virtutis quasi germen efficitur.nam ut sint illa vendibiliora haec uberiora certe sunt.cur deinde metrodori liberos commendas.mihi inquam qui te id ipsum rogavi nam adhuc meo fortasse vitio quid ego quaeram non perspicis.quibus ego vehementer assentior.cur iustitia laudatur mihi enim satis est ipsis non satis.quid est enim aliud esse versutum nobis heracleotes ille dionysius flagitiose descivisse videtur a stoicis propter oculorum dolorem.diodorus eius auditor adiungit ad honestatem vacuitatem doloris.nos quidem virtutes sic natae sumus ut tibi serviremus aliud negotii nihil habemus.
 *
 * Here are a few facts and definition about the text above:
 *
 * Everything is lowercase.
 * There are only letters, full stops (.), and single whitespace characters.
 * A word is defined as a sequence of letters delimited by either a whitespace or a full stop . character.
 * A full stop character is not considered a word. A full stop is never preceded or followed by whitespace.
 * Any two words are separated either by a single whitespace character (dolor sit), or by a full stop with no spaces (elit.sed)
 * A sentence is defined as a sequence of words delimited by a full stop . character.
 * Note:
 *
 * You are free to copy paste the text above as a string in your preferred language, there is no need to read from a file in your code.
 * The text has been cleaned up to allow for trivial parsing, we are not particularly interested in parsing techniques for now.
 * At this stage, your solution is only required to work with the text above, do not worry about the general case.
 * Strive to write clear and maintainable code, performance comes later.
 * Code answers to the following questions about the text above
 * How many words are there in the text?
 * How many sentences are there in the text?
 * What is the length of the longest word?
 * Which six words occur the most in the text?
 * What percentage of the words only occur once?
 * What is the average number of words per sentence?
 * Which three two-word phrases occur the most in the text?
 * Bonus question
 * What is the prominence of the five words that occur the most in the text? Prominence can be defined as the ratio of the position of a given word to the position of the other words in a text (the earlier in the text a word occurs, the more prominent it is).
 * Prominence can be calculated in the following way:
 *
 * prominence = (totalWords - ((positionSum - 1) / positionsNum)) * (100 / totalWords)
 * where:
 *
 * totalWords is the total number of words in a text.
 * positionSum is the sum of all positions of the word (e.g. if a word occurs on position 1 and 4 the sum of its positions is 5).
 * positionsNum is the number of positions of the word.
 * the first word in a text is at position 1.
 * The prominence of one word in the first position in a fifteen word sentence having unique words is: (15 - ((1 - 1) / 1)) * (100 / 15)) = 100%
 *
 * The prominence of the same word being the last word in that sentence would be: (15 - ((15 - 1) / 1)) * (100 / 15)) = 6.67%
 *
 * The prominence of the same word occurring three times on position 1, 7 and 15 would be: (15 - ((23 - 1) / 3)) * (100 / 15)) = 51.11%
 */
public class StringManipulation {
  private String input;

  public StringManipulation(String input) {
    this.input = input;
  }

  //How many words are there in the text?
  public int wordCount() {
    return words().length;
  }

  //How many sentences are there in the text?
  public int sentenceCount() {
    String[] sentencesInit = input.split("\\.");
    final String[] sentences = Arrays.stream(sentencesInit).filter(a -> !a.trim().isBlank())
        .toArray(String[]::new);
    return sentences.length;
  }

  //What is the length of the longest word?
  public int longestWord() {
    final String[] words = words();
    int len = words.length;
    int max = 0;
    for (int i = 0; i < len; i++) {
      String word = words[i];
      int wordLen = word.length();
      if (wordLen>max) {
        max = wordLen;
      }
    }
    return max;
  }

  //Which six words occur the most in the text?
  //TODO: Incorrect. Use caterpillar method
  public String highFrequentSixWords() {
    //Replace all the dots and make a uniform text
    //Make all the words as lowercase
    //Split all the strings in an array
    String[] wordsInit = words();
    final String[] words = Arrays.stream(wordsInit).map(String::toLowerCase).collect(Collectors.toList()).toArray(String[]::new);
    //Gather every six words into a String
    Map<String, Integer> frequencyMap = new HashMap<>(words.length/6);
    int len = words.length;
    int startIndex = 0;
    int endIndex = 0;
    while (len>0) {
      endIndex+=6;
      if (endIndex>words.length || startIndex>words.length) {
        break;
      }
      StringBuilder key = new StringBuilder();
      for (int i = startIndex; i < endIndex; i++) {
        key.append(words[i]);
        key.append(" ");
      }
      //Put or Update the String in a Map with the corresponding frequency
      frequencyMap.merge(key.toString(), 1, Integer::sum);
      startIndex+=6;
      len-=6;
    }
    //Find the Max value of the HashMap and get the entry
    final String highFrequentString = findMax(frequencyMap);
    //Return the high frequency
    return highFrequentString;
  }

  //What percentage of the words only occur once?
  public double percentOfSingleOccuringWords() {
    //Replace all the dots and make a uniform text
    //Make all the words as lowercase
    String[] wordsInit = words();
    final String[] words = Arrays.stream(wordsInit).map(String::toLowerCase).collect(Collectors.toList()).toArray(String[]::new);
    Map<String, Integer> frequencyMap = new HashMap<>(words.length);
    for (String word: words) {
      frequencyMap.merge(word,1,Integer::sum);
    }
    final long count = countOfWordsWithAGivenValue(frequencyMap, 1);
    final double percent = (Double.valueOf(count)/Double.valueOf(wordCount()))*100;
    return percent;
  }

  //What is the average number of words per sentence?
  public double averageNoOfWordsPerSentence() {
    return wordCount()/sentenceCount();
  }

  //Which three two-word phrases occur the most in the text?
  public String[] threeFrequent2Words() {
    final String cleanText = input.replaceAll(".", "//s").toLowerCase();
    String[] wordsInit = words();
    final String[] words = Arrays.stream(wordsInit).map(String::toLowerCase).collect(Collectors.toList()).toArray(String[]::new);
    int len = words.length;
    int startIndex = 0;
    Map<String, Integer> frequencyMap = new HashMap<>(words.length);
    while( len> startIndex+1) {
      String twoWord = words[startIndex]+" "+words[startIndex+1];
      frequencyMap.merge(twoWord, 1, Integer::sum);
      startIndex++;
    }
    final Map<Integer, List<Entry<String, Integer>>> collect = frequencyMap.entrySet()
        .stream()
        .collect(groupingBy(Entry::getValue)).entrySet().stream()
        .collect(toMap(a -> a.getKey(), a -> a.getValue()));

    // now let's sort the map in decreasing order of keys
    final LinkedHashMap<Integer, List<Entry<String, Integer>>> collect1 = collect.entrySet()
        .stream().sorted(Collections.reverseOrder(comparingByKey()))
        .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    final List<String> strings = collect1.entrySet().stream()
        .map(a -> a.getValue().get(0).getKey()).collect(toList());
    return strings.toArray(new String[strings.size()]);
  }
  private <K, V extends Comparable<V>> K findMax(Map<K, V> map) {
    Entry<K, V> maxEntry = Collections.max(map.entrySet(), comparing(Entry::getValue));
    return maxEntry.getKey();
  }
  private long countOfWordsWithAGivenValue(Map<String, Integer> map, Integer value) {
    final Map<Integer, List<Entry<String, Integer>>> countVsSameWords = map.entrySet().stream()
        .collect(groupingBy(Entry::getValue));
    if (countVsSameWords.containsKey(1)) {
      return countVsSameWords.get(1).stream().count();
    }
    return 0l;
  }
  public String[] words() {
    final String clearText = input.replace(".", " ");
    String[] words = clearText.split("\\s+");
    return Arrays.stream(words).filter(a -> !a.trim().isBlank()).toArray(String[]::new);
  }
}
