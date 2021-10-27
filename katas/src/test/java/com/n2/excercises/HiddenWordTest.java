package com.n2.excercises;

import org.junit.jupiter.api.Test;

public class HiddenWordTest {

  @Test
  void test1() {
    HiddenWord hiddenWord = new HiddenWord("HARPS");
    assert(hiddenWord.getHint("AAAAA")).equals("+A+++");
    assert(hiddenWord.getHint("HELLO")).equals("H****");
    assert(hiddenWord.getHint("HEART")).equals("H*++*");
    assert(hiddenWord.getHint("HARMS")).equals("HAR*S");
    assert(hiddenWord.getHint("HARPS")).equals("HARPS");
  }
  @Test
  void test2() {
    HiddenWord hiddenWord = new HiddenWord("HARPS");
    assert(hiddenWord.getHint("aaaaa")).equals("+A+++");
    hiddenWord = new HiddenWord("laptop");
    assert(hiddenWord.getHint("laptop")).equals("LAPTOP");
    assert(hiddenWord.getHint("lappot")).equals("LAP+O+");
    assert(hiddenWord.getHint("DUKTOP")).equals("***TOP");
    assert(hiddenWord.getHint("      ")).equals("******");
    hiddenWord = new HiddenWord("new york");
    assert(hiddenWord.getHint("new york")).equals("NEW YORK");
    assert(hiddenWord.getHint("wen york")).equals("+E+ YORK");
    assert(hiddenWord.getHint(" wenyork")).equals("++++YORK");
    assert(hiddenWord.getHint("new yort")).equals("NEW YOR*");
    assert(hiddenWord.getHint("12345678")).equals("********");
    assert(hiddenWord.getHint("bad")).equals("");
  }
}
