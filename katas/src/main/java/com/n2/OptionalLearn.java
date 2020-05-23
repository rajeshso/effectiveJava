package com.n2;

import java.util.Optional;

public class OptionalLearn {

  private void nullable() {
    String name = "Rajesh";
    System.out.println("---1---");
    System.out.println((name.length()>5) ? name: null);
    System.out.println("---2----");
    Optional<String> nameOptional = Optional.of(name);
    nameOptional.ifPresent(s-> System.out.println(s));
//    if (name!=null)
//      return name;
//    else
//      return "defaultValue";
    System.out.println("---3----");
    System.out.println(nameOptional.orElse("defaultValue"));
    System.out.println("---4----");
    System.out.println(Optional.ofNullable(name).orElse("defaultValue"));
    System.out.println("---5----");
    Optional.ofNullable(name).orElseGet(()->"defaultValue");
    System.out.println(nameOptional.filter(n->n.equals("Rajesh")).orElse("defaultValue"));
    System.out.println("---6----");
    //Difference between orElse and orElseGet
    String text = null;
    String defaultText = Optional.ofNullable(text).orElseGet(this::getDefaultValue);
    System.out.println(defaultText);
    defaultText = Optional.ofNullable(text).orElse(getDefaultValue());
    System.out.println(defaultText);

  }

  private  String getDefaultValue() {
    System.out.println("Getting Default Value");
    return "Default Value";
  }

  public static void main(String[] args) {
    new OptionalLearn().nullable();
  }
}
