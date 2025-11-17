package com.epam.training.bence_sziver.tests.steps;

import com.epam.training.bence_sziver.DriverManager;
import com.epam.training.bence_sziver.pages.DashboardPage;
import com.epam.training.bence_sziver.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Before
    public void setUp() {
        driver = DriverManager.createDriver("firefox");
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am on the logging page")
    public void isOnLoginPage() {
        loginPage.openPage();
    }

    @When("I enter {string} as username")
    public void entersUsername(String username) {
        loginPage.typeUsername(username);
    }

    @When("I enter {string} as password")
    public void entersPassword(String password) {
        loginPage.typePassword(password);
    }

    @When("I clear the username field")
    public void clearsUsername() {
        loginPage.clearUsername();
    }

    @When("I clear the password field")
    public void clearsPassword() {
        loginPage.clearPassword();
    }

    @When("I click the login button")
    public void clicksLogin() {
        dashboardPage = loginPage.login();
    }

    @Then("I should see an error message containing {string}")
    public void errorShouldShow(String expected) {
        assertThat(loginPage.checkLoginError(), containsString(expected));
    }

    @Then("I should see the dashboard title {string}")
    public void titleShouldShow(String expected) {
        assertThat(dashboardPage.getDashboardTitle(), is(expected));
    }
}
