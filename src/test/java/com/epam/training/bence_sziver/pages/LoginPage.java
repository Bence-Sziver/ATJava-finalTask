package com.epam.training.bence_sziver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    private static final String url = "https://www.saucedemo.com/";

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(url);
    }

    public LoginPage typeUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clearUsername() {
        usernameField.clear();
        return this;
    }

    public LoginPage clearPassword() {
        passwordField.clear();
        return this;
    }

    public void login() {
        loginButton.click();
    }

    public String checkLoginError() {
        return errorMessage.getText();
    }
}
