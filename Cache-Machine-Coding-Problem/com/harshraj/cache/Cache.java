package com.harshraj.cache;

import com.harshraj.cache.DoublyLinkedList.DoublyLinkedList;
import com.harshraj.cache.DoublyLinkedList.Node;
import com.harshraj.cache.Storage.Storage;

public class Cache {
  private Storage storage;
  private DoublyLinkedList DLL;
  private Integer capacity;

  public Cache(Integer capaity) {
    this.capacity = capaity;
    storage = new Storage();
    DLL = new DoublyLinkedList();
  }

  public Integer get(Integer key) {
    if(storage.contains(key)==false) {
      return null;
    }
    Node node = storage.get(key);
    DLL.removeNode(node);
    storage.put(key, DLL.addElementAtLast(key, node.getValue()));
    return node.getValue();
  }

  public void put(Integer key, Integer value) {
    if(storage.contains(key)==true) {
      DLL.removeNode(storage.get(key));
      storage.remove(key);
    }
    if(capacity==storage.getSize()) {
      Node node = DLL.getFirstNode();
      System.out.println("Memory Full, Evicting least recently used key. " + node.getKey());
      DLL.removeNode(node);
      storage.remove(node.getKey());
    }
    DLL.addElementAtLast(key, value);
    storage.put(key, DLL.getLastNode());
  }
}
