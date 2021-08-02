package com.harshraj.ExpenseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseManager {

  private static final String PERCENT = "PERCENT";
  private static final String EXACT = "EXACT";
  private static final String EQUAL = "EQUAL";
  private final HashMap<String, HashMap<String, Double>> balances;

  public ExpenseManager(List<String> users) {
    balances = new HashMap<>();
    for(String user: users) {
      balances.put(user, new HashMap<>());
    }
  }

  public void addExpense(
    String paidByUserId,
    Double amount,
    List<String> userIds,
    String splitType,
    List<Double> splits
    ) {
      if(splitType.equals(EQUAL)) {
        createEqualExpense(paidByUserId, amount, userIds);
      } else if(splitType.equals(EXACT)) {
        createExactExpense(paidByUserId, amount, userIds, splits);
      } else if(splitType.equals(PERCENT)) {
        createPercentExpense(paidByUserId, amount, userIds, splits);
      } else {
        System.out.println("Split not valid!!");
      }
  }

  public List<String> getBalancesById(String id) {
    List<String> messages = new ArrayList<>();
    if (!balances.containsKey(id)) {
      return null;
    }
    for(String user: balances.get(id).keySet()) {
      String message = "";
      if(balances.get(id).get(user) < 0) {
        message += user + " owes " + id + ": " + Math.abs(balances.get(id).get(user));  
      } else {
        message += id + " owes " + user + ": " + Math.abs(balances.get(id).get(user));
      }
      messages.add(message);
    }
    return messages;
  }

  public List<String> getAllBalances() {
    List<String> messages = new ArrayList<>();
    for(String user1: balances.keySet()) {
      for(String user2: balances.get(user1).keySet()) {
        String message = "";
        if(balances.get(user1).get(user2) < 0) {
          message += user2 + " owes " + user1 + ": " + Math.abs(balances.get(user1).get(user2));  
        } else {
          message += user1 + " owes " + user2 + ": " + Math.abs(balances.get(user1).get(user2));
        }
        messages.add(message);
      }
    }
    return messages;
  }

  private void createEqualExpense(String paidByUserId, Double amount, List<String> userIds) {
    Integer noOfusers = userIds.size();
    if (noOfusers > 0) {
      Double equalAmount = amount / noOfusers;
      if (balances.containsKey(paidByUserId)) {
        var userBalances = balances.get(paidByUserId);
        for(String user: userIds) {
          if (user == paidByUserId)
            continue;
          if (userBalances.containsKey(user)) {
            Double balance = userBalances.get(user);
            userBalances.put(user, balance-equalAmount);
          } else {
            userBalances.put(user, -equalAmount);
          }
        }
      }
    }
  }

  private void createPercentExpense(String paidByUserId, Double amount, List<String> userIds, List<Double> splits) {
    if (!isSplitValid(splits, 100.0)) {
      System.out.println("Split not valid!!");
      return ;
    }
    Integer noOfusers = userIds.size();
    if (noOfusers > 0) {
      if (balances.containsKey(paidByUserId)) {
        var userBalances = balances.get(paidByUserId);
        int index = 0;
        for(String user: userIds) {
          Double value = (splits.get(index) / 100.0) * amount;
          if (userBalances.containsKey(user)) {
            Double balance = userBalances.get(user);
            userBalances.put(user, balance-value);
          } else {
            userBalances.put(user, -value);
          }
          index++;
        }
      }
    }
  }

  private void createExactExpense(String paidByUserId, Double amount, List<String> userIds, List<Double> splits) {
    if (!isSplitValid(splits, amount)) {
      System.out.println("Split not valid!!");
      return ;
    }
    Integer noOfusers = userIds.size();
    if (noOfusers > 0) {
      if (balances.containsKey(paidByUserId)) {
        var userBalances = balances.get(paidByUserId);
        int index = 0;
        for(String user: userIds) {
          if (userBalances.containsKey(user)) {
            Double balance = userBalances.get(user);
            userBalances.put(user, balance-splits.get(index));
          } else {
            userBalances.put(user, -splits.get(index));
          }
          index++;
        }
      }
    }
  }

  private Boolean isSplitValid(List<Double> splits, Double totalAmount) {
    Double currAmount = 0.0;
    for(Double split: splits)
      currAmount += split;
    return currAmount == totalAmount;
  }
}
