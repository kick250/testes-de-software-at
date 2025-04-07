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
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private WebDriver driver;
    private Faker faker;
    private RootPage rootPage;
    private LoginPage loginPage;
    private SignupPage signupPage;

    private String email;
    private String password;


    @BeforeEach
    public void setup() {
        faker = Faker.instance();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        rootPage = new RootPage(driver);
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);

        var birthdate = faker.date().birthday(10, 20).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        email = faker.internet().emailAddress();
        password = faker.internet().password();

        rootPage.clickLogin();
        loginPage.signupWith(faker.name().firstName(), email);
        signupPage.signupWith(
                password,
                birthdate.getDayOfMonth(),
                birthdate.getMonthValue(),
                birthdate.getYear(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.gameOfThrones().house(),
                faker.address().fullAddress(),
                faker.address().state(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.phoneNumber().phoneNumber()
        );

        driver.get("https://automationexercise.com/");
        rootPage.clickLogout();
        driver.get("https://automationexercise.com/");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testLogin() throws IOException {
        try {
            rootPage.clickLogin();
            loginPage.login(email, password);

            assertTrue(rootPage.isLogoutButtonDisplayed());
            assertEquals("https://automationexercise.com/", driver.getCurrentUrl());
        } catch (Exception e) {
            var body = driver.findElement(By.cssSelector("body"));
            File screenshot = body.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshot, new File("LoginTest_testLogin_error.png"));
            fail();
        }
    }
}
