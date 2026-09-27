package SeleniumAssignment101;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestScenarios extends Baseclass{

    @Test(priority = 1)
    public void simpleformdemo() {
        //click simple form demo
        //<a href="https://www.testmuai.com/selenium-playground/simple-form-demo/" class="text-black text-size-14 hover:text-lambda-900 leading-relaxed">Simple Form Demo</a>

        driver.findElement(By.linkText("Simple Form Demo")).isDisplayed();
        driver.findElement(By.linkText("Simple Form Demo")).click();


        //Validate that the URL contains “simple-form-demo”
        Assert.assertTrue(driver.getCurrentUrl().contains("simple-form-demo"), "URL does not contain 'simple-form-demo'");

        // Create a variable for a string value, e.g., “Welcome to TestMu AI”.
        String Inputtext = "Welcome to TestMu AI";

        //Use this variable to enter values in the “Enter Message” text box.
        //<input type="text" class="border border-gray-550 w-full h-35 rounded px-10" placeholder="Please enter your Message" id="user-message">
        driver.findElement(By.id("user-message"));
        driver.findElement(By.id("user-message")).sendKeys(Inputtext);

        //Click “Get Checked Value”.
        //<button type="button" id="showInput" class="mt-20 mb-10 bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white p-10 rounded focus:outline-none w-180">Get Checked Value</button>
        driver.findElement(By.xpath("//button[@id='showInput']")).isDisplayed();
        driver.findElement(By.xpath("//button[@id='showInput']")).click();


        //Validate whether the same text message is displayed in the right-hand
        //panel under the “Your Message:” section.
        //<p id="message" class="mt-20">Testcheck</p>
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement outputext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        Assert.assertEquals(Inputtext, outputext.getText(), "Text does not match as Expected:");

    }
        @Test(priority = 2)
        public void sliderdemo()
        {
            //Open the https://www.testmuai.com/selenium-playground/ page and
            //click “Drag & Drop Sliders”.
            driver.findElement(By.linkText("Drag & Drop Sliders")).isDisplayed();
            driver.findElement(By.linkText("Drag & Drop Sliders")).click();

            //Select the slider “Default value 15” and drag the bar to make it 95 by
            //validating whether the range value shows 95.
//<div id="slider3" class="w-6/12 smtablet:w-full mt-20 pr-40 smtablet:pr-0"><h4 class="font-bold text-black mb-10"> Default value 15</h4><div class="sp__range sp__range-success"><input type="range" class="sp__range" min="1" max="100" value="15"><output id="rangeSuccess">15</output></div></div>
//<input type="range" class="sp__range" min="1" max="100" value="15">
       WebElement slider=driver.findElement(By.xpath("//div[@id='slider3']//input[@type='range']"));
       WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
       wait.until(ExpectedConditions.elementToBeClickable(slider));
       //<output id="rangeSuccess">95</output>
       WebElement output=driver.findElement(By.id("rangeSuccess"));
       Actions actions=new Actions(driver);
       actions.moveToElement(slider).clickAndHold().moveByOffset(215,0).release().perform();
       Assert.assertEquals(output.getText(),"95","Slider value did not update correctly.");

        }

    @Test(priority=3)
    public void fromDemo() {
//open https://www.testmuai.com/selenium-playground/
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        driver.findElement(By.linkText("Input Form Submit")).click();

        //2. Click “Submit” without filling in any information in the form.
        WebElement submitBttn = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        submitBttn.click();

        //3. Assert “Please fill out this field.” error message.
        WebElement companyField = driver.findElement(By.id("company"));
        String validationMessage =
                (String) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", companyField);
        Assert.assertEquals(validationMessage, "Please fill out this field.");

        //4. Fill in Name, Email, and other fields.
        driver.findElement(By.name("name")).sendKeys("John");
        driver.findElement(By.id("inputEmail4")).sendKeys("johndoe14@mail.com");
        driver.findElement(By.id("inputPassword4")).sendKeys("Password@12345");
        driver.findElement(By.name("company")).sendKeys("ABC Org");
        driver.findElement(By.xpath("//input[@placeholder='Website']")).sendKeys("www.google.com");

        //5. From the Country drop-down, select “United States” using the text property.
        WebElement Country = driver.findElement(By.name("country"));
        Select countries = new Select(Country);
        countries.selectByVisibleText("United States");

        //6. Fill in all elds and click “Submit”.
        driver.findElement(By.name("city")).sendKeys("us");
        driver.findElement(By.id("inputAddress1")).sendKeys("address 1");
        driver.findElement(By.id("inputAddress2")).sendKeys("address 2");
        driver.findElement(By.xpath("//input[@placeholder='State']")).sendKeys("united state");
        driver.findElement(By.name("zip")).sendKeys("441133");
        submitBttn.click();

        //7. Once submitted, validate the success message “Thanks for contacting us, we will get back to you shortly.” on the screen.
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='success-msg hidden']")));
        String expMsg = successMessage.getText();
        Assert.assertEquals("Thanks for contacting us, we will get back to you shortly.",expMsg,"Msg Does not match:");
    }

}

