package com.n2.l49;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@ToString
@Getter//for tests
public class Risk {
  private Double beta;
  private Double gamma;
}
