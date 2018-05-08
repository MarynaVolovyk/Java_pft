package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new NewContact("anna", "egorova", "Lermontowska 20/9\\nPoland\\n30-300", null), false);
    app.getContactHelper().submitContactModification();

  }

}
