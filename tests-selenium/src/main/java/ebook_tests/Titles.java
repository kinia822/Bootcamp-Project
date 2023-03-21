package ebook_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Titles extends Abstract {
    @FindBy(xpath = "//*[@id=\"add-title-button\"]")
    WebElement addNewButton;

    @FindBy(xpath = "//li[1]/div/a/button")
    WebElement showCopiesButton;

    @FindBy(xpath = "//li[2]/div/button[1]")
    WebElement editButton;

    @FindBy(xpath = "//li[2]//button[2]")
    WebElement removeButton;

    @FindBy(name = "title")
    WebElement titleInput;

    @FindBy(name = "author")
    WebElement authorInput;

    @FindBy(name = "year")
    WebElement yearInput;

    @FindBy(name = "submit-button")
    WebElement submitButton;

    public Titles(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean addTitle(String title, String author, int year) {
        addNewButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        titleInput.sendKeys(title);
        authorInput.sendKeys(author);
        yearInput.sendKeys(String.valueOf(year));
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (driver.findElements(By.className("alert__content")).size() == 1) {
            return false;
        } else return true;
    }

    public boolean editTitle(String title, String author, int year) {
        editButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        titleInput.clear();
        titleInput.sendKeys(title);
        authorInput.clear();
        authorInput.sendKeys(author);
        yearInput.clear();
        yearInput.sendKeys(String.valueOf(year));
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (driver.findElements(By.className("alert__content")).size() == 1) {
            return false;
        } else return true;
    }

    public boolean removeTitle() {
        int sizeBefore = driver.findElements(By.cssSelector(".titles-list__item")).size();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        removeButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".titles-list__item"), sizeBefore));
        int sizeAfter = driver.findElements(By.cssSelector(".titles-list__item")).size();
        return sizeBefore == sizeAfter + 1;
    }

    public boolean showCopies() {
        showCopiesButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver.findElements(By.xpath("//*[@id=\"title-copies\"]/div/h2")).size() == 1;
    }
}
