package udemy.tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.udemy.pages.flightreservation.*;
import udemy.tests.BaseTest;
import udemy.tests.flightreservation.model.FlightReservationTestData;
import udemy.util.Config;
import udemy.util.Constants;
import udemy.util.JsonUtil;

public class FlightReservationTest extends BaseTest {
    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }


    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.firstname(), testData.lastname());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterUserAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.clickRegister();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());

        registrationConfirmationPage.clickGoToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void searchFlightTest(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());

        flightSearchPage.selectPassengers(testData.passengersCount());
        flightSearchPage.clickSearchFlightsButton();
    }

    @Test(dependsOnMethods = "searchFlightTest")
    public void flightSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());

        flightsSelectionPage.selectFlights();
        flightsSelectionPage.clickConfirmFlightsButton();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightsConfirmationPage flightsConfirmationPage = new FlightsConfirmationPage(driver);
        Assert.assertTrue(flightsConfirmationPage.isAt());

        Assert.assertEquals( flightsConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
