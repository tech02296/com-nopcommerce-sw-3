package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyProductArrangeInAlphaBaticalOrder(){
        // 1.1 Click on Computer Menu.
        driver.findElement(By.xpath("//a[text() = 'Computers']")).click();
        //1.2 Click on Desktop
        driver.findElement(By.xpath("//a[text() = ' Desktops ']")).click();
        //1.3 Select Sort By position "Name: Z to A"
        driver.findElement(By.id("//select[@id='products-orderby']")).click();
        //1.4 Verify the Product will arrange in Descending order.
        String expectedMessage = "Name: Z to A";
        String actualMessage = driver.findElement(By.xpath("//option[contains(text(),'Name: Z to A')]")).getText();
        Assert.assertEquals("Name: Z to A",expectedMessage,actualMessage);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException{
        //2.1 Click on Computer Menu.
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        // 2.2 Click on Desktop
        clickOnElement(By.xpath("//div[@class='sub-category-item']//h2/a"));
        //2.3 Select Sort By position "Name: A to Z"
        clickOnElement(By.xpath("//option[contains(text(),'Name: A to Z')]"));
        Thread.sleep(2000);
        // 2.4 Click on "Add To Cart"
        clickOnElement(By.xpath("//a[text() = 'Build your own computer']"));
        ////body/div[6]/div[3]/div[1]/div[3]/div[1]/parent::div
        //2.5 Verify the Text "Build your own computer"
        String expectedMessage="Build your own computer";
        String actualMessage = driver.findElement(By.xpath("//h1[contains(text(),'Build your own computer')]")).getText();
        Assert.assertEquals("Build your own computer",expectedMessage,actualMessage);
        // 2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        WebElement dropDown = driver.findElement(By.name("product_attribute_1"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
        //2.7.Select "8GB [+$60.00]" using Select class.
        WebElement dropDown2 = driver.findElement(By.name("product_attribute_2"));
        Select select2 = new Select(dropDown2);
        select2.selectByValue("5");
        //2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//input [@id = 'product_attribute_3_7']"));
        //2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.xpath("//input [@id = 'product_attribute_4_9']"));

        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
        //[+$5.00]"
        driver.findElement(By.xpath("//input[@id='product_attribute_5_12']")).click();

        //clickOnElement(By.xpath(" //input [@id = 'product_attribute_5_12']"));
        Thread.sleep(2000);
        //2.11 Verify the price "$1,470.00"
        String expectedMessage1 = "$1,475.00";
        String actualMessage1 = driver.findElement(By.xpath("//span[@id='price-value-1']")).getText();
        Assert.assertEquals(expectedMessage1,actualMessage1);
        //2.12 Click on "ADD TO CARD" Button.
        clickOnElement(By.id("add-to-cart-button-1"));
        //2.13 Verify the Message "The product has been added to your shopping cart" on Top
        //green Bar
        String expectedMessage2 = "The product has been added to your shopping cart";
        String actualMessage2 = driver.findElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]")).getText();
        Assert.assertEquals("The product has been added to your shopping cart",expectedMessage2,actualMessage2);
        //After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));

        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        /*WebElement shoppingCart = driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        WebElement goToCart = driver.findElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).moveToElement(goToCart).click().build().perform();*/
        Thread.sleep(1000);
        WebElement shoppingCart = driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        WebElement goToCart = driver.findElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).build().perform();
        actions.moveToElement(goToCart).click();
        clickOnElement(By.xpath("//button[normalize-space()='Go to cart']"));
        //2.15 Verify the message "Shopping cart"
        String expectedMessage3 = "Shopping cart";
        String actualMessage3 = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals(expectedMessage3,actualMessage3);

        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        clearTextToElement(By.className("qty-input"));
        sendTextToElement(By.className("qty-input"),"2");
        clickOnElement(By.id("updatecart"));
        /*clickOnElement(By.xpath("//input[@id='itemquantity11223']"));
        clickOnElement(By.xpath("//button[@id='updatecart']"));*/

        //2.17 Verify the Total"$2,950.00"
        String expectedMessage4 = "$2,950.00";
        String actualMessage4 = driver.findElement(By.xpath(" //tbody/tr[4]/td[2]")).getText();
        Assert.assertEquals("Shopping cart",expectedMessage4,actualMessage4);

        //2.18 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.19 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        // 2.20 Verify the Text “Welcome, Please Sign In!”
        String expectedMessage5 = "Welcome, Please Sign In!";
        String actualMessage5 = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")).getText();
        Assert.assertEquals("Welcome, Please Sign In!",expectedMessage5,actualMessage5);

        //2.21Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        Thread.sleep(1000);
        //2.22 Fill the all mandatory field
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("experience");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("tester");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("experiencetester1234@yahoo.com");
        driver.findElement(By.id("BillingNewAddress_Company")).sendKeys("hightech");
        WebElement dropDown1 = driver.findElement(By.name("BillingNewAddress.CountryId"));
        Select select1 = new Select(dropDown1);
        select1.selectByVisibleText("United Kingdom");
        // select1.selectByVisibleText("Other");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("london");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("22,alexandra avenue");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("HA2 2XN");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("07103845739");

        //2.23 Click on “CONTINUE”
        clickOnElement(By.name("save"));
        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_1"));
        //2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        //2.31 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));

        //2.32 Select “Visa” From Select credit card dropdown
        WebElement dropDown3 = driver.findElement(By.id("CreditCardType"));
        Select select3 = new Select(dropDown3);
        select3.selectByVisibleText("Visa");

        //2.33 Fill all the details
        driver.findElement(By.id("CardholderName")).sendKeys("experience tester");
        driver.findElement(By.id("CardNumber")).sendKeys("4929123466045002");
        WebElement drop = driver.findElement(By.id("ExpireMonth"));
        Select select5 = new Select(drop);
        select5.selectByValue("8");
        WebElement drop1 = driver.findElement(By.id("ExpireYear"));
        Select select6 = new Select(drop1);
        select6.selectByValue("2025");
        driver.findElement(By.id("CardCode")).sendKeys("452");
        //2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));

        String expectedMessage6 = "Credit Card";
        String actualMessage6 = driver.findElement(By.xpath("//span[normalize-space()='Credit Card'] ")).getText();
        Assert.assertEquals("Payment Method: Credit Card",expectedMessage6,actualMessage6);

        String expectedMessage7 = "Next Day Air";
        String actualMessage7 = driver.findElement(By.xpath("//span[normalize-space()='Next Day Air']")).getText();
        Assert.assertEquals("Shipping Method: Next Day Air",expectedMessage7,actualMessage7);

        String expectedMessage8 = "$2,950.00";
        String actualMessage8 = driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText();
        Assert.assertEquals("Total",expectedMessage8,actualMessage8);

        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        String expectedMessage9 = "Thank you";
        String actualMessage9 = driver.findElement(By.xpath("//h1[contains(text(),'Thank you')]")).getText();
        Assert.assertEquals("Thank you",expectedMessage9,actualMessage9);

        String expectedMessage10 = "Your order has been successfully processed!";
        String actualMessage10 = driver.findElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")).getText();
        Assert.assertEquals("Your order has been successfully processed!",expectedMessage10,actualMessage10);
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        String expectedMessage11 = "Welcome to our store";
        String actualMessage11 = driver.findElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")).getText();
        Assert.assertEquals("Welcome to our store",expectedMessage11,actualMessage11);

    }

    @After
    public void tearDown(){
        //  closBrowser();
    }


}



