package ebook_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Abstract {
    @FindBy(id = "login")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "password-repeat")
    WebElement passwordRepeat;

    @FindBy(id = "login-btn")
    WebElement loginButton;

    @FindBy(id = "register-btn")
    WebElement signUpButton;

    @FindBy(className = "alert__content")
    WebElement alert;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean login(String username, String password) {
        loginField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        if (driver.findElements(By.className("alert__content")).size() == 1) {
            return false;
        } else return true;
    }

    public boolean register(String username, String password) {
        signUpButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlToBe("https://ta-bookrental-fe.onrender.com/register"));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//label"), 3));
        loginField.sendKeys(username);
        passwordField.sendKeys(password);
        passwordRepeat.sendKeys(password);
        signUpButton.click();
        wait.until(ExpectedConditions.visibilityOf(alert));
        if (alert.getText().equals("You have been successfully registered!")) {
            return true;
        } else return false;
    }
}
