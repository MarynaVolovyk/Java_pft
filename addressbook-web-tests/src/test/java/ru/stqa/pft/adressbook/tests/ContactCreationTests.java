package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;
import java.util.List;
import java.util.Comparator;


public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() {
    app.goTo().gotoHome();
    List<NewContact> before = app.getContactHelper().getContactList();
    NewContact contact = new NewContact("mila", "Ri", "Rydluwka 5, Krakow", "test1");
    app.goTo().gotoAddNew();
    app.getContactHelper().createContact(contact, true);
    List<NewContact> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super NewContact> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
