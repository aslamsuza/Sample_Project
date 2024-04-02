package com.okr.testcases;

import static org.testng.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import com.okr.pages.ContactUsPageObjects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ContactUsPageTest extends HomePageTest {

    private static final Logger logger = LogManager.getLogger(ContactUsPageTest.class);

//    @Test(priority=2)
    public void testContactUsPage() throws org.json.simple.parser.ParseException, InterruptedException {
        ExtentTest test = report.createTest("Contact Us Page Test");

//        HomePageObjects homePage = new HomePageObjects(driver);
//        homePage.clickContactUs();
//        test.log(Status.INFO, "Clicked on 'Contact Us' link in the home page.");

        ContactUsPageObjects contactUsPage = new ContactUsPageObjects(driver);

        JSONObject testData = parseTestDataFromJson("C:\\Aslam\\Roosters\\Config\\dummy.json");
        test.log(Status.INFO, "Test data parsed successfully.");

        String dropDownOption = (String) testData.get("dropDownOption");
        contactUsPage.selectOptionFromDropdown(dropDownOption);
        test.log(Status.INFO, "Dropdown option selected: " + dropDownOption);

        String searchValue = (String) testData.get("searchValue");
        contactUsPage.enterLocationAndSelectAddress(searchValue);
        test.log(Status.INFO, "Location entered and address selected: " + searchValue);

        String date = (String) testData.get("date");
        contactUsPage.selectDateDropDown(date);
        test.log(Status.INFO, "Date selected: " + date);

        JSONObject incorrectData = (JSONObject) testData.get("incorrectData");

        contactUsPage.ticketNumber((String) incorrectData.get("ticketNumber"));
        String errorMessage = contactUsPage.getErrorMessage();
        test.log(Status.INFO, "Ticket number entered incorrectly. Error message: " + errorMessage);
        assertEquals("Enter a valid number", errorMessage);

        JSONObject correctData = (JSONObject) testData.get("correctData");

        contactUsPage.ticketNumber((String) correctData.get("tickerNumber"));

        contactUsPage.stylistName((String) incorrectData.get("stylistName"));
        errorMessage = contactUsPage.getErrorMessage();
        test.log(Status.INFO, "Stylist name entered incorrectly. Error message: " + errorMessage);
        assertEquals("Enter a valid name. Numbers are not permitted.", errorMessage);

        contactUsPage.stylistName((String) correctData.get("stylistName"));

        contactUsPage.whatWouldYouLikeToTellUs((String) correctData.get("feedBack"));

        contactUsPage.FirstName((String) incorrectData.get("firstName"));
        errorMessage = contactUsPage.getErrorMessage();
        test.log(Status.INFO, "First name entered incorrectly. Error message: " + errorMessage);
        assertEquals("Enter a valid name. Numbers are not permitted.", errorMessage);

        contactUsPage.FirstName((String) correctData.get("firstName"));

        contactUsPage.LastName((String) incorrectData.get("lastName"));
        errorMessage = contactUsPage.getErrorMessage();
        test.log(Status.INFO, "Last name entered incorrectly. Error message: " + errorMessage);
        assertEquals("Enter a valid name. Numbers are not permitted.", errorMessage);

        contactUsPage.LastName((String) correctData.get("lastName"));

        contactUsPage.phoneNumber((String) correctData.get("phoneNumber"));

        contactUsPage.email((String) incorrectData.get("email"));
        errorMessage = contactUsPage.getErrorMessage();
        test.log(Status.INFO, "Email entered incorrectly. Error message: " + errorMessage);
        assertEquals("Enter a valid email", errorMessage);

        contactUsPage.email((String) correctData.get("email"));

        contactUsPage.optionalAddress((String) correctData.get("addressInput"));
        contactUsPage.optionalCity((String) correctData.get("cityInput"));
        contactUsPage.optionalZipCode((String) correctData.get("zipCodeInput"));
        contactUsPage.countryDropDown((String) correctData.get("countryDropDownOption"));
        contactUsPage.stateDropDown((String) correctData.get("stateDropDownOption"));

        // Log the information from Reporter.log into the Extent report
        test.info("Test Completed >>> Report Generated");

        report.flush();
    }

    public JSONObject parseTestDataFromJson(String filePath) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject testData = null;
        try {
            // Parse JSON file
            Object obj = parser.parse(new FileReader(filePath));
            testData = (JSONObject) obj;
            logger.info("JSON file parsed successfully.");
        } catch (IOException e) {
            logger.error("Error occurred while parsing JSON file: " + e.getMessage());
            e.printStackTrace();
        }
        return testData;
    }
}
