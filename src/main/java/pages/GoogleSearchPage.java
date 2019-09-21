package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Date;

public class GoogleSearchPage extends BasePage{
    public String url = "https://www.google.com/";

    private String search_input_locator = "//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input";//xpath
    private String search_button_locator = "btnK";//name

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        driver.get(this.url);
        waitForLoadPage(9000, driver);
    }

    public void inputInSearchField(String query){
        WebElement searchField = driver.findElement(By.xpath(this.search_input_locator));
        searchField.sendKeys(query);
    }
    public void clickOnSearchBtn(){
        WebElement search_run = this.driver.findElement(By.name(this.search_button_locator));
        search_run.click();
    }
}
