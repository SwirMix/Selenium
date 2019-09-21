package utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Utils {

    public static boolean Check(String expected, String actual){
        boolean status = actual.equals(expected);
        return status;
    }

    public static void saveFailArtefacts(WebDriver driver, String test_name, Logger logger) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        BufferedImage image = ImageIO.read(scrFile);
        String fail_screenshot_path = "artefacts/" + test_name + "_" + new Date().getTime() + ".png";
        ImageIO.write(image, "png", new File(fail_screenshot_path));

        logger.error("Failed screenshot: " + fail_screenshot_path);
        String stored_report = driver.getPageSource();

        String html_rep = test_name + "_" + new Date().getTime() + ".html";
        File f = new File("artefacts/" + html_rep);
        FileWriter writer = new FileWriter(f,true);
        writer.write(stored_report);
        logger.error("Failed assert: " + fail_screenshot_path + " and his html: " + html_rep);
        writer.close();
    }

    public static void assertTwoStringParams(String expected, String actual, WebDriver driver, String test_name, Logger logger) throws IOException {
        if(!Check(expected, actual)){
            System.out.println();
            logger.error("Failed Assertion, expected: " + expected + " but actual: " + actual);
            logger.error("Assert: " + test_name);
            saveFailArtefacts(driver, test_name, logger);
        };//check href
        Assert.assertEquals(expected, actual);
    }
}
