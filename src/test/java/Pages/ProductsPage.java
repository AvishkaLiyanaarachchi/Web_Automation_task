package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ProductsPage {

    //In this class use Page object model design pattern to perform all actions of products web page for application

    By filterDropdown = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");
    By addtoCartOption = By.id("add-to-cart-sauce-labs-backpack");
    By cartIcon = By.xpath("//a[@class ='shopping_cart_link']");
    By checkoutBtn = By.id("checkout");
    By continueBtn = By.id("continue");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalcode = By.id("postal-code");
    By finishBtn = By.id("finish");
    By backtoHome = By.id("back-to-products");
    By menuIcon = By.id("react-burger-menu-btn");
    By logoutBtn = By.xpath("//*[@id=\"logout_sidebar_link\"]");

    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void timeout2000(){
        try{
            Thread.sleep(2000);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public boolean filter_products_option(){
        boolean status = true;
        try{
            driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div")).click();
            WebElement filterOption = driver.findElement(filterDropdown);
            Select select = new Select(filterOption);//Create select object to visible dynamic dropdown values
            select.selectByValue("za");
            ProductsPage.timeout2000();
            select.selectByValue("az");
            ProductsPage.timeout2000();

            try{
                WebElement selectOption = select.getFirstSelectedOption();
                String val = selectOption.getText();
                System.out.println(val);

                Assert.assertTrue(true, "Name filter with Z to A");
            }catch (Exception ex){
                System.out.println(ex);
            }

            status = true;
            return status;
        }catch (Exception ex){
            status = false;
            return status;
        }
    }

    public boolean select_product_add_to_the_cart(){
        boolean status = true;

        try {
            driver.findElement(addtoCartOption).click();
            ProductsPage.timeout2000();
            driver.findElement(cartIcon).click();
            ProductsPage.timeout2000();

            try{

                if(driver.findElement(By.xpath("//div[@class ='cart_quantity']")).isDisplayed()){
                    Assert.assertTrue(true, "Display added item under cart section");
                }else{
                    Assert.assertFalse(true, "Not display added item under cart section");
                }
            }catch (Exception ex){
                System.out.println(ex);
            }

            status = true;
            return status;
        }catch (Exception ex){
            status = false;
            return status;
        }
    }

    public boolean checkout_product_under_cart_section(String fname, String lname, String postalcodeVal){
        boolean status = true;

        try {
            driver.findElement(checkoutBtn).click();
            ProductsPage.timeout2000();

            //Create Soft assertion object and call assetTrue, assertFalse, assertEquals method to verify conditions
            //If condition is not met, execute other conditions
            SoftAssert soft = new SoftAssert();
            if(driver.findElement(firstName).isDisplayed()){
                soft.assertTrue(true, "Display enter fields for your information");
            }else{
                soft.assertFalse(true,"Not display enter fields for your information");
            }

            driver.findElement(continueBtn).click();
            ProductsPage.timeout2000();

            if(driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]")).isDisplayed()){
                soft.assertTrue(true, "Display error message for required fields");
            }else{
                soft.assertFalse(true, "Not display error message for required fields");
            }

            driver.findElement(firstName).sendKeys(fname);
            ProductsPage.timeout2000();
            driver.findElement(lastName).sendKeys(lname);
            ProductsPage.timeout2000();
            driver.findElement(postalcode).sendKeys(postalcodeVal);
            ProductsPage.timeout2000();
            driver.findElement(continueBtn).click();
            ProductsPage.timeout2000();

            if(driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[2]")).isDisplayed() && driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[4]")).isDisplayed()){
                soft.assertTrue(true,"Display checkout overview details");
            }else{
                soft.assertFalse(true,"Not display checkout overview details");
            }
            soft.assertAll(); //Generate error messages for fail test scripts in console

            status = true;
            return status;
        }catch (Exception ex){
            status = false;
            return status;
        }
    }

    public boolean logout_option_from_system(){
        boolean status = true;
        try{
            driver.findElement(finishBtn).click();
            ProductsPage.timeout2000();
            driver.findElement(backtoHome).click();
            ProductsPage.timeout2000();
            driver.findElement(menuIcon).click();
            ProductsPage.timeout2000();
            driver.findElement(logoutBtn).click();
            ProductsPage.timeout2000();

            try{
                if(driver.findElement(By.id("user-name")).isDisplayed()){
                    Assert.assertTrue(true, "Successfully logout from the system and redirect to the login section");
                }else{
                    Assert.assertFalse(true, "Not successfully logout from the system");
                }
            }catch (Exception ex){
                System.out.println(ex);
            }
            status = true;
            return status;
        }catch (Exception ex){
            status = false;
            return status;
        }
    }


}
