package core;

import core.browsers.*;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class Driver {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public enum BrowserType {
        CHROME("Chrome", new String[]{"--disable-extensions", "--allow-running-insecure-content",
                "--ignore-certificate-errors", "--disable-print-preview", "--test-type", "--disable-web-security", "--disk-cache-size=1",
                "--media-cache-size=1", "--disable-infobars"}),
        OPERA("Opera"),
        FIREFOX("FireFox"),
        IE("InternetExplorer"),
        SAFARI("Safari"),
        EDGE("Edge");
        private final Object[] values;
        BrowserType(Object... vals) {
            values = vals;
        }
        public String getName() {
            return (String) values[0];
        }

        public String[] getOptions(){
            return (String[]) values[1];
        }
        public static BrowserType findByName(String name){
            for(BrowserType v : values()){
                if( v.getName().equals(name)){
                    return v;
                }
            }
            return BrowserType.CHROME;
        }

    }
    public static WebDriver getWebDriver() {
        if (webDriver.get() == null){
            BrowserType type = BrowserType.findByName(Prop.get("browser"));
            switch (type){
                case CHROME:{
                    setWebDriver(new Chrome().getDriver());
                    break;
                }
                case OPERA:{
                    setWebDriver(new Opera().getDriver());
                    break;
                }
                case FIREFOX:{
                    setWebDriver(new FireFox().getDriver());
                    break;
                }
                case IE:{
                    setWebDriver(new InternetExplorer().getDriver());
                    break;
                }
                case SAFARI:{
                    setWebDriver(new Safari().getDriver());
                    break;
                }
                case EDGE:{
                    setWebDriver(new Edge().getDriver());
                    break;
                }

            }
            webDriver.get().manage().timeouts().implicitlyWait(Long.parseLong(Prop.get("defaultImplicitWait")), TimeUnit.SECONDS);
            webDriver.get().manage().timeouts().pageLoadTimeout(Long.parseLong(Prop.get("pageLoadTimeout")),TimeUnit.SECONDS);
        }
        return webDriver.get();
    }
    public static WebDriver getWebDriver(boolean createNewDriver){
        if (!createNewDriver){
            return webDriver.get();
        }
        return getWebDriver();
    }

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}
