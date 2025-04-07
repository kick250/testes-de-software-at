package com.at.test.exercise4;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupPage {
    private WebDriver driver;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    private By titleInput = By.id("id_gender2");
    private By passowrdInput = By.id("password");
    private By birthdateDayInput = By.id("days");
    private By birthdateMonthInput = By.id("months");
    private By birthdateYearInput = By.id("years");
    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By companyNameInput = By.id("company");
    private By addressInput = By.id("address1");
    private By stateInput = By.id("state");
    private By cityInput = By.id("city");
    private By zipInput = By.id("zipcode");
    private By phoneInput = By.id("mobile_number");
    private By submitButton = By.cssSelector("[data-qa='create-account']");

    public void signupWith(String password, int birthdateDay, int birthdateMonth, int birthdateYear, String firstName, String lastName, String companyName, String address, String state, String city, String zip, String phone) {
        driver.findElement(titleInput).click();
        driver.findElement(passowrdInput).sendKeys(password);
        selectBirthdateDay(birthdateDay);
        selectBirthdateMonth(birthdateMonth);
        selectBirthdateYear(birthdateYear);
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(companyNameInput).sendKeys(companyName);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipInput).sendKeys(zip);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(submitButton).click();
    }

    private void selectBirthdateDay(int birthdateDay) {
        var select = new Select(driver.findElement(birthdateDayInput));
        select.selectByVisibleText(birthdateDay + "");
    }

    private void selectBirthdateMonth(int birthdateMonth) {
        var select = new Select(driver.findElement(birthdateMonthInput));
        select.selectByValue(birthdateMonth + "");
    }

    private void selectBirthdateYear(int birthdateYear) {
        var select = new Select(driver.findElement(birthdateYearInput));
        select.selectByVisibleText(birthdateYear + "");
    }
}