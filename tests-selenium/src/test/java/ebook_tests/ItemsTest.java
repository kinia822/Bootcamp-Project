package ebook_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

class ItemsTest {

    Titles titles;
    LoginPage loginPage;
    WebDriver driver;
    Items items;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        items = new Items(driver);
        titles = new Titles(driver);
        loginPage = new LoginPage(driver);
        loginPage.login("hello125", "password123");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        titles.showCopies();
    }

    @Test
    public void shouldAddItem() {
        assertTrue(items.addItem(2023, "March", 11));
    }

    @Test
    public void shouldRemoveItem() { assertTrue(items.removeItem()); }

    @Test
    public void shouldShowRents() {
        assertTrue(items.showRents());
    }

    @AfterEach
    public void testDown() {
        driver.close();
    }
}