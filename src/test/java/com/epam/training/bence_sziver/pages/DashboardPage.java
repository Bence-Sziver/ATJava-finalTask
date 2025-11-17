package com.epam.training.bence_sziver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement title;

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getDashboardTitle() {
        return title.getText();
    }
}
