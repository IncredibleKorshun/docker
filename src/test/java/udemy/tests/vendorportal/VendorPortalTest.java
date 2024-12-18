package udemy.tests.vendorportal;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.udemy.pages.vendorportal.DashboardPage;
import org.udemy.pages.vendorportal.LoginPage;
import udemy.tests.BaseTest;
import udemy.tests.vendorportal.model.VendorPortalTestData;
import udemy.util.Config;
import udemy.util.Constants;
import udemy.util.JsonUtil;

public class VendorPortalTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());

        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultCount(), Integer.parseInt(testData.searchResultCount()));

        dashboardPage.logout();
    }
}
