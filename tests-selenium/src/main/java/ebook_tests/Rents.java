package ebook_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Rents extends Abstract {

    @FindBy(name = "add-rent-button")
    WebElement addRentButton;

    @FindBy(name = "customer-name")
    WebElement nameInput;

    @FindBy(name = "rent-date")
    WebElement rentDate;

    @FindBy(name = "expiration-date")
    WebElement expirationDate;

    @FindBy(name = "submit-button")
    WebElement submitButton;

    @FindBy(xpath = "//li[1]//button[1]")
    WebElement editButton;

    @FindBy(xpath = "//li[1]//button[2]")
    WebElement removeButton;

    public Rents(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean addRent(int year, String month, int day, String name) {
        int sizeBefore = driver.findElements(By.xpath("//li")).size();
        addRentButton.click();
        nameInput.sendKeys(name);
        selectRentDate(year, month, day);
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        if (driver.findElements(By.className("alert__content")).size() == 1) {
            return false;
        }
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//li"), sizeBefore + 1));
        int sizeAfter = driver.findElements(By.xpath("//li")).size();
        return sizeAfter == sizeBefore + 1;
    }

    public boolean editRent(int year, String month, int day, String name, int yearTo, String monthTo, int dayTo) {
        editButton.click();
        nameInput.clear();
        nameInput.sendKeys(name);
        selectRentDate(year, month, day);
        selectExpirationDate(yearTo, monthTo, dayTo);
        submitButton.click();
        if (driver.findElements(By.className("alert__content")).size() == 1) {
            return false;
        } else return true;
    }

    public boolean removeRent() {
        int sizeBefore = driver.findElements(By.xpath("//li")).size();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        removeButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//li"), sizeBefore));
        int sizeAfter = driver.findElements(By.xpath("//li")).size();
        return sizeBefore == sizeAfter + 1;
    }

    private void selectRentDate(int year, String month, int day) {
        rentDate.click();
        driver.findElement(By.xpath("(//span[@class='day__month_btn up'])[1]")).click();
        driver.findElement(By.xpath("(//span[@class='month__year_btn up'])[1]")).click();
        driver.findElement(By.xpath("(//*[text()='" + year + "'])[1]")).click();
        driver.findElement(By.xpath("(//*[text()='" + month + "'])[1]")).click();
        driver.findElement(By.xpath("(//*[text()='" + day + "'])[1]")).click();
    }

    private void selectExpirationDate(int year, String month, int day) {
        expirationDate.click();
        driver.findElement(By.xpath("(//span[@class='day__month_btn up'])[2]")).click();
        driver.findElement(By.xpath("(//span[@class='month__year_btn up'])[2]")).click();
        driver.findElement(By.xpath("(//*[text()='" + year + "'])[2]")).click();
        driver.findElement(By.xpath("(//*[text()='" + month + "'])[2]")).click();
        driver.findElement(By.xpath("(//*[text()='" + day + "'])[2]")).click();
    }
}
