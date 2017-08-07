import core.Browser;
import core.Log;
import core.TestListener;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.GoogleSearchPage;

import java.util.ArrayList;
import java.util.Collection;

@Listeners({TestListener.class})
public class LocalTest {

    private static final String SEARCH_PATTERN = "automation";

   @AfterMethod
    public static void afterMethod(){
        Browser.quit();
    }

    @Test
    @Parameters("searchPattern")
    public void testCheckBrowserTitleAfterSearch(@Optional(SEARCH_PATTERN) String searchPattern){
        new GoogleSearchPage().open().search(searchPattern);
        Assert.assertTrue(Browser.getTitle().startsWith(searchPattern));
    }

    @Test
    @Parameters({"searchPattern", "index", "expectedUrl"})
    public void testVerifySecondUrl(@Optional(SEARCH_PATTERN) String searchPattern,@Optional("0") String index,
                                    @Optional("https://en.wikipedia.org/wiki/Automation") String expectedUrl){
        String urlText = new GoogleSearchPage().open().search(searchPattern).getResultUrlTextByIndex(Integer.parseInt(index));
        Assert.assertTrue(urlText.toLowerCase().contains(expectedUrl.toLowerCase()),String.format("%s expected url not equal to %s actual url",expectedUrl,urlText));
    }

    @Test
    @Parameters({"searchPattern", "maxCountPage", "expectedPageTitle"})
    public void testFindExpectedTitleInRangePages(@Optional(SEARCH_PATTERN) String searchPattern,@Optional("5") String maxCountPage,@Optional("Automation Direct") String expectedPageTitle){
        String result = new GoogleSearchPage().open().search(searchPattern).getSearchTitleFromResults(expectedPageTitle,Integer.parseInt(maxCountPage));
        Assert.assertNotNull(result,String.format("Cannot find %s link from 1 to %s pages",expectedPageTitle, maxCountPage));
    }
    @DataProvider(name = "linkTexts", parallel = true)
    public Object[] getLinkTexts() {
        ArrayList<String> linkTexts = (new GoogleSearchPage()).open().search(SEARCH_PATTERN).getResultLinkTexts();
        Collection<Object[]> data = new ArrayList<>();
        linkTexts.forEach(item -> data.add(new Object[]{item}));
        Browser.quit();
        return linkTexts.toArray();
    }

    @Test(dataProvider = "linkTexts")
    @Parameters("linkText")
    public void testVerifyResultLinksAfterSearch(String linkText){
        Log.info(String.format("Verify if %s searchPatter exists in %s",SEARCH_PATTERN,linkText));
        Assert.assertTrue(linkText.toLowerCase().contains(SEARCH_PATTERN.toLowerCase()), String.format("Cannot found '%s' search pattern in '%s' link", SEARCH_PATTERN, linkText));
    }
}