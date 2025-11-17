package com.epam.training.bence_sziver.tests;

import com.epam.training.bence_sziver.DriverManager;
import com.epam.training.bence_sziver.pages.DashboardPage;
import com.epam.training.bence_sziver.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.createDriver("firefox");
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

    @Test
    public void testEmptyPassword() {
        loginPage.typeUsername("username")
                .typePassword("password")
                .clearPassword()
                .login();
        assertEquals("Epic sadface: Password is required", loginPage.checkLoginError());
    }

    @ParameterizedTest
    @CsvSource({"'standard_user', 'secret_sauce'",
                "'locked_out_user', 'secret_sauce'",
                "'problem_user', 'secret_sauce'",
                "'performance_glitch_user', 'secret_sauce'",
                "'error_user', 'secret_sauce'",
                "'visual_user', 'secret_sauce"})
    public void testValidCredentials(String username, String password) {
        dashboardPage = (DashboardPage) loginPage.typeUsername(username)
                                                 .typePassword(password)
                                                 .login();
        assertEquals("Swag Labs", dashboardPage.getDashboardTitle());
    }
}
