package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() {

    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().fillContactForm(new NewContact("kira5", "kilanina", "Lermontowska 20/9\\nPoland\\n30-300", "test1"), true);
    app.getContactHelper().submitNewContact();
    app.getContactHelper().returnToContactPage();
  }

}
