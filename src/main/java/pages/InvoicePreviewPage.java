package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;


public class InvoicePreviewPage {

    private WebDriver driver;
    WebDriverWait wait;

    private By pdfButton = By.xpath("//span[text()='PDF']/parent::button");

    public InvoicePreviewPage(WebDriver driver, String windowTitle) {
        this.driver = driver;

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for(String window: driver.getWindowHandles()){
            driver.switchTo().window(window);
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pdfButton));
    }

    public void clickPDFButton(){
        driver.findElement(pdfButton).click();

        //Wait for download to finish before exiting this method
        File file = new File("/Users/angie/Downloads/INV12345.pdf");
        while (!file.exists()) {
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
