package auntor.project.testCases.productAndCartModule;
/*
Test case:
1. Validate the total amount displayed in the checkout page matches with sum of product amounts selected for shopping

Steps:
1. Enter to the app by fillup all info (Country name,Your name,Gender)
2. Add to cart any product using scrolling
3. Go to cart option and validate total Amount is right or wrong
 */

/*
Test case:
1. Validate mobile gestures working for select Checkbox by tap , links(Long press)and navigate to WebView
2. Verify if user do operations on web view and can navigate back to Native App if needed

Steps:
1. Enter to the app by fillup all info (Country name,Your name,Gender)
2. Add to cart any product using scrolling
3. Go to cart option and validate total Amount is right or wrong
4. Check the checkbox and long press to the terms and condition option and cancel the terms and condition popup
5. Click on visit to the website..... option and go to chrome driver in google.com
6. Search available context using driver.getContextHandles()
7. Switch to webview context and enter "Hello World" in google.com and press Enter using Keys
8. Navigate back to the app using new KeyEvent(AndroidKey.BACK)
9. Stop webview context and start Native App context

 */

import auntor.project.pageObjects.CartPage;
import auntor.project.pageObjects.ProductListPage;
import auntor.project.testCases.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductAmountAndGestureTest extends BaseClass {
    ProductListPage productListPage;
    CartPage cartPage;

    @Test(priority = 1)
    public void productAmountTest() throws InterruptedException, IOException {
        productListPage = new ProductListPage(driver);
        cartPage = new CartPage(driver);
        productListPage.selectMultipleProductInCart();
        Double sumOfProduct = cartPage.sumOfProduct();
        Double totalAmount = cartPage.getTotalAmount();
        if (sumOfProduct.equals(totalAmount)) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else {
            logger.warn("Test Failed");
            captureScreen("productAmountTest");
            Assert.assertTrue(false);
        }

    }

    @Test(priority = 2)
    public void gesturesTest() throws InterruptedException {
        cartPage.gesturesOfCartModule();
        logger.info("Test Passed");
        Assert.assertTrue(true);
    }
}
