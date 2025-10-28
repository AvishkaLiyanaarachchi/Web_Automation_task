package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this); //Intialize web elements
    }

    //Use page factory pattern to identify locators
    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginBtn;

    public static void timeout2000(){
        try{
            Thread.sleep(2000);
        }catch (Exception ex){}
    }

    public boolean enter_invalid_username_and_password(String inuname, String inpass){
        boolean status = true;
        try {
            username.sendKeys(inuname);//Send text to the input field
            LoginPage.timeout2000(); //wait two second to interact other web element
            password.sendKeys(inpass);
            LoginPage.timeout2000();
            loginBtn.click(); //Click button
            LoginPage.timeout2000();

            try {
                //assertion message to verify condition
                if (driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3/text()")).isDisplayed()) {
                    Assert.assertEquals(true, true, "Display error message with invalid credentials");
                } else {
                    Assert.assertEquals(true, false, "Not display error message with invalid credentials");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }

            status=true;
            return status;

        }catch (Exception ex){
            System.out.println(ex);

            status=false;
            return status;
        }


    }

    public boolean enter_valid_username_and_password(String uname, String pass){
        boolean status = true;
        try{
            username.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));//Clear the input field
            LoginPage.timeout2000();
            username.sendKeys(uname);
            LoginPage.timeout2000();
            password.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            LoginPage.timeout2000();
            password.sendKeys(pass);
            LoginPage.timeout2000();
            loginBtn.click();
            LoginPage.timeout2000();

            try{
                if(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).isDisplayed()){
                    Assert.assertTrue(true,"Successfully move to the items page");
                }else{
                    Assert.assertFalse(false, "Unsuccessfully move to the items page");
                }
            }catch (Exception ex){
                System.out.println(ex);
            }

            status=true;
            return status;
        }catch (Exception ex){
            System.out.println(ex);

            status=false;
            return status;
        }

    }





}
