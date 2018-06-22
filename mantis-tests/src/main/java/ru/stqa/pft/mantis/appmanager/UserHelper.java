package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }


  public void selectUserByUsername(String username) {
    click(By.linkText(username));
  }

  public void initResetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finish(String resetPasswordLink, String newpassword) {
    wd.get(resetPasswordLink);
    type(By.name("password"), newpassword);
    type(By.name("password_confirm"), newpassword);
    click(By.cssSelector("input[value='Update User']"));
  }
}
