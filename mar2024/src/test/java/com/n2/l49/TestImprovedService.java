package com.n2.l49;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class TestImprovedService {
  @Test
  public void testProcessQuoteWithDifferentTimestamps() {
    ImprovedRiskService service = new ImprovedRiskService();
    Set<Quote> quotes = new HashSet<>();

    String symbol = "AAPL";
    long currentTime = System.currentTimeMillis();

    Quote quote1 = new Quote(symbol,  3.01,currentTime);
    Quote quote2 = new Quote(symbol, 3.38,currentTime + 2);
    Quote quote3 = new Quote(symbol, 2.79,currentTime - 2);

    quotes.add(quote1);
    quotes.add(quote2);
    quotes.add(quote3);

    Map<String, Risk> riskMap = service.calculateRisk(quotes);

    // Add assertions to verify the behavior
    assertNotNull(riskMap.get(symbol));
    assertEquals(1.014, riskMap.get(symbol).getBeta());
  }
}
