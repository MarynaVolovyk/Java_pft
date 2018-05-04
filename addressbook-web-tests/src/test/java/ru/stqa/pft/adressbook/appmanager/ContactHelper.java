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


  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[8]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact() {
    if (!wd.findElement(By.name("selected[]")).isSelected()) {
      click(By.name("selected[]"));

    }
  }

    public void deleteContactSelected () {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));

    }

    public void confirmContactDeletion () {
      wd.switchTo().alert().accept();
    }
  }
