package com.n2;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class TestBigDecimalOperations {

  @Test
  void testBigDecimalOperations(){
     BigDecimal a = new BigDecimal("1.0");
     BigDecimal b = new BigDecimal("2.0");
     BigDecimal c = a.add(b);
     System.out.println("a = "+a);
     System.out.println("b = "+b);
     System.out.println("c = "+c);

     BigDecimal d = a.subtract(b);
    System.out.println("a = "+a);
    System.out.println("b = "+b);
    System.out.println("c = "+c);
     System.out.println("d = "+d);
    // BigDecimal e = a.multiply(b);
    // System.out.println(e);
    // BigDecimal f = a.divide(b);
    // System.out.println(f);
  }
}
