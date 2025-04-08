package com.at.test.exercise4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RootPage {
    private WebDriver driver;

    public RootPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginButton = By.cssSelector("[href='/login']");
    private By logoutButton = By.cssSelector("[href='/logout']");

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }

    public boolean isLogoutButtonDisplayed() {
        return driver.findElement(logoutButton).isDisplayed();
    }
}