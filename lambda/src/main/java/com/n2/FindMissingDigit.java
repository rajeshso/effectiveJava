package com.n2;


import java.util.Arrays;

public class FindMissingDigit {

  public static void main(String[] args) {
    int[] A = {1,4,8,2};
/*    System.out.println(new TestJava1().solution(A));
    A = new int[]{1, 3, 6, 4, 1, 2};
    System.out.println(new TestJava1().solution(A));
    A = new int[] {1, 2, 3};
    System.out.println(new TestJava1().solution(A));*/
    A = new int[]{-1,-3};
    System.out.println(new FindMissingDigit().solution1(A));
  }
  static int solution(int a[])
  {
    a = unique_array(a);
    int n = a.length;
    int i, total;
    total = (n + 1) * (n + 2) / 2;
    for (i = 0; i < n; i++)
      total -= a[i];
    return total;
  }
  public int solution1(int[] A) {
    // write your code in Java SE 8
    A = unique_array(A);
    A = ascendingOrder(A);
    int len;
    len = A.length;
    System.out.print("Ascending Order:");
    for (int i = 0; i < len ; i++)
    {
      System.out.print(A[i] + ",");
    }
    int missingDigit = findMissingDigit(A);
    return missingDigit;
  }

  private int[] ascendingOrder(int[] A) {
    int len = A.length;
    int temp;
    for (int i = 0; i < len; i++)
    {
      for (int j = i + 1; j < len; j++)
      {
        if (A[i] > A[j])
        {
          temp = A[i];
          A[i] = A[j];
          A[j] = temp;
        }
      }
    }
    return A;
  }

  public int findMissingDigit(int[] A) {
    int len = A.length;
    int missingNumber=0;
    System.out.println("\nAscending Order:");
    for (int i = 0; i < len ; i++)
    {
      System.out.print(A[i] + ",");
    }
    for (int i = 1; i < len ; i++) {
      if (Math.abs(A[i] - A[i-1]) != 1) {
        missingNumber = A[i - 1] + 1;
        break;
      }
    }
     if (missingNumber == 0) {
        missingNumber = A[len-1]+1;
      }
      System.out.println("\n"+missingNumber + " is the missingNumber");
    return missingNumber;
    }
  static int[] unique_array(int[] my_array)
  {
    System.out.println("Original Array : ");

    for (int i = 0; i < my_array.length; i++)
    {
      System.out.print(my_array[i]+"\t");
    }

    //Assuming all elements in input array are unique

    int no_unique_elements = my_array.length;

    //Comparing each element with all other elements

    for (int i = 0; i < no_unique_elements; i++)
    {
      for (int j = i+1; j < no_unique_elements; j++)
      {
        //If any two elements are found equal

        if(my_array[i] == my_array[j])
        {
          //Replace duplicate element with last unique element

          my_array[j] = my_array[no_unique_elements-1];

          no_unique_elements--;

          j--;
        }
      }
    }

    //Copying only unique elements of my_array into array1

    int[] array1 = Arrays.copyOf(my_array, no_unique_elements);

    //Printing arrayWithoutDuplicates

    System.out.println();

    System.out.println("Array with unique values : ");

    for (int i = 0; i < array1.length; i++)
    {
      System.out.print(array1[i]+"\t");
    }

    System.out.println();

    System.out.println("---------------------------");
    return array1;
  }
}
