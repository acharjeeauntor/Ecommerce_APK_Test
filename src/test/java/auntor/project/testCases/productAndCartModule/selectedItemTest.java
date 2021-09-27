package auntor.project.testCases.productAndCartModule;


/*
Test case:
1. Validate if the items selected in the page 2 are matching with items displayed in check out page

Steps:
1. Enter to the app by fillup all info (Country name,Your name,Gender)
2. Add to cart any product using scrolling
3. Go to cart option and validate selected items are match or not

 */

import auntor.project.pageObjects.CartPage;
import auntor.project.pageObjects.ProductListPage;
import auntor.project.testCases.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class productAndCartModuleTest extends BaseClass {
    ProductListPage productListPage;
    CartPage cartPage;

    @Test
    public void matchedSelectedItem() throws InterruptedException, IOException {
        productListPage = new ProductListPage(driver);
        cartPage = new CartPage(driver);
        productListPage.addAProductInCart();
        String productName = cartPage.getSelectedProductName();
        if(productName.equals("PG 3")){
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }else{
            logger.warn("Test Failed");
            captureScreen("matchedSelectedItem");
            Assert.assertTrue(false);
        }
    }





}
