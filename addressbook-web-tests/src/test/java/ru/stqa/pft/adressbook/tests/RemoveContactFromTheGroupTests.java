package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;
import ru.stqa.pft.adressbook.model.NewContact;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RemoveContactFromTheGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if (app.db().contacts().size() == 0) {
      app.goTo().gotoAddNew();
      app.contact().create(new NewContact().withName(app.properties.getProperty("contactName")).withAddress(app.properties.getProperty("contactAddress"))
              .withLastname(app.properties.getProperty("contactLastname")));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().createGroup(new GroupData().withName(app.properties.getProperty("groupName")));
    }
  }

  @Test
  public void testRemoveContactFromTheGroup() {
    NewContact contact = getContactWithGroup();
    Groups before = new Groups(contact.getGroups());
    Groups groups = contact.getGroups();
    GroupData selectedGroup = groups.iterator().next();

    app.goTo().home();
    app.contact().removeGroupFromContact(contact, selectedGroup);

    Contacts contactsAfter = app.db().contacts();
    NewContact contactAfter = contactsAfter.getContactWithID(contact.getId());
    Groups after = new Groups(contactAfter.getGroups());
    before.remove(selectedGroup);
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }

  public NewContact getContactWithGroup() {
    for (NewContact contact : app.db().contacts()) {
      int contactSize = contact.getGroups().size();
      if (contactSize > 0) {
        return contact;
      } else if (contactSize == 0) {
        app.goTo().home();
        app.contact().create(new NewContact().withName(app.properties.getProperty("contactName")).withAddress(app.properties.getProperty("contactAddress"))
                .withLastname(app.properties.getProperty("contactLastname")));
      }
      return contact;
    }
    return null;
  }
}
