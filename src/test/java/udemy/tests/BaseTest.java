package udemy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import udemy.listener.TestListener;
import udemy.util.Config;
import udemy.util.Constants;

import java.net.URL;

@Listeners({TestListener.class})
public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
//    @Parameters({"browser"})
    public void setDriver(ITestContext context){
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        context.setAttribute(Constants.DRIVER, driver);
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @SneakyThrows
    private WebDriver getRemoteDriver() {
        Capabilities capabilities= new ChromeOptions();

        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);

        return new RemoteWebDriver(new URL(String.format(urlFormat, hubHost)), capabilities);
    }

    @AfterTest
    public void tearDown(){
        this.driver.quit();
    }
}
