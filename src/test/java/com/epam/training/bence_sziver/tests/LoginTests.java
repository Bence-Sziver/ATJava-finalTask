package com.epam.training.bence_sziver.tests;

import com.epam.training.bence_sziver.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        loginPage = new LoginPage(driver);
        loginPage.openPage();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testEmptyFields() {
        loginPage.typeUsername("username")
                 .typePassword("password")
                 .clearUsername()
                 .clearPassword()
                 .login();
        assertEquals("Epic sadface: Username is required", loginPage.checkLoginError());
    }
}
