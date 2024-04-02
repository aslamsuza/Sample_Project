package com.okr.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects extends BaseClass {
	
	 public HomePageObjects(WebDriver driver) {
	        BaseClass.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

    @FindBy(xpath = "//ion-label[@id='contact_us']")
    WebElement contactUsLabel;
  
    public void clickContactUs() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", contactUsLabel);
        contactUsLabel.click();
    }
}
