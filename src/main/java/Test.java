import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);



        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

      /*  String url = driver.findElement(By.cssSelector("a[href*='soapui']")).getAttribute("href");
        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();

        connection.setRequestMethod("HEAD");
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode); */
                                                            // <-- for 1 link


        //  1. Get all URLs tied up to the links

        //  2. Java methods that will call URL's and gets you the status code

        //  3. If status code >400 then that url is not working -> the link is broken


        List<WebElement> links = driver.findElements(By.xpath("//div[@id='gf-BIG']/table/tbody/tr/td/ul/li/a"));
        SoftAssert softAssert = new SoftAssert();
        for (WebElement link: links)
        {
            String url = link.getAttribute("href");

            HttpURLConnection connection1 = (HttpURLConnection)new URL(url).openConnection();
            connection1.setRequestMethod("HEAD");
            connection1.connect();
            int responseCode = connection1.getResponseCode();
            System.out.println(responseCode);
            softAssert.assertTrue(responseCode<400, "The link with Text: " + link.getText() + " is broken!" + " The response code is: "+responseCode);
        }
        softAssert.assertAll();
    }
}
