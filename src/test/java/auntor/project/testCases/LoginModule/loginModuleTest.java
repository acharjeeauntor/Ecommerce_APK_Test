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
    @Test
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
