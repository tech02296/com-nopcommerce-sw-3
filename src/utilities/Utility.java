package utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {
    /**
     * this method is click on element
     */
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    /**
     * this method is get text from element
     */
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

    /**
     * This method will send text to element
     */
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }
    //*******************************Alert Methods *****************************//
    //  5 methods
    //  Switch to alert
    // accept alert
    // get text from alert
    // dismiss alert
   /* public void switchToAlert(By by,String text){
        clickOnElement(By.id("alertbtn")); // click on alert button
        Alert alert = driver.switchTo().alert();//Creating alert object reference and switch to Alert
        alert.getText();
        System.out.println(alert.getText());*/
    // }
    //******************************select Class Method******************************//
    /**
     * This method will select the option by visible text
     */
    public void selectByVisibleTextFromDropDown(By by, String text){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    public void selectByValueFromDropDown(By by, String value){
        new Select(driver.findElement(by)).selectByValue(value);
    }

    public void clearTextToElement(By by){
        driver.findElement(by).clear();
    }
}


