package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Date;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public static void waitForLoadPage(long timeout, WebDriver driver){

        boolean state = false;
        long startTime = new Date().getTime();

        while (state){
            state = stateChecker(driver);
            if (new Date().getTime() >= startTime + timeout) new Throwable("page timeout");
        }
    }

    public static boolean stateChecker(WebDriver driver){
        Boolean readyStateComplete = false;
        while (!readyStateComplete)
        {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
            readyStateComplete = (String) executor.executeScript("return document.readyState") == "complete";
        }
        return readyStateComplete;
    }
}
