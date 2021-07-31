package com.harshraj.cache.DoublyLinkedList;

public class Node {
  private Integer value;
  private Integer key;
  Node next;
  Node prev;

  public Node(Integer key, Integer value) {
    this.value = value;
    this.key = key;
    next = null;
    prev = null;
  }

  public Integer getValue() {
    return value;
  }

  public Node getNext() {
    return next;
  }

  public Node getPrev() {
    return prev;
  }

  public Integer getKey() {
    return key;
  }
}
