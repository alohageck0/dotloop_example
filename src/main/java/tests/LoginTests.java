package tests;


import data.MySQLDataProviders;
import objectsRepo.HomePage;
import objectsRepo.MyLoopsPage;
import objectsRepo.SignInPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import serviceClasses.TestTemplate;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * LoginTests class used to store automated test cases.
 * <p>
 * It is used for test cases related with login functionality.
 *
 * @author Evgenii Iavorovich evgenii.iavorovich@qolsys.com
 */
public class LoginTests extends TestTemplate {
   public LoginTests(RemoteWebDriver webDriver) {
      super(webDriver);
   }

   public LoginTests() {
   }

   //   @AfterMethod(alwaysRun = true)
   @Override
   public void tearDown(Method test) throws IOException {
      logger.info("overriden teardown method");
   }

   /**
    * Login test case
    */
//   @Test(groups = {"login", "smoke"}, dataProvider = "usernames", dataProviderClass = MySQLDataProviders.class, dependsOnMethods = {"clickSignIn"})
   @Test(groups = {"login", "smoke"}, dependsOnMethods = {"clickSignIn"})
   public void login() {
//   public void login(String username, String password) {
      String username = "iavorovich@gmail.com";
      String password = "qolsys123";
      logger.info("login test case started");
      clickSignIn();

      SignInPage signInPage = new SignInPage(getWebDriver());
      signInPage.getUsernameTextfield().sendKeys(username);
      logger.info("Username " + username + " entered");

      signInPage.getPasswordTextfield().sendKeys(password);
      logger.info("Password entered");

      signInPage.getSignInButton().click();
      logger.info("Sign in button clicked");


      MyLoopsPage loopsPage = new MyLoopsPage(getWebDriver());
      logger.info("Wait until page loaded");
      getWait().until(ExpectedConditions.presenceOfElementLocated(loopsPage.getTabTitleLocator()));
      logger.info("My loops age loaded");

      Assert.assertEquals(loopsPage.getTabTitle().getText(), "Loops");
      logger.info("My Loops page asserted");

   }

   @Test(groups = {"login", "smoke"})
   public void clickSignIn() {
      logger.info("clickSignIn test case started");
      getWebDriver().navigate().to("https://dotloop.com/");
      logger.info("Home page opened");

      HomePage homePage = new HomePage(getWebDriver());
      homePage.getSignIn().click();
      logger.info("Sign in clicked");

      SignInPage signInPage = new SignInPage(getWebDriver());
      Assert.assertEquals(signInPage.getLoginPageTitle().getText(), "Welcome to dotloop.");
      logger.info("Sign in page opened");
   }

}
