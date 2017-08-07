package core.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Safari {
    private WebDriver driver;
    public WebDriver getDriver(){
        return this.driver;
    }
    public Safari(){
        driver = new SafariDriver();
    }
}