package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="mantis_user_table")

public class UserData {
  @Id
  @Column(name="id")
  @XStreamOmitField
  private int id;

  @Expose
  @Column(name="username")
  @Type(type = "text")
  private String username;

  @Expose
  @Column(name="realname")
  @Type(type = "text")
  private String realname;

  @Expose
  @Column(name="email")
  @Type(type = "text")
  private String email;

  private Set<UserData> users = new HashSet<UserData>();

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getRealname() {
    return realname;
  }

  public UserData withRealname(String realname) {
    this.realname = realname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", realname='" + realname + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(username, userData.username) &&
            Objects.equals(realname, userData.realname) &&
            Objects.equals(email, userData.email);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, username, realname, email);
  }
}
