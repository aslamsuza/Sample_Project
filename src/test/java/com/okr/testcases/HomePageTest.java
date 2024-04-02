package com.okr.testcases;

import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.okr.pages.BaseClass;
import com.okr.pages.HomePageObjects;

public class HomePageTest extends BaseClass{

    private static final Logger logger = LogManager.getLogger(HomePageTest.class);

//    @Test(priority=1)
    public void testHomePage() throws InterruptedException {
        HomePageObjects homePage = new HomePageObjects(driver);
        homePage.clickContactUs();
        logger.info("Clicked on 'Contact Us' link in the home page.");
    }
}
