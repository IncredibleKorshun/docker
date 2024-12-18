package org.udemy.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.pages.BasePage;

public class FlightsConfirmationPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FlightsConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightsConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

    public FlightsConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(flightsConfirmationElement));
        return flightsConfirmationElement.isDisplayed();
    }

    public String getPrice() {
        String totalPrice = totalPriceElement.getText();
        logger.info("Total price is: {}", totalPrice);
        return totalPrice;
    }

}
