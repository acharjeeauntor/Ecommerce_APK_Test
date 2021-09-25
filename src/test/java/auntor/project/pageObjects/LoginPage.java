package auntor.project.pageObjects;

import auntor.project.testCases.BaseClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
    AndroidDriver ldriver;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    AndroidElement countryNameDropDownOption;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    AndroidElement customerName;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    AndroidElement genderSelection;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    AndroidElement shopBtn;

    public LoginPage(AndroidDriver driver) {
        ldriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void login(String country) throws InterruptedException {
        Thread.sleep(5000);
        countryNameDropDownOption.click();
        scrollToView(country);
        ldriver.findElementByXPath("//*[@text='"+country+"']").click();
        customerName.sendKeys("Auntor");
        ldriver.hideKeyboard();
        genderSelection.click();
        shopBtn.click();
    }

}
