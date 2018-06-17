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
      Groups groups = app.db().groups();
      app.goTo().gotoAddNew();
      app.contact().create(new NewContact().withName(app.properties.getProperty("contactName")).withAddress(app.properties.getProperty("contactAddress"))
              .withLastname(app.properties.getProperty("contactLastname")).inGroup(groups.iterator().next()));
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
//    Groups groups = contact.getGroups();
    GroupData selectedGroup = contact.getGroups().iterator().next();

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
        Groups groups = app.db().groups();
        app.goTo().gotoAddNew();
        NewContact contacWithGroup = new NewContact().withName(app.properties.getProperty("contactName")).withAddress(app.properties.getProperty("contactAddress"))
                .withLastname(app.properties.getProperty("contactLastname")).inGroup(groups.iterator().next());
        app.contact().create(contacWithGroup);
        Contacts newContacts = app.db().contacts();
        contacWithGroup.withId(newContacts.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        return contacWithGroup;
      }
      return null;
    }
    return null;
  }
}
