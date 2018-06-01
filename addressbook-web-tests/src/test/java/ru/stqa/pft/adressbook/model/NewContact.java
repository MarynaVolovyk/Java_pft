package ru.stqa.pft.adressbook.model;

import java.util.Objects;

public class NewContact {
  private int id;
  private String name;
  private String lastname;
  private String address1;
  private String group;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewContact that = (NewContact) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address1, that.address1) &&
            Objects.equals(group, that.group);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, lastname, address1, group);
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

  public NewContact withId(int id) { this.id = id; return this; }
  public NewContact withName(String name) { this.name = name; return this;}
  public NewContact withLastname(String lastname) { this.lastname = lastname; return this; }
  public NewContact withAddress1(String address1) { this.address1 = address1; return this;}
  public NewContact withGroup(String group) { this.group = group; return this;}
  public int getId() {
    return id;
  }

}
