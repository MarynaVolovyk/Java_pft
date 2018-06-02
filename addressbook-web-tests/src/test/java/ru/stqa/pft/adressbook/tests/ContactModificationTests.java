package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.NewContact;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void testGroupModification() {
    app.goTo().home();
    if (!app.contact().isThereAContact()) {
      app.goTo().gotoAddNew();
     app.contact().createContact(new NewContact().withName("katia").withLastname("annete").withAddress1("Rydluwka 5, Krakow").withGroup( "test1"), true);
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.contact().all();
    NewContact modifiedContact = before.iterator().next();
    app.contact().initContactModification(before.size() -1);
    NewContact contact = new NewContact().withId(modifiedContact.getId()).withName("Met").withLastname("Red").withAddress1("dom");
    app.contact().modify(contact);
    app.goTo().home();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}

