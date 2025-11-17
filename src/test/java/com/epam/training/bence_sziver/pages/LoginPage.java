package com.epam.training.bence_sziver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private static final String url = "https://www.saucedemo.com/";

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(url);
        log.info("Website opened");
    }

    public LoginPage typeUsername(String username) {
        usernameField.sendKeys(username);
        log.info("Username entered: {}", usernameField.getText());
        return this;
    }

    public LoginPage typePassword(String password) {
        passwordField.sendKeys(password);
        log.info("Password entered: {}", passwordField.getText());
        return this;
    }

    public LoginPage clearUsername() {
        usernameField.clear();
        log.info("Username cleared");
        return this;
    }

    public LoginPage clearPassword() {
        passwordField.clear();
        log.info("Password cleared");
        return this;
    }

    public DashboardPage login() {
        loginButton.click();
        log.info("Login button clicked");
        return new DashboardPage(driver);
    }

    public String checkLoginError() {
        return errorMessage.getText();
    }
}
