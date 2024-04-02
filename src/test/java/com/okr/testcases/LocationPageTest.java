package com.okr.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.okr.pages.LocationPageObjects;


public class LocationPageTest extends ContactUsPageTest {
	String headerLink = "locations";
	String searchValue = "03801"; 
	String location = "WOODBURY";
	String firstService = "senior";
	String secondService = "add_on_service_time";
	String name = "Kate";                  
	String estimatedTime = "3:00 PM";      
	String firstName = "allan";
	String lastName = "walker";
	String phoneNumber = "8005133620";
	String email = "testwoodbury@mailinator.com";


	private static final Logger logger = LogManager.getLogger(LocationPageTest.class);

	@Test(priority=1)
	public void testLocationPage() throws InterruptedException {
		LocationPageObjects locationPage = new LocationPageObjects(driver);

		logger.info("Clicking on 'location' link");
		locationPage.clickOnHeaderLink(headerLink);

		logger.info("Entering search value: " + searchValue + " and clicking on search button");
		locationPage.enterSearchValue(searchValue);

		logger.info("Selecting location: " + location);
		locationPage.selectLocation(location);

		logger.info("Adding check-in");
		locationPage.addCheck(firstService);

		logger.info("click on Add Another Service Button");
		locationPage.ClickOnAddAnotherServiceButton();
		
		logger.info("Adding check-in");
		locationPage.addCheck(secondService);

		logger.info("Selecting stylist: " + name);
		locationPage.selectStylist(name);

		logger.info("Selecting estimated check-in time: " + estimatedTime);
		locationPage.selectEstimateCheckInTime(estimatedTime);

		logger.info("Entering first name: " + firstName);
		locationPage.firstnameInputField(firstName);

		logger.info("Entering last name: " + lastName);
		locationPage.lastnameInputField(lastName);

		logger.info("Entering phone number: " + phoneNumber);
		locationPage.phoneNumberInputField(phoneNumber);

		logger.info("Entering email: " + email);
		locationPage.emailInputField(email);

		logger.info("Clicking on check-in button");
		locationPage.clickOnCheckInButton();

		
		String actualCheckInTime = locationPage.getActualTime();
		Assert.assertEquals(actualCheckInTime, estimatedTime, "Check-in time does not match");
		
		String actualAddress = locationPage.getActualAddress();
		Assert.assertEquals(actualAddress, location.toLowerCase(), "Check-in time does not match");
		
		String actualFullName = (firstName + " " + lastName).toLowerCase().trim(); 
		String expectedFullName = locationPage.getFullName();
		Assert.assertEquals(actualFullName, expectedFullName, "Full name does not match");
			
		String actualService = locationPage.getService();
		Assert.assertEquals(actualService, firstService.toLowerCase(), "Service does not match");
		
		String actualStylist = locationPage.getStylistName();
		Assert.assertEquals(actualStylist, name.toLowerCase(), "Stylist does not match");
		
//		locationPage.clickOnNextGuestButton();
		
		
		
//		
//		String actualFirstService = driver.findElement(By.xpath(locationPage.checkInText)).getText().trim();
//		Assert.assertEquals(actualFirstService.toLowerCase(), firstService.toLowerCase(), "Service does not match");
//		
//		
//		String stylistExpectedName = name.replace("S", "").toLowerCase().trim();
//		String actualStylist = driver.findElement(By.xpath(locationPage.CheckstylistNameText)).getText().trim();
//		actualStylist = actualStylist.replace("Stylist: ", "");
//		actualStylist = actualStylist.toLowerCase().trim();
//		Assert.assertEquals(actualStylist, stylistExpectedName, "Stylist does not match");
//		
//		String expectedFullName = (firstName + " " + lastName).toLowerCase().trim(); 
//		String actualFullName = driver.findElement(By.xpath(locationPage.checkGuestName)).getText().trim();
//		Assert.assertEquals(actualFullName, expectedFullName, "Full name does not match");
//		
//		 locationPage.clickOnNextGuestButton();
//		 
//		 
//		 String actualSecondService = secondService.replace("_", " ");
//		 
//		 String expectedSecondService = driver.findElement(By.xpath(locationPage.checkSecondService)).getText().trim().toLowerCase();
//		 Assert.assertEquals(actualSecondService, expectedSecondService, "Service does not match");
	 

		


		


	}


}

