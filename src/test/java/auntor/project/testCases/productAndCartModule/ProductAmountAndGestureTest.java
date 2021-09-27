package auntor.project.testCases.productAndCartModule;
/*
Test case:
1. Validate the total amount displayed in the checkout page matches with sum of product amounts selected for shopping

Steps:
1. Enter to the app by fillup all info (Country name,Your name,Gender)
2. Add to cart any product using scrolling
3. Go to cart option and validate total Amount is right or wrong
 */

import auntor.project.pageObjects.CartPage;
import auntor.project.pageObjects.ProductListPage;
import auntor.project.testCases.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductAmountTest extends BaseClass {
    ProductListPage productListPage;
    CartPage cartPage;

    @Test
    public void productAmountTest() throws InterruptedException, IOException {
        productListPage=new ProductListPage(driver);
        cartPage = new CartPage(driver);
        productListPage.selectMultipleProductInCart();
       Double sumOfProduct = cartPage.sumOfProduct();
        Double totalAmount = cartPage.getTotalAmount();
        if(sumOfProduct.equals(totalAmount)){
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }else{
            logger.warn("Test Failed");
            captureScreen("productAmountTest");
            Assert.assertTrue(false);
        }

    }


}
