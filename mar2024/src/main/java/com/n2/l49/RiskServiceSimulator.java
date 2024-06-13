package com.n2.l49;

/**
 A bank has an old application which receives a bulk collection of quotes at a set interval.
 Each of these quotes lists a price for a specific symbol, and has timestamp.
 The application contains a RiskService which processes the quotes by calculating risk (Beta and Gamma)
 using the RiskCalculationLibrary class and saves the results in a Map that can be queried.
 The problem is that this processing is too slow.
 The beta takes 300 milliseconds to calculate, and gamma takes 600 milliseconds.
 So if the application receives a bulk collection of ten quotes, it will take a total of 9 seconds to process them all.
 Assume the application will be running on a state-of-the-art multicore server that can handle hundreds of concurrent threads.
 */

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This is the Simulator to drive the Risk Service System.
 */
public class RiskServiceSimulator {
 // private final static Logger LOGGER = LoggerFactory.getLogger(RiskServiceSimulator.class);
  private static final int UPDATE_RISK_INTERVAL_SEC = 3;
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    // Create a risk service which receives batches timestamped of quotes,
    // calculates risk measures "beta" and "gamma", and saves the results so
    // that they can be queried later.

    final RiskServiceInterface riskService = new ImprovedRiskService();

    // Try out the service by sending a batch of quotes every ten seconds
    new ScheduledThreadPoolExecutor(1).scheduleWithFixedDelay(() -> {

      //If there are multiple quotes with the same symbol, only the latest time quote has to be considered. the older quote has to be ignored
      Set<Quote> quotes = new HashSet<>();
      quotes.add(new Quote("IBM", 1.99, System.currentTimeMillis()));
      quotes.add(new Quote("IBM", 2.01, System.currentTimeMillis() + 2));
      quotes.add(new Quote("MSFT", 3.44, System.currentTimeMillis() + 1));
      quotes.add(new Quote("MSFT", 3.38, System.currentTimeMillis() + 3));
      quotes.add(new Quote("HW", 1.12, System.currentTimeMillis()));
      quotes.add(new Quote("AAPL", 3.38, System.currentTimeMillis()));
      quotes.add(new Quote("AAPL", 3.52, System.currentTimeMillis() + 2));

      long startTime = System.currentTimeMillis();
      riskService.calculateRisk(quotes);
      System.out.println("Risk calculation returned in : " + (System.currentTimeMillis() - startTime) + " milliseconds");

      System.out.println(riskService.getRisk("IBM"));
      System.out.println(riskService.getRisk("MSFT"));
    }, 0, UPDATE_RISK_INTERVAL_SEC, TimeUnit.SECONDS).get();

  }
}
