package framework;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Leonela on 3/13/2018.
 */
/*given url https://www.hotels.com/
when select hotels deal
then i select destination
then select checkin and add 6 nights for check out date
then select number of room
then select amount adults from dropdowm menu
then select how many kids and ages from dropdown manu
and click on search button
 */
public class TA4 {

    public static void main(String arg[]) {


        System.setProperty("webdriver.chrome.driver", "C:/Users/Leonela/Desktop/Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.hotels.com/");

        String textToSelect = "Minnesota City";


        driver.findElement(By.xpath("//*[@id=\"managed-overlay\"]/button")).click();
        driver.findElement(By.id("hdr-deals")).click();
        driver.findElement(By.xpath("//*[@id=\"qf-1q-destination\"]")).sendKeys("Minnesota City");
        driver.findElement(By.name("q-destination")).sendKeys(Keys.ENTER);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1); // to get tomorrows day
        Date tomorrow = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        String day = sdf.format(tomorrow.getTime());
        Calendar chkout = Calendar.getInstance();
        chkout.add(Calendar.DAY_OF_YEAR, 6);
        Date plus6days = chkout.getTime();
        String sixNights = sdf.format(plus6days.getTime());

        driver.findElement(By.className("widget-query-date")).click();

        WebElement dateWidget = driver.findElement(By.className("widget-daterange-cont"));
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

        for (WebElement cell : columns) {
            String days = cell.getText();
            //Select tomorrow Date
            if (days.equals(day)) {
                cell.click();

            }
        }
        driver.findElement(By.id("q-localised-check-out"));
        WebElement dateWidget1 = driver.findElement(By.className("widget-daterange-cont"));
        List<WebElement> checkout = dateWidget.findElements(By.tagName("td"));

        for (WebElement dayout : checkout) {
            String days = dayout.getText();
            //Select tomorrow Date
            if (days.equals(sixNights)) {
                dayout.click();

            }

            Select selectrooms = new Select(driver.findElement(By.id("q-rooms")));
            selectrooms.selectByIndex(0);
            Select selectadults = new Select(driver.findElement(By.id("q-room-0-adults")));
            selectadults.selectByIndex(1);
            Select selectchildrens = new Select(driver.findElement(By.id("q-room-0-children")));
            selectchildrens.selectByIndex(2);
            Select kidAge1 = new Select(driver.findElement(By.id("q-room-0-child-0-age")));
            kidAge1.selectByIndex(3);
            Select kidAge2 = new Select(driver.findElement(By.id("q-room-0-child-1-age")));
            kidAge2.selectByIndex(5);
            driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Search')]")).click();


            driver.quit();

        }
    }
}
