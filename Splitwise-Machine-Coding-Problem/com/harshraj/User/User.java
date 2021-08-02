package com.harshraj.User;

public class User {
  private String name;
  private String id;
  private String email;
  private Long number;

  public User(String id, String name, String email, Long number) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.number = number;
  }

  public String getEmail() {
    return email;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Long getNumber() {
    return number;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNumber(Long number) {
    this.number = number;
  }
}
