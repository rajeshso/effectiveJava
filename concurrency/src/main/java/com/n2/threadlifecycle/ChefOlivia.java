package com.n2.threadlifecycle;

class ChefOlivia extends Thread {

  ChefOlivia(String name) {
    super(name);
  }

  @Override
  public void run() {
    System.out.printf("\n %s Starting to thaw..", this.getName() + " " + this.getState());
    System.out.printf("\n %s is Waiting to cool down..", this.getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.printf("\n Cooled down");
    System.out.printf("\n Thaw complete");
  }
}

class ThreadLifecycleDemo {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Barron started and requests Olvia's help");
    Thread chefOlivia = new ChefOlivia("Olivia");
    System.out.println("Olivia state:" + chefOlivia.getState());

    System.out.println("Barron tells Olivia to start.");
    chefOlivia.start();
    System.out.println("\nOlivia state:" + chefOlivia.getState());

    System.out.println("\nBarron continues cooking soup");
    Thread.sleep(500);
    System.out.println("\nOlivia state:" + chefOlivia.getState());

    System.out.println("\n Barron patiently waits for Olivia to return");
    chefOlivia.join();
    System.out.println("Olivia state:" + chefOlivia.getState());

    System.out.println("\n Olivia returns");
    System.out.println("Barron and Olivia complete!");
  }
}

