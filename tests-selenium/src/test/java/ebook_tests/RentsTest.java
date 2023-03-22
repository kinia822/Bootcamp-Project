package ebook_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class RentsTest {

    LoginPage loginPage;
    Titles titles;
    Items items;
    Rents rents;
    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        rents = new Rents(driver);
        items = new Items(driver);
        titles = new Titles(driver);
        loginPage = new LoginPage(driver);
        loginPage.login("hello125", "password123");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        titles.showCopies();
        items.showRents();
    }

    @Test
    public void shouldAddRent() {
        assertTrue(rents.addRent(2020, "May", 11, "John Knock"));
    }

    @Test
    public void shouldNotAddRent() {
        assertFalse(rents.addRent(2020, "March", 15, ""));
    }

    @Test
    public void shouldRemoveRent() {
        assertTrue(rents.removeRent());
    }

    @Test
    public void shouldEditRent() {
        assertTrue(rents.editRent(2022, "March", 14, "Eddy Mone", 2023, "April", 22));
    }

    @AfterEach
    public void testDown() {
        driver.close();
    }
}