package ru.stqa.pft.adressbook.model;

import java.util.Objects;

public class NewContact {
  private int id;
  private final String name;
  private final String password;
  private final String address1;
  private String group;

  public NewContact(String name, String password, String address1, String group) {
    this.name = name;
    this.password = password;
    this.address1 = address1;
    this.group = group;
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

  public String getGroup() {
    return group;
  }

  public NewContact(String name, String password, String address1) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.password = password;
    this.address1 = address1;
  }
  public NewContact(int id, String name, String password, String address1) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.address1 = address1;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewContact newContact = (NewContact) o;
    return Objects.equals(name, newContact.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }
  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

}
