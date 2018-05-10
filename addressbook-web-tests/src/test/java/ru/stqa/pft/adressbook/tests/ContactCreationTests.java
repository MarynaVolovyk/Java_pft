package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() {

    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().createContact(new NewContact("katia", "annete", "Rydluwka 5, Krakow", "test1"), true);
  }

}
