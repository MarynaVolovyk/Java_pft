package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.NewContact;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(NewContact newContact, boolean creation) {
    type(By.name("firstname"), newContact.getName());
    type(By.name("lastname"), newContact.getPassword());
    type(By.name("address"), newContact.getAddress1());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContact.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

    public void deleteContactSelected () {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void confirmContactDeletion () {
      wd.switchTo().alert().accept();
    }

  public void createContact(NewContact contact, boolean b) {
    fillContactForm(contact, b);
    submitNewContact();
    returnToContactPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
  public List<NewContact> getContactList() {
    List<NewContact> groups = new ArrayList<NewContact>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      NewContact contact = new NewContact(id, name, null, null);
      groups.add(contact);
    }
    return groups;
  }
}
