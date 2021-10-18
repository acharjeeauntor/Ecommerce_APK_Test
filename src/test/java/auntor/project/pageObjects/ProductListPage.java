package auntor.project.pageObjects;

import auntor.project.testCases.BaseClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage extends BaseClass {
    AndroidDriver ldriver;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    AndroidElement pageHeader;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    AndroidElement cartBtn;

    public ProductListPage(AndroidDriver driver) {
        ldriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isPageTitleShow(){
        if(pageHeader.getText().equals("Products")){
            return true;
        }else{
            return false;
        }
    }


    public void addAProductInCart() throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()" + ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(" + "new UiSelector().text(\"PG 3\"));");
        int productCount = driver.findElementsById("com.androidsample.generalstore:id/productName").size();
        for (int i = 0; i < productCount; i++) {
            String productName = driver.findElementsById("com.androidsample.generalstore:id/productName").get(i).getText();
            if (productName.equals("PG 3")) {
                driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
                break;
            }
        }
        cartBtn.click();
        Thread.sleep(5000);
    }

    public void selectMultipleProductInCart() throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"PG 3\"))");
        driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
        driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
        cartBtn.click();
        Thread.sleep(4000);

    }


}
