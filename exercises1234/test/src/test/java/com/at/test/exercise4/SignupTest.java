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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.ZoneId;
import java.io.File;

public class SignupTest {
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
        signupPage = new SignupPage(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testSignup() throws IOException {
        try {
            var birthdate = faker.date().birthday(10, 20).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            rootPage.clickLogin();
            loginPage.signupWith(faker.name().firstName(), faker.internet().emailAddress());
            signupPage.signupWith(
                    faker.internet().password(),
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

            assertEquals("https://automationexercise.com/account_created", driver.getCurrentUrl());
        } catch (Exception e) {
            var body = driver.findElement(By.cssSelector("body"));
            File screenshot = body.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshot, new File("SignupTest_testSignup_error.png"));
            fail();
        }
    }
}
