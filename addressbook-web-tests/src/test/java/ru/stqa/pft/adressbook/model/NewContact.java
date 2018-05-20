package ru.stqa.pft.adressbook.model;

import java.util.Objects;

public class NewContact {
  private int id;
  private final String name;
  private final String lastname;
  private final String address1;
  private String group;

  public NewContact(String name, String lastname, String address1, String group) {
    this.name = name;
    this.lastname = lastname;
    this.address1 = address1;
    this.group = group;
  }

  public String getName() {
    return name;
  }
  public String getLastname() {
    return lastname;
  }
  public String getAddress1() {
    return address1;
  }
  public String getGroup() {
    return group;
  }

  public NewContact(int id, String name, String lastname, String address1) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.address1 = address1;
  }
  public NewContact( String name, String lastname, String address1) {

    this.name = name;
    this.lastname = lastname;
    this.address1 = address1;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "NewContact{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address1='" + address1 + '\'' +
            ", group='" + group + '\'' +
            '}';
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
}
