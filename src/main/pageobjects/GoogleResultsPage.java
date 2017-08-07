package pageobjects;

import core.BasePage;
import core.Browser;
import core.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class GoogleResultsPage extends BasePage{

    @FindBy(className = "cur")
    private WebElement currentPage;
    @FindBy(id = "pnnext")
    private WebElement nextPageButton;
    @FindBy(className = "_Rm")
    private List<WebElement> resultUrls;
    @FindBy(xpath = "//*[@class='r']/a[@href]")
    private List<WebElement> resultLinks;

    public ArrayList<String> getResultLinkTexts(){
        Log.info("Get text from results links");
        ArrayList<String> linkTexts = new ArrayList<>();
        for (WebElement element: resultLinks){
            linkTexts.add(element.getText());
        }
        return linkTexts;
    }
    private ArrayList<String> getResultUrls() {
        Log.info("Get result urls");
        ArrayList<String> urls = new ArrayList<>();
        for (WebElement element : resultUrls) {
            urls.add(element.getText());
        }
        return urls;
    }
    public String getResultUrlTextByIndex(int index) {
        Log.info(String.format("Get ulr text using %s index",index));
        return this.getResultUrls().get(index);
    }
    public String getSearchTitleFromResults(String searchTitle,int maxPageToSearchIn){
        Log.info(String.format("Search %s title within %s pages",searchTitle,maxPageToSearchIn));
        int actualPage;
        do {
           ArrayList<String> actualResultLinks =  getResultLinkTexts();
            for (String s:actualResultLinks){
                if (s.equalsIgnoreCase(searchTitle)){
                    return s;
                }
            }
            clickToNextPage(actualResultLinks);
            actualPage = Integer.parseInt(currentPage.getText());
        }while(actualPage<=maxPageToSearchIn);
        return null;
    }
    private void clickToNextPage(ArrayList<String> actualResultLinks){
        Log.info("Click to the next page");
        Browser.scrollToElement(nextPageButton);
        nextPageButton.click();
        int tries = 0;
        while(actualResultLinks==getResultLinkTexts() || tries<10 ) {
            tries++;
        }
    }
}
