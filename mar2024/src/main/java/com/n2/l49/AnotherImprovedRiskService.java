package com.n2.l49;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AnotherImprovedRiskService implements RiskServiceInterface {

  private final Map<String, Risk> riskMap = new ConcurrentHashMap<>();
  private final Map<String, Long> latestQuoteTimestamps = new ConcurrentHashMap<>();

  @Override
  public Map<String, Risk> calculateRisk(Set<Quote> quotes) {
    System.out.println("Starting parallel risk calculation of the quotes ");
    //Create a fixed thread pool
    ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //submit tasks for each quote to calculate beta and gamma in parallel
    for (Quote quote : quotes) {
      executor.submit(() -> {
        latestQuoteTimestamps.compute(quote.getSymbol(),(symbol, existingTimeStamp)->{
          Long newTimeStamp = quote.getTime();
          if (existingTimeStamp == null || newTimeStamp > existingTimeStamp) { //If the new quote is much newer than the earlier, please calculate
            System.out.println("Accepting the quote for the calculation for "+quote.getSymbol() + " because the given timestamp "+newTimeStamp +" is much newer");
            Risk risk = new Risk();
            risk.setBeta(calculateBeta(quote));
            risk.setGamma(calculateGamma(quote));
            System.out.println("The Risk for symbol "+risk);
          }else {
            System.out.println("Skipping the calculation for "+quote.getSymbol() + " because the given timestamp "+newTimeStamp +" is obsolete");
          }
          return newTimeStamp;
        });
      });


/*      executor.submit(()-> {
        Risk risk = new Risk();
        risk.setBeta(calculateBeta(quote));
        risk.setGamma(calculateGamma(quote));
        riskMap.put(quote.getSymbol(),risk);
        System.out.println("Risk for "+quote.getSymbol()+" is "+risk);
      });*/
    }
    //Shutdown the executor and wait for all existing tasks to complete
    executor.shutdown();
    //block until all tasks are completed
    try {
      executor.awaitTermination(1, TimeUnit.MINUTES);
    }catch (InterruptedException exception) {
      executor.shutdownNow();
    }
    return riskMap;
  }


  @Override
  public String getRisk(String ticker) {
    return riskMap.get(ticker) == null ? "" : riskMap.get(ticker).toString();
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
