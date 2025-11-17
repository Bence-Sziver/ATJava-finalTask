package com.epam.training.bence_sziver.tests;

import com.epam.training.bence_sziver.DriverManager;
import com.epam.training.bence_sziver.pages.DashboardPage;
import com.epam.training.bence_sziver.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);

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
        log.info("Start Test: testEmptyFields");
        loginPage.typeUsername("username")
                 .typePassword("password")
                 .clearUsername()
                 .clearPassword()
                 .login();
        assertThat(loginPage.checkLoginError(), containsString("Username is required"));
        log.info("End Test: testEmptyFields");
    }

    @Test
    public void testEmptyPassword() {
        log.info("Start Test: testEmptyPassword");
        loginPage.typeUsername("username")
                .typePassword("password")
                .clearPassword()
                .login();
        assertThat(loginPage.checkLoginError(), containsString("Password is required"));
        log.info("End Test: testEmptyPassword");
    }

    @ParameterizedTest
    @CsvSource({"'standard_user', 'secret_sauce'",
                "'locked_out_user', 'secret_sauce'",
                "'problem_user', 'secret_sauce'",
                "'performance_glitch_user', 'secret_sauce'",
                "'error_user', 'secret_sauce'",
                "'visual_user', 'secret_sauce"})
    public void testValidCredentials(String username, String password) {
        log.info("Start Test: testValidCredentials with username: {}, password: {}", username, password);
        DashboardPage dashboardPage = (DashboardPage) loginPage.typeUsername(username)
                                                               .typePassword(password)
                                                               .login();
        assertThat(dashboardPage.getDashboardTitle(), is("Swag Labs"));
        log.info("End Test: testValidCredentials with username: {}, password: {}", username, password);
    }
}
