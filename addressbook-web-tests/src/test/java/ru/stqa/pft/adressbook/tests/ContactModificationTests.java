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
    if (app.db().contacts().size() == 0) {
      app.goTo().gotoAddNew();
      app.contact().create(new NewContact().withName(app.properties.getProperty("contactName")).withAddress(app.properties.getProperty("contactAddress"))
              .withLastname(app.properties.getProperty("contactLastname")).withGroup(app.properties.getProperty("groupName")));
    }
   }

  @Test
  public void testContactModification() {

    Contacts before = app.db().contacts();
    NewContact modifiedContact = before.iterator().next();
    NewContact contact = new NewContact().withId(modifiedContact.getId()).withName(app.properties.getProperty("modifiedContactName"))
            .withLastname(app.properties.getProperty("modifiedContactLastname")).withAddress(app.properties.getProperty("modifiedContactAddress"));
    app.goTo().home();
    app.contact().modify(contact);
    app.goTo().home();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}

