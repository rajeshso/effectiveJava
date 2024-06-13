package com.n2.l49;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImprovedRiskService extends RiskService {
  private final static Logger LOGGER = LoggerFactory.getLogger(ImprovedRiskService.class);
  // Number of threads to use for parallel processing
  private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
  // Create a map to store the results
  Map<String, Risk> riskMap = new ConcurrentHashMap<>();
  Map<String, Long> quoteComplete = new ConcurrentHashMap<>();

  @Override
  public Map<String, Risk> calculateRisk(Set<Quote> quotes) {
    System.out.println("Starting risk calculation...");
    // Create a fixed thread pool with the number of available processors
    ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

    // Submit tasks for each quote to calculate beta and gamma in parallel
    for (Quote quote : quotes) {
      executor.submit(() -> {
        quoteComplete.compute(quote.getSymbol(), (symbol, time) -> {
          if (time == null || quote.getTime() > time) {
            Risk risk = new Risk();
            risk.setBeta(calculateBeta(quote));
            risk.setGamma(calculateGamma(quote));
            riskMap.put(symbol, risk);
            LOGGER.info("Risk for " + symbol + ": " + risk);
            return quote.getTime();
          } else {
            LOGGER.info("Skipping older timestamped quote for " + symbol);
            return time;
          }
        });
      });
    }

    // Shutdown the executor and wait for all tasks to complete
    executor.shutdown();
    try {
      if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }

    LOGGER.info("Risk calculation complete.");
    return riskMap;
  }

  @Override
  public String getRisk(String ticker) {
    Risk risk = riskMap.get(ticker);
    return risk != null ? risk.toString() : "";
  }

  //This is just a rough calculation. Don't need to focus on the actual calculation.
  private Double calculateGamma(Quote quote) {
    try {
      TimeUnit.MILLISECONDS.sleep(600);//Assume that the calculation of gamma takes 600 milliseconds
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    double marketVolatility = 0.1;
    double quoteVolatility = 0.2;
    return (quoteVolatility / marketVolatility) * quote.getValue();
  }

  //This is just a rough calculation. Don't need to focus on the actual calculation.
  private Double calculateBeta(Quote quote) {
    try {
      TimeUnit.MILLISECONDS.sleep(300);//Assume that the calculation of beta takes 300 milliseconds
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    double deltaSensitivity = 0.3;
    return deltaSensitivity * quote.getValue();
  }

}
