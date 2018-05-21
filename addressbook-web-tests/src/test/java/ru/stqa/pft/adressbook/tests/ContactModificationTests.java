package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void testGroupModification() {
    app.goTo().home();
    if (!app.contact().isThereAContact()) {
      app.goTo().gotoAddNew();
      app.contact().createContact(new NewContact("katia", "annete", "Rydluwka 5, Krakow", "test1"), true);
    }
  }

  @Test
  public void testContactModification() {

    List<NewContact> before = app.contact().list();
    app.contact().initContactModification(before.size() -1);
    NewContact contact = new NewContact(before.get(before.size() - 1).getId(),"Met", "Red", null);
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.goTo().home();
    List<NewContact> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(contact);
    Comparator<? super NewContact> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
  }

