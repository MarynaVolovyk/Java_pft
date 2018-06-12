package ru.stqa.pft.adressbook.model;

import java.io.File;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class NewContact {
  private int id;
  private String name;
  private String lastname;
  private String address1;
  private String group;
  private String homePhone;
  private String workPhone;
  private String mobilePhone;
  private String allPhones;
  //private String allEmails;
  private String email;
  private String email2;
  private String email3;
  private File photo;

    public NewContact() {
    name = "";
    lastname = "";
    address1 = "";
    group = "";
    homePhone = "";
    workPhone = "";
    mobilePhone = "";
    allPhones = "";
    email = "";
    email2 = "";
    email3 = "";
  }

  public String getName() {
    return name;
  }
  public String getLastname() {
    return lastname;
  }
  public String getAddress1() { return address1;  }
  public String getGroup() {
    return group;
  }
  public String getAllPhones() { return allPhones; }
  public String getHomePhone() { return homePhone;  }
  public String getWorkPhone() { return workPhone;  }
  public String getMobilePhone() { return mobilePhone;  }
  public File getPhoto() { return photo;  }

  public String getAllEmails() {
    StringBuilder emails = new StringBuilder();
    if (!isEmpty(email)) emails.append(email);
    if (!isEmpty(email2)) emails.append(email2);
    if (!isEmpty(email3)) emails.append(email3);
    return emails.toString();
  }

  public String getEmail() {  return email; }
  public String getEmail2() {  return email2;  }
  public String getEmail3() {   return email3;  }

  public NewContact withId(int id) {
    this.id = id;
    return this;
  }

  public NewContact withName(String name) {
    this.name = name;
    return this;
  }

  public NewContact withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public NewContact withAddress1(String address1) {
    this.address1 = address1;
    return this;
  }

  public NewContact withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public NewContact withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public NewContact withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public NewContact withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public NewContact withGroup(String group) {
    this.group = group;
    return this;
  }

  //public NewContact withAllEmails(String allEmails) { this.allEmails = allEmails; return this;}
  public NewContact withEmail1(String email1) {
    this.email = email1;
    return this;
  }

  public NewContact withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public NewContact withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public NewContact withEmail(String email) {
    if (isEmpty(this.email)) {
      this.email = email;
    } else if (isEmpty(this.email2)) {
      this.email2 = email;
    } else if (isEmpty(this.email3)) {
      this.email3 = email;
    }
    return this;
  }

  public int getId() {
    return id;
  }
  public NewContact withPhoto(File photo)
  { this.photo = photo;
  return this; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewContact that = (NewContact) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address1, that.address1) &&
            Objects.equals(homePhone, that.homePhone) &&
            Objects.equals(workPhone, that.workPhone) &&
            Objects.equals(mobilePhone, that.mobilePhone) &&
            Objects.equals(email, that.email) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, address1, homePhone, workPhone, mobilePhone, allPhones, email, email2, email3);
  }

  @Override
  public String toString() {
    return "NewContact{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address1='" + address1 + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", allPhones='" + allPhones + '\'' +
           // ", allEmails='" + allEmails + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            '}';
  }

}
