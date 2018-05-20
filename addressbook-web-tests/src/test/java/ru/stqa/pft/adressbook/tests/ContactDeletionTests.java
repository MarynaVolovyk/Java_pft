package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper().gotoHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNew();
      app.getContactHelper().createContact(new NewContact("katia", "annete", "Rydluwka 5, Krakow", "test1"), true);
    }

    List<NewContact> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContactSelected();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().gotoHome();
    List<NewContact> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }
}
