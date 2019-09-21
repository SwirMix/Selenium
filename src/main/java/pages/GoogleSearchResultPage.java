package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearchResultPage extends BasePage{
    private String search_result_container_locator = "search";//id
    private String result_item_locator = "bkWMgd";

    public GoogleSearchResultPage(WebDriver driver) {
        super(driver);
        super.waitForLoadPage(5000, driver);
    }

    public List<WebElement> getSearchResult(){
        WebElement result_container = this.driver.findElement(By.id(this.search_result_container_locator));
        List<WebElement> elements = result_container.findElements(By.className(this.result_item_locator));
        return elements;
    }
}
