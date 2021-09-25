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

}
