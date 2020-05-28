package com.n2.codility;

public class Flags {
  public static int solution1(int[] n) {
    int len = n.length;
    int flags =0;
    int past=0,current=0,future=0;
    for (int i = 0; i < len; i+=3) {
      current = n[i];
      if (i==0) {
        past = 0;
      }else {
        past = n[i-1];
      }
      if (future>len) {
        future = len;
      }else {
        future = n[i+1];
      }
    }
    return 0;
  }
}
