package com.n2;

import java.util.Arrays;

//https://app.codility.com/programmers/task/number_of_disc_intersections/
public class NumberOfDiscIntersections {
  public int solution(int[] a) {
    int len = a.length;
    int j = 0;
    DiscLog[] discLogs = new DiscLog[len*2];
    for (int i = 0; i < len; i++) {
      discLogs[j++] = new DiscLog(i - a[i], 1);//Left position of the radius
      discLogs[j++] = new DiscLog(i + a[i], 0);//Right position of the radius
    }
    Arrays.sort(discLogs);
    System.out.println(Arrays.toString(discLogs));
    return 1;
  }
  private static class DiscLog implements Comparable<DiscLog> {
    final int x;
    final int startEnd;// start is 1 and end is 0

    public DiscLog(int x, int startEnd) {
      this.x = x;
      this.startEnd = startEnd;
    }

    @Override
    public int compareTo(DiscLog other) {
      return (this.x != other.x) ? Integer.compare(this.x, other.x) : Integer.compare(this.startEnd, this.startEnd);
    }

    @Override
    public String toString() {
      return "DiscLog{" +
          "x=" + x +
          ", startEnd=" + startEnd +
          '}'+"\n";
    }
  }
}
