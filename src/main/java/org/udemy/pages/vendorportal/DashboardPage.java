package org.udemy.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.pages.BasePage;

public class DashboardPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultsCountElement;

    @FindBy(css = "img.img-profile")
    private WebElement userProfilePictureElement;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        return searchInput.isDisplayed();
    }

    public String getMonthlyEarning(){
       return monthlyEarningElement.getText();
    }

    public String getAnnualEarning(){
        return annualEarningElement.getText();
    }

    public String getProfitMargin(){
        return profitMarginElement.getText();
    }

    public String getAvailableInventory(){
        return availableInventoryElement.getText();
    }

    public void searchOrderHistoryBy(String keyword) {
        searchInput.sendKeys(keyword);
    }

    public int getSearchResultCount() {
        String resultText = searchResultsCountElement.getText();
        String [] arr = resultText.split(" ");
        int resultCount = Integer.parseInt(arr[5]);
        logger.info("Result count is {}", resultCount);
        return resultCount;
    }

    public void logout() {
       userProfilePictureElement.click();
       wait.until(ExpectedConditions.visibilityOf(logoutLink));
       logoutLink.click();
       wait.until(ExpectedConditions.visibilityOf(modalLogoutButton));
       modalLogoutButton.click();
    }
}
