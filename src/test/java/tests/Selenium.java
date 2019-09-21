package tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GoogleSearchPage;
import pages.GoogleSearchResultPage;

import java.io.IOException;
import java.util.List;

import static pages.BasePage.waitForLoadPage;
import static utils.Utils.assertTwoStringParams;

public class Selenium {

    private WebDriver driver;
    private GoogleSearchPage google;
    private GoogleSearchResultPage searchResults;
    private String testName = "Search_yandex_test";

    final static Logger logger = Logger.getLogger(Selenium.class);

    @Before
    public void init(){
        this.driver = new ChromeDriver();
        logger.debug("ChromeDriver is running");
        this.driver.manage().window().fullscreen();
        logger.debug("set fullsize of window");
    }

    @Test
    public void runTest() throws InterruptedException, IOException {
        this.Script();
    }

    private void Script() throws IOException {

        google = new GoogleSearchPage(driver);
        logger.debug("created GoogleSearchPage - SUCCESS");
        google.goToPage();//load google url
        logger.debug("loaded google(https://google.com) - SUCCESS");
        google.inputInSearchField("yandex.ru");//setText into search input
        logger.debug("sendKeys: yandex.com");
        google.clickOnSearchBtn();//send post to google with query
        logger.debug("go to result of search");

        searchResults = new GoogleSearchResultPage(driver);//create search result page
        List<WebElement> result_items = searchResults.getSearchResult();//get container with result without promo

        WebElement first_result = result_items.get(0);//get first result
        WebElement first_result_href = first_result.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div/div[1]/a"));

        String href = first_result_href.getAttribute("href");//get his href
        logger.info("href on first link: " + href);
        assertTwoStringParams("https://yandex.ru/", href, driver, this.testName + "_failed_assert_href", logger);

        logger.debug("click on first link");
        first_result_href.click();//go to url by link
        waitForLoadPage(4000, driver);//wait full page

        String current_url = driver.getCurrentUrl();//check current url
        assertTwoStringParams("just_test_of_artefacts_Grubber", current_url, driver, this.testName + "_failed_assert_current_url", logger);
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
