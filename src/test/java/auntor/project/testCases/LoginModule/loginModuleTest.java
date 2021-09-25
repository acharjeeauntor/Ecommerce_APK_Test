package auntor.project.testCases.LoginModule;

import auntor.project.pageObjects.LoginPage;
import auntor.project.pageObjects.ProductListPage;
import auntor.project.testCases.BaseClass;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class loginModuleTest extends BaseClass {
    LoginPage loginPage;
    ProductListPage productListPage;

    /*
Test case:
1. Verify error toast message is showing or not when name field is empty
2. Verify app goes to shop page by entering all valid information (Country,name,gender)

 */

    @Test(priority = 1)
    public void checkEmptyFieldValidation() throws IOException {
        loginPage=new LoginPage(driver);
       String message = loginPage.getErrorMessage("Bangladesh");
       if(message.equals("Please enter your name")){
           logger.info("Test Passed");
           Assert.assertTrue(true);
       }else {
           logger.warn("Test Failed");
           captureScreen("checkEmptyFieldValidation");
           Assert.assertTrue(false);
       }
    }

    @Test(priority = 2)
    public void loginToApp() throws InterruptedException, IOException {
        loginPage=new LoginPage(driver);
        productListPage= new ProductListPage(driver);
        loginPage.login("Bangladesh");
        Thread.sleep(2000);
        if(productListPage.isPageTitleShow()){
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }else{
            logger.warn("Test Failed");
            captureScreen("loginToApp");
            Assert.assertTrue(false);
        }
    }





}
