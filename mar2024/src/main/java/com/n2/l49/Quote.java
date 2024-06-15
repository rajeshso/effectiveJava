package com.n2.l49;

public class Quote {
  private final String symbol;
  private final Double value;
  private final Long time;

  public Quote(String symbol, Double value, Long time) {
    this.symbol = symbol;
    this.value = value;
    this.time = time;
  }

  public String getSymbol() {
    return symbol;
  }

  public Double getValue() {
    return value;
  }

  public Long getTime() {
    return time;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Quote quote)) {
      return false;
    }
    return ( symbol.equals(quote.symbol) && value.equals(quote.value) && time.equals(quote.time));
  }

  @Override
  public  int hashCode() {
    return symbol.hashCode()+value.hashCode()+time.hashCode();
  }

/*  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Quote quote)) {
      return false;
    }

    return symbol.equals(quote.symbol) && value.equals(quote.value) && time.equals(quote.time);
  }*/

/*  @Override
  public int hashCode() {
    int result = symbol.hashCode();
    result = 31 * result + value.hashCode();
    result = 31 * result + time.hashCode();
    return result;
  }*/

  @Override
  public String toString() {
    return "Quote{" +
        "symbol='" + symbol + '\'' +
        ", value=" + value +
        ", time=" + time +
        '}';
  }
}
