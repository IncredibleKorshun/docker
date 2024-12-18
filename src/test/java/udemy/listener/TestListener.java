package udemy.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import udemy.util.Constants;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult testResult){
        TakesScreenshot driver = (TakesScreenshot) testResult.getTestContext().getAttribute(Constants.DRIVER);
        String screenshot = driver.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
        String htmlImage = String.format(htmlImageFormat, screenshot);
        Reporter.log(htmlImage);
    }
}
