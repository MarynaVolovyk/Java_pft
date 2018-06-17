package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;
import ru.stqa.pft.adressbook.model.NewContact;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToTheGroupTests extends TestBase {

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
  public void testAddingContactToTheGroup() {
    NewContact contact = getContactWithLessGroups();
    GroupData group = getMissingGroup(contact);
    Groups before = new Groups(contact.getGroups());
    app.goTo().home();
    app.contact().addGroup(contact, group);

    Contacts contactsAfter = app.db().contacts();
    NewContact contactAfter = contactsAfter.getContactWithID(contact.getId());
    Groups after = new Groups(contactAfter.getGroups());
    before.add(group);
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }

  private GroupData getMissingGroup(NewContact contact) {

    for (GroupData group : app.db().groups()) {
      if (!contact.getGroups().contains(group)) {
        return group;
      }
    }
        app.goTo().groupPage();
        app.group().createGroup(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));

    for (GroupData group : app.db().groups()) {
      if (!contact.getGroups().contains(group)) {
        return group;
      }
    }
    return null;
  }

  public NewContact getContactWithLessGroups() {
    int groupSize = app.db().groups().size();
    for (NewContact contact : app.db().contacts()) {
      int contactSize = contact.getGroups().size();
      if (contactSize < groupSize) {
        return contact;
      } else if (contactSize == groupSize) {
        app.goTo().groupPage();
        app.group().createGroup(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        return contact;
      }
      return null;
    }
    return null;
  }
}

