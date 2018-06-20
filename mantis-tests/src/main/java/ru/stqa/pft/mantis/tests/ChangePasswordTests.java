package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePasword() {
    String user = "administrator";
    String password = "root";
    app.registration().loginAsAdministrator(user, password);
    app.goTo().manageUsers();
    Users before = app.db().users();
    UserData selectedUser = before.iterator().next();
//    UserData user = new UserData().withId(selectedUser.getId());
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }


}
