package com.n2.l49;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class RiskService implements RiskServiceInterface {

  @Override
  public Map<String, Risk> calculateRisk(Set<Quote> quotes) {

    return Collections.emptyMap();
  }

  @Override
  public String getRisk(String ticker) {
    return "";
  }
}
