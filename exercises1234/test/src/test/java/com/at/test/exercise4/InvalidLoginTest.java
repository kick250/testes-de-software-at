package com.at.test.exercise4;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidLoginTest {
    private WebDriver driver;
    private Faker faker;
    private RootPage rootPage;
    private LoginPage loginPage;
    private SignupPage signupPage;


    @BeforeEach
    public void setup() {
        faker = Faker.instance();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        rootPage = new RootPage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testInvalidLogin() throws IOException {
        try {
            rootPage.clickLogin();
            loginPage.login(faker.internet().emailAddress(), faker.internet().password());

            assertTrue(loginPage.isErrorMessageDisplayed());
            assertEquals("https://automationexercise.com/login", driver.getCurrentUrl());
        } catch (Exception e) {
            var body = driver.findElement(By.cssSelector("body"));
            File screenshot = body.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshot, new File("InvalidLoginTest_testInvalidLogin_error.png"));
            fail();
        }
    }
}
