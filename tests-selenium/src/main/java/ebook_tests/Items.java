package ebook_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Items extends Abstract {

    @FindBy(id = "add-item-button")
    WebElement addNewButton;

    @FindBy(name = "submit-button")
    WebElement submitButton;

    @FindBy(xpath = "//li[2]/div/button[2]")
    WebElement removeButton;

    @FindBy(xpath = "//li[2]/div/a/button")
    WebElement showRentsButton;

    @FindBy(id = "id")
    WebElement purchaseDate;

    public Items(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean addItem(int year, String month, int day) {
        int sizeBefore = driver.findElements(By.xpath("//li")).size();
        addNewButton.click();
        selectDate(year, month, day);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//li"), sizeBefore + 1));
        int sizeAfter = driver.findElements(By.xpath("//li")).size();
        return sizeAfter == sizeBefore + 1;
    }

    public boolean removeItem() {
        int sizeBefore = driver.findElements(By.cssSelector(".items-list__item")).size();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        removeButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".items-list__item"), sizeBefore));
        int sizeAfter = driver.findElements(By.cssSelector(".items-list__item")).size();
        return sizeBefore == sizeAfter + 1;
    }

    public boolean showRents() {
        showRentsButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver.findElements(By.xpath("//*[@id=\"rents\"]/div/h2")).size() == 1;
    }

    private void selectDate(int year, String month, int day) {
        purchaseDate.click();
        driver.findElement(By.cssSelector(".day__month_btn.up")).click();
        driver.findElement(By.cssSelector(".month__year_btn.up")).click();
        driver.findElement(By.xpath("//*[text()='" + year + "']")).click();
        driver.findElement(By.xpath("//*[text()='" + month + "']")).click();
        driver.findElement(By.xpath("//*[text()='" + day + "']")).click();
        submitButton.click();
    }
}
