package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.goTo().home();
    if (!app.contact().isThereAContact()) {
      app.goTo().gotoAddNew();
      app.contact().createContact(new NewContact("katia", "annete", "Rydluwka 5, Krakow", "test1"), true);
    }

    List<NewContact> before = app.contact().list();
    app.contact().selectContact(before.size() - 1);
    app.contact().deleteContactSelected();
    app.contact().confirmContactDeletion();
    app.goTo().home();
    List<NewContact> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }
}
