package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePasword() throws IOException, MessagingException {
    String adminUserName = "administrator";
    String adminPassword = "root";
    String newpassword = "newpassword";
    app.registration().loginAsAdministrator(adminUserName, adminPassword);
    app.goTo().manageUsers();
    Users before = app.db().users();
    UserData selectedUser = before.iterator().next();
    app.user().selectUserByUsername(selectedUser.getUsername());
    String email = selectedUser.getEmail();
    String user = selectedUser.getUsername();
    app.user().initResetPassword();
    app.mail().waitForMail(1, 1000);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetPasswordLink = findResetPasswordLink(mailMessages, email);
    app.user().finish(resetPasswordLink, newpassword);
    assertTrue(app.newSession().login(user, newpassword));
  }
  private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }


}
