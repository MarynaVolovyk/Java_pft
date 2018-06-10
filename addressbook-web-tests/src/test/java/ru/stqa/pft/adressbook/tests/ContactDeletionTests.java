package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.NewContact;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if (!app.contact().isThereAContact()) {
      app.goTo().gotoAddNew();
      app.contact().create(new NewContact().withName("katia").withAddress1("Rydluwka 5, Krakow").withLastname("annete").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() {

    Contacts before = app.contact().all();
    NewContact deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().home();
    Contacts after = app.contact().all();
    assertEquals(after.size(), (before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
