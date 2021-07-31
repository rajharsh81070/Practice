package com.harshraj.cache.Storage;

import java.util.HashMap;

import com.harshraj.cache.DoublyLinkedList.Node;

public class Storage {
  private HashMap<Integer, Node> mapper;

  public Storage() {
    mapper = new HashMap<>();
  }

  public Node get(Integer key) {
    return mapper.get(key);
  }

  public void put(Integer key, Node value) {
    mapper.put(key, value);
  }

  public Boolean contains(Integer key) {
    return mapper.containsKey(key);
  }

  public Integer getSize() {
    return mapper.size();
  }

  public void remove(Integer key) {
    mapper.remove(key);
  }
}
