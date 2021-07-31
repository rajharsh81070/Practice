package com.harshraj;

// import java.util.Scanner;

import com.harshraj.cache.Cache;

public class Main {

  private static final String GET = "get";
  private static final String PUT = "put";
  private static final String YES_STRING = "y";
  private static final String DO_YOU_WISH_TO_CONTINUE_Y_N = "Do you wish to continue: Y/N ? ";

  public static void main(String[] args) {
    System.out.print("Please provide the size of cache: ");
    // Scanner scanner = new Scanner(System.in);
    Integer capaity = Integer.parseInt(System.console().readLine());
    Cache cache = new Cache(capaity);
    System.out.print(DO_YOU_WISH_TO_CONTINUE_Y_N);
    String contine = System.console().readLine();
    while(contine.toLowerCase().equals(YES_STRING)) {
      String input[] = System.console().readLine().split(" ");
      if(input[0].toLowerCase().equals(PUT)) {
        Integer key = Integer.parseInt(input[1]);
        Integer value = Integer.parseInt(input[2]);
        cache.put(key, value);
      } else if(input[0].toLowerCase().equals(GET)) {
        Integer key = Integer.parseInt(input[1]);
        System.out.println("Value = " + cache.get(key));
      } else {
        System.out.println("Invalid Input");
      }
      System.out.print(DO_YOU_WISH_TO_CONTINUE_Y_N);
      contine = System.console().readLine();
    }
    // scanner.close();
  }
}