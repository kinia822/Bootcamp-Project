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

class LoginPageTest {
    LoginPage loginPage;
    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void shouldLogIn() {
        assertTrue(loginPage.login("hello125", "password123"));
    }

    @ParameterizedTest
    @MethodSource("loginParameters")
    public void shouldNotLogIn(String username, String password) {
        assertFalse(loginPage.login(username, password));
    }

    private static Stream<Arguments> loginParameters() {
        return Stream.of(
                Arguments.of("hello125", "password"),
                Arguments.of("hello125", ""),
                Arguments.of("", "password123"),
                Arguments.of("", "")
        );
    }

    /*@Test
    public void shouldRegister() {
        assertTrue(loginPage.login("testlog", "testpass"));
    }*/

    @ParameterizedTest
    @MethodSource("registerParameters")
    public void shouldNotRegister(String username, String password) {
        assertFalse(loginPage.register(username, password));
    }

    private static Stream<Arguments> registerParameters() {
        return Stream.of(
                Arguments.of("hello125", "password123"),
                Arguments.of("hello125", ""),
                Arguments.of("", "password")
        );
    }

    @AfterEach
    public void testDown() {
        driver.close();
    }
}