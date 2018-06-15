package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.NewContact;

public class AddingContactToTheGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if (app.db().contacts().size() == 0) {
      app.goTo().gotoAddNew();
      app.contact().create(new NewContact().withName(app.properties.getProperty("contactName")).withAddress(app.properties.getProperty("contactAddress"))
              .withLastname(app.properties.getProperty("contactLastname")));
    }
  }

  @Test
  public void testAddingContactToTheGroup() {
    NewContact contact = getContactWithLessGroups();
    GroupData group = getMissingGroup(contact);
    app.contact().addGroup(contact, group);
  }

  private GroupData getMissingGroup(NewContact contact) {
    for (GroupData group : app.db().groups()) {
      if (!contact.getGroups().contains(group)) return group;
    }
    return null;
  }

  public NewContact getContactWithLessGroups() {
    int gs = app.db().groups().size();
    int id = 0;
    for (NewContact contact : app.db().contacts()) {
      int cs = contact.getGroups().size();
      if (cs < gs) {
        return contact;
      }
    }
    return null;
  }
}

