package org.udemy.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.udemy.pages.BasePage;

public class FlightSearchPage extends BasePage {

    @FindBy(id = "passengers")
    private WebElement passengersSelect;

    @FindBy(id = "depart-from")
    private WebElement departFromSelect;

    @FindBy(id = "arrive-in")
    private WebElement arriveInSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersSelect));
        return passengersSelect.isDisplayed();
    }

    public void selectPassengers(String numberOfPassengers){
        Select passengers = new Select(this.passengersSelect);
        passengers.selectByValue(numberOfPassengers);
    }

    public void clickSearchFlightsButton(){
        this.searchFlightsButton.click();
    }
}
