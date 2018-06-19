package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void start(String username, String email) {
    String url = app.getProperty("web.baseURL") + "/signup_page.php";
    wd.get(url);
  }
}
