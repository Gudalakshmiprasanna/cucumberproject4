package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CheckoutProcess {
    WebDriver driver;

    @Given("the user is logged into the SauceDemo website")
    public void the_user_is_logged_into_the_sauce_demo_website() throws InterruptedException {
        // Initialize Chrome driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the website
        driver.get("https://www.saucedemo.com");

        // Login to the application
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
    }

    @Given("the user has added a product to the cart")
    public void the_user_has_added_a_product_to_the_cart() throws InterruptedException {
        // Add product to the cart
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(2000);
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() throws InterruptedException {
        // Go to the cart and proceed to checkout
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);
    }

    @When("the user enters the following details:")
    public void the_user_enters_the_following_details(DataTable dataTable) throws InterruptedException {
        // Retrieve data from the feature file table
        List<Map<String, String>> userData = dataTable.asMaps(String.class, String.class);

        // Enter user details in checkout form
        driver.findElement(By.id("first-name")).sendKeys(userData.get(0).get("First Name"));
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys(userData.get(0).get("Last Name"));
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys(userData.get(0).get("Postal Code"));
        Thread.sleep(2000);
    }

    @When("the user continues to the next step")
    public void the_user_continues_to_the_next_step() throws InterruptedException {
        // Click on continue button
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @When("the user confirms the order")
    public void the_user_confirms_the_order() throws InterruptedException {
        // Finish the order
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);
    }

    @Then("the order confirmation message should be displayed")
    public void the_order_confirmation_message_should_be_displayed() throws InterruptedException {
        // Verify the confirmation message
        String confirmationMessage = driver.findElement(By.className("complete-header")).getText();
        if (confirmationMessage.equals("Thank you for your order!")) {
            System.out.println("Order confirmation message displayed: " + confirmationMessage);
        } else {
            System.out.println("Order confirmation message NOT displayed.");
        }
        Thread.sleep(2000);
        // Close the browser
        driver.quit();
    }
}
