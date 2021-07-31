package com.harshraj.cache.DoublyLinkedList;

public class DoublyLinkedList {
  private Node head;
  private Node tail;

  public DoublyLinkedList() {
    head = new Node(-1, -1);
    tail = new Node(-1, -1);
    head.next = tail;
    tail.prev = head;
  }

  public void addNodeAtLast(Node node) {
    Node prevNodeOfTail = tail.prev;
    tail.prev = node;
    node.next = tail;
    node.prev = prevNodeOfTail;
    prevNodeOfTail.next = node;
  }

  public Node addElementAtLast(Integer key, Integer element) {
    Node newNode = new Node(key, element);
    addNodeAtLast(newNode);
    return newNode;
  }

  public void removeNode(Node node) {
    if(node!=null){
      Node prevNode = node.prev;
      Node nextNode = node.next;
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
    }
  }

  private Boolean isItemPresent() {
    return head.next != tail;
  }

  public Node getFirstNode() {
    if(!isItemPresent()) {
      return null;
    }
    return head.next;
  }

  public Node getLastNode() {
    if(!isItemPresent()) {
      return null;
    }
    return tail.prev;
  }

  public Node getHead() {
    return head;
  }

  public Node getTail() {
    return tail;
  }
}
