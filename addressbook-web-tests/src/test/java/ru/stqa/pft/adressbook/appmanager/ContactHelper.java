package ru.stqa.pft.adressbook.appmanager;

//import com.sun.org.apache.xpath.internal.operations.String;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.NewContact;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class ContactHelper extends HelperBase {

  private Contacts contactCache = null;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContact() {
    click(By.name("submit"));
  }

  public void fillContactForm(NewContact newContact, boolean creation) {
    type(By.name("firstname"), newContact.getName());
    type(By.name("lastname"), newContact.getLastname());
    type(By.name("address"), newContact.getAddress());
    type(By.name("home"), newContact.getHomePhone());
    type(By.name("mobile"), newContact.getMobilePhone());
    type(By.name("email"), newContact.getEmail());
//    attach(By.name("photo"), newContact.getPhoto());

    if (creation) {
      Select groupSelector = new Select(wd.findElement(By.name("new_group")));
      groupSelector.selectByIndex(0);

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void initContactModification(int id) {
    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
  }

  public void create(NewContact contact) {
    fillContactForm(contact, true);
    submitNewContact();
    returnToContactPage();
  }

  public void modify(NewContact contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
  }

  public void delete(NewContact contact) {
    selectContactById(contact.getId());
    deleteContactSelected();
    confirmContactDeletion();
  }
  public void addGroup(NewContact contact, GroupData groupData) {
    selectContactById(contact.getId());
    Select groupSelector = new Select(wd.findElement(By.name("to_group")));
    groupSelector.selectByValue(Integer.toString(groupData.getId()));
    //click on Add To button
    click(By.name("add"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteContactSelected() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      contacts.add(new NewContact().withId(id).withName(name).withLastname(lastname).withAddress(address).withAllPhones(allPhones).withEmail(allEmails));
    }
    return contacts;
  }

  public NewContact infoFromEditForm(NewContact contact) {
    initContactModificationById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address1 = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new NewContact().withId(contact.getId()).withName(name).withLastname(lastname)
            .withHomePhone(homePhone).withWorkPhone(workPhone).withMobilePhone(mobilePhone).withEmail1(email).withEmail2(email2)
            .withEmail3(email3).withAddress(address1);
  }

  public String infoFromDetailsForm(NewContact contact) {
    initContactDetailsViewById(contact.getId());
    String detailsInfo = wd.findElement(By.id("content")).getText();
    return detailsInfo;
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  private void initContactDetailsViewById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }
}
