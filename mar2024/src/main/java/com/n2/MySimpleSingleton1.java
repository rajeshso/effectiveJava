package com.n2;

final class MySimpleSingleton1 {
  //Lazy loading, non-thread-safe
  private final String name;

  private static MySimpleSingleton1 INSTANCE = null;
  private MySimpleSingleton1() {
    name = "Default Name";
  }
  private MySimpleSingleton1(String name) {
    this.name = name;
  }
  static MySimpleSingleton1 create(String name) {
    if (INSTANCE == null) {
      INSTANCE = new MySimpleSingleton1("Default Name");
    }
    return INSTANCE;
  }
  String myNameIs() {
    return name;
  }
}

final class MySingleton1 {
  //Early Loading Singleton, Thread safe
  private static final MySingleton1 INSTANCE = new MySingleton1();
  private MySingleton1() {}

  public static MySingleton1 getInstance() {
    return INSTANCE;
  }
 }

final class MySingleton2 {
  //Lazy Loading Singleton, Thread safe
  private static volatile MySingleton2 INSTANCE = new MySingleton2(); //volatile ensures visibility of changes across threads
  private final static Object lockObj = new Object();// locks should be final objects so the object instance we are synchronizing on,
  // never changes

  private MySingleton2() {}

  public static MySingleton2 getInstance() {
    if (INSTANCE == null) {
      synchronized (lockObj) {
        if (INSTANCE == null) { //double-check locking
          INSTANCE = new MySingleton2();
        }
      }
    }
    return INSTANCE;
  }
}

