package ebook_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TitlesTest {

    Titles titles;
    LoginPage loginPage;
    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        titles = new Titles(driver);
        loginPage = new LoginPage(driver);
        loginPage.login("hello125", "password123");
    }

    @Test
    public void shouldAddTitle() {
        assertTrue(titles.addTitle("Hp", "jk", 15));
    }

    @ParameterizedTest
    @MethodSource("addTitleParameters")
    public void shouldNotAddTitle(String title, String author, int year) {
        assertFalse(titles.addTitle(title, author, year));
    }

    private static Stream<Arguments> addTitleParameters() {
        return Stream.of(
                Arguments.of("", "xd", 3),
                Arguments.of("me", "xd", 0),
                Arguments.of("me", "", 3)
        );
    }

    @Test
    public void shouldEditTitle() {
        assertTrue(titles.editTitle("Hp", "JK", 15));
    }

    @Test
    public void shouldRemoveTitle() {
        assertTrue(titles.removeTitle());
    }


    @Test
    public void shouldShowCopies() {
        assertTrue(titles.showCopies());
    }

    @AfterEach
    public void testDown() {
        driver.close();
    }
}
