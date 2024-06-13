package com.n2.l49;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Quote {
  private String symbol;
  private Double value;
  private Long time;
}
