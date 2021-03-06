package core;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Виталий on 02.04.2017.
 */
public class Browser {

    public static void open(String url) {
        Log.info(String.format("Open browser with %s url",url));
        WebDriver wb =Driver.getWebDriver();
        wb.manage().window().maximize();
        wb.get(url);
    }
    public static String getTitle(){
        Log.info("Get title from browser");
        return Driver.getWebDriver().getTitle();
    }
    public static void quit(){
        if (Driver.getWebDriver(false) != null){
            Log.info("Close browser");
            Driver.getWebDriver().quit();
            Driver.setWebDriver(null);
        }

    }
    public static void scrollToElement(WebElement element){
        ((JavascriptExecutor) Driver.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

}