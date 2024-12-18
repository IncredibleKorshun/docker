package org.udemy.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.udemy.pages.BasePage;

public class RegistrationConfirmationPage extends BasePage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightSearchButton;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
        return goToFlightSearchButton.isDisplayed();
    }

    public void clickGoToFlightSearch() {
        this.goToFlightSearchButton.click();
    }
}
