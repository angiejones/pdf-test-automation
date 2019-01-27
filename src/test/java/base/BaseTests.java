package base;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class BaseTests {

    protected static WebDriver driver;
    protected static EyesManager eyesManager;

    @BeforeClass
    public static void setUp() {
        Properties props = System.getProperties();
        try {
            props.load(new FileInputStream(new File("resources/test.properties")));
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        driver = new ChromeDriver();
        driver.get(System.getProperty("sites.invoices.url"));
        eyesManager = new EyesManager(driver, "Invoice Simple");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        eyesManager.abort();
    }

}