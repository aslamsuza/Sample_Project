package com.okr.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LocationPageObjects extends BaseClass {


	public LocationPageObjects(WebDriver driver) {
		BaseClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Actions actions;
	public JavascriptExecutor jsExecutor;


	public String locationLink = "//ion-item[@id='header_%s']"; 

	public String findLocationNearYouTextBox = "//ion-input[@id='zipcode']";

	public String searchButton = "//ion-button[@id='search_btn']";  

	public String locationText = "//ion-label[contains(text(), '%s')]";  // WOODBURY

	public String addService = "//ion-button[@id='%s_add']"; // HairCut Services 

	public String addAnotherServiceButton = "//ion-button[@id='add_another-service_btn']";

	public String addOnLabel = "//ion-label[contains(text(), 'ADD-ONS')]";

	//	public String secondService = "//ion-button[@id='%s_add']"; // Add On Service Time 

	public String dateLabel = "//ion-label[contains(text(), 'Date')]";

	public String stylistLabel = "//ion-label[contains(text(), 'Stylist')]";

	public String stylistName = "//ion-label[contains(text(), '%s')]"; // Kate S

	public String estimatedCheckInTimeLabel = "//ion-label[contains(text(), 'Estimated Check-In Time')]";

	public String estimatedCheckInTime = "//ion-label[contains(text(), '%s')]"; // 2:25 PM

	public String firstNameTextInput = "//input[@name='firstName']";

	public String lastNameTextInput = "//input[@name='lastName']";

	public String phoneNumberTextInput = "//input[@id='phone']";

	public String emailTextInput = "//input[@name='email']";

	//	public String nextGuestButton = "//div[@class='active-btn cursor-pointer']";  

	public String checkInTime = "//ion-label[@id='checkin_time']";

	public String checkLocation = "//div[@class='address-1']";

	public String CheckstylistNameText = "//div[@id='checkin_stylist']";

	public String checkInDateCheck = "//ion-label[@id='checkin_date']";	

	public String checkService = "//div[@id='checkin_service']";

	public String checkGuestName = "//div[@id='checkin_guest_name']";

	public String dateTime = "//ion-row[@class='date-time md ']";

	public String checkSecondService = "//div[@id='checkin_service']";

	public String confirmCheckInButton = "//ion-label[@id='check-in-confirm']";


	public WebElement waitForClickable(By locator) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));  
		return wait.until(ExpectedConditions.elementToBeClickable(locator)); 
	}

	public WebElement waitForVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));  
		return wait.until(ExpectedConditions.elementToBeClickable(locator)); 
	}

	public void clearInputField(By locator) {
		WebElement element = driver.findElement(locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();
	}

	public void scrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Clicks on the header link specified by the given link.
	 *
	 * @param link The link identifier or text to click on.
	 */
	public void clickOnHeaderLink(String link) {
		String xpath = locationLink.replace("%s", link);
		waitForClickable(By.xpath(xpath)).click();    
	}

	/**
	 * Enters the specified input value into the search field, clears it first if needed,
	 * and submits the search query.
	 *
	 * @param inputValue The value to be entered into the search field.
	 */
	public void enterSearchValue(String inputValue) {
		waitForVisibility(By.xpath(findLocationNearYouTextBox)).click();
		clearInputField(By.xpath(findLocationNearYouTextBox));
		WebElement searchValueInput = waitForClickable(By.xpath(findLocationNearYouTextBox));
		Actions act = new Actions(driver);
		act.moveToElement(searchValueInput).sendKeys(inputValue).build().perform();
		waitForVisibility(By.xpath(searchButton)).click();
	}

	/**
	 * Selects the specified location and performs check-in.
	 *
	 * @param location The location to be selected.
	 * @param checkIn  The type of check-in button to click.
	 */
	public String selectLocation(String location) {
		String xpath = locationText.replace("%s", location);
		scrollToElement(By.xpath(xpath));
		driver.findElement(By.xpath(xpath)).click();
		return location.toLowerCase();
	}


	/**
	 * Adds a check-in for a service.
	 * This method performs the following actions:
	 * 1. Clicks on the add button to select a service.
	 * 2. Clicks on the "Add Another Service" button.
	 * 3. Scrolls to the "Add-On" label.
	 * 4. Clicks on the "Add-On Service Time Add" button.
	 *
	 * @param serviceName The name of the service to be added for check-in.
	 */
	public String addCheck(String serviceName) {    
		String xpath = addService.replace("%s", serviceName);
		WebElement element = driver.findElement(By.xpath(xpath));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
//		scrollToElement(By.xpath(xpath));
		WebElement addCheckIn = waitForClickable(By.xpath(xpath));
		addCheckIn.click();
		System.out.println(serviceName);
		return serviceName;
	}

	public void ClickOnAddAnotherServiceButton() {	
		WebElement anotherCheckIn =waitForVisibility(By.xpath(addAnotherServiceButton));
		anotherCheckIn.click();		
	}
	/**
	 * Selects the stylist with the specified name.
	 * This method performs the following actions:
	 * 1. Scrolls to the element containing the stylist label.
	 * 2. Clicks on the stylist with the specified name.
	 * 3. Prints the selected stylist's first name.
	 *
	 * @param name The name of the stylist to select.
	 * @return 
	 */
	public String selectStylist(String name) {
		scrollToElement(By.xpath(stylistLabel));
		String xpath = stylistName.replace("%s", name);
		waitForClickable(By.xpath(xpath)).click();
		System.out.println("Stylist Name: " + name.toLowerCase());
		return name;
	}
	/**
	 * Selects the estimated check-in time.
	 * This method performs the following actions:
	 * 1. Scrolls to the element containing the estimated check-in time label.
	 * 2. Clicks on the estimated check-in time specified.
	 * 3. Prints the selected check-in time.
	 *
	 * @param time The estimated check-in time to select.
	 */
	public String selectEstimateCheckInTime(String time) {
		scrollToElement(By.xpath(estimatedCheckInTimeLabel));
		String xpath = estimatedCheckInTime.replace("%s", time);
		WebElement selectTime = waitForClickable(By.xpath(xpath));
		selectTime.click();
		System.out.println("Selected Time: " + time);
		return time;
	}
	/**
	 * Enters the provided input value into the first name input field.
	 * This method performs the following actions:
	 * 1. Clicks on the first name input field to activate it.
	 * 2. Enters the provided input value into the first name input field.
	 *
	 * @param inputValue The value to be entered into the first name input field.
	 */
	public void firstnameInputField(String inputValue) {
		waitForClickable(By.xpath(firstNameTextInput)).click();
		WebElement firstname = waitForClickable(By.xpath(firstNameTextInput));
		firstname.sendKeys(inputValue);
	}
	/**
	 * Enters the provided input value into the last name input field.
	 * This method performs the following actions:
	 * 1. Clicks on the last name input field to activate it.
	 * 2. Enters the provided input value into the last name input field.
	 *
	 * @param inputValue The value to be entered into the last name input field.
	 */
	public void lastnameInputField(String inputValue) {
		waitForClickable(By.xpath(lastNameTextInput)).click();
		WebElement lastname = waitForClickable(By.xpath(lastNameTextInput));
		lastname.sendKeys(inputValue);
	}
	/**
	 * Enters the provided input value into the phone number input field.
	 * This method performs the following actions:
	 * 1. Clicks on the phone number input field to activate it.
	 * 2. Enters the provided input value into the phone number input field.
	 *
	 * @param inputValue The value to be entered into the phone number input field.
	 */
	public void phoneNumberInputField(String inputValue) {
		waitForClickable(By.xpath(phoneNumberTextInput)).click();
		WebElement phoneNumber = waitForClickable(By.xpath(phoneNumberTextInput));
		phoneNumber.sendKeys(inputValue);
	}
	/**
	 * Enters the provided input value into the email input field.
	 * This method performs the following actions:
	 * 1. Clicks on the email input field to activate it.
	 * 2. Enters the provided input value into the email input field.
	 *
	 * @param inputValue The value to be entered into the email input field.
	 */
	public void emailInputField(String inputValue) {
		waitForClickable(By.xpath(emailTextInput)).click();
		WebElement email = waitForClickable(By.xpath(emailTextInput));
		email.sendKeys(inputValue);
	}
	/**
	 * Clicks on the check-in button.
	 * This method performs the following actions:
	 * 1. Scrolls to the element containing the check-in button.
	 * 2. Clicks on the check-in button.
	 */

	public void clickOnCheckInButton() {
		scrollToElement(By.xpath(confirmCheckInButton));
		WebElement checkInButton = waitForClickable(By.xpath(confirmCheckInButton));
		checkInButton.click();
	}

	//	public void clickOnNextGuestButton() {
	//		scrollToElement(By.xpath(nextGuestButton));
	//		WebElement guestButton = waitForClickable(By.xpath(nextGuestButton));
	//		guestButton.click();
	//		scrollToElement(By.xpath(dateTime));
	//	}

	/**
	 * Retrieves the actual check-in time.
	 *
	 * @return The actual check-in time as a string.
	 */
	public String getActualTime() {
		String actualCheckInTime = driver.findElement(By.xpath(checkInTime)).getText().trim();
		return actualCheckInTime;
	}

	/**
	 * Retrieves the actual address from a pre-defined location.
	 *
	 * @return The extracted location as a lowercase string.
	 */
	public String getActualAddress() {
		String actualLocation = driver.findElement(By.xpath(checkLocation)).getText().trim();
		String[] parts = actualLocation.split(" ");
		String extractedLocation = parts[1];
		return extractedLocation.toLowerCase();
	}

	/**
	 * Retrieves the stylist name from a pre-defined location.
	 *
	 * @return The extracted stylist name as a lowercase string.
	 */
	public String getStylistName() {
		String actualStylist = driver.findElement(By.xpath(CheckstylistNameText)).getText().trim();
		actualStylist = actualStylist.replace("Stylist: ", "").trim();
		return actualStylist.toLowerCase();
	}

	/**
	 * Retrieves the full name from a pre-defined location.
	 *
	 * @return The extracted full name as a lowercase string.
	 */
	public String getFullName() {
		String actualFullName = driver.findElement(By.xpath(checkGuestName)).getText().trim();
		return actualFullName.toLowerCase();
	}

	/**
	 * Retrieves the service information from a pre-defined location.
	 *
	 * @return The extracted service information as a lowercase string.
	 */
	public String getService() {
		String actualService = driver.findElement(By.xpath(checkService)).getText().trim();
		return actualService.toLowerCase();
	}


}


