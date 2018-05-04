package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.adressbook.model.NewContact;


public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(NewContact groupData) {
    type(By.name("firstname"), groupData.getName());
    type(By.name("lastname"), groupData.getPassword());
    type(By.name("address"), groupData.getAddress1());
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }


}