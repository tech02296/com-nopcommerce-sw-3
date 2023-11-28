package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";
    String menu,expectedText;
    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }
    public void selectMenu(String menu){
        this.menu = menu;
        //This method should click on the menu whatever name is passed as parameter.
        clickOnElement(By.xpath(menu));
    }
    public void verifyTopMenuTest(){

        menu = getTextFromElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
    }
    @Test
    public void verifyPageNavigation(){
        //select the Menu and click on it and verify the page navigation
        verifyTopMenuTest();
        expectedText ="Computers";
        Assert.assertEquals(expectedText,menu);
    }
    @After
    public void tearDown(){
        // closBrowser();
    }
}


