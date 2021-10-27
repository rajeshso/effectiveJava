package com.n2.excercises;

//https://runestone.academy/runestone/books/published/apcsareview/FreeResponse/HiddenWord.html
public class HiddenWord {

  private String word;
  private static final char IN_HIDDEN_WORD_IN_DIFFERENT_POSITION = '+';
  private static final char NOT_IN_HIDDEN_WORD = '*';
  private int len;

  public HiddenWord(String word) {
    this.word = word.toUpperCase();
    this.len = word.length();
  }

  public String getHint(String guess) {
    if (guess.length() != this.len) return "";
    guess = guess.toUpperCase();
    final char[] guessArr = guess.toCharArray();
    final char[] wordArr = word.toCharArray();
    char[] resultArr = new char[len];
    for (int i = 0; i < len; i++) {
      if (wordArr[i] == guessArr[i]) {
        resultArr[i] = wordArr[i];
      }else if (this.word.indexOf(guessArr[i]) != -1) {
        resultArr[i] = IN_HIDDEN_WORD_IN_DIFFERENT_POSITION;
      }else {
        resultArr[i] = NOT_IN_HIDDEN_WORD;
      }
    }
    return String.copyValueOf(resultArr);
  }
}
