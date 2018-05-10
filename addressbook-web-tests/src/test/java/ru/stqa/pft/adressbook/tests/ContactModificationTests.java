package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNew();
      app.getContactHelper().createContact(new NewContact("katia", "annete", "Rydluwka 5, Krakow", "test1"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new NewContact("anna", null, null, null), false);
    app.getContactHelper().submitContactModification();

  }

}
