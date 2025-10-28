package TestFiles;

import Pages.LoginPage;
import Pages.ProductsPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Objects;

public class TestRunnerFile {

    WebDriver driver;

    LoginPage loginpage;
    ProductsPage product;

    int pass_count;
    int fail_count;
    int total_count;
    ArrayList<String> pass_method_list = new ArrayList<String>();
    ArrayList<String> fail_method_list = new ArrayList<String>();
    static ExtentTest test;
    static ExtentReports report;

    public static void timeout2000() {
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @BeforeClass
    public static void startTest()
    {
        //Create object of Extend report and provide path to generate and provide information to include report
        report = new ExtentReports("./Ecom_Test_Reports/Ecom-Site_report.html");
        report.addSystemInfo("Ecom demo site","Dashboard");
        test = report.startTest("Product Dashboard");
    }

    @BeforeClass
    public void beforeClass() {
        try{
            WebDriverManager.chromedriver().setup();//Setup the chrome driver
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito"); // Add the incognito argument
            driver = new ChromeDriver(options);
            driver.get("https://www.saucedemo.com/");//Give the URL
            driver.manage().window().maximize();

            TestRunnerFile.timeout2000();
            try{
                String actualURL = driver.getCurrentUrl();
                String expectedURL = "https://www.saucedemo.com/";
                SoftAssert soft = new SoftAssert();
                soft.assertEquals(actualURL, expectedURL, "Domain URL is correct");
                soft.assertAll();

            }catch (Exception ex){
                System.out.println(ex);
            }

        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Provide priority for each test method to execute
    @Test(priority = 1)
    public void test_login_method_with_invalid_credentials() {
        total_count ++;
        try{
            loginpage = new LoginPage(driver);
            boolean status = loginpage.enter_invalid_username_and_password("sdsds", "4232");
            if(status==true){
                pass_count++;
                Assert.assertEquals(true, pass_method_list.add("enter_invalid_username_and_password"),"Test Passed:- Unsuccessful login with invalid credentials");
            }else{
                fail_count ++;
                Assert.assertEquals(true, fail_method_list.add("enter_invalid_username_and_password"), "Test Failed:- Successful login with invalid credentials");
            }

            System.out.println("Pass method list: "+pass_method_list);
            System.out.println("Fail method list: "+fail_method_list);

            TestRunnerFile.timeout2000();
        }catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Test(priority = 2)
    public void test_login_method_with_valid_credentials() {
        total_count ++;
        try{
            loginpage = new LoginPage(driver);
            boolean status = loginpage.enter_valid_username_and_password("standard_user", "secret_sauce");
            if(status==true){
                pass_count++;
                Assert.assertEquals(true, pass_method_list.add("enter_valid_username_and_password"),"Test passed:- Successfully login with valid credentials");
            }else{
                fail_count++;
                Assert.assertEquals(true, fail_method_list.add("enter_valid_username_and_password"),"Test failed:- Unsuccessfully login with valid credentials");
            }

            System.out.println("Pass method list: "+pass_method_list);
            System.out.println("Fail method list: "+fail_method_list);

            TestRunnerFile.timeout2000();
        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Test(priority = 3)
    public void test_filter_products_option() {
        total_count ++;
        try{
            product = new ProductsPage(driver);
            boolean status = product.filter_products_option();
            if(status==true){
                pass_count++;
                Assert.assertEquals(true, pass_method_list.add("filter_products_option"),"Test Passed:- Filter products with A to Z and Z to A");
            }else{
                fail_count++;
                Assert.assertEquals(true, fail_method_list.add("filter_products_option"), "Test failed:- Not filter products with A to Z and Z to A");
            }

            System.out.println("Pass method list: "+pass_method_list);
            System.out.println("Fail method list: "+fail_method_list);

            TestRunnerFile.timeout2000();

        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Test(priority = 4)
    public void test_select_product_add_to_the_cart() {
        total_count ++;
        try{
            product = new ProductsPage(driver);
            boolean status = product.select_product_add_to_the_cart();
            if(status==true){
                pass_count++;
                Assert.assertEquals(true, pass_method_list.add("select_product_add_to_the_cart"), "Test Passed:- Display selected product under cart section");
            }else{
                fail_count++;
                Assert.assertEquals(true, fail_method_list.add("select_product_add_to_the_cart"),"Test Failed:- Display selected product under cart section");
            }

            System.out.println("Pass method list: "+pass_method_list);
            System.out.println("Fail method list: "+fail_method_list);

            TestRunnerFile.timeout2000();

        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Test(priority = 5)
    public void test_checkout_product_under_cart_section() {
        total_count ++;
        try{
            product = new ProductsPage(driver);
            boolean status = product.checkout_product_under_cart_section("Dilki","Liyanage","10245");
            if(status==true){
                pass_count++;
                Assert.assertEquals(true, pass_method_list.add("checkout_product_under_cart_section"),"Test Passed:- Successfully checkout product");
            }else{
                fail_count++;
                Assert.assertEquals(true, fail_method_list.add("checkout_product_under_cart_section"),"Test Failed:- Unsuccessfully checkout product");
            }

            System.out.println("Pass method list: "+pass_method_list);
            System.out.println("Fail method list: "+fail_method_list);

            TestRunnerFile.timeout2000();

        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Test(priority = 6)
    public void test_logout_option_from_system() {
        total_count ++;
        try{
            product = new ProductsPage(driver);
            boolean status = product.logout_option_from_system();
            if(status==true){
                pass_count++;
                Assert.assertEquals(true, pass_method_list.add("logout_option_from_system"),"Test passed:- Successfully logout from the system");
            }else{
                fail_count++;
                Assert.assertEquals(true, fail_method_list.add("logout_option_from_system"),"Test passed:- Unsuccessfully logout from the system");
            }

            System.out.println("Pass method list: "+pass_method_list);
            System.out.println("Fail method list: "+fail_method_list);

            TestRunnerFile.timeout2000();

        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Generate report including pass, fails scenariuos and provide screenshots for fails test methods
    @AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            //Take base64Screenshot screenshot for extent reports
            String base64Screenshot =
                    "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
            //test.log(LogStatus.FAIL , test.addBase64ScreenShot(base64Screenshot));
            test.log(LogStatus.FAIL , "Test Failed: " +result.getName()+"&nbsp&nbsp\n\n"+
                    "<p><b>"+result.getThrowable()+"<p><b>"+ " " + test.addBase64ScreenShot(base64Screenshot));


        } else if (result.getStatus()==ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "Test Passed:  "+result.getName());
        }else{
            test.log(LogStatus.SKIP, "Test Skipped: "+result.getName());
        }

    }

    //Clear test report
    @AfterTest
    public static void endTest()
    {
        report.endTest(test);
        report.flush();
    }
}
