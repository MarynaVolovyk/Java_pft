package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.NewContact;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().home();
    NewContact contact = app.contact().all().iterator().next();
    NewContact contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    NewContact contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

    String mergedInfoFromDetailsForm = mergeDetailsInfo(contactInfoFromDetailsForm);
    String mergedInfoFromEditForm = mergeDetailsInfo(contactInfoFromEditForm);

    assertThat(mergedInfoFromDetailsForm, equalTo(mergedInfoFromEditForm));
  }

  private String mergeDetailsInfo(NewContact contact){
    return Arrays.asList(contact.getName(), contact.getLastname(), contact.getAddress(),
            contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getAllEmails())
            .stream()
            .filter((s) -> s!=null && !s.equals("") )
            .map(ContactDetailsTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String allEmails) {
    return allEmails.replaceAll("\\s", "").replaceAll("[-() ]","");
  }
}

