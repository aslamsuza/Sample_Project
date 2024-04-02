package com.okr.pages;

import java.time.Duration;
import java.time.Month;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPageObjects extends BaseClass {
	
	 public ContactUsPageObjects(WebDriver driver) {
	        BaseClass.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	
	public WebDriverWait wait;
	public JavascriptExecutor jsExecutor;
	public Actions actions;

	
	// Dropdown to choose the topic
	public String chooseTopicDropdown = "//ion-select[@name='choose_topic']/parent::ion-item"; //choose_topic

	// Option to give feedback about an experience in the dropdown
	public String selectOptionInChooseTopicDropDown = "//ion-label[text()='%s']//parent::ion-item"; //Give feedback about an experience

	// Label for selecting the visited salon
	public String whichSalonDidYouVisitLabel = "//p[contains(text(), 'Which salon did you visit?')]";

	// Text input for entering zipcode, city, or state
	public String zipcodeCityorStateTextInput = "//ion-input[@id='search_input']/parent::ion-item"; //

	// Button for initiating the search
	public String searchButton = "//ion-button[@id='search_btn']"; 

	// Address of Linden Square
	public String address = "//ion-item[@id='the_shoppes_at_powers_ferry_item']"; //ion-item[@id='the_shoppes_at_powers_ferry_item']

	// Text input for entering the date of service
	public String dateOfServiceTextInput = "//ion-item[@id='open-calendar']";

	// Label for ticket number
	public String ticketNumberLabel = "//p[text()='Ticket number']";

	// Text input for entering the ticket number
	public String ticketNumberTextInput = "//ion-input[@id='ticket-number']//parent::ion-item";

	// Error message for validation
	public String validationErrorMessage = "//ion-text[@color='danger']";

	// Text input for entering stylist's name
	public String stylistNameTextInput = "//ion-input[@id='stylist-name']//parent::ion-item";

	// Text input for providing feedback
	public String whatWouldYouLikeToTellUsTextInput = "//ion-textarea[@name='feedbackMessage']//parent::ion-item";

	// Label for first name
	public String firstNameTextLabel = "//p[text()='First name']//parent::ion-row";

	// Text input for entering first name
	public String firstNameTextInput = "//ion-input[@id='first-name']//parent::ion-item";

	// Text input for entering last name
	public String lastNameTextInput = "//ion-input[@id='last-name']//parent::ion-item";

	// Text input for entering phone number
	public String phoneNumberTextInput = "//input[@id='phone']//parent::ion-item";

	// Text input for entering email
	public String emailTextInput = "//ion-input[@id='email']//parent::ion-item";

	// Label for address
	public String addressTextLabel = "//p[text()='Address']";

	// Text input for entering address
	public String addressTextInput = "//ion-input[@id='address']//parent::ion-item";

	// Text input for entering city
	public String cityTextInput = "//ion-input[@id='city']//parent::ion-item";

	// Dropdown for selecting country
	public String ClickOnCountryDropdown = "//ion-select[@name='country']//parent::ion-item";

	// Option for selecting USA in the country dropdown
	public String selectCountry = "//ion-label[text()='%s']//parent::ion-item";

	// Text input for entering zipcode
	public String zipCodeTextInput = "//ion-input[@id='zipcode']//parent::ion-item";

	// Dropdown for selecting state
	public String ClickOnstateDropDown = "//ion-select[@name='state']//parent::ion-item";

	// Option for selecting ALASKA in the state dropdown
	public String selectState = "//ion-label[text()='%s']//parent::ion-item";

	/**
	 * Waits for the element located by the given locator to be clickable.
	 * 
	 * @param locator The locator of the element to wait for.
	 * @return The WebElement that is clickable.
	 */
	public WebElement waitForClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  
		return wait.until(ExpectedConditions.elementToBeClickable(locator)); 
	}

	public WebElement waitForVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  
		return wait.until(ExpectedConditions.elementToBeClickable(locator)); 
	}

	/**
	 * Scrolls to the element located by the given locator.
	 * 
	 * @param locator The locator of the element to scroll to.
	 */
	public void scrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Clears the input field of the given WebElement.
	 * 
	 * @param element The WebElement representing the input field to clear.
	 */
	public void clearInputField(WebElement element) {
		actions = new Actions(driver);
		actions.moveToElement(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();
	}

	/**
	 * Selects the specified option from a dropdown.
	 * 
	 * @param optionToSelect The option to select.
	 */
	public void selectOptionFromDropdown(String optionToSelect) {
		String xpath = selectOptionInChooseTopicDropDown.replace("%s", optionToSelect);
		waitForClickable(By.xpath(chooseTopicDropdown)).click();
		waitForClickable(By.xpath(xpath)).click();
	}

	/**
	 * Enters the provided input value into the location field, performs a search, scrolls to the address, and selects it.
	 * 
	 * @param locationInputValue The value to enter into the location field.
	 */
	public void enterLocationAndSelectAddress(String locationInputValue) {

		waitForVisibility(By.xpath(whichSalonDidYouVisitLabel)).click();
		WebElement locationInput = waitForClickable(By.xpath(zipcodeCityorStateTextInput));
		locationInput.sendKeys(locationInputValue);
		waitForClickable(By.xpath(searchButton)).click();
		scrollToElement(By.xpath(address));
		waitForClickable(By.xpath(address)).click();
	}


	/**
	 * Gets the error message displayed on the page.
	 * 
	 * @return The error message as a String.
	 */
	public String getErrorMessage() {
		WebElement errorMessageElement = waitForClickable(By.xpath(validationErrorMessage));
		String errorMessageText = errorMessageElement.getText();
		System.out.println(errorMessageText);
		return errorMessageText;
	}



	public void selectDateDropDown(String inputDate) {

		// Parse inputDate to get the target month, year, and day
		String[] dateParts = inputDate.split("-");
		int targetDay = Integer.parseInt(dateParts[0]);
		int targetMonth = Integer.parseInt(dateParts[1]);
		int targetYear = Integer.parseInt(dateParts[2]);


		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement dateInput = waitForClickable(By.xpath(dateOfServiceTextInput));
		dateInput.click();

		// Retrieve current month and year text
		String text;
		int currentMonth, currentYear;

		while (true) {
			text = (String) executor.executeScript(
					"return document.querySelector('#ion-react-wrapper > ion-datetime').shadowRoot.querySelector('div.datetime-calendar > div.calendar-header > div.calendar-action-buttons > div.calendar-month-year > ion-item > ion-label').textContent.trim();"
					);
			String[] parts = text.split(" ");
			currentMonth = Month.valueOf(parts[0].toUpperCase()).getValue();
			currentYear = Integer.parseInt(parts[1]);

			// Break the loop if the current date matches the target date
			if (currentMonth == targetMonth && currentYear == targetYear) {
				System.out.println("Reached the target month and year: " + text);
				break;
			}

			// Click the next button to navigate to the next month
			executor.executeScript(
					"document.querySelector('#ion-react-wrapper > ion-datetime').shadowRoot.querySelector('div.datetime-calendar > div.calendar-header > div.calendar-action-buttons > div.calendar-next-prev > ion-buttons > ion-button:nth-child(1)').click();"
					);
		}

		// Click on the target date button
		executor.executeScript(
				"document.querySelector('#ion-react-wrapper > ion-datetime').shadowRoot.querySelectorAll('div.datetime-calendar > div.calendar-body.ion-focusable > div:nth-child(2) > div > button')" +
						".forEach(btn => { if (btn.textContent.trim() === '" + targetDay + "') btn.click(); });"
				);
	}

	/**
	 * Enters the provided ticket number into the ticket number field.
	 * 
	 * @param ticketNumberInputValue The ticket number to enter.
	 */
	public void ticketNumber(String ticketNumberInputValue) {
		waitForVisibility(By.xpath(ticketNumberLabel)); 
		scrollToElement(By.xpath(ticketNumberLabel));
		WebElement ticketNumberInput = waitForClickable(By.xpath(ticketNumberTextInput));
		ticketNumberInput.sendKeys(ticketNumberInputValue);
	}


	/**
	 * Enters the provided stylist name into the stylist name field.
	 * 
	 * @param stylistNameInputValue The stylist name to enter.
	 */
	public void stylistName(String stylistNameInputValue) {
		WebElement stylistNameInput = waitForClickable(By.xpath(stylistNameTextInput));
		stylistNameInput.sendKeys(stylistNameInputValue);
	}

	// Similarly, update parameter names for other methods...

	/**
	 * Enters the provided text into the "What would you like to tell us?" input field, handles error messages, and clears the input field.
	 * 
	 * @param inputValue1 The first text to enter.
	 * @param inputValue2 The second text to enter.
	 */
	public void whatWouldYouLikeToTellUs(String inputValue) {
		WebElement whatWouldYouLikeToTellUsInput = waitForClickable(By.xpath(whatWouldYouLikeToTellUsTextInput));
		whatWouldYouLikeToTellUsInput.sendKeys(inputValue);
	}

	/**
	 * Enters the first name provided, handles error messages, and clears the input field.
	 * 
	 * @param inputValue1 The first name to enter.
	 * @param inputValue2 The second name to enter.
	 */
	public void FirstName(String inputValue) {
		scrollToElement(By.xpath(firstNameTextLabel));
		WebElement firstNameInput = waitForClickable(By.xpath(firstNameTextInput));
		firstNameInput.sendKeys(inputValue);
	}

	/**
	 * Enters the last name provided, handles error messages, and clears the input field.
	 * 
	 * @param inputValue1 The first last name to enter.
	 * @param inputValue2 The second last name to enter.
	 */
	public void LastName(String inputValue) {
		WebElement lastNameInput = waitForClickable(By.xpath(lastNameTextInput));
		lastNameInput.sendKeys(inputValue);
	}

	/**
	 * Enters the phone number provided, handles error messages, and clears the input field.
	 * 
	 * @param inputValue1 The first phone number to enter.
	 * @param inputValue2 The second phone number to enter.
	 */
	public void phoneNumber(String inputValue) {
		WebElement phoneNumberInput = waitForClickable(By.xpath(phoneNumberTextInput));
		phoneNumberInput.sendKeys(inputValue);

	}

	/**
	 * Enters the email address provided, handles error messages, and clears the input field.
	 * 
	 * @param inputValue1 The first email address to enter.
	 * @param inputValue2 The second email address to enter.
	 */
	public void email(String inputValue) {
		WebElement emailInput = waitForClickable(By.xpath(emailTextInput));
		emailInput.sendKeys(inputValue);
	}

	/**
	 * Enters the optional address provided into the address input field.
	 * 
	 * @param inputValue The address to enter.
	 */
	public void optionalAddress(String inputValue) {
		scrollToElement(By.xpath(addressTextLabel));
		WebElement addressInput = waitForClickable(By.xpath(addressTextInput));
		addressInput.sendKeys(inputValue);
	}

	/**
	 * Enters the optional city provided into the city input field.
	 * 
	 * @param inputValue The city to enter.
	 */
	public void optionalCity(String inputValue) {
		WebElement cityInput = waitForClickable(By.xpath(cityTextInput));
		cityInput.sendKeys(inputValue);
	}

	/**
	 * Enters the optional zip code provided into the zip code input field.
	 * 
	 * @param inputValue The zip code to enter.
	 */
	public void optionalZipCode(String inputValue) {
		WebElement zipCodeInput = waitForClickable(By.xpath(zipCodeTextInput));
		zipCodeInput.sendKeys(inputValue);
	}

	/**
	 * Selects the specified country from the country dropdown.
	 * 
	 * @param option The country to select.
	 */
	public void countryDropDown(String option) {
		String xpath = selectCountry.replace("%s", option); 
		waitForClickable(By.xpath(ClickOnCountryDropdown)).click();
		waitForClickable(By.xpath(xpath)).click();
	}

	/**
	 * Selects the specified state from the state dropdown.
	 * 
	 * @param option The state to select.
	 */
	public void stateDropDown(String option) {
		String xpath = selectState.replace("%s", option); 
		waitForClickable(By.xpath(ClickOnstateDropDown)).click();
		waitForClickable(By.xpath(xpath)).click();
	}

}

