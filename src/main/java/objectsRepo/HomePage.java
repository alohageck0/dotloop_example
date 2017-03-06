package objectsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import serviceClasses.PageTemplate;

public class HomePage extends PageTemplate {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//*[@id='myTopnav']/div/div[2]/li[6]/a")
    private WebElement signIn;


    public WebElement getSignIn() {
        return signIn;
    }
}
