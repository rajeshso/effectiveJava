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
    LOGGER.info("Starting risk calculation...");
    // Create a fixed thread pool with the number of available processors
    ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

    // Submit tasks for each quote to calculate beta and gamma in parallel
    for (Quote quote : quotes) {
      if (quoteComplete.containsKey(quote.getSymbol()) && quoteComplete.get(quote.getSymbol()) > quote.getTime()) { // Skip quotes whose time is in the past
        LOGGER.info("Skipping older timestamped quote for " + quote.getSymbol());
        continue;
      }
      executor.submit(() -> {
        Risk risk = new Risk();
        risk.setBeta(calculateBeta(quote));
        risk.setGamma(calculateGamma(quote));
        riskMap.put(quote.getSymbol(), risk);
        LOGGER.info("Risk for " + quote.getSymbol() + ": " + risk);
      });
      quoteComplete.put(quote.getSymbol(), quote.getTime());
    }

    // Shutdown the executor and wait for all tasks to complete
    executor.shutdown();
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return riskMap;
  }

  @Override
  public String getRisk(String ticker) {
    if (riskMap != null) {
      Risk risk = riskMap.get(ticker);
      if (risk != null) {
        return risk.toString();
      }
    }
    return "";
  }

  //This is just a rough calculation. Don't need to focus on the actual calculation.
  private Double calculateGamma(Quote quote) {
    // Placeholder for historical volatility of the market (replace with actual calculation)
    double marketVolatility = 0.1;
    // Placeholder for historical volatility of the specific quote (replace with actual calculation)
    double quoteVolatility = 0.2;

    // Rough beta calculation based on volatility ratio
    return (quoteVolatility / marketVolatility) * quote.getValue();
  }

  //This is just a rough calculation. Don't need to focus on the actual calculation.
  private Double calculateBeta(Quote quote) {
    // Placeholder for an indicator of delta sensitivity
    double deltaSensitivity = 0.3;

    // Rough gamma based calculation on delta sensitivity
    return deltaSensitivity * quote.getValue();
  }
}
