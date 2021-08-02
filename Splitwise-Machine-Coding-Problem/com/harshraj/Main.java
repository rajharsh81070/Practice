package com.harshraj;

import java.util.ArrayList;
import java.util.List;

import com.harshraj.ExpenseManager.ExpenseManager;

public class Main {

  private static final String EQUAL = "EQUAL";
  private static final String INVALID_INPUT = "Invalid Input!!!";
  private static final String SHOW = "SHOW";
  private static final String EXPENSE = "EXPENSE";

  public static void main(String[] args) {
    List<String> users = new ArrayList<>();
    for(int i=1; i<=4; i++) 
      users.add("u"+i);
    ExpenseManager expenseManager = new ExpenseManager(users);
    while(true) {
      String inputLine = System.console().readLine();
      String inputs[] = inputLine.split(" ");
      String inputType = inputs[0];
      if (inputType.equals(EXPENSE)) {
        String paidByUserId = inputs[1];
        Double amount = Double.parseDouble(inputs[2]);
        Integer noOfUser = Integer.parseInt(inputs[3]);
        List<String> userIds = new ArrayList<String>(noOfUser);
        int i = 4;
        for(i=4; i<noOfUser+4; i++) {
          userIds.add(inputs[i]);
        }
        String splitType = inputs[i++];
        List<Double> splits = new ArrayList<Double>(noOfUser);
        if(splitType!=EQUAL) {
          for(; i<inputs.length; i++) {
            splits.add(Double.parseDouble(inputs[i]));
          }
        }
        expenseManager.addExpense(paidByUserId, amount, userIds, splitType, splits);
      } else if (inputType.equals(SHOW)) {
        if (inputs.length==1) {
          var messages = expenseManager.getAllBalances();
          if (messages.size() == 0) {
            System.out.println("No balances");
            continue;
          }
          for(String message: messages) {
            System.out.println(message);
          }
        } else {
          String userId = inputs[1];
          var messages = expenseManager.getBalancesById(userId);
          if (messages.size() == 0) {
            System.out.println("No balances");
            continue;
          }
          for(String message: messages) {
            System.out.println(message);
          }
        }
      } else {
        System.out.println(INVALID_INPUT);
      }
    }
  }
}
