package pageobjects;

import core.BasePage;
import core.Browser;
import core.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Виталий on 01.04.2017.
 */
public class GoogleSearchPage extends BasePage {

    @FindBy(id = "lst-ib")
    private WebElement searchField;
    @FindBy(className = "lsb")
    private WebElement searchButton;

    public GoogleSearchPage open() {
        String url = "https://www.google.com.ua/";
        Log.info(String.format("Open '%s' address", url));
        Browser.open(url);
        return this;
    }

    public GoogleResultsPage search(String searchPattern){
        Log.info(String.format("Search for '%s' pattern", searchPattern));
        searchField.sendKeys(searchPattern);
        searchButton.click();
        return new GoogleResultsPage();
    }
}
