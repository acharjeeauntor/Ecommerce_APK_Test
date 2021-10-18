package auntor.project.pageObjects;

import auntor.project.testCases.BaseClass;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Set;

public class CartPage extends BaseClass {
    AndroidDriver ldriver;

    public CartPage(AndroidDriver driver) {
        ldriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    AndroidElement productName;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    AndroidElement totalAmount;
    @AndroidFindBy(className = "android.widget.CheckBox")
    AndroidElement checkBox;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    AndroidElement termConditionBtn;
    @AndroidFindBy(id = "android:id/button1")
    AndroidElement closeBtn;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    AndroidElement visitToWebsiteBtn;


    public String getSelectedProductName() {
        return productName.getText();
    }

    public double sumOfProduct() {
        int count = driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
        double sumOfProduct = 0;
        for (int i = 0; i < count; i++) {
            String amount = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText();
            double productAmount = getAmount(amount);
            sumOfProduct += productAmount;
        }
        return sumOfProduct;
    }


    public double getTotalAmount() {
        String total = totalAmount.getText();
        double totalAmount = getAmount(total);
        return totalAmount;
    }


    public static double getAmount(String value) {
        value = value.substring(1);
        double productAmount = Double.parseDouble(value);
        return productAmount;
    }


    public void gesturesOfCartModule() throws InterruptedException {
        TouchAction t = new TouchAction(driver);
        t.tap(TapOptions.tapOptions().withElement(ElementOption.element(checkBox))).perform();
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(termConditionBtn)).withDuration(Duration.ofSeconds(2))).release().perform();
        closeBtn.click();
        visitToWebsiteBtn.click();
        Thread.sleep(7000);
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName);
        }

        // Switch to webview context

        //driver.context("WEBVIEW_com.androidsample.generalstore");
//        driver.findElement(By.name("q")).sendKeys("Hello World");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        // Stop webview context and start Native App context
        //driver.context("NATIVE_APP");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(5000);

    }


}
