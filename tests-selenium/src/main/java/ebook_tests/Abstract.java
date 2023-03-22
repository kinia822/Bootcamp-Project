package ebook_tests;

import org.openqa.selenium.WebDriver;

public class Abstract {
    protected WebDriver driver;
    public Abstract(WebDriver driver) {
        this.driver = driver;
    }
}
