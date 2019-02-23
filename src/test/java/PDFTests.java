import base.BaseTests;
import base.EyesManager;
import org.junit.Assert;
import org.junit.Test;
import pages.InvoiceGeneratorPage;
import pages.InvoicePreviewPage;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class PDFTests extends BaseTests {

    @Test
    public void testInvoices() {
        
        String invoiceNumber = "INV12345";

        InvoiceGeneratorPage generatorPage = new InvoiceGeneratorPage(driver);
        generatorPage.setInvoiceTitle("Invoice from your Personal Stylist");
        generatorPage.setLogo(new File("resources/logo.png"));
        generatorPage.selectColor(4);

        generatorPage.setFromName("Marie Combs");
        generatorPage.setFromEmail("mcombs@example.com");
        generatorPage.setFromAddress("123 Main Street");
        generatorPage.setFromCityState("New York, NY");
        generatorPage.setFromZipCode("12345");
        generatorPage.setFromPhone("(555) 555 5555");

        generatorPage.setToName("John Brown");
        generatorPage.setToEmail("john@example.com");
        generatorPage.setToAddress("456 Main Street");

        generatorPage.setInvoiceNumber(invoiceNumber);

        generatorPage.setItemDescription(1, "Dress");
        generatorPage.setItemPrice(1, "54.99");
        generatorPage.setItemAdditionalDetails(1, "Floral print maxi dress");
        generatorPage.clickToAddNewItem();

        generatorPage.setItemDescription(2, "Leggings");
        generatorPage.setItemPrice(2, "14.99");
        generatorPage.setItemAdditionalDetails(2, "pink sheer leggings");
        generatorPage.clickToAddNewItem();

        generatorPage.setItemDescription(3, "Shoes");
        generatorPage.setItemPrice(3, "29.99");
        generatorPage.setItemAdditionalDetails(3, "yellow mary jane heels");

        InvoicePreviewPage previewPage = generatorPage.clickGetLink();
        eyesManager.validateWindow();
        previewPage.clickPDFButton();

        //TODO: change the pathname to your own
        File downloadedPDF = new File("/Users/angie/Downloads/" + invoiceNumber + ".pdf");
        String destination = "resources/Invoice_PDFs/" + invoiceNumber + ".pdf";
        Assert.assertTrue(invoiceNumber + ".pdf file was not moved to test location",
                FileUtils.moveFile(downloadedPDF, destination));

        try{
            Assert.assertTrue("Error validating PDF", EyesManager.validatePDF(destination));
        }
        catch (InterruptedException|IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
