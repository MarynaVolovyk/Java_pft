package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;
import ru.stqa.pft.adressbook.model.Contacts;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() {
    app.goTo().home();
    Contacts before = app.contact().all();
    NewContact contact = new NewContact().withName("mila").withLastname("Ri").withAddress1("Rydlowka 5, Krakow").withGroup("test1");
    app.goTo().gotoAddNew();
    app.contact().createContact(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
