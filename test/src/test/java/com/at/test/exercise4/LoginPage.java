package com.at.test.exercise4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By signupName = By.cssSelector("[data-qa='signup-name']");
    private By signupEmail = By.cssSelector("[data-qa='signup-email']");
    private By sugnupButton = By.cssSelector("[data-qa='signup-button']");
    private By errorMessage = By.cssSelector("form p[style='color: red;']");

    private By loginEmailInput = By.cssSelector("[data-qa='login-email']");
    private By loginPassowrdInput = By.cssSelector("[data-qa='login-password']");
    private By loginButton = By.cssSelector("[data-qa='login-button']");

    public void signupWith(String name, String email) {
        driver.findElement(signupName).sendKeys(name);
        driver.findElement(signupEmail).sendKeys(email);
        driver.findElement(sugnupButton).click();
    }

    public void login(String email, String password) {
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPassowrdInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        var element = driver.findElement(errorMessage);
        return element.isDisplayed() && element.getText().equals("Your email or password is incorrect!");
    }
}