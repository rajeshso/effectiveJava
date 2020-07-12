package com.n2.excercises;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;





public class Result1 {
  public static int minimumMoves1(List<Integer> arr1, List<Integer> arr2) {
    // Write your code here
    return 0;
  }
  private static int minimumMoves(List<Integer> arr1, List<Integer> arr2) {
    int[] a = arr1.stream().mapToInt(Integer::intValue).toArray();
    int[] b = arr2.stream().mapToInt(Integer::intValue).toArray();
    int arr1Length = a.length;
    int moveCounter = 0;
    for (int i = 0; i < arr1Length; i++) {
      String arr1s = String.valueOf(a[i]);
      String arr2s = String.valueOf(b[i]);
      for (int c = 0; c < arr1s.length(); c++) {
        int arr1Value = Integer.parseInt(Character.toString(arr1s.charAt(c)));
        int arr2Value = Integer.parseInt(Character.toString(arr2s.charAt(c)));
        if (arr1Value > arr2Value) {
          moveCounter += arr1Value - arr2Value;
        } else {
          moveCounter += arr2Value - arr1Value;
        }
      }
    }
    return moveCounter;
  }
/*  String[] getMovieTitles(String substr) {
    String response;
    int firstPage = 1;
    int total = Integer.MAX_VALUE;
    List<String> movieTitles = new ArrayList<>();
    while (firstPage <= total) {
      try {
        URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + firstPage);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        while ((response = bufferedReader.readLine()) != null) {
          JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
          total = jsonObject.get("total_pages").getAsInt();
          JsonArray data = jsonObject.getAsJsonArray("data");
          for (int j = 0; j < data.size(); j++) {
            String title = data.get(j).getAsJsonObject().get("Title").getAsString();
            movieTitles.add(title);
          }
        }
        bufferedReader.close();
        firstPage++;
      } catch (Exception ex) {
        ex.printStackTrace();
      }

    }
    Collections.sort(movieTitles);
    return movieTitles.toArray(new String[0]);
  }*/

  public static void main(String[] args) {
    //System.out.println(minimumMoves(new int[]{123,543}, new int[]{321,279}));
    System.out.println(minimumMoves(List.of(123,543), List.of(321,279)));
    System.out.println(minimumMoves(List.of(1234,4321), List.of(2345,3214)));
/*    final String[] spidermen = getMovieTitles("spiderman");
    for (String s: spidermen
    ) {
      System.out.println(s);
    }*/


  }
}
