package com.n2.misc;

public class Main {
  //The functional method of Consumer is accept(T t)
  //forEach takes a Consumer
  //Read more: https://www.java67.com/2018/10/java-8-stream-and-functional-programming-interview-questions-answers.html#ixzz7tmqSMQig
  @FunctionalInterface
  public interface ConsumerTalkable {
    void talk(String text);
  }

  //The Supplier is a functional interface that returns an object. It's similar to the factory method or new(), which returns an object
  //Read more: https://www.java67.com/2018/10/java-8-stream-and-functional-programming-interview-questions-answers.html#ixzz7tmqJI2ua
  @FunctionalInterface
  public interface SupplierTalkable {
    String talk(String text);
  }

  public static void main(String[] args) {
    ConsumerTalkable consumerTalkable = (text -> System.out.println("Talking "+text));
    SupplierTalkable supplierTalkable = (text -> "Talking " + text);
    String textToTalk = "Hello Expresso";
    consumerTalkable.talk(textToTalk);
    consumerTalkable.talk("to Rajesh");
    System.out.println(supplierTalkable.talk("to Rajesh"));
  }
}
