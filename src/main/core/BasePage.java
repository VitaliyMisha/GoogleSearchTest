package core;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Виталий on 01.04.2017.
 */
public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.getWebDriver(), this);
    }

}

