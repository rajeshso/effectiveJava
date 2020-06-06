package com.n2.execSchedule;


class VegetableChopper extends Thread {

  public int vegetablesCut = 0;
  public static boolean chopping = true;

  public VegetableChopper(String name) {
    super(name);
  }

  @Override
  public void run() {
    while (chopping) {
      vegetablesCut++;
      System.out.printf("\n %s chopped a vegetable", this.getName());
    }
  }
}

public class ExecutionScheduleDemo {

  public static void main(String[] args) throws InterruptedException {
    VegetableChopper rajesh = new VegetableChopper("Rajesh");
    VegetableChopper priya = new VegetableChopper("Priya");
    rajesh.start();
    priya.start();
    Thread.sleep(1000);
    VegetableChopper.chopping = false;
    rajesh.join();
    priya.join();
    System.out.printf("\n Rajesh chopped %d vegetables ", rajesh.vegetablesCut);
    System.out.printf("\n Priya chopped %d vegetables ", priya.vegetablesCut);
  }
}
