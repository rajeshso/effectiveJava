package com.n2.l49;


public class Risk {
  private Double beta;
  private Double gamma;

  public Risk() {
  }

  public Double getBeta() {
    return beta;
  }

  public void setBeta(Double beta) {
    this.beta = beta;
  }

  public Double getGamma() {
    return gamma;
  }

  public void setGamma(Double gamma) {
    this.gamma = gamma;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Risk risk)) {
      return false;
    }

    return getBeta().equals(risk.getBeta()) && getGamma().equals(risk.getGamma());
  }

  @Override
  public int hashCode() {
    int result = getBeta().hashCode();
    result = 31 * result + getGamma().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Risk{" +
        "beta=" + beta +
        ", gamma=" + gamma +
        '}';
  }
}
