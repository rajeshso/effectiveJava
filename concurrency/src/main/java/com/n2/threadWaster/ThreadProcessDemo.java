package com.n2.threadWaster;

public class ThreadProcessDemo {

  public static void main(String[] args) {
    //display current information about this process
    Runtime rt = Runtime.getRuntime();
    long used = (rt.totalMemory() - rt.freeMemory()) / 1024;
    System.out.printf("   Process ID: %d \n", ProcessHandle.current().pid());
    System.out.printf(" Thread count: %d \n", Thread.activeCount());
    System.out.printf(" Memory usage: %d kb\n", used);

    //Start 6 new threads
    System.out.printf("\n Starting 6 new CPU waster threads....");
    for (int i = 0; i < 6; i++) {
      new Thread(new CPUWaster(), "Thread Number " + i).start();
    }

    //display current information about this process
    used = (rt.totalMemory() - rt.freeMemory()) / 1024;
    System.out.printf("   Process ID: %d \n", ProcessHandle.current().pid());
    System.out.printf(" Thread count: %d \n", Thread.activeCount());
    System.out.printf(" Memory usage: %d kb\n", used);
  }
}
