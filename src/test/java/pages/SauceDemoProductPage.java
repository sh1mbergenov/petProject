package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoProductPage {
    public SauceDemoProductPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement backpackAddToCart;
    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    public WebElement bikeLightAddToCart;
    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    public WebElement tShirtAddToCart;
    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    public WebElement fleeceAddToCart;
    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    public WebElement oneSidAddToCart;
    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    public WebElement redTShirtAddToCart;




}
