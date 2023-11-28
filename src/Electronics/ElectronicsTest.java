package Electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully(){
        WebElement electronics = driver.findElement((By.xpath("//a[@href = '/electronics']/parent::li")));
        WebElement cellPhone = driver.findElement(By.xpath("//a[@href = '/cell-phones']/parent::li"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronics).moveToElement(cellPhone).click().build().perform();
        String expectedMessage = "Cell phones";
        String actualMessage = driver.findElement(By.xpath("//h1[contains(text(),'Cell phones')]")).getText();
        Assert.assertEquals("Cell phones",expectedMessage,actualMessage);

    }
    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException{
        //2.1 Mouse Hover on “Electronics” Tab
        WebElement electronics = driver.findElement((By.xpath("//a[@href = '/electronics']/parent::li")));
        WebElement cellPhone = driver.findElement(By.xpath("//a[@href = '/cell-phones']/parent::li"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronics).moveToElement(cellPhone).click().build().perform();

        //2.2 Mouse Hover on “Cell phones” and click
        actions.moveToElement(electronics).moveToElement(cellPhone).click();

        //2.3 Verify the text “Cell phones”
        String expectedMessage = "Cell phones";
        String actualMessage = driver.findElement(By.xpath("//h1[contains(text(),'Cell phones')]")).getText();
        Assert.assertEquals("Cell phones",expectedMessage,actualMessage);

        //2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));
        Thread.sleep(2000);

        //2.5 Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.xpath("//div[@class='product-item']//img[@title='Show details for Nokia Lumia 1020']"));
        ////div[@class='product-item']//img[@title='Show details for Nokia Lumia 1020']

        //2.6 Verify the text “Nokia Lumia 1020”
        String expectedMessage1 = "Nokia Lumia 1020";
        String actualMessage1 = driver.findElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]")).getText();
        Assert.assertEquals("Nokia Lumia 1020",expectedMessage1,actualMessage1);

        //2.7 Verify the price “$349.00”
        String expectedMessage2 = "$349.00";
        String actualMessage2 = driver.findElement(By.xpath("//span[@id='price-value-20']")).getText();
        Assert.assertEquals("$349.00",expectedMessage2,actualMessage2);

        //2.8 Change quantity to 2
        clickOnElement(By.xpath("//input[@id='product_enteredQuantity_20']"));

        //2.9 Click on “ADD TO CART” tab
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String expectedMessage3 = "The product has been added to your shopping cart";
        String actualMessage3 = driver.findElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]")).getText();
        Assert.assertEquals("The product has been added to your shopping cart",expectedMessage3,actualMessage3);

        //After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        WebElement shoppingCart = driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        WebElement goToCart = driver.findElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(shoppingCart).moveToElement(goToCart).click().build();
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        //2.12 Verify the message "Shopping cart"

        String expectedMessage4 = "Shopping cart";
        String actualMessage4 = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals(expectedMessage4,actualMessage4);
        //2.13 Verify the quantity is 2
        clearTextToElement(By.className("qty-input"));
        sendTextToElement(By.className("qty-input"),"2");
        clickOnElement(By.id("updatecart"));
        //2.14 Verify the Total $698.00
        String expectedMessage5 = "$698.00";
        String actualMessage5 = driver.findElement(By.xpath(" //span[@class='value-summary']//strong[contains(text(),'$698.00')]")).getText();
        Assert.assertEquals(expectedMessage5,actualMessage5);

        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.16 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.17 Verify the Text “Welcome, Please Sign In!”
        String expectedMessage6 = "Welcome, Please Sign In!";
        String actualMessage6 = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")).getText();
        Assert.assertEquals("Welcome, Please Sign In!",expectedMessage6,actualMessage6);

        //2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));

        //2.19 Verify the text “Register”
        String expectedMessage7 = "Register";
        String actualMessage7 = driver.findElement(By.xpath("//h1[contains(text(),'Register')]")).getText();
        Assert.assertEquals(expectedMessage7,actualMessage7);

        //2.20 Fill the mandatory fields
        clickOnElement(By.xpath("//input[@id='gender-male']"));
        driver.findElement(By.id("FirstName")).sendKeys("experience");
        driver.findElement(By.id("LastName")).sendKeys("tester");
        WebElement dropDown = driver.findElement(By.xpath("//input[@id='LastName']"));
        Select select = new Select(dropDown);
        select.selectByValue("<option value=\"6\" xpath=\"1\">6</option>");
        WebElement dropDown1 = driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        Select select1 = new Select(dropDown1);
        select1.selectByVisibleText("June");
        WebElement dropDown2 = driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));
        Select select2 = new Select(dropDown2);
        select2.selectByVisibleText("2000");
        driver.findElement(By.id("Email")).sendKeys("experiencetester@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("tech1234@");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("tech1234@");
        clickOnElement(By.xpath(""));

        //2.21 Click on “REGISTER” Button
        clickOnElement(By.id("register-button"));
        //2.22 Verify the message “Your registration completed”
        String expectedText = "Your registration completed";
        String actualText = driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).getText();
        Assert.assertEquals(expectedText,actualText);

        //2.23 Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        //2.24 Verify the text “Shopping card”
        String etext = "Shopping cart";
        String aText = driver.findElement(By.xpath("//h1[contains(text(),'Shopping cart')]")).getText();

        //2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.26 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));
        //2.27 Fill the Mandatory fields
        driver.findElement(By.xpath("//label[contains(text(),'First name:')]")).sendKeys("experience");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("tester");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("experiencetester@gmail.com");
        WebElement dropDow = driver.findElement(By.name("BillingNewAddress.CountryId"));
        Select sel = new Select(dropDow);
        sel.selectByVisibleText("United Kingdom");
        select1.selectByVisibleText("Other");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("london");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("22,alexandra avenue");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("HA2 2XN");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("07303845739");

        //2.28 Click on “CONTINUE”
        clickOnElement(By.name("save"));

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_2"));

        //2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]"));

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

        //2.35 Verify “Payment Method” is “Credit Card”
        String expectedMessage8 = "Payment Method: Credit Card";
        String actualMessage8 = driver.findElement(By.xpath("//span[contains(text(),'Payment Method:')]://span[contains(text(),'Credit Card')] ")).getText();
        Assert.assertEquals("Payment Method: Credit Card",expectedMessage8,actualMessage8);
        //2.36 Verify “Shipping Method” is “2nd Day Air”
        String expectedShippingMethod= "Shipping Method: 2nd Day Air";
        String actualShippingMethod = driver.findElement(By.xpath("//span[contains(text(),'Shipping Method:')]")).getText();
        Assert.assertEquals(expectedShippingMethod,actualShippingMethod);

        //2.37 Verify Total is “$698.00”

        String expectedPayment1 = "$698.00";
        String actualPayment1 = getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));
        Assert.assertEquals(expectedPayment1,actualPayment1);

        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //2.39 Verify the Text “Thank You”
        String message = "Thank you";
        String message1 = driver.findElement(By.xpath("//h1[contains(text(),'Thank you')]")).getText();
        Assert.assertEquals(message,message1);

        //2.40 Verify the message “Your order has been successfully processed!”
        String verifyMessage = "Your order has been successfully processed!";
        String verifyMessage1 = driver.findElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")).getText();
        Assert.assertEquals(verifyMessage,verifyMessage1);

        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //2.42 Verify the text “Welcome to our store”
        String verifyText = "Welcome to our store";
        String verifyText1 = driver.findElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")).getText();
        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String verifyUrl = "https://demo.nopcommerce.com/";
        String verifyUrl1 = driver.getCurrentUrl();
        Assert.assertEquals(verifyUrl,verifyUrl1);

    }

    @After
    public void tearDown(){
        // closBrowser();
    }
}



