package Stepsdefinitions;

import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {

    LoginPage loginpage;
    WebDriver driver;

    public static void timeout2000(){
        try{
            Thread.sleep(2000);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Given("user is on the login page")
    public void user_is_on_the_login_page(){
        WebDriverManager.chromedriver().setup();//Setup the chrome driver
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");//Give the URL
        driver.manage().window().maximize();
        LoginSteps.timeout2000();
    }

    @When("user enters invalid username and invalid password")
    public void user_enters_invalid_username_and_invalid_password(){
        driver.findElement(By.id("user-name")).sendKeys("fdf5345");
        LoginSteps.timeout2000();
        driver.findElement(By.id("password")).sendKeys("45454");
        LoginSteps.timeout2000();


    }

    @And("user clicks on login button")
    public void user_clicks_on_login_button(){
        driver.findElement(By.id("login-button")).click();
        LoginSteps.timeout2000();
    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password(){
        driver.findElement(By.id("user-name")).sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        LoginSteps.timeout2000();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        LoginSteps.timeout2000();
        driver.findElement(By.id("password")).sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        LoginSteps.timeout2000();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        LoginSteps.timeout2000();

    }
    @And("user click on login button")
    public void user_click_on_login_button(){
        driver.findElement(By.id("login-button")).click();
        LoginSteps.timeout2000();
    }

    @Then("user should be redirect to the dashboard")
    public void user_should_be_redirect_to_the_dashboard(){
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).isDisplayed();
    }

    @Then("an error message is displayed")
    public void an_error_message_is_displayed(){
        driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).isDisplayed();
    }
}
