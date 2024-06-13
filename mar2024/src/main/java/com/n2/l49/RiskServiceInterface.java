package com.n2.l49;

import java.util.Map;
import java.util.Set;

public interface RiskServiceInterface {
  Map<String, Risk> calculateRisk(Set<Quote> quotes);
  String getRisk(String ticker);
}
