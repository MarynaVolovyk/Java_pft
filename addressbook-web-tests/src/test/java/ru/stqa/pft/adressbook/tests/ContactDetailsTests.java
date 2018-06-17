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

    String contactInfoFromDetailsForm = fixDetailsInfo(app.contact().infoFromDetailsForm(contact));
    String mergedInfoFromEditForm = mergeDetailsInfo(contactInfoFromEditForm);

    assertThat(contactInfoFromDetailsForm, equalTo(mergedInfoFromEditForm));
  }

  private String fixDetailsInfo(String contactInfo) {
    return contactInfo.replaceAll("\\s", "").replaceAll("[-() ]","").replace("H:","")
            .replace("M:","").replace("W:","").split("Memberof:")[0];
  }

  private String mergeDetailsInfo(NewContact contact){
    return Arrays.asList(contact.getName(), contact.getLastname(), contact.getAddress(),
            contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getAllEmails())
            .stream().filter((s) -> s!=null && !s.equals(""))
            .map(ContactDetailsTests::cleaned).collect(Collectors.joining(""));
  }

  public static String cleaned(String allEmails) {
    return allEmails.replaceAll("\\s", "").replaceAll("[-() ]","");
  }
}

