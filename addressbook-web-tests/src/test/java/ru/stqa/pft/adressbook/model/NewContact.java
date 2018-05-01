package ru.stqa.pft.adressbook.model;

public class NewContact {
  private final String name;
  private final String password;
  private final String address1;

  public NewContact(String name, String password, String address1) {
    this.name = name;
    this.password = password;
    this.address1 = address1;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getAddress1() {
    return address1;
  }
}
