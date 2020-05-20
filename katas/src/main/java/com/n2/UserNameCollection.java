package com.n2;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

class UserNameCollection {
  private Map<UserKey, BigDecimal> userMap = new HashMap (10);
  protected void add(String firstName, String lastName, BigDecimal salary) {
    UserKey userKey = new UserKey(firstName, lastName);
  }
}

@AllArgsConstructor
class UserKey {
  private String firstName;
  private String lastName;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserKey)) {
      return false;
    }

    UserKey userKey = (UserKey) o;

    if (!firstName.equals(userKey.firstName)) {
      return false;
    }
    return lastName.equals(userKey.lastName);
  }

  @Override
  public int hashCode() {
    int result = firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    return result;
  }
}
