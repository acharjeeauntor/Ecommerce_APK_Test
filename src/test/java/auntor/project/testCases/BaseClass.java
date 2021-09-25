package auntor.project.testCases;

import auntor.project.utilities.Config;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static AndroidDriver<AndroidElement> driver;
    public static Logger logger;
    public static AppiumDriverLocalService service;

    @BeforeClass
    public void automationEnvStart() throws IOException, InterruptedException {
        logger = Logger.getLogger("GeneralStore");
        PropertyConfigurator.configure("log4j.properties");
        startServer();
        capabilities();
    }

    @AfterClass
    public void automationEnvStop() {
        driver.quit();
        service.stop();
    }


    public AppiumDriverLocalService startServer() {
        //
        boolean flag = checkIfServerIsRunning(4723);
        if (!flag) {

            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;

    }


    public static AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {

        Config config = new Config();
        String testApkName = config.getApkName();
        String deviceType = config.getDeviceType();

        DesiredCapabilities dc = new DesiredCapabilities();

        if (deviceType.contains("emulator")) {
            startEmulator();
        }
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceType);

        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);

        dc.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/TestApk/" + testApkName + ".apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public static boolean checkIfServerIsRunning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);

            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static void startEmulator() throws IOException, InterruptedException {

        Runtime.getRuntime().exec(System.getProperty("user.dir") + "Configuration\\startEmulator.bat");
        Thread.sleep(6000);
    }


    public void scrollToView(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))");
    }


    public static void captureScreen(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + testCaseName + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot Taken");
    }


}
