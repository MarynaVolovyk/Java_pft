package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactPhonesEmailsTests extends TestBase{

  @Test

  public void testContactPhones() {
    app.goTo().home();
    NewContact contact = app.contact().all().iterator().next();
    NewContact contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  private String mergePhones(NewContact contact){
           return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
          .stream().filter((s) -> ! s.equals(""))
          .map(ContactPhonesEmailsTests::cleaned).collect(Collectors.joining("\n"));
  }
  private String mergeEmails(NewContact contact){
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhonesEmailsTests::cleaned).collect(Collectors.joining("\n"));
  }
  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-() ]","");
  }
}
